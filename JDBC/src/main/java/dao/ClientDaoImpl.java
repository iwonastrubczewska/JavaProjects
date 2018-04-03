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
            statement = conn.prepareStatement(SQL);
            statement.setString(1, c.getFirstName());
            statement.setString(2, c.getLastName());
            statement.setString(3, c.getPesel());
            statement.setString(4, c.getEmail());

            statement.executeUpdate();
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
        final String SQL = "delete from CLIENT where id=?";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(SQL);
            statement.setInt(1, c.getId());
            statement.executeUpdate();
        }
        catch (SQLException ex) {
            throw new DataAccessException(ex);
        }

        statement.close();
        conn.close();
    }

    @Override
    public void update(Client c) {
        PreparedStatement statement = null;
        final String SQL = "update CLIENT set firstName=?, lastName=?, email=? where id=?";
        try {
            Connection conn = ConnectionFactory.connect();
            statement = conn.prepareStatement(SQL);
            statement.setString(1,c.getFirstName());
            statement.setString(2,c.getLastName());
            statement.setString(3,c.getEmail());
            statement.setInt(4,c.getId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
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
            statement = conn.prepareStatement(SQL);
            statement.setInt(1, id);


            ResultSet rs = statement.executeQuery(SQL);
            if (rs.next()) {
                result = new Client();
                result.setId(rs.getInt("id"));
                result.setFirstName(rs.getString("firstName"));
                result.setLastName(rs.getString("lastName"));
                result.setPesel(rs.getString("pesel"));
                result.setEmail(rs.getString("email"));
            }

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
            statement = conn.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery(SQL);

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
            ex.printStackTrace();
            throw new DataAccessException(ex);
        }

        return list;
    }

}
