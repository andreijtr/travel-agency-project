package com.sda.travel_agency.util;

public interface Constants {
    public static final String USER_LOGIN_FAILED = "User or password are incorrect! Please try again!";
    public static final String USER_LOGIN_SUCCESSFULLY = "Login successfully!";
    public static final String USER_NOT_FOUND = "User not found!";

    public static final String HOTEL_NO_ROOMS = "Sorry! This hotel is full for this period! Try another period of time!";

    public static final String FLIGHT_NO_SEATS = "Sorry! This flight is full for this period! Try another period of time!";

    public static final double AMOUNT_FOR_DISCOUNT_TEN_PERCENT = 1000;
    public static final double AMOUNT_FOR_DISCOUNT_TWENTY_PERCENT = 2000;

    String BUY_SUCCESSFUL = "Congratulation! You bought a trip!";
    String TRIP_NUMBER = "Your trip number is: ";

    String INSUFFICIENT_PLACES_HOTEL = "Sorry! Insufficient places in our hotel for this period!";

    String HOTEL_NAME = "Hotel name";
    String HOTEL_STANDARD = "Standard";
    String HOTEL_DESCRIPTION = "Descriere";
    String CITY = "City";
    String COUNTRY = "Country";
    String CONTINENT = "Continent";

    String EXPORT_FAIL_NO_FILE = "Export fail, file not found!";
    String EXPORT_FAIL_DURING_WRITE = "Export fail, error during writing in file!";
    String EXPORT_SUCCESSFULLY = "Export successfully!";
}
