package ru.javaops.bootjava.controller.dish;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.repository.DishRepository;

import java.net.URI;
import java.util.List;

import static ru.javaops.bootjava.util.validation.UtilValidation.assureIdConsistent;
import static ru.javaops.bootjava.util.validation.UtilValidation.checkNew;

@Slf4j
@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminDishController {
    static final String REST_URL = "/api/v1/admin/dishes";
    private final DishRepository repository;

    @GetMapping
    public List<Dish> getAll() {
        log.info("dish getAll");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dish with id {}", id);
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish with id {}", id);
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish) {
        log.info("create dish {}", dish);
        checkNew(dish);
        Dish created = repository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int id) {
        log.info("update dish {} with id {}", dish, id);
        assureIdConsistent(dish, id);
        repository.save(dish);
    }
}
