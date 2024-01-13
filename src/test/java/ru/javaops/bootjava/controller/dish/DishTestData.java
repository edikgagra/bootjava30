package ru.javaops.bootjava.controller.dish;

import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.controller.MatcherFactory;

import java.util.Date;
import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "dishDate", "restaurant");

    public static final int FIRST_DISH_ID = 1;
    public static final int SECOND_DISH_ID = 2;
    public static final int THIRD_DISH_ID = 3;
    public static final int FOURTH_DISH_ID = 4;
    public static final int FIRTH_DISH_ID = 5;
    public static final int SIXTH_DISH_ID = 6;
    public static final int SEVENTH_DISH_ID = 7;
    public static final int EIGHTH_DISH_ID = 8;
    public static final int NINTH_DISH_ID = 9;

    public static final int NOT_FOUND = 100;

    public static final Dish firstDish = new Dish(FIRST_DISH_ID, "First Dish", 100);
    public static final Dish secondDish = new Dish(SECOND_DISH_ID, "Second Dish", 150);
    public static final Dish thirdDish = new Dish(THIRD_DISH_ID, "Third Dish", 200);
    public static final Dish fourthDish = new Dish(FOURTH_DISH_ID, "Fourth Dish", 100);
    public static final Dish fifthDish = new Dish(FIRTH_DISH_ID, "Firth Dish", 150);
    public static final Dish sixthDish = new Dish(SIXTH_DISH_ID, "Sixth Dish", 200);
    public static final Dish seventhDish = new Dish(SEVENTH_DISH_ID, "Seventh Dish", 100);
    public static final Dish eighthDish = new Dish(EIGHTH_DISH_ID, "Eighth Dish", 150);
    public static final Dish ninthDish = new Dish(NINTH_DISH_ID, "Ninth Dish", 200);

    public static final List<Dish> dishes = List.of(
            firstDish,
            secondDish,
            thirdDish,
            fourthDish,
            fifthDish,
            sixthDish,
            seventhDish,
            eighthDish,
            ninthDish
    );

    public static Dish getUpdated() {
        return new Dish(FIRST_DISH_ID, "Updated Dish", 300, new Date());
    }

    public static Dish getNew() {
        return new Dish(null, "New Dish", 500, new Date());
    }
}
