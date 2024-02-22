package com.cg.customer1n.dao;

import com.cg.customer1n.model.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAO implements IProvinceDAO {
    private static final String INSERT_PROVINCE = "INSERT INTO province (name) VALUES (?)";
    private static final String UPDATE_PROVINCE_SQL = "UPDATE province SET name = ? WHERE id = ?";
    private String jdbcURL = "jdbc:mysql://localhost:3306/customer?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Raisingthebar123!!/";
    private String SELECT_PROVINCE_BY_ID = "SELECT * FROM province WHERE id = ?";
    private String SELECT_PROVINCE = "SELECT * FROM province";

    @Override
    public List<Province> selectProvinces() {
        List<Province> provinces = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVINCE);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int province_id = rs.getInt("id");
                String province_name = rs.getString("name");
                Province province = new Province(province_id, province_name);
                provinces.add(province);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return provinces;
    }

    @Override
    public Province findProvinceById(int id) {
        Province province = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVINCE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int province_id = rs.getInt("id");
                String province_name = rs.getString("name");
                province = new Province(province_id, province_name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return province;
    }

    @Override
    public void insertProvince(Province province) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROVINCE)) {
            preparedStatement.setString(1, province.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void updateProvince(Province province) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PROVINCE_SQL)) {
            statement.setString(1, province.getName());
            statement.setInt(2, province.getId());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    @Override
    public void deleteProvince(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM province WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}