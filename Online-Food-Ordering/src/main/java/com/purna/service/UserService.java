package com.purna.service;

import com.purna.model.Order;
import com.purna.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;



}
