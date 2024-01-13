package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity {
    @Formula("(SELECT COUNT(*) FROM VOTES v WHERE v.restaurant_id = id)")
    private Integer voteCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    @Schema(hidden = true)
    @Nullable
    @JsonIgnore
    private Set<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    @Nullable
    @JsonIgnore
    private Set<Dish> dishes;
    public Restaurant(Integer id, String name, Integer voteCount) {
        super(id, name);
        this.voteCount = voteCount;
    }
}
