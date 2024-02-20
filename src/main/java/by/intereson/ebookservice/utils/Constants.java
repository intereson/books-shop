package by.intereson.ebookservice.utils;

import java.math.BigDecimal;

public class Constants {
    /**
     * PARAMETERS
     */
    public static final String LOG_REQUEST_PATTERN = "{}->{}:{}, request {}";
    public static final String LOG_RESPONSE_PATTERN = "{}->{}:{}, response {}";
    public static final BigDecimal START_SUM_PRICE = new BigDecimal("0.00");
    public static final Integer START_INTEGER_NULL_INDEX = 0;
    public static final String ROLE_NEW_USER = "USER";
    public static final String DATE_TIME_PATTERN = "dd.MM.uuuu HH-mm";
    /**
     * EXCEPTIONS
     */
    public static final String RESOURCE_NOT_FOUND_KEY = "resource.missing.message";
    public static final String DEFAULT_RESOURCE_NOT_FOUND_MESSAGE = "An error has occurred in your database query! The resource was not found!";
    public static final String HANDLER_EXCEPTION_PATTERN = "Exception:{}";
    public static final String DEFAULT_SHOPPING_CART_IS_EMPTY_MESSAGE = "Shopping cart is empty! Fill the shopping cart and then place an order";
    public static final String SHOPPING_CART_IS_EMPTY_KEY = "cart.empty.message";
    public static final String QUANTITY_FAIL_KEY = "quantity.fail.message";
    public static final String DEFAULT_QUANTITY_FAIL_MESSAGE = "There is no such amount!";
    /**
     * URL
     */
    public static final String API_URL = "api/v1";

    public static final String BOOKS_ID_URL = "books/{id}";
    public static final String BOOKS_URL = "books";
    public static final String BOOKS_GENRE_URL = "books/genre";

    public static final String ORDERS_ID_URL = "orders/{id}";
    public static final String ORDERS_URL = "orders";
    public static final String ORDERS_STATUS_URL = "orders/status";
    public static final String ORDERS_SELECTED_URL = "orders/selected";

    public static final String ORDER_DETAILS_ID_URL = "order-details/{id}";
    public static final String ORDER_DETAILS_URL = "order-details";

    public static final String ROLES_ID_URL = "roles/{id}";
    public static final String ROLES_URL = "roles";

    public static final String SHOPPING_CARTS_ID_URL = "shopping-carts/{id}";

    public static final String USERS_ID_URL = "users/{id}";
    public static final String USERS_URL = "users";
    public static final String USER_ID_ORDERS_URL = "users/{id}/orders";
}


