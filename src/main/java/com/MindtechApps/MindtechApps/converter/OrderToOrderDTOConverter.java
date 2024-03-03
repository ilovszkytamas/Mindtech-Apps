package com.MindtechApps.MindtechApps.converter;

import com.MindtechApps.MindtechApps.dto.request.OrderDTO;
import com.MindtechApps.MindtechApps.entity.Order;
import org.springframework.core.convert.converter.Converter;

public class OrderToOrderDTOConverter implements Converter<Order, OrderDTO> {
    @Override
    public OrderDTO convert(Order source) {
        return OrderDTO
            .builder()
            .restaurantId(source.getRestaurant().getId())
            .specialInstructions(source.getSpecialInstructions())
            .build();
    }
}
