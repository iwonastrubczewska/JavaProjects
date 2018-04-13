package projekt;

import java.util.Random;
import java.util.LinkedList;

public class Klient {

	LinkedList<Karta> Karty = new LinkedList<Karta>();
	public Klient(){}
	
	public void wczytajKarty()
	{
		int ilosckart=0;
		int ktorakarta;
		Random generator = new Random();//losujemy ilosc kart
		ilosckart=(generator.nextInt(3)+1);

		for(int i=0; i<ilosckart; i++)
		{
			ktorakarta=generator.nextInt(3);//losujemy ktor¹ karte tworzymy 0-debetowa 1-kredytowa 2-bankomatowa
			switch(ktorakarta){
				case 0:{
					this.dodajKarte(new Debetowa());
					break;
				}
				case 1:{
					this.dodajKarte(new Kredytowa());
					break;
				}
				case 2:{
					this.dodajKarte(new Bankomatowa());
					break;
				}
			}
		} 
	}
	public void dodajKarte(Karta k){
		Karty.add(k);
	}
	
	public boolean czyZawiera(Karta k) // metoda u¿yta przy autoryzacji
	{

		for(int i=0; i<Karty.size(); i++)
		{
			if(Karty.get(i).equals(k))
				return true; 	
		}return false;
	}
	
	public LinkedList<Karta> getKarty()
	{
		return Karty;
	}
	
}
