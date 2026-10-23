
package com.onlineshop.model;

import java.util.regex.Pattern;

public class Customer {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final long id;
    private final String name;
    private final String email;
    private String address;
    private final String password;
    private boolean authenticated;

    public Customer(long id, String name, String email,
                    String address, String password) {
        this.id = id;
        this.name = requireNotBlank(name, "Customer name");
        this.email = requireValidEmail(email);
        this.address = requireNotBlank(address, "Customer address");
        this.password = requireValidPassword(password);
        this.authenticated = false;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = requireNotBlank(address, "Customer address");
    }

    public boolean login(String credential) {
        if (credential == null || !this.password.equals(credential)) {
            return false;
        }

        this.authenticated = true;
        return true;
    }

    public void logout() {
        this.authenticated = false;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    private static String requireNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " must not be null or blank"
            );
        }

        return value.trim();
    }

    private static String requireValidEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Invalid email address");
        }

        String normalizedEmail = email.trim();

        if (!EMAIL_PATTERN.matcher(normalizedEmail).matches()) {
            throw new IllegalArgumentException("Invalid email address");
        }

        return normalizedEmail;
    }

    private static String requireValidPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "Password must not be null or blank"
            );
        }

        // Ne pas appliquer trim() au mot de passe.
        return password;
    }
}
