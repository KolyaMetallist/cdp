package com.ticketbooking.model;

/**
 * Ticket entity
 */
public interface Ticket extends Entity {
    public enum Category {STANDARD, PREMIUM, BAR}

    long getEventId(); 
    void setEventId(long eventId);      
    long getUserId();    
    void setUserId(long userId);
    Category getCategory();
    void setCategory(Category category);
    int getPlace();
    void setPlace(int place);

}
