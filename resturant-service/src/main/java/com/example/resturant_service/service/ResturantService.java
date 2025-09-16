package com.example.resturant_service.service;

import com.example.resturant_service.dto.Resturant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResturantService {

    private final List<Resturant> resturants = new ArrayList<>();

    public ResturantService() {
        resturants.add(new Resturant("1", "Tandoori Palace", List.of("Chicken Tikka", "Naan", "Biryani")));
        resturants.add(new Resturant("2", "Pizza Hub", List.of("Pepperoni Pizza", "Veggie Pizza", "Garlic Bread")));
        resturants.add(new Resturant("3", "Burger Express", List.of("Cheeseburger", "Veggie Burger", "Fries")));
    }

    public List<Resturant> getAllResturants() {
        return resturants;
    }

    public Resturant getResturantById(String id) {
        return resturants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Boolean menuItemExists(String id, String item) {
        Resturant resturant = resturants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
        
        return resturant.getMenuItems().contains(item);
    }
}
