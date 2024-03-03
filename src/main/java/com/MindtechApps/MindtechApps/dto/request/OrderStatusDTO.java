package com.MindtechApps.MindtechApps.dto.request;

import com.MindtechApps.MindtechApps.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO {
    OrderStatus orderStatus;
}
