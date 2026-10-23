package com.onlineshop.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    /*
    * L’objectif est de réduire la répétition dans les tests. Pour l’instant, nous allons ajouter un helper sans modifier les tests existants.
    * */
    private Customer createValidCustomer() {
        return new Customer(
                1L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );
    }

    @Test
    void shouldCreateCustomerWithValidData() {
        Customer customer = new Customer(
                1L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!");

        assertEquals(1L, customer.getId());
        assertEquals("Chaker Ben Said", customer.getName());
        assertEquals("chaker@example.com", customer.getEmail());
        assertEquals("Sfax, Tunisia", customer.getAddress());
    }

    @Test
    void shouldRejectNullName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        2L,
                        null,
                        "chaker@example.com",
                        "Sfax, Tunisia",
                        "Password123!"));
    }

    @Test
    void shouldRejectEmptyName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        3L,
                        "",
                        "chaker@example.com",
                        "Sfax, Tunisia",
                        "Password123!"));
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        4L,
                        "   ",
                        "chaker@example.com",
                        "Sfax, Tunisia",
                        "Password123!"));
    }


    @Test
    void shouldRejectNullEmail() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        5L,
                        "Chaker Ben Said",
                        null,
                        "Sfax, Tunisia",
                        "Password123!"));
    }

    @Test
    void shouldRejectNullAddress() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        10L,
                        "Chaker Ben Said",
                        "chaker@example.com",
                        null,
                        "Password123!"
                )
        );
    }

    @Test
    void shouldRejectEmptyAddress() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        11L,
                        "Chaker Ben Said",
                        "chaker@example.com",
                        "",
                        "Password123!"
                )
        );
    }


    @Test
    void shouldRejectBlankAddress() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        12L,
                        "Chaker Ben Said",
                        "chaker@example.com",
                        "   ",
                        "Password123!"
                )
        );
    }


    @Test
    void shouldRejectNullPassword() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        13L,
                        "Chaker Ben Said",
                        "chaker@example.com",
                        "Sfax, Tunisia",
                        null
                )
        );
    }


    @Test
    void shouldRejectEmptyPassword() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        14L,
                        "Chaker Ben Said",
                        "chaker@example.com",
                        "Sfax, Tunisia",
                        ""
                )
        );
    }


    @Test
    void shouldRejectBlankPassword() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        15L,
                        "Chaker Ben Said",
                        "chaker@example.com",
                        "Sfax, Tunisia",
                        "   "
                )
        );
    }

    //Étape suivante : tester la connexion avec login()
    @Test
    void shouldLoginWithCorrectPassword() {
        Customer customer = createValidCustomer();
        boolean result = customer.login("Password123!");

        assertTrue(result);
        assertTrue(customer.isAuthenticated());
    }

    // Étape suivante : refuser un mot de passe incorrect
    @Test
    void shouldRejectLoginWithIncorrectPassword() {
        Customer customer = new Customer(
                17L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        boolean result = customer.login("WrongPassword!");

        assertFalse(result);
        assertFalse(customer.isAuthenticated());
    }

    // Étape suivante : tester login(null)
    @Test
    void shouldRejectLoginWithNullPassword() {
        Customer customer = new Customer(
                18L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        boolean result = customer.login(null);

        assertFalse(result);
        assertFalse(customer.isAuthenticated());
    }

    // Étape suivante : tester logout()
    @Test
    void shouldLogoutAfterSuccessfulLogin() {
        Customer customer = new Customer(
                19L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        customer.login("Password123!");
        customer.logout();

        assertFalse(customer.isAuthenticated());
    }

    // Étape suivante : appeler logout() sans connexion préalable
    @Test
    void shouldRemainUnauthenticatedWhenLoggingOutWithoutLogin() {
        Customer customer = new Customer(
                20L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        customer.logout();

        assertFalse(customer.isAuthenticated());
    }


    @Test
    void shouldUpdateAddress() {
        Customer customer = new Customer(
                21L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        customer.setAddress("Tunis, Tunisia");

        assertEquals("Tunis, Tunisia", customer.getAddress());
    }

    @Test
    void shouldRejectNullAddressWhenUpdatingAddress() {
        Customer customer = new Customer(
                22L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> customer.setAddress(null)
        );

        assertEquals("Sfax, Tunisia", customer.getAddress());
    }

    @Test
    void shouldRejectEmptyAddressWhenUpdatingAddress() {
        Customer customer = new Customer(
                22L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> customer.setAddress("")
        );

        assertEquals("Sfax, Tunisia", customer.getAddress());
    }

    @Test
    void shouldRejectWhitespaceAddressWhenUpdatingAddress() {
        Customer customer = new Customer(
                22L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> customer.setAddress("   ")
        );

        assertEquals("Sfax, Tunisia", customer.getAddress());
    }

    @Test
    void shouldTrimCustomerName() {
        Customer customer = new Customer(
                25L,
                "  Chaker Ben Said  ",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        assertEquals("Chaker Ben Said", customer.getName());
    }

    @Test
    void shouldRejectPasswordWithDifferentCase() {
        Customer customer = new Customer(
                26L,
                "Chaker Ben Said",
                "chaker@example.com",
                "Sfax, Tunisia",
                "Password123!"
        );

        boolean result = customer.login("password123!");

        assertFalse(result);
        assertFalse(customer.isAuthenticated());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abc",
            "a@b",
            "a b@c.com",
            "@b.com",
            ""
    })
    void shouldRejectInvalidEmails(String email) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(
                        27L,
                        "Chaker Ben Said",
                        email,
                        "Sfax, Tunisia",
                        "Password123!"
                )
        );
    }


    @Test
    void shouldTrimEmail() {
        Customer customer = new Customer(
                1L, "Chaker Ben Said", "  chaker@example.com  ",
                "Sfax, Tunisia", "Password123!");

        assertEquals("chaker@example.com", customer.getEmail());
    }

    @Test
    void shouldLoginSuccessfullyAfterFailedAttempt() {
        Customer customer = createValidCustomer();

        assertFalse(customer.login("WrongPassword123!"));
        assertFalse(customer.isAuthenticated());

        assertTrue(customer.login("Password123!"));
        assertTrue(customer.isAuthenticated());
    }
}
