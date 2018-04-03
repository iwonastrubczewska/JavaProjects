package dao;

import main.ConnectionFactory;
import models.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface ClientDao {

    void save(Client t) throws SQLException;
    void delete (Client t) throws SQLException;
    void update (Client t);
    Client findById(Integer id);
    List<Client> findAll();

}
