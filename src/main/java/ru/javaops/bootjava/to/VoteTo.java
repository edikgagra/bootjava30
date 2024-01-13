package ru.javaops.bootjava.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Date;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {
    @NotNull
    Integer restaurantId;
    @NotNull
    Integer userId;
    @NotNull
    Date voteDate;

    public VoteTo(Integer id, Integer restaurantId, Integer userId, Date voteDate) {
        super(id);
        this.restaurantId = restaurantId;
        this.userId = userId;
        this. voteDate = voteDate;
    }
}
