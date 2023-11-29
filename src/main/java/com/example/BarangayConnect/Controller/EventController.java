package com.example.BarangayConnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BarangayConnect.Entity.EventEntity;
import com.example.BarangayConnect.Service.EventService;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;

    // Create
    @PostMapping("addInfo")
    public EventEntity addEvent(@RequestBody EventEntity eventEntity) {
        return eventService.addEvent(eventEntity);
    }

    // Read 
    @GetMapping("getAllInfo")
    public List<EventEntity> getAllInfo() {
        return eventService.getAllInfo();
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
