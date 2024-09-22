package dao.impl;

import dao.api.ProductRepository;
import model.Category;
import model.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger logger = Logger.getLogger(ProductRepositoryImpl.class.getName());
    private final DataSource dataSource;

    public ProductRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Product create(Product product) {
        String sql = "INSERT INTO products (uuid, category, name, price) VALUES (?, ?, ?, ?) )";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, product.uuid().toString());
            preparedStatement.setString(2, product.category().toString());
            preparedStatement.setString(3, product.name());
            preparedStatement.setBigDecimal(4, product.price());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        String sql = "UPDATE products SET uuid = ?, category = ?, name = ?, price = ? WHERE uuid = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, product.uuid().toString());
            statement.setString(2, product.category().toString());
            statement.setString(3, product.name());
            statement.setBigDecimal(4, product.price());
            statement.setString(5, product.uuid().toString());
            return product;
        } catch (SQLException e) {
            logger.severe("Error update product: " + e.getMessage());
            throw new RuntimeException("Failed to get all products: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT * FROM products";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()
        ) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(mapRowToProduct(resultSet));
            }
            return products;

        } catch (SQLException e) {
            logger.severe("Error getting all products: " + e.getMessage());
            throw new RuntimeException("Failed to get all products: " + e.getMessage());
        }
    }

    @Override
    public Optional<Product> getById(UUID id) {
        String sql = "SELECT * FROM products WHERE uuid = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, id.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = mapRowToProduct(resultSet);
                    return Optional.of(product);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.severe("Error getting product by Uuid: " + e.getMessage());
            throw new RuntimeException("Failed to get all products: " + e.getMessage());
        }
    }

    @Override
    public void delete(Product product) {
        String sql = "DELETE FROM product WHERE uuid = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, product.uuid().toString());
        } catch (SQLException e) {
            logger.severe("Error getting all products: " + e.getMessage());
            throw new RuntimeException("Failed to get all products: " + e.getMessage());
        }
    }


    private Product mapRowToProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                UUID.fromString(resultSet.getString("uuid")),
                Category.valueOf(resultSet.getString("category")),
                resultSet.getString("name"),
                resultSet.getBigDecimal("price")
        );
    }
}
