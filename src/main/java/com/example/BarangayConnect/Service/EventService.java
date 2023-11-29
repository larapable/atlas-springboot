package com.example.BarangayConnect.Service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarangayConnect.Entity.EventEntity;
import com.example.BarangayConnect.Repository.EventRepository;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    // Create
    public EventEntity addEvent(EventEntity eventEntity) {
        return eventRepository.save(eventEntity);
    }

    // Read
    public List<EventEntity> getAllInfo() {
        return eventRepository.findAll();
    }

    // Update
    @SuppressWarnings("finally")
    public EventEntity updateEvent(int eventId, EventEntity newEventDetails) {
        EventEntity eventInfo = new EventEntity();
        try {
            // Search the id number of the event to be updated
            eventInfo = eventRepository.findById(eventId).get();

            eventInfo.setEventTitle(newEventDetails.getEventTitle());
            eventInfo.setEventDescription(newEventDetails.getEventDescription());
            eventInfo.setEventDate(newEventDetails.getEventDate());
            eventInfo.setEventLocation(newEventDetails.getEventLocation());
            eventInfo.setEventTime(newEventDetails.getEventTime());
            eventRepository.save(eventInfo);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Event " + eventId + " does not exist!");
        } finally {
            return eventRepository.save(eventInfo);
        }
    }

    // Delete
    public String deleteEvent(int eventId) {
        String msg = "";
        if (eventRepository.findById(eventId) != null) {
            eventRepository.deleteById(eventId);
            msg = "Event " + eventId + " has been deleted!";
        } else {
            msg = "Event " + eventId + " does not exist!";
        }
        return msg;
    }

}
