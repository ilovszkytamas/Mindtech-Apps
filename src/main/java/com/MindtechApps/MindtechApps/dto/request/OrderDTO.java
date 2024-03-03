package com.MindtechApps.MindtechApps.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long restaurantId;
    private List<ItemAmount> items;
    private String specialInstructions;
}
