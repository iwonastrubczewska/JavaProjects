package projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;


public class Centrum {
//Listy banków, firm i p³atnoœci.
	LinkedList<Bank> Banki = new LinkedList<Bank>();
	LinkedList<Firma> Firmy = new LinkedList<Firma>();
	LinkedList<Platnosc> Platnosci = new LinkedList<Platnosc>();
	private static int nrBanku=1;

	public void dodajBank(Bank b){
		Banki.add(b);
	}
	public void stworzBank() throws FileNotFoundException//Dodawanie banku za pomoc¹ przycisku "dodaj bank"
	{
		String nazwa="Nowy Bank ";
		StringBuilder tmp = new StringBuilder(nazwa);
		tmp.append(Integer.toString(nrBanku));
		nazwa = tmp.toString();//Tworzenie nowej nazwy banku dla ka¿dego wywo³ania metody, tak aby nazwy siê nie powtarza³y. (u¿ycie static nrBanku)
		nrBanku++;
		Bank banktmp=new Bank(nazwa);
		this.dodajBank(banktmp);
		banktmp.dodajKlientow();//Wywo³ujemy metodê klasy bank która tworzy listê klientów tego banku
	}
	public void wczytajBanki(String nazwaPliku) throws FileNotFoundException
	{
		//wczytywanie banków z pliku
		String nazwaBanku;
		File file = new File(nazwaPliku);
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine())
		{
			if(scanner.hasNextLine())
			{
				nazwaBanku=scanner.nextLine();
				Bank banktmp=new Bank(nazwaBanku);
				this.dodajBank(banktmp);//dodawanie banku do listy banków
				banktmp.dodajKlientow();//Wywo³ujemy metodê klasy bank która tworzy listê klientów tego banku
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
				krs=scanner.nextLine();//wczytujemy liniê po linii numery KRS znajduj¹ce siê w pliku
				switch(ktorafirma%3)//Tworzymy obiekty firm, tak aby na liœcie znalaz³y siê firmy ka¿dego rodzaju. 
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
			if(b.czyZawiera(p.getNrKarty()))//Wywo³ujemy metodê czyZawiera z klasy Bank, która mówi czy dany bank zawiera w swoich zasobach kartê o zadanym numerze. 
			{
				tmpBank=b;//Je¿eli zawiera to przypisujemy go naszemu bankowi pomocniczemu, którego bêdziemy u¿ywaæ w dalszej czêœci metody.
			}
		}
		if(tmpBank.czyZaakceptowana(p))//Bank weryfikuje czy mo¿na zaakceptowaæ p³atnoœæ.
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
