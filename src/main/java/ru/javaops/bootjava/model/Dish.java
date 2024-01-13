package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends NamedEntity implements Serializable {
    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "dish_date", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dishDate = new Date();

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @Nullable
    private Restaurant restaurant;

    public Dish(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

    public Dish(Integer id, String name, Integer price, Date dishDate) {
        super(id, name);
        this.price = price;
        this.dishDate = dishDate;
    }

    public Dish(Integer id, String name, Integer price, Date dishDate, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.dishDate = dishDate;
        this.restaurant = restaurant;
    }
}
