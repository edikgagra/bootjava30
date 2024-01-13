package ru.javaops.bootjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.to.RestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {
    public static RestaurantTo asTo(Restaurant restaurant, List<Dish> dishes) {
        if (restaurant == null) {
            return null;
        }
        return new RestaurantTo(
                restaurant.getId(),
                restaurant.getName(),
                dishes.stream().map(DishUtil::asTo).collect(Collectors.toList())
        );
    }
}
