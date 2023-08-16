package com.bookstore.common.service;

//import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.User;

import java.util.List;

public interface UserService {
    public User retrieveUserById(Integer id);
    public User retrieveByUserName(String username);
    public User saveUser(User user);
    public User updateUser(User user);
    public void deleteUser(User user);
    public Boolean isExistUserName(String username);
    public Boolean isExistEmail(String email);
    public Boolean isExistPhoneNumber(String phoneNumber);
    public List<Order> retrieveOrdersByUserId(Integer id);

}
