package com.bookstore.modules.user.api;

import com.bookstore.common.entity.*;
import com.bookstore.common.entity.compositekey.UserBookKey;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.BookService;
import com.bookstore.common.service.RateService;
import com.bookstore.common.service.ReviewService;
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

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BookService bookService;
    private final ReviewService reviewService;
    private final RateService rateService;
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

    @PostMapping(value = {Uri.USERS_REVIEWS})
    public ResponseEntity<?> CreateReviewForUser(@RequestBody(required = false) MultipartFile image, @Valid @RequestBody ReviewCreateRequest reviewCreateRequest){
        // get user
        User user = userService.retrieveUserById(reviewCreateRequest.getUserId());
        // get book
        Book book = bookService.retrieveById(reviewCreateRequest.getBookId());
        String imagePath = null;
        // resolve image;
        if(image != null){
            String fileName = "image_" + System.currentTimeMillis() + image.getOriginalFilename();
            imagePath = "D:/Projects/BookStoreImages/" + fileName;
            try {
                image.transferTo(new File(imagePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // just using one - directional mapping
        Review review = new Review(user, book, reviewCreateRequest.getComment(), imagePath);
        reviewService.saveReview(review);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = {Uri.USERS_REVIEWS})
    public ResponseEntity<?> DeleteReviewForUser(@RequestParam Integer reviewId){
        Review review = reviewService.retrieveReviewById(reviewId);
        reviewService.deleteReview(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* <------------------- Uri.USERS_RATES --------------------> */
    @PostMapping(value = {Uri.USERS_RATES})
    public ResponseEntity<?> CreateRateForUser(@Valid @RequestBody RateCreateRequest rateCreateRequest){
        User user = userService.retrieveUserById(rateCreateRequest.getUserId());
        Book book = bookService.retrieveById(rateCreateRequest.getBookId());
        UserBookKey userBookKey = new UserBookKey(user.getId(), book.getId());
        Rate rate = new Rate(userBookKey, user, book, rateCreateRequest.getRating());
        rateService.saveRate(rate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping(value = {Uri.USERS_RATES})
    public ResponseEntity<?> DeleteRateForUser(@RequestParam UserBookKey rateId){
        Rate rate = rateService.retrieveRateById(rateId);
        rateService.deleteRate(rate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
