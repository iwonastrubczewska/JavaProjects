package projekt;


import java.io.FileNotFoundException;
import java.util.*;

/*Zak³adamy, ¿e banki które wspó³pracuj¹ z naszym centrum obs³uguj¹ polskich klientów
 * dlatego ka¿d¹ transakcjê zamieniaj¹ z danej waluty na PLNY
 * w tym celu zostala stworzona metoda zamienNaPLN.
 * */


public class Bank 
{

	private LinkedList<Klient> Klienci = new LinkedList<Klient>();
	private  String nazwaB; //nazwa banku np PKO;
	
	public Bank()
	{	
	}
	
	public Bank(String nazwa)
	{
		nazwaB=nazwa;
	}
	
	
	public void dodajKlientow() throws FileNotFoundException{

		Random gen=new Random();//losujemy iloœæ klientów
		int iloscklientow=(gen.nextInt(20)+3);
		for (int i=0; i<iloscklientow; i++)
		{
			Klient klienttmp=new Klient();//tworzymy nowego klienta
			this.dodajKlienta(klienttmp);
			klienttmp.wczytajKarty();//przypisujemy klientowi karty.
		}
		
	
					

		//scanner.close();
	}
	
	public String getNazwa()
	{
		return nazwaB;
	}
	
	public void dodajKlienta(Klient k)
	{
		Klienci.add(k);
	}
	
	public void usunKlienta(Klient k)
	{
		Klienci.remove(k);
	}
	
	public LinkedList<Klient> getKlienci()
	{
		return Klienci;
	}
	public Karta getKarta(String nr)
	{
		for(Klient k:Klienci)
		{
			for(Karta kart:  k.getKarty())
			{
				if(kart.getNr().equals(nr)) return kart;
			}
		}
		return null;
	}
	public boolean czyZaakceptowana(Platnosc p)// Mteoda zwraca true je¿eli bank wyda³ zgode na zaakceptowanie transakcji 
	{	
		for( Klient k : Klienci)
		{
			for( Karta karta : k.getKarty())
			{
				if(karta.getNr().equals(p.getNrKarty()))
				{
					if(karta.sprawdzWyplacalnosc(zamienNaPLN(p.getKwota(),p.getWaluta())))return true;
					else return false;
				}
			}
		}
		return false;
		
	}
	public boolean czyZawiera(String nr)//Metoda zwraca wartoœæ true je¿eli dany bank zawiera w swoich zasobach karte o danym numerze
	{
		int pom=0;
		for( Klient k : Klienci)
		{
			for( Karta karta : k.getKarty())
			{
				if(nr.equals(karta.getNr())) pom=1;
			}
		}
		if(pom==1) return true;
		else return false; 
	}
	private double zamienNaPLN(double kwota, String waluta)//Metoda przelicza waluty na potrzeby wydania decyzji dotycz¹cej autoryzacji p³atnoœci. 
	{
		switch (waluta)
		{
		case "EUR": return (kwota*4.1882);
		case "USD": return (kwota*3.7323);
		case "CHF": return (kwota*3.8433);
		case "UAH": return (kwota*0.1421);
		default : return kwota;
		}
	}
}

