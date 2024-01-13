package ru.javaops.bootjava.controller.vote;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.to.VoteTo;

import java.net.URI;
import java.util.List;

import static ru.javaops.bootjava.util.VoteUtil.asListTo;
import static ru.javaops.bootjava.util.validation.UtilValidation.assureIdConsistent;
import static ru.javaops.bootjava.util.validation.UtilValidation.checkNew;
import static ru.javaops.bootjava.controller.vote.AdminVoteController.REST_URL;

@Slf4j
@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminVoteController {
    public static final String REST_URL = "/api/v1/admin/votes";
    private final VoteRepository repository;

    @GetMapping
    public List<VoteTo> getAll() {
        log.info("vote getAll");
        return asListTo(repository.findAll());
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get vote with id {}", id);
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete vote with id {}", id);
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody Vote vote) {
        log.info("create vote {}", vote);
        checkNew(vote);
        Vote created = repository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Vote vote, @PathVariable int id) {
        log.info("update vote {} with id={}", vote, id);
        assureIdConsistent(vote, id);
        repository.save(vote);
    }
}
