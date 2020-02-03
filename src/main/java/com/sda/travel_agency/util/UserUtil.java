package com.sda.travel_agency.util;

import com.sda.travel_agency.entities.User;

public class UserUtil {

    public static double applyDiscount(User user, double totalPrice) {
        if (user.getTotalAmount() > Consts.AMOUNT_FOR_DISCOUNT_TWENTY_PERCENT) {
            totalPrice -= (0.2 * totalPrice);
        } else if (user.getTotalAmount() > Consts.AMOUNT_FOR_DISCOUNT_TEN_PERCENT) {
            totalPrice -= (0.1 * totalPrice);
        }
        return totalPrice;
    }
}
