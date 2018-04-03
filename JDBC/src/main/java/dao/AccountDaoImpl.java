package dao;

import main.ConnectionFactory;
import models.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void save(Account a) {
        final String SQL = "insert into ACCOUNT values (DEFAULT,?,?,?)";
        PreparedStatement statement;
        try {
            Connection conn = ConnectionFactory.connect();
            statement = conn.prepareStatement(SQL);

            statement.setString(1, a.getNotes());
            statement.setLong(2, a.getBalance());
            statement.setInt(3, a.getId_client());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException ex) {

        }

    }

    @Override
    public void delete(Account a) {
        final String SQL = " delete from ACCOUNT where id=?";
        PreparedStatement statement = null;
        try {
            Connection conn = ConnectionFactory.connect();
            statement = conn.prepareStatement(SQL);
            statement.setInt(1, a.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException ex) {

        }

    }

    @Override
    public void update(Account a) {
        final String SQL = "update ACCOUNT set notes=?, balance=? where id=?";
        PreparedStatement statement = null;
        try {
            Connection conn = ConnectionFactory.connect();
            statement = conn.prepareStatement(SQL);
            statement.setString(1, a.getNotes());
            statement.setLong(2, a.getBalance());
            statement.setInt(3, a.getId());

        } catch (SQLException ex) {
        }
    }

    @Override
    public Account findById(Integer id) {
        final String SQL = "select * from ACCOUNT where id = ?";
        Account result = null;
        PreparedStatement statement = null;
        try {
            Connection conn = ConnectionFactory.connect();
            statement = conn.prepareStatement(SQL);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery(SQL);
            if (rs.next()) {
                result = new Account();
                result.setId(rs.getInt("Id"));
                result.setBalance(rs.getLong("Balance"));
                result.setNotes(rs.getString("Notes"));
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Account> findAll() {
        final String SQL = "select * from ACCOUNT";
        PreparedStatement statement = null;
        Account result = null;
        LinkedList<Account> list = new LinkedList<>();
        try {
            Connection conn = ConnectionFactory.connect();
            statement = conn.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                result = new Account();
                result.setId(rs.getInt("id"));
                result.setBalance(rs.getLong("Balance"));
                result.setNotes(rs.getString("Notes"));

                list.add(result);
            }

            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            //throw new DataAccessException(ex);
        }

        return list;
    }
}
