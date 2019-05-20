package ru.internship.ballot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.internship.ballot.model.Restaurant;
import ru.internship.ballot.repository.RestaurantRepository;
import ru.internship.ballot.service.RestaurantService;

import java.util.List;
import java.util.Optional;

import static ru.internship.ballot.util.ValidationUtil.checkNotFoundWithId;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    @Override
    public Restaurant get(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return checkNotFoundWithId(restaurant.orElse(null), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAllByOrderByTitle();
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return checkNotFoundWithId(restaurantRepository.getWithDishes(id), id);
    }
}
