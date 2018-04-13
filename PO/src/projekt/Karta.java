package projekt;

import java.util.*;

abstract class Karta {
	static int nr=1000000000;//nr który slu¿y do tworzenia numerów kart
	
	LinkedList<Platnosc> Platnosci = new LinkedList<Platnosc>();
	
	private String nrKarty;
	
	public Karta()
	{
		nrKarty=Integer.toString(nr);
		nr++;
	}
	
	public Karta(String numer){
		nrKarty=numer;
	}
	
	public String getNr(){
		return nrKarty;
	}
	
	abstract public boolean sprawdzWyplacalnosc(double kwota);//metoda zwraca true je¿eli dana karta ma mo¿liwoœæ zaplaciæ dan¹ kwotê
	abstract public String getRodzaj();
	abstract public double getSaldo();
}
