package ru.javaops.bootjava.controller.vote;

import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.to.VoteTo;
import ru.javaops.bootjava.controller.MatcherFactory;

import java.util.List;

import static ru.javaops.bootjava.controller.restaurant.RestaurantTestData.*;
import static ru.javaops.bootjava.controller.user.UserTestData.*;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "voteDate", "user.password", "user.registered", "restaurant.dishes", "restaurant.votes", "user.votes", "restaurant.voteCount");
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VoteTo.class, "voteDate");
    public static final int FIRST_VOTE_ID = 1;
    public static final int SECOND_VOTE_ID = 2;
    public static final int NOT_FOUND = 100;
    public static final Vote firstVote = new Vote(FIRST_VOTE_ID, firstUser, firstRestaurant);
    public static final Vote secondVote = new Vote(SECOND_VOTE_ID, secondUser, secondRestaurant);

    public static final List<Vote> votes = List.of(firstVote, secondVote);

    public static final List<Vote> votesWithAdminVote = List.of(firstVote, secondVote, new Vote(3, admin, thirdRestaurant));

    public static Vote getUpdated() {
        return new Vote(FIRST_VOTE_ID, firstUser, secondRestaurant);
    }

    public static Vote getNew() {
        return new Vote(null, admin, thirdRestaurant);
    }

}
