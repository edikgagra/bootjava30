package ru.javaops.bootjava.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {

    @NotNull
    Integer price;

    public DishTo(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }
}
