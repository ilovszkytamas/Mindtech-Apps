package com.MindtechApps.MindtechApps.service;

import com.MindtechApps.MindtechApps.entity.Restaurant;
import com.MindtechApps.MindtechApps.entity.User;
import com.MindtechApps.MindtechApps.enums.Role;
import com.MindtechApps.MindtechApps.repository.RestaurantRepository;
import com.MindtechApps.MindtechApps.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataLoader {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        if (userRepository.findAll().size() < 1) {

            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName("restaurant");
            restaurant.setLocation("location");
            restaurant = restaurantRepository.save(restaurant);


            User testUser = new User();
            testUser.setEmail("test");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode("test");
            testUser.setPassword(encodedPassword);
            testUser.setUserName("test");
            testUser.setRole(Role.CUSTOMER);

            userRepository.save(testUser);

            User restaurantUser = new User();
            restaurantUser.setEmail("testRestaurant");
            restaurantUser.setPassword(encodedPassword);
            restaurantUser.setUserName("testRestaurant");
            restaurantUser.setRole(Role.RESTAURANT);
            restaurantUser.setRestaurant(restaurant);

            restaurant.setOwner(restaurantUser);

            userRepository.save(restaurantUser);
            restaurantRepository.save(restaurant);
        }
    }
}
