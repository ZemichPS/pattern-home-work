package model;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(UUID uuid, Category category, String name, BigDecimal price) {
}
