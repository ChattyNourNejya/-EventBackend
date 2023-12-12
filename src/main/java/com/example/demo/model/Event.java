package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long eventId;
	
	@Column(name="event_name")
    private String eventName;
	
	@Column(name="event_date")
    private String date;
    
	@Column(name="event_location")
	private String location;
	
	@Column(name="event_description")
    private String description;
	  
	
    @Column(name="event_image")
    private String image;

    @Column(name="event_price")
    private Double price;

    @Column(name="event_url")
    private String url;

	
    @OneToMany(mappedBy = "event", orphanRemoval = true)
    private List<Feedback> feedbackList;

	private String  filePath;
	
	public Event() {
	}
	public Event(String eventName, String date, String location, String description) {
		super();
		this.eventName = eventName;
		this.date = date;
		this.location = location;
		this.description = description;
	}
	
	
	

	public Event(String eventName, String date, String location, String description, String image, Double price,
			String url, List<Feedback> feedbackList) {
		super();
		this.eventName = eventName;
		this.date = date;
		this.location = location;
		this.description = description;
		this.image = image;
		this.price = price;
		this.url = url;
		this.feedbackList = feedbackList;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}
	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	  public String getImage() {
	        return image;
	    }

	    public void setImage(String image) {
	        this.image = image;
	    }

	    public Double getPrice() {
	        return price;
	    }

	    public void setPrice(Double price) {
	        this.price = price;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }
	    public void setFilePath(String filePath) {
	        this.filePath = filePath;
	    }

	
}
