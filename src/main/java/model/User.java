package model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Retention;
import java.util.UUID;


public record User (
     UUID uuid,
     String name,
     String surname,
     String email,
     String phone
){};

