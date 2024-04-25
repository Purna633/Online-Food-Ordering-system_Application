package com.purna.controller;

import com.purna.model.Food;
import com.purna.model.Restaurant;
import com.purna.model.User;
import com.purna.request.CreateFoodRequest;
import com.purna.response.MessageResponse;
import com.purna.service.FoodService;
import com.purna.service.RestaurantService;
import com.purna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user= userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.getRestaurantByUserId(user.getId());
        Food food=foodService.createFood(req,req.getCategory(),restaurant);

        return  new ResponseEntity<>(food, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user=userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse res=new MessageResponse();
        res.setMessage("food deleted successfully");


        return  new ResponseEntity<>(res, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user=userService.findUserByJwtToken(jwt);
        Food food=foodService.updateAvailabilityStatus(id);


        return  new ResponseEntity<>(food, HttpStatus.CREATED);

    }
}
