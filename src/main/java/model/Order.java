package model;

import java.util.List;
import java.util.UUID;

public record Order(UUID uuid, UUID userUuid, List<Product> productList){}
