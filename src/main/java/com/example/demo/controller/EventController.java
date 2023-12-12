package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Event; // Assuming Event is a model class
import com.example.demo.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/events") // Changed the request mapping
public class EventController {
	
	
	@Autowired
	private final EventRepository eventRepository; //

	// Constructor injection for EventRepository
	public EventController(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}


    @CrossOrigin("http://localhost:4200")
	@GetMapping("all")
	public List<Event> getAllEvents() { 
		return eventRepository.findAll();
	}
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/creatEvent")
    public ResponseEntity<Event> createEvent(@RequestParam("file") MultipartFile file, 
                                             @RequestParam("event") String eventJson,
                                             UriComponentsBuilder uriBuilder) 
    throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Event event = objectMapper.readValue(eventJson, Event.class);

        // Generate a unique filename
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        
        // Save uploaded file
        file.transferTo(new File("/event-student-management-system/src/main/resources/upload", fileName));

        // Set event's file path
        event.setFilePath(fileName);

        // Save event to database
        Event savedEvent = eventRepository.save(event);

        // Create response
        return ResponseEntity.created(uriBuilder.path("/api/events/{id}")
                                             .buildAndExpand(savedEvent.getEventId())
                                             .toUri())
                           .body(savedEvent);
    }
	// get event by id r
    @CrossOrigin("http://localhost:4200")
	@GetMapping("/event/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable Long id) {
    	Event event = eventRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Event not exist with id :" + id));
		return ResponseEntity.ok(event);
	}
    
	@PutMapping("/updateEvent/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails){
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("event not exist with id :" + id));
		
		event.setEventName(eventDetails.getEventName());
		event.setDescription(eventDetails.getDescription());
		event.setLocation(eventDetails.getLocation());
		event.setDate(eventDetails.getDate());
		event.setPrice(eventDetails.getPrice());
		event.setUrl(eventDetails.getUrl());
		
		Event updateEvent = eventRepository.save(event);
		return ResponseEntity.ok(updateEvent);
	}
    
    
    
    
    @DeleteMapping("/delete/{id}")
    @CrossOrigin("http://localhost:4200") 
	public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable Long id){
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Event not exist with id :" + id));
		
		eventRepository.delete(event);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}