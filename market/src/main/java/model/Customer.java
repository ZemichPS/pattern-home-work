package model;

import java.util.UUID;


public record Customer(
        UUID uuid,
        String name,
        String surname,
        String email,
        String phone
) {
}


