package com.bookstore.modules.user.api;

import com.bookstore.common.entity.User;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.UserService;
import com.bookstore.modules.order.dto.OrderDto;
import com.bookstore.modules.order.service.OrderModuleService;
import com.bookstore.modules.user.dto.UserDto;
import com.bookstore.modules.user.request.*;
import com.bookstore.modules.user.service.UserModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserModuleService userModuleService;
    private final OrderModuleService orderModuleService;

    /* <---------------- Uri.USERS ------------------> */

    @GetMapping(value = {Uri.USERS})
    public ResponseEntity<?> RetrieveUserById(@RequestParam Integer userId){
        UserDto userDto = userModuleService.convertToUserDto(userService.retrieveUserById(userId));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // non - test
    @PutMapping(value = {Uri.USERS})
    public ResponseEntity<Object> UpdateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        User user = userService.retrieveUserById(userUpdateRequest.getId());
        user.setUsername(userUpdateRequest.getUsername());
        user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        user.setEmail(userUpdateRequest.getEmail());
        userService.updateUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    // non - test
    @DeleteMapping(value = {Uri.USERS})
    public ResponseEntity<Object> DeleteUser(@RequestParam Integer userId){
        User user = userService.retrieveUserById(userId);
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* <------------------- Uri.USERS_ORDERS ------------------> */

    // non - test
    @GetMapping(value = {Uri.USERS_ORDERS})
    public ResponseEntity<?> RetrieveALlOrdersForUser(@RequestParam Integer userId){
        List<OrderDto> orderDtoList = orderModuleService.OrderToOrderDto(userService.retrieveOrdersByUserId(userId));
        return ResponseEntity.ok(orderDtoList);
    }

    /* <---------------------- Uri.USERS_REVIEWS ------------------------> */

    // implement
    @PostMapping(value = {Uri.USERS_REVIEWS})
    public ResponseEntity<?> CreateReviewForUser(@RequestBody(required = false) MultipartFile image, @Valid @RequestBody ReviewCreateRequest reviewCreateRequest){
        return  null;
    }
    // implement
    @DeleteMapping(value = {Uri.USERS_REVIEWS})
    public ResponseEntity<?> DeleteReviewForUser(@RequestParam Integer reviewId){
        return  null;
    }

    /* <------------------- Uri.USERS_RATES --------------------> */
    // implement
    @PostMapping(value = {Uri.USERS_RATES})
    public ResponseEntity<?> CreateRateForUser(@Valid @RequestBody RateCreateRequest rateCreateRequest){
        return  null;
    }
    // implement
    @DeleteMapping(value = {Uri.USERS_RATES})
    public ResponseEntity<?> DeleteRateForUser(@RequestParam Integer rateId){return  null;}

}
