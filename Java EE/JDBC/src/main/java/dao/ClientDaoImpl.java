package dao;

import exceptions.DataAccessException;
import main.ConnectionFactory;
import models.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

    @Override
    public void save(Client c) throws SQLException {

        final String SQL = "insert into CLIENT values (DEFAULT,?,?,?,?)";
        Connection conn = ConnectionFactory.connect();
        PreparedStatement statement = null;
        try {
            if (conn != null) {
                statement = conn.prepareStatement(SQL);
                statement.setString(1, c.getFirstName());
                statement.setString(2, c.getLastName());
                statement.setString(3, c.getPesel());
                statement.setString(4, c.getEmail());

                statement.executeQuery();
            }

        }
        catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

            statement.close();
            conn.close();

    }

    @Override
    public void delete(Client c) throws SQLException {

        Connection conn = ConnectionFactory.connect();
        final String SQL = "DELETE  FROM  CLIENT Where ID = ?";
        PreparedStatement statement = null;
        try {
            if (conn != null) {
                statement = conn.prepareStatement(SQL);
            }
            if (statement != null) {
                statement.setInt(1, c.getId());
                //statement.executeUpdate();
                statement.executeQuery();
            }
        }
        catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

            statement.close();
            conn.close();

    }

    @Override
    public void update(Client c) {

        final String SQL = "update CLIENT set firstName=?, lastName=?, pesel=?, email=? where id=?";
        try {
            Connection conn = ConnectionFactory.connect();
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(SQL);
                statement.setString(1,c.getFirstName());
                statement.setString(2,c.getLastName());
                statement.setString(3,c.getPesel());
                statement.setString(4,c.getEmail());
                statement.setInt(5,c.getId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
           // ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Client findById(Integer id) {

        final String SQL = "select * from CLIENT where id = ?";
        Client result = null;
        PreparedStatement statement = null;
        try {
            Connection conn = ConnectionFactory.connect();
            if (conn != null) {
                statement = conn.prepareStatement(SQL);
                statement.setInt(1, id);
            }

            ResultSet rs = null;
            rs = statement.executeQuery();

            if (rs.next()) {
                result = new Client();
                result.setId(rs.getInt("id"));
                result.setFirstName(rs.getString("firstName"));
                result.setLastName(rs.getString("lastName"));
                result.setPesel(rs.getString("pesel"));
                result.setEmail(rs.getString("email"));
            } else throw new NullPointerException();

            rs.close();
            conn.close();
        }
        catch (SQLException ex) {

            throw new DataAccessException(ex);
        }
        return result;
    }

    @Override
    public List<Client> findAll() {
        final String SQL = "select * from CLIENT";
        PreparedStatement statement = null;
        Client result = null;
        LinkedList<Client> list = new LinkedList<>();
        try {
            Connection conn = ConnectionFactory.connect();
            if (conn != null) {
                statement = conn.prepareStatement(SQL);
            }
            ResultSet rs = null;
            if (statement != null) {
                rs = statement.executeQuery();
            }
            while (rs.next()) {
                result = new Client();
                result.setId(rs.getInt("id"));
                result.setFirstName(rs.getString("firstName"));
                result.setLastName(rs.getString("lastName"));
                result.setPesel(rs.getString("pesel"));
                result.setEmail(rs.getString("email"));

                list.add(result);
            }

            rs.close();
            conn.close();

        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

        return list;
    }

}
