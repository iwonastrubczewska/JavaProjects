
import org.junit.BeforeClass;
import org.junit.Test;

public class BankImplTests {

    private static  BankImpl bank;

    @BeforeClass
    public static void begin() {

        bank = new BankImpl();
        bank.createAccount("Iwona Strubczewska", "Sochocin");
        bank.createAccount("Jan Kowalski", "Warszawa");
        bank.createAccount("Adam Nowak", "Gdynia");

    }

    @Test
    public void test1()
    {
        assert bank.getList().get(0).getName().equals("Iwona Strubczewska");
        assert bank.getList().get(0).getAddress().equals("Sochocin");
        assert bank.getList().get(1).getName().equals("Jan Kowalski");
        assert bank.getList().get(1).getAddress().equals("Warszawa");
        assert bank.getList().get(2).getName().equals("Adam Nowak");
        assert bank.getList().get(2).getAddress().equals("Gdynia");

    }

    @Test
    public void test2()
    {
        assert bank.getList().get(0).getId()==0;
        assert bank.getList().get(1).getId()==1;
        assert bank.getList().get(2).getId()==2;

    }

    @Test
    public void test3()
    {
        long amount0 = bank.getList().get(0).getBalance();
        long amount1 = bank.getList().get(1).getBalance();
        long amount2 = bank.getList().get(2).getBalance();

        bank.deposit(0,1000);
        bank.deposit(1,2000);
        bank.deposit(2,3000);

        assert bank.getBalance(0)==(1000+amount0) ;
        assert bank.getBalance(1)==(2000+amount1) ;
        assert bank.getBalance(2)==(3000+amount2) ;
    }

    @Test
    public void test4()
    {
        long transfer = bank.getList().get(0).getBalance();
        bank.deposit(0,1000);
        bank.transfer(0,1,1000);
        assertEquals(transfer, bank.getList().get(0).getBalance());
    }

    @Test
    public void test5()
    {
        try {bank.deposit(10,3000);}
        catch(Bank.AccountIdException exception)
        {
            assert true;
        }
    }

    @Test
    public void test6()
    {
        try {bank.transfer(0,10,30);}
        catch(Bank.AccountIdException exception)
        {
            assert true;
        }
    }
    @Test
    public void test7()
    {
        try {bank.withdraw(0,25000);}
        catch(Bank.InsufficientFundsException exception)
        {
            assert true;
        }
    }

    @Test
    public void test8()
    {
        try {bank.withdraw(10,1000);}
        catch(Bank.AccountIdException exception)
        {
            assert true;
        }
    }

    @Test
    public void test9()
    {
        try {bank.getBalance(10);}
        catch(Bank.AccountIdException exception)
        {
            assert true;
        }
    }

    @Test
    public void test10()
    {
        bank.getList().get(0).setBalance(1000);
        try {bank.transfer(0,1,100000);}
        catch(Bank.InsufficientFundsException exception)
        {
            assert true;
        }
    }



}







