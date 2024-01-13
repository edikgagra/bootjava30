package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    @Query("SELECT d FROM Dish d WHERE d.id = :id AND CAST(d.dishDate AS DATE) = CAST(current date as date)")
    List<Dish> getDishesByRestaurantIdAndCurrentDay(Integer id);
}
