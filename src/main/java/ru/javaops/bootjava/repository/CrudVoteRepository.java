package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;

import java.util.Date;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends BaseRepository<Vote> {
    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId ORDER BY v.voteDate DESC LIMIT 1")
    Vote getLastUserVote(Integer userId);

    @Modifying
    @Query("UPDATE Vote v SET v.restaurant.id = :restaurantId, v.voteDate = :date WHERE v.id = :id")
    void updateVote(Integer restaurantId, Date date, Integer id);
}
