package ru.internship.ballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.internship.ballot.model.Dish;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Override
    @Transactional
    Dish save(Dish dish); // диша сама соединяется с рестораном?

    Optional<Dish> findById(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    boolean delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.date DESC")
    List<Dish> getAllByRestaurant(@Param("restaurantId") int restaurantId);

  //  Optional<Dish> get(int id, int restaurantId);

   // List<Dish> getAll(int restaurantId);

}
