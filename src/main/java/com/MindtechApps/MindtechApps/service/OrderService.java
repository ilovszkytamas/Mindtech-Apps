package com.MindtechApps.MindtechApps.service;

import com.MindtechApps.MindtechApps.dto.request.ItemAmount;
import com.MindtechApps.MindtechApps.dto.request.OrderDTO;
import com.MindtechApps.MindtechApps.entity.Order;
import com.MindtechApps.MindtechApps.entity.Restaurant;
import com.MindtechApps.MindtechApps.entity.User;
import com.MindtechApps.MindtechApps.enums.OrderStatus;
import com.MindtechApps.MindtechApps.repository.OrderRepository;
import com.MindtechApps.MindtechApps.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    public Order handleCustomerOrder(final User customer, final OrderDTO orderDTO) throws Exception {
        Map<Integer, Integer> items = orderDTO
            .getItems()
            .stream()
            .collect(Collectors.toMap(
                ItemAmount::getItemId,
                ItemAmount::getAmount)
        );

        final Optional<Restaurant> restaurant = restaurantRepository.findById(Long.valueOf(orderDTO.getRestaurantId()));
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant does not exists");
        }

        final Order order = Order.builder()
            .customer(customer)
            .specialInstructions(orderDTO.getSpecialInstructions())
            .orderStatus(OrderStatus.RECEIVED)
            .items(items)
            .restaurant(restaurant.get())
            .build();

        return orderRepository.save(order);
    }
}
