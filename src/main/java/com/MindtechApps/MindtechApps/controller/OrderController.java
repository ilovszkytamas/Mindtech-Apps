package com.MindtechApps.MindtechApps.controller;

import com.MindtechApps.MindtechApps.dto.request.OrderDTO;
import com.MindtechApps.MindtechApps.dto.request.OrderStatusDTO;
import com.MindtechApps.MindtechApps.entity.Order;
import com.MindtechApps.MindtechApps.entity.User;
import com.MindtechApps.MindtechApps.enums.OrderStatus;
import com.MindtechApps.MindtechApps.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final ConversionService conversionService;
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public OrderDTO customerOrder(final @AuthenticationPrincipal User customer, final @RequestBody OrderDTO orderDTO) throws Exception {
        return conversionService.convert(orderService.handleCustomerOrder(customer, orderDTO), OrderDTO.class);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('RESTAURANT')")
    public List<Order> getOrders(final @AuthenticationPrincipal User owner) {
        return orderService.getOrders(owner);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RESTAURANT')")
    public Order getOrderById(final @PathVariable Long id) throws Exception {
        return orderService.getOrderInfoById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('RESTAURANT')")
    public Order updateOrderStatus(final @PathVariable Long id, final @RequestBody OrderStatusDTO orderStatus) throws Exception {
        return orderService.updateOrderStatus(id, orderStatus.getOrderStatus());
    }
}
