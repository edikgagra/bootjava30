package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import static ru.javaops.bootjava.util.validation.UtilValidation.checkExisted;
import static ru.javaops.bootjava.util.validation.UtilValidation.checkModification;


@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {


    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    int delete(int id);

    @SuppressWarnings("SpringTransactionalMethodCallsInspection")
    default void deleteExisted(int id) {
        checkModification(delete(id), id);
    }

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id")
    T get(int id);

    default T getExisted(int id) {
        return checkExisted(get(id), id);
    }
}