package ru.javaops.bootjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.to.DishTo;

@UtilityClass
public class DishUtil {
    public static DishTo asTo(Dish dish) {
        return new DishTo(
                dish.getId(),
                dish.getName(),
                dish.getPrice()
        );
    }
}
