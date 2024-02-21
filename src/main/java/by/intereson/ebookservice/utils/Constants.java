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
    public static final Integer MIN_QUANTITY_BOOK = 1;
    public static final String ROLE_NEW_USER = "USER";
    public static final String DATE_TIME_PATTERN = "dd.MM.uuuu HH-mm";
    public static final String RESPONSE = "response";
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
    public static final String OPEN_LIBRARY_FAIL_KEY = "library.fail.message";
    public static final String OPEN_LIBRARY_FAIL_MESSAGE = "Error receiving data from API Open Library!";
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
    public static final String SEARCH_REQUEST_URL = "?q=";
    public static final String SEARCH_REQUEST_PARAMETERS_URL = "&fields=first_publish_year,availability&limit=1";
    /**
     * TABLE NAMES
     */
    public static final String BOOKS = "BOOKS";
    public static final String ORDERS = "ORDERS";
    public static final String ORDER_DETAILS = "ORDER_DETAILS";
    public static final String ROLES = "ROLES";
    public static final String SHOPPING_CARTS = "SHOPPING_CARTS";
    public static final String USERS = "USERS";
    public static final String USER_ROLE = "USER_ROLE";
    public static final String USER_LIKE_BOOK = "USER_LIKE_BOOK";
    public static final String BOOK_SEQ_NAME = "BOOK_SEQ";
    public static final String ORDER_SEQ_NAME = "ORDER_SEQ";
    public static final String ORDER_DETAIL_SEQ_NAME = "ORDER_DETAIL_SEQ";
    public static final String ROLE_SEQ_NAME = "ROLE_SEQ";
    public static final String SHOPPING_CART_SEQ_NAME = "SHOPPING_CART_SEQ";
    public static final String USER_SEQ_NAME = "USER_SEQ";
    /**
     * COLUMN NAMES
     */
    public static final String BOOK_NAME = "BOOK_NAME";
    public static final String AUTHOR = "AUTHOR";
    public static final String PUBLISHING_YEAR = "PUBLISHING_YEAR";
    public static final String FIRST_PUBLISH_YEAR = "FIRST_PUBLISH_YEAR";
    public static final String PUBLISHING_HOUSE = "PUBLISHING_HOUSE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String PRICE = "PRICE";
    public static final String CREATE_TIME = "CREATE_TIME";
    public static final String QUANTITY = "QUANTITY";
    public static final String RESERVE_QUANTITY = "RESERVE_QUANTITY";
    public static final String CREATE_DATE_TIME = "CREATE_DATE_TIME";
    public static final String UPDATE_DATE_TIME = "UPDATE_DATE_TIME";
    public static final String SUM_PRICE = "SUM_PRICE";
    public static final String USER_ID = "USER_ID";
    public static final String BOOK_PRICE = "PRICE_BOOK";
    public static final String ORDER_ID = "ORDER_ID";
    public static final String BOOK_ID = "BOOK_ID";
    public static final String SHOPPING_CART_ID = "SHOPPING_CART_ID";
    public static final String ROLE_NAME = "NAME";
    public static final String USER_NAME = "NAME";
    public static final String FAMILY = "FAMILY";
    public static final String EMAIL = "EMAIL";
    public static final String LOGIN = "LOGIN";
    public static final String PASSWORD = "PASSWORD";
    public static final String ROLE_ID = "ROLE_ID";
}


