package dao;

import models.Client;
import java.sql.SQLException;
import java.util.List;


public interface ClientDao {

    void save(Client c) throws SQLException;
    void delete (Client c) throws SQLException;
    void update (Client c);
    Client findById(Integer id);
    List<Client> findAll();

}
