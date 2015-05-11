package com.ticketbooking.model;

import java.util.Date;

/**
 * Event Entity
 */
public interface Event extends Entity {
    
    String getTitle();
    void setTitle(String title);
    Date getDate();
    void setDate(Date date);
}
