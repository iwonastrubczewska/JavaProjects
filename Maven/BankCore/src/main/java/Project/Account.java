package Project;


public class Account {
    private String name, address;
    private Integer id;
    private long balance;
    private static Integer num=0;

    public Account() { }

    public Account (String name, String address, long balance)
    {
        this.name = name;
        this.address = address;
        this.balance = balance;
        this.id = num;
        num ++;

    }

    public String getName( )
    {
        return name;
    }

    public String getAddress( )
    {
        return address;
    }

    public long getBalance( )
    {
        return balance;
    }

    public Integer getId( )
    {
        return id;
    }

    public void setBalance( long amount ){
        this.balance=amount;
    }


}


