package models;

public class Account {

    private Integer id;
    private String notes;
    private long balance;
    private Integer id_client;

    public Account(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", notes='" + notes + '\'' +
                ", balance=" + balance +
                ", id_client=" + id_client +
                '}';
    }
}
