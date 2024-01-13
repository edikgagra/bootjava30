package ru.javaops.bootjava.controller.user;

import ru.javaops.bootjava.model.Role;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.util.JsonUtil;
import ru.javaops.bootjava.controller.MatcherFactory;

import java.util.Collections;
import java.util.Date;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password", "votes");

    public static final int FIRST_USER_ID = 1;
    public static final int SECOND_USER_ID = 2;
    public static final int ADMIN_ID = 3;
    public static final int NOT_FOUND = 100;
    public static final String USER_MAIL = "user@gmail.com";
    public static final String USER_SECOND_MAIL = "user2@gmail.com";
    public static final String ADMIN_MAIL = "admin@gmail.com";

    public static final User firstUser = new User(FIRST_USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User secondUser = new User(SECOND_USER_ID, "UserSecond", USER_SECOND_MAIL, "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(FIRST_USER_ID, "UpdatedName", USER_MAIL, "newPass", false, new Date(), Collections.singleton(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String password) {
        return JsonUtil.writeAdditionProps(user, "password", password);
    }
}
