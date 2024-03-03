package com.MindtechApps.MindtechApps.controller;

import com.MindtechApps.MindtechApps.dto.request.OrderDTO;
import com.MindtechApps.MindtechApps.entity.User;
import com.MindtechApps.MindtechApps.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final ConversionService conversionService;
    private final OrderService orderService;

    @PostMapping
    public OrderDTO customerOrder(final @AuthenticationPrincipal User customer, final @RequestBody OrderDTO orderDTO) throws Exception {
        return conversionService.convert(orderService.handleCustomerOrder(customer, orderDTO), OrderDTO.class);
    }
}
