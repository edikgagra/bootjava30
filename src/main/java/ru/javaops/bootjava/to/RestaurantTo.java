package ru.javaops.bootjava.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {
    List<DishTo> menu;

    public RestaurantTo(Integer id, String name, List<DishTo> menu) {
        super(id, name);
        this.menu = menu;
    }
}
