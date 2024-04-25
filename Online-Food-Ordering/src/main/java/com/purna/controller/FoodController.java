package com.purna.controller;

import com.purna.model.Food;
import com.purna.model.Restaurant;
import com.purna.model.User;
import com.purna.request.CreateFoodRequest;
import com.purna.service.FoodService;
import com.purna.service.RestaurantService;
import com.purna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                                 @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user= userService.findUserByJwtToken(jwt);

        List<Food> foods=foodService.searchFood(name);

        return  new ResponseEntity<>(foods, HttpStatus.CREATED);

    }


    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam (required = false) boolean vegetarian,
            @RequestParam  (required = false) boolean seasonal,
            @RequestParam (required = false) boolean nonveg,
            @RequestParam (required = false) String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user= userService.findUserByJwtToken(jwt);

        List<Food> food=foodService.getRestaurantsFood(restaurantId,vegetarian,nonveg,seasonal,food_category);

        return  new ResponseEntity<>(food, HttpStatus.OK);

    }
}
