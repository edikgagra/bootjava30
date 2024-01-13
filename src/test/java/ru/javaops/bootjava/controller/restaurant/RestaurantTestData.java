package ru.javaops.bootjava.controller.restaurant;

import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.to.RestaurantTo;
import ru.javaops.bootjava.controller.MatcherFactory;

import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "votes", "dishes", "voteCount");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class);

    public static final int FIRST_RESTAURANT_ID = 1;
    public static final int SECOND_RESTAURANT_ID = 2;
    public static final int THIRD_RESTAURANT_ID = 3;

    public static final int NOT_FOUND = 100;

    public static final Restaurant firstRestaurant = new Restaurant(FIRST_RESTAURANT_ID, "First Restaurant", 1);
    public static final Restaurant secondRestaurant = new Restaurant(SECOND_RESTAURANT_ID, "Second Restaurant", 1);
    public static final Restaurant thirdRestaurant = new Restaurant(THIRD_RESTAURANT_ID, "Third Restaurant", 0);

    public static final List<Restaurant> restaurants = List.of(firstRestaurant, secondRestaurant, thirdRestaurant);

    public static Restaurant getUpdated() {
        return new Restaurant(FIRST_RESTAURANT_ID, "Updated Restaurant", 1);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant", 0);
    }
}
