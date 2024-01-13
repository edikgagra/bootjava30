package ru.javaops.bootjava.repository;

import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.model.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteRepository {
    void vote(User user, Restaurant restaurant);

    List<Vote> findAll();

    Optional<Vote> findById(int id);

    void deleteExisted(int id);

    Vote save(Vote vote);

    Vote getExisted(int id);
}
