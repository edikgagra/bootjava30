package ru.javaops.bootjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.javaops.bootjava.exception.VoteRequestException;
import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.model.Vote;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteRepository {
    private final CrudVoteRepository repository;
    private static final LocalTime CAN_CHANGE_BEFORE_TIME = LocalTime.of(11, 0);

    private final Clock clock;

    @Override
    public void vote(User user, Restaurant restaurant) {
        Vote vote = repository.getLastUserVote(user.getId());
        if (vote == null || vote.getVoteDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now(clock))) {
            repository.save(new Vote(null, user, restaurant));
            return;
        }
        if (LocalTime.now(clock).isAfter(CAN_CHANGE_BEFORE_TIME)) {
            throw new VoteRequestException("You have already voted for restaurant today");
        }
        if (vote.getRestaurant().getId() == restaurant.getId()) {
            throw new VoteRequestException("You have already voted for this restaurant");
        }
        repository.updateVote(restaurant.getId(), new Date(), vote.getId());
    }

    @Override
    public List<Vote> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Vote> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void deleteExisted(int id) {
        repository.deleteExisted(id);
    }

    @Override
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public Vote getExisted(int id) {
        return repository.getExisted(id);
    }
}
