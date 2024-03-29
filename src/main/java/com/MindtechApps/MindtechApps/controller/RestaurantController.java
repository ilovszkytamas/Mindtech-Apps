package com.MindtechApps.MindtechApps.controller;

import com.MindtechApps.MindtechApps.entity.MenuItem;
import com.MindtechApps.MindtechApps.entity.Restaurant;
import com.MindtechApps.MindtechApps.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<String> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Restaurant getRestaurantInfoById(final @PathVariable Long id) throws Exception {
        return restaurantService.getRestaurantInfoById(id);
    }

    @GetMapping("{id}/menu")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public List<MenuItem> getRestaurantMenuById(final @PathVariable Long id) throws Exception {
        return restaurantService.getRestaurantMenuById(id);
    }
}
