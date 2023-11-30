package com.example.BarangayConnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BarangayConnect.Entity.EventEntity;
import com.example.BarangayConnect.Service.EventService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;

    // Create
    @PostMapping("addEvent")
    public EventEntity addEvent(@RequestBody EventEntity eventEntity) {
        return eventService.addEvent(eventEntity);
    }

    // Read 
    @GetMapping("getAllEvent")
    public List<EventEntity> getAllInfo() {
        return eventService.getAllInfo();
    }
    // Read 
    @GetMapping("/getAllInfoByID/{eventId}")
    public List<EventEntity> getAllInfoById(@PathVariable int eventId) {
        return eventService.findById(eventId);
    }

    // Update
    public EventEntity updateEvent(int eventId, EventEntity newEventDetails) {
        return eventService.updateEvent(eventId, newEventDetails);
    }

    // Delete
    public String deleteEvent(int eventId) {
        return eventService.deleteEvent(eventId);
    }
}
