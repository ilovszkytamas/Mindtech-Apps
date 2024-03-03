package com.MindtechApps.MindtechApps.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Restaurant")
@ToString
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String restaurantName;
    private String location;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    private List<MenuItem> menuItems;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    private List<Order> orders;
}
