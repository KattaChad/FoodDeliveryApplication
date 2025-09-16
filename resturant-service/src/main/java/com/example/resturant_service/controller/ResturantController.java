package com.example.resturant_service.controller;

import com.example.resturant_service.dto.Resturant;
import com.example.resturant_service.service.ResturantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resturants")
public class ResturantController {

    private final ResturantService resturantService;

    public ResturantController(ResturantService resturantService) {
        this.resturantService = resturantService;
    }

    @GetMapping("/list")
    public List<Resturant> getAllResturants() {
        return resturantService.getAllResturants();
    }

    @GetMapping("/find/{id}")
    public Resturant getResturantById(@PathVariable String id) {
        return resturantService.getResturantById(id);
    }

    @GetMapping("/{id}/menu/{item}")
    public Boolean checkItemAvailability(@PathVariable String id, @PathVariable String item) {
        return resturantService.menuItemExists(id, item);
    }
}
