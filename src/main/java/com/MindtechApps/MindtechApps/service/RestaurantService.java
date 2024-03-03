package com.MindtechApps.MindtechApps.service;

import com.MindtechApps.MindtechApps.entity.MenuItem;
import com.MindtechApps.MindtechApps.entity.Restaurant;
import com.MindtechApps.MindtechApps.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public List<String> getAllRestaurants() {
        return restaurantRepository.findAll().stream().map(Restaurant::getRestaurantName).collect(Collectors.toList());
    }

    public Restaurant getRestaurantInfoById(final Long id) throws Exception {

        return getRestaurantById(id);
    }

    public List<MenuItem> getRestaurantMenuById(final Long id) throws Exception {
        Restaurant restaurant = getRestaurantById(id);

        return restaurant.getMenuItems();
    }

    private Restaurant getRestaurantById(final Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant does not exists");
        }

        return restaurant.get();
    }
}
