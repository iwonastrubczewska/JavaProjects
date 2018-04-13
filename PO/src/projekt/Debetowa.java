package projekt;

import java.util.Random;

/*Karta debetowa w konstruktorze tworzy obiekt klasy karta i rozszerza go o 
zmienn� saldo. W metodzie czyWyplacalna sprawdzamy czy karta posiada dostateczna 
ilosc srodkow na koncie aby dokonac transakcji.
Karta debetowa cechuje sie tym, �e mo�na dokona� transakcji na kwot� 
mniejsz� lub r�wn� aktualnego saldo + debet==200 z�

*/
public class Debetowa extends Karta{

	private double saldo;
	
	public Debetowa(){
		super();
		Random generator = new Random();
		int mnoznik=generator.nextInt(120000);
		saldo=generator.nextDouble()*mnoznik;
	}
	
	public Debetowa(String nr){
		super(nr);
		Random generator = new Random();
		int mnoznik=generator.nextInt(120000);
		saldo=generator.nextDouble()*mnoznik;

	}
	public String getRodzaj()
	{
		return "Debetowa";
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
		if(kwota>(saldo+200))return false;
		else 
		{
			saldo=saldo-kwota;
			return true; 
		}
	}
}
