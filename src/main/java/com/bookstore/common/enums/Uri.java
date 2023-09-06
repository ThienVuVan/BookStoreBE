package com.bookstore.common.enums;

/**
* @author: ThienVuVan
* @since: 8/8/2023 - 10:16 AM
* @description:  Class Contain All EndPoint.
* @update:
*
**/
public class Uri {
    /* User */
    public static final String USERS = "/api/v1/users";
    public static final String USERS_LOGIN = "/api/v1/users/login";
    public static final String USERS_SIGNUP = "/api/v1/users/signup";
    public static final String USERS_ORDERS = "/api/v1/users/orders";
    public static final String USERS_REVIEWS = "/api/v1/users/reviews";
    public static final String USERS_RATES = "/api/v1/users/rates";

    /* Book */
    public static final String BOOKS = "/api/v1/books";
    public static final String BOOKS_PAGE = "/api/v1/books/pages";
    public static final String BOOKS_FILTER = "/api/v1/books/filter";
    public static final String BOOKS_SHOP = "/api/v1/books/shop";
    public static final String BOOKS_DETAILS = "/api/v1/books/details";
    public static final String BOOKS_REVIEWS = "/api/v1/books/reviews/page";
    public static final String BOOKS_RATES = "/api/v1/books/rates/count";

    /* Shop */
    public static final String SHOPS = "/api/v1/shops";
    public static final String SHOPS_BOOK = "/api/v1/shops/books";
    public static final String SHOPS_DETAILS = "/api/v1/shops/details";
    public static final String SHOPS_ORDERS = "/api/v1/shops/orders";

    /* Order */
    public static final String ORDERS = "/api/v1/orders";
    public static final String ORDERS_ORDER_ITEMS = "/api/v1/orders/items";

    /* Category */
    public static final String CATEGORIES = "/api/v1/categories";
    public static final String CHILD_CATEGORIES = "/api/v1/categories/children";
    public static final String PARENT_CATEGORIES = "/api/v1/categories/parents";

    /* Author */
    public static final String AUTHORS = "/api/v1/authors";

}
