package by.intereson.ebookservice.utils;

import java.math.BigDecimal;

import static java.sql.Types.NULL;

public class Constants {
    /**
     * PARAMETERS
     */
    public static final String LOG_REQUEST_PATTERN = "{}->{}:{}, request {}";
    public static final String LOG_RESPONSE_PATTERN = "{}->{}:{}, response {}";
    public static final BigDecimal START_SUM_PRICE = new BigDecimal(NULL);
    public static final Integer START_INTEGER_NULL_INDEX = 0;
    public static final String ROLE_NEW_USER = "USER";
    public static final String DATE_TIME_PATTERN = "dd.MM.uuuu HH-mm";
    /**
     * EXCEPTIONS
     */
    public static final String RESOURCE_NOT_FOUND_KEY ="resource.missing.message";
    public static final String DEFAULT_RESOURCE_NOT_FOUND_MESSAGE="An error has occurred in your database query! The resource was not found!";
    public static final String HANDLER_EXCEPTION_PATTERN="Exception:{}";
    public static final String DEFAULT_SHOPPING_CART_IS_EMPTY_MESSAGE="Shopping cart is empty! Fill the shopping cart and then place an order";
    public static final String SHOPPING_CART_IS_EMPTY_KEY ="cart.empty.message";
    public static final String QUANTITY_FAIL_KEY = "quantity.fail.message";
    public static final String DEFAULT_QUANTITY_FAIL_MESSAGE="There is no such amount!";

}


