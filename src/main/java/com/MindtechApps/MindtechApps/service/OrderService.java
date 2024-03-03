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
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Order> getOrders(final User owner) {
        return restaurantRepository.findById(owner.getRestaurant().getId()).get().getOrders();
    }

    public Order getOrderInfoById(final Long id) throws Exception {
        return getOrderById(id);
    }

    public Order updateOrderStatus(final Long id, final OrderStatus orderStatus) throws Exception {
        final Order order = getOrderById(id);
        order.setOrderStatus(orderStatus);

        return orderRepository.save(order);
    }

    private Order getOrderById(final Long id) throws Exception {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new Exception("Order does not exists");
        }

        return order.get();
    }
}
