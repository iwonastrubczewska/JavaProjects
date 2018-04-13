package projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Date;

abstract class Firma {

	LinkedList<Platnosc> Platnosci= new LinkedList<Platnosc>();
	LinkedList<String> Waluty=new LinkedList<String>();
	private String KRS, waluta, data;
	private double kwota;

	public Firma(){
		
	}
	
	public Firma(String krs){
	
		KRS=krs;
	}
	public Platnosc wykonajTransakcje(Karta k) throws FileNotFoundException
	{
		//losowanie kwoty
		Random gen=new Random();
		int mnoznik=gen.nextInt(11000);
		kwota=(gen.nextDouble()*mnoznik)+1; // +1 ¿eby kwota p³atnoœci nie by³a równa 0
		//losowanie waluty
		this.wczytajWaluty();
		int ktorawaluta=gen.nextInt(Waluty.size());
		waluta=Waluty.get(ktorawaluta);
		//generowanie gaty
		Date date = new Date();		
		data=date.toString();
		//stworzenie obiektu p³atnoœæ.P³atnoœæ jeszcze musi mieæ przypisany nr karty. To dzieje sie w konstruktorze p³atnoœci. 
		Platnosc ptmp= new Platnosc(kwota, waluta, data, this.getKRS(),k);
		this.dodajPlatnosc(ptmp);
		return ptmp;
	}
	private void wczytajWaluty() throws FileNotFoundException
	{
		String wal;
		File file = new File("Waluty.txt");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine())
		{
			if(scanner.hasNextLine())
			{
				wal=scanner.nextLine();
				Waluty.add(wal);
			}
		}
		scanner.close();
	}
	abstract String getRodzaj();
	public void dodajPlatnosc(Platnosc p){
		Platnosci.add(p);
	}
	
	public double getKwota(){
	
		return kwota;
		
	}
	public String getWaluta(){
		return waluta;
	}
	
	public String getKRS(){
		return KRS;
	}
	
	
	public String getData(){
		return data;
	}
	
	public void setKwota(double kw){
		this.kwota=kw;
	}
	public void setWaluta(String wal){
		this.waluta=wal;
	}
	
	public void zapiszdopliku(LinkedList<Platnosc> Platnosci) throws FileNotFoundException{
		
		
		     PrintWriter zapis= new PrintWriter("Platnosci.txt");
		     
		     for(Platnosc p : Platnosci){
		            
		    	 	zapis.print(p.getNrKarty());
		            zapis.print(p.getKwota());
		            zapis.print(p.getWaluta());
		            zapis.print(p.getData());
		            zapis.print(p.getStatus());
		            
		            }
		        zapis.close();
		}
		
	}

