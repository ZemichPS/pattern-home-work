package dao.impl;

import dao.api.OrderRepository;
import model.Category;
import model.Order;
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

public class OrderRepositoryImpl implements OrderRepository {

    private static final Logger logger = Logger.getLogger(OrderRepositoryImpl.class.getName());
    private final DataSource dataSource;

    public OrderRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM orders";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {

            }


        } catch (SQLException e) {
            logger.severe("Error finding user by id: " + e.getMessage());
        }

        return List.of();
    }

    @Override
    public Optional<Order> getById(UUID id) {
        return null;
    }

    @Override
    public void delete(Order id) {

    }

    private Order mapRowToOrder(ResultSet resultSet) throws SQLException {
        return null;
    }
}
