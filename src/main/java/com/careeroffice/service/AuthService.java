package com.careeroffice.service;

import javax.servlet.http.HttpSession;

import com.careeroffice.database.DatabaseConnection;
import com.careeroffice.model.User;

/**
 * Handles user authentication.
 */
public class AuthService {

    /**
     * The HTTP session for a request.
     */
    private HttpSession session;

    /**
     * Initializes auth service.
     * 
     * @param session The session for a request.
     */
    public AuthService(HttpSession session) {
        this.session = session;
    }

    /**
     * Checks if the user is logged in.
     * 
     * @return Whether the user is logged in.
     */
    public boolean isLoggedIn() {
        return session.getAttribute("loggedIn") != null;
    }

    /**
     * Checks if the user has a specific role.
     * 
     * @param role A specific role that the user should have.
     * @return If the user has the specific role.
     */
    public boolean hasRole(String role) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRoleId().equals(role))
                return true;
        }
        return false;
    }

    /**
     * Get the logged in user.
     * 
     * @return The logged in user.
     */
    public User getUser() {
        return (session.getAttribute("user") != null) ? (User) session.getAttribute("user") : null;
    }

}
