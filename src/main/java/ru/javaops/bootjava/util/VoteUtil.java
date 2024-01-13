package ru.javaops.bootjava.util;

import lombok.experimental.UtilityClass;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.to.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class VoteUtil {
    public static List<VoteTo> asListTo(List<Vote> votes) {
        return votes.stream()
                .map(VoteUtil::asTo)
                .collect(Collectors.toList());
    }

    public static VoteTo asTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getRestaurant().getId(), vote.getUser().getId(), vote.getVoteDate());
    }
}
