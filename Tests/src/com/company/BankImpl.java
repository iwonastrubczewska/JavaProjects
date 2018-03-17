package com.company;

import java.util.*;

public class BankImpl implements Bank {
    private ArrayList<Account> list;


    public BankImpl()
    {
        list = new ArrayList<Account>();
    }

    public Integer findAccount(String name, String address)
    {
        for(Account a: list){
            if(a.getName().equals(name) && a.getAddress().equals(address)) return a.getId();
        } return null;

    }

    public Integer createAccount(String name, String address)
    {
        Integer tmp = findAccount(name,address);
        if(tmp!=null)
            return tmp;
        else {
            Account a = new Account(name,address,0);
            list.add(a);
            return a.getId();
        }
    }
    public void deposit(Integer id, long amount) throws AccountIdException {
        for (Account a : list) {
            if (id.equals(a.getId()))
            {
                a.setBalance(a.getBalance() + amount);
                return;
            }
        }  throw new AccountIdException();
    }

    public long getBalance(Integer id) throws AccountIdException
    {
        for(Account a : list)
            if(id.equals(a.getId())) return a.getBalance();

        throw new  AccountIdException();
    }


    public void withdraw(Integer id, long amount) throws AccountIdException,InsufficientFundsException
    {
        if(getBalance(id)-amount<0)
            throw new InsufficientFundsException();
        else
            for(Account a: list)
                if(id.equals(a.getId())) {
                    a.setBalance(a.getBalance() - amount);
                    return;
                }

        throw new AccountIdException();

    }

    public void transfer(Integer idSource, Integer idDestination, long amount) throws AccountIdException,InsufficientFundsException
    {
        Integer source = null;
        Integer destination = null;

        for(Account a: list)
            if(idSource==a.getId()) source=a.getId();
        for(Account a: list)
            if(idDestination==a.getId()) destination=a.getId();

        if(source==null || destination==null)
            throw new AccountIdException();
        else if (getBalance(idSource) - amount < 0)
            throw new InsufficientFundsException();
        else {
            withdraw(source, amount);
            deposit(destination, amount);
        }
    }

    public ArrayList<Account> getList(){

        return list;

    }

}






