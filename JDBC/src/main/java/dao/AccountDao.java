package dao;

import models.Account;
import java.util.List;

public interface AccountDao {

    void save(Account a);
    void delete (Account a);
    void update (Account a);
    Account findById(Integer id);
    List<Account> findAll();

}
