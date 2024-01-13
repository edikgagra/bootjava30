package ru.javaops.bootjava.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.util.UsersUtil;

@Slf4j
public abstract class AbstractUserController {

    @Autowired
    protected UserRepository repository;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public User get(int id) {
        log.info("get {}", id);
        return repository.findById(id).orElse(null);
    }

    public void delete(int id) {
        log.info("delete user with id {}", id);
        repository.deleteExisted(id);
    }

    protected User prepareAndSave(User user) {
        return repository.save(UsersUtil.prepareToSave(user));
    }
}