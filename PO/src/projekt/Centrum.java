package projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;


public class Centrum {
//Listy bank�w, firm i p�atno�ci.
	LinkedList<Bank> Banki = new LinkedList<Bank>();
	LinkedList<Firma> Firmy = new LinkedList<Firma>();
	LinkedList<Platnosc> Platnosci = new LinkedList<Platnosc>();
	private static int nrBanku=1;

	public void dodajBank(Bank b){
		Banki.add(b);
	}
	public void stworzBank() throws FileNotFoundException//Dodawanie banku za pomoc� przycisku "dodaj bank"
	{
		String nazwa="Nowy Bank ";
		StringBuilder tmp = new StringBuilder(nazwa);
		tmp.append(Integer.toString(nrBanku));
		nazwa = tmp.toString();//Tworzenie nowej nazwy banku dla ka�dego wywo�ania metody, tak aby nazwy si� nie powtarza�y. (u�ycie static nrBanku)
		nrBanku++;
		Bank banktmp=new Bank(nazwa);
		this.dodajBank(banktmp);
		banktmp.dodajKlientow();//Wywo�ujemy metod� klasy bank kt�ra tworzy list� klient�w tego banku
	}
	public void wczytajBanki(String nazwaPliku) throws FileNotFoundException
	{
		//wczytywanie bank�w z pliku
		String nazwaBanku;
		File file = new File(nazwaPliku);
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine())
		{
			if(scanner.hasNextLine())
			{
				nazwaBanku=scanner.nextLine();
				Bank banktmp=new Bank(nazwaBanku);
				this.dodajBank(banktmp);//dodawanie banku do listy bank�w
				banktmp.dodajKlientow();//Wywo�ujemy metod� klasy bank kt�ra tworzy list� klient�w tego banku
			}
		}
		scanner.close();
	}
	public void wczytajFirmy(String nazwaPliku) throws FileNotFoundException
	{
		String krs;
		File file = new File(nazwaPliku);
		Scanner scanner = new Scanner(file);

		int ktorafirma=0;//
		while(scanner.hasNextLine())
		{
			if(scanner.hasNextLine())
			{
				krs=scanner.nextLine();//wczytujemy lini� po linii numery KRS znajduj�ce si� w pliku
				switch(ktorafirma%3)//Tworzymy obiekty firm, tak aby na li�cie znalaz�y si� firmy ka�dego rodzaju. 
				{
					case 0: 
						{
							this.dodajFirme(new Sklep(krs));
							break;
						}
					case 1: 
						{
							this.dodajFirme(new ZakladUslugowy(krs));
							break;
						}
					default: this.dodajFirme(new FirmaTransportowa(krs));		
				}	
			}
			ktorafirma++;	
		}
		scanner.close();
		
	}

	
	private void dodajPlatnosc(Platnosc p)
	{
		Platnosci.add(p);
	}
	
	public boolean autoryzacja(Platnosc p){
		Bank tmpBank=new Bank();//tworzymy bank pomocniczy
		for( Bank b : Banki)
		{
			if(b.czyZawiera(p.getNrKarty()))//Wywo�ujemy metod� czyZawiera z klasy Bank, kt�ra m�wi czy dany bank zawiera w swoich zasobach kart� o zadanym numerze. 
			{
				tmpBank=b;//Je�eli zawiera to przypisujemy go naszemu bankowi pomocniczemu, kt�rego b�dziemy u�ywa� w dalszej cz�ci metody.
			}
		}
		if(tmpBank.czyZaakceptowana(p))//Bank weryfikuje czy mo�na zaakceptowa� p�atno��.
		{
			p.setStatus("Transakcja zaakceptowana");
			dodajPlatnosc(p);
			return true;
			
		}
		else
		{
			p.setStatus("Transakcja odrzucona");
			dodajPlatnosc(p);
			return false;	
		}	
	}
	
	public Bank ZnajdzBankZawierajacy(String nr)
	{
		for(Bank b: Banki)
		{
			if(b.czyZawiera(nr)) return b;
		}
		return null;
		
	}
	
	public Firma wyszukajFirme(String KRS)
	{
		for(Firma f: Firmy)
		{
			if(f.getKRS().equals(KRS)) return f;
		
		}
		return null;
		
	}
	
	
	public  LinkedList<Bank> getBanki(){
		return Banki;
	}
	public void dodajFirme(Firma f){
		Firmy.add(f);
	}
	
	public  LinkedList<Firma> getFirmy(){
		return Firmy;
	}
	
	public  LinkedList<Platnosc> getPlatnosci(){
		return Platnosci;
	}
	
	
	public void zapiszFirmy(LinkedList<Firma> Firmy) throws FileNotFoundException{ 

	    PrintWriter zapis= new PrintWriter("Firmy.txt");
	     
	     for(Firma f : Firmy){
	            
	    	 	zapis.println(f.getKRS());
	            }
	        zapis.close();
	}
	
	public void zapiszBanki(LinkedList<Bank> Banki) throws FileNotFoundException{
		
			PrintWriter zapis= new PrintWriter("Banki.txt");
	     
	     for(Bank b : Banki){
	            
	    	 	zapis.println(b.getNazwa());
	    	 	
	    	 	        
	            }
	        zapis.close();
	}
	public void zapiszPlatnosci(LinkedList<Platnosc> Platnosci) throws FileNotFoundException
	{ 

	    PrintWriter zapis= new PrintWriter("Platnosci.txt");
	     
	     for(Platnosc p : Platnosci){
	            
	    	 	zapis.println(p.getNrKarty());
	    	 	zapis.println(p.getKRS());
	    	 	zapis.println(p.getKwota());
	    	 	zapis.println(p.getWaluta());
	    	 	zapis.println(p.getData());
	    	 	zapis.println(p.getStatus());
	    	 	
	            }
	        zapis.close();
	}
}
