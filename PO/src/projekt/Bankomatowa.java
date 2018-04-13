package projekt;

import java.util.Random;

//Transakcji kart¹ bankomatow¹ mo¿na dokonaæ, gdy kwota nie przekracza srodków na koncie.
public class Bankomatowa extends Karta{

	private double saldo;
	public Bankomatowa(){
		super();
		Random generator = new Random();
		int mnoznik=generator.nextInt(120000);
		saldo=generator.nextDouble()*mnoznik; //losujemy liczbê typu double 0-1 , po czym mno¿ymy j¹ przez mno¿nik, wynik jest saldem konta
		
	}
	public Bankomatowa(String nr){
		super(nr);
		Random generator = new Random();
		int mnoznik=generator.nextInt(120000);
		saldo=generator.nextDouble()*mnoznik;
	}
	public String getRodzaj()
	{
		return "Bankomatowa";
	}
	public double getSaldo()
	{
		return saldo; 
	}
	public void setSaldo(double kwota)
	{
		saldo=saldo+kwota;
	}
	public boolean sprawdzWyplacalnosc(double kwota)
	{
		if(kwota>(saldo))return false;
		else 
		{
			saldo=saldo-kwota;
			return true; 
		}
	}
}
