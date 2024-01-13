package ru.javaops.bootjava.controller.restaurant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.to.RestaurantTo;
import ru.javaops.bootjava.controller.AuthUser;

import java.util.List;

import static ru.javaops.bootjava.util.RestaurantUtil.asTo;

@Slf4j
@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserRestaurantController {
    public static final String REST_URL = "/api/v1/user/restaurants";
    private final RestaurantRepository repository;
    private final DishRepository dishRepository;
    private final VoteRepository voteRepository;

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("user restaurant getAll");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("user restaurant get with id {}", id);
        return asTo(repository.findById(id).orElse(null), dishRepository.getDishesByRestaurantIdAndCurrentDay(id));
    }

    @PostMapping("/vote/{id}")
    public void vote(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        log.info("user with id {} vote for restaurant with id {}", authUser.getUser().getId(), id);
        voteRepository.vote(authUser.getUser(), repository.getExisted(id));
    }
}
