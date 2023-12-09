package com.example.BarangayConnect.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BarangayConnect.Entity.EventEntity;
import com.example.BarangayConnect.Repository.EventRepository;
import com.example.BarangayConnect.Service.EventService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;
    @Autowired
    EventRepository eventRepository;

    // Create
    @PostMapping("/addEvent")
    public EventEntity addEvent(@RequestBody EventEntity eventEntity) {
        return eventService.addEvent(eventEntity);
    }

    // Read all
    @GetMapping("/getAllEvent")
    public List<EventEntity> getAllInfo() {
        return eventService.getAllInfo();
    }

    // Read by id
    @GetMapping("/getAllEVentByID/{eventId}")
    public Optional<EventEntity> getInfoById(@PathVariable int eventId) {
        return eventService.getInfoById(eventId);
    }

    // Update by id
    @PutMapping("/updateEvent/{eventId}")
    public EventEntity updateEvent(@PathVariable int eventId, @RequestBody EventEntity newEventDetails) {
        return eventService.updateEvent(eventId, newEventDetails);
    }

    // D - Delete
    @PutMapping("/deleteEvent/{eventId}")
    public ResponseEntity<java.util.Map<String, String>> deleteEvent(@PathVariable int eventId) {
        java.util.Map<String, String> response = new HashMap<>();
        Optional<EventEntity> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            EventEntity event = eventOptional.get();
            event.setIsDeleted(true); // Set isDeleted to true instead of deleting the record
            eventRepository.save(event); // Save the updated event record
            response.put("message", "Event " + eventId + " is successfully deleted");
        } else {
            response.put("message", "Event " + eventId + " does not exist");
        }
        return ResponseEntity.ok(response);
    }
}
