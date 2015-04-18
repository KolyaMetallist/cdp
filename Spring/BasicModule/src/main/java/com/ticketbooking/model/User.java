package com.ticketbooking.model;

/**
 * User entity
 */
public interface User extends Entity {

    String getName();
    void setName(String name);

    /**
     * User email. UNIQUE.
     * @return User email.
     */
    String getEmail();
    void setEmail(String email);
}
