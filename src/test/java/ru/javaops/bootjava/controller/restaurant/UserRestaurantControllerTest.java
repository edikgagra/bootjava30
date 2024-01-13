package ru.javaops.bootjava.controller.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.exception.VoteRequestException;
import ru.javaops.bootjava.repository.CrudVoteRepository;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.VoteRepository;
import ru.javaops.bootjava.repository.VoteRepositoryImpl;
import ru.javaops.bootjava.controller.AbstractControllerTest;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.bootjava.util.RestaurantUtil.asTo;
import static ru.javaops.bootjava.controller.restaurant.RestaurantTestData.*;
import static ru.javaops.bootjava.controller.restaurant.UserRestaurantController.REST_URL;
import static ru.javaops.bootjava.controller.user.UserTestData.*;
import static ru.javaops.bootjava.controller.vote.VoteTestData.*;

public class UserRestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private CrudVoteRepository voteRepository;

    @Autowired(required = false)
    private VoteRepository testVoteRepository;

    @Autowired
    private VoteRepository voteRepositoryImpl;

    @Mock
    private Clock clock;

    @Autowired
    private DishRepository dishRepository;

    private static final ZonedDateTime NOW = ZonedDateTime.of(
            LocalDateTime.of(
                    LocalDate.now().getYear(),
                    LocalDate.now().getMonth(),
                    LocalDate.now().getDayOfMonth(),
                    12,
                    0
            ),
            ZoneId.systemDefault()
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(clock.getZone()).thenReturn(NOW.getZone());
        when(clock.instant()).thenReturn(NOW.toInstant());
        testVoteRepository = new VoteRepositoryImpl(
                voteRepository,
                clock
        );
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + FIRST_RESTAURANT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(asTo(
                        firstRestaurant,
                        dishRepository.getDishesByRestaurantIdAndCurrentDay(FIRST_RESTAURANT_ID)
                )));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurants));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(AdminRestaurantController.REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void vote() {
        assertDoesNotThrow(() -> testVoteRepository.vote(admin, thirdRestaurant));
        VOTE_MATCHER.assertMatch(voteRepositoryImpl.findAll(), votesWithAdminVote);

    }

    @Test
    void voteSecondTimeAtDay() {
        assertThrows(VoteRequestException.class,
                () -> testVoteRepository.vote(firstUser, secondRestaurant));
        VOTE_MATCHER.assertMatch(voteRepositoryImpl.findAll(), votes);
    }

    @Test
    void voteSameRestaurantInOneDay() {
        when(clock.instant()).thenReturn(NOW.minusHours(5).toInstant());
        assertThrows(VoteRequestException.class,
                () -> testVoteRepository.vote(firstUser, firstRestaurant));
        VOTE_MATCHER.assertMatch(voteRepositoryImpl.findAll(), votes);
    }

    @Test
    @Transactional
    void changeVote() {
        when(clock.instant()).thenReturn(NOW.minusHours(5).toInstant());
        assertDoesNotThrow(() -> testVoteRepository.vote(firstUser, secondRestaurant));
        RESTAURANT_MATCHER.assertMatch(voteRepositoryImpl.getExisted(FIRST_VOTE_ID).getRestaurant(), secondRestaurant);
    }
}
