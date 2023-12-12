package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;
    private String comment;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    

    public Feedback() {
	
	}

	public Feedback(String comment, int rating, Event event) {
		super();
		this.comment = comment;
		this.rating = rating;
		this.event = event;
	}

	public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
