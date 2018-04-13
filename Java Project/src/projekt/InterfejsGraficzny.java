package projekt;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;


import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class InterfejsGraficzny implements ActionListener {
	 JButton przycisk, przycisk1, przycisk2, przycisk3, przycisk4, przycisk5, przycisk6, przycisk7, przycisk8, przycisk9, przycisk10, przycisk11, przycisk12;
	 
	 
	 JTextArea pole, pole1, pole2;
	 static Centrum c;
	 static Scanner sc;
	 static Platnosc platnosctmp;
		
	public static void main(String[] args) throws FileNotFoundException{

		c = new Centrum();
		sc=new Scanner(System.in);
		
			c.wczytajBanki("Banki.txt");
			c.wczytajFirmy("Firmy.txt");
		
			
		InterfejsGraficzny grafic = new InterfejsGraficzny();
		
		grafic.dzialanie();
		
	}
	
	public void dzialanie(){
	
		JFrame ramka = new JFrame();
		JPanel panel= new JPanel();
		JPanel panel2 = new JPanel();
		
		
	
		panel2.setBackground(Color.GRAY);
		
		ramka.getContentPane().add(BorderLayout.EAST,panel2);
		
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		
	
		
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		przycisk = new JButton("Dodaj Bank ");
		przycisk.addActionListener(new Przycisk());
		
		
		przycisk1 = new JButton("Odbierz transakcjê ");
		przycisk1.addActionListener(new Przycisk1());
		
		przycisk2 = new JButton("Zapisz listê Banków do pliku");
		przycisk2.addActionListener(new Przycisk2());
	
		przycisk3 = new JButton("Zapisz listê Firm do pliku");
		przycisk3.addActionListener(new Przycisk3());
		
		przycisk4 = new JButton("Zapisz listê P³atnoœci do pliku");
		przycisk4.addActionListener(new Przycisk4());
		
		przycisk5 = new JButton(" Wyszukiwanie transakcji na kwotê mniejsz¹ ni¿ 500 ");
		przycisk5.addActionListener(new Przycisk5());
		
		przycisk6 = new JButton("Wyszukiwanie tylko transakcji zaakceptowanych ");
		przycisk6.addActionListener(new Przycisk6());
		
		przycisk7 = new JButton(" Wyszukiwanie p³atnoœci tylko w walucie EURO ");
		przycisk7.addActionListener(new Przycisk7());
		
		przycisk8 = new JButton(" Wyszukiwanie transakcji które zosta³y odrzucone tylko przy kwotach mniejszych ni¿ 1000");
		przycisk8.addActionListener(new Przycisk8());
		
		przycisk9 = new JButton("Wyszukiwanie transakcji które zosta³y zaakceptowane tylko przy kwotach wiêkszych ni¿ 4000 ");
		przycisk9.addActionListener(new Przycisk9());
		
		przycisk10 = new JButton("Wyszukiwanie transakcji w walucie EUR lub USD");
		przycisk10.addActionListener(new Przycisk10());
		
		przycisk11 = new JButton("Wyszukiwanie transakcji wiêkszych ni¿ 2000 euro lub zaakceptowanych transakcji w walucie USD ");
		przycisk11.addActionListener(new Przycisk11());
		
		przycisk12 = new JButton("Transakcje zaakceptowane przy kwocie ponad 5000 euro lub odrzucone przy mniejszej ni¿ 2000 euro ");
		przycisk12.addActionListener(new Przycisk12());
		

		
		
		
		
		
		panel2.add(przycisk);
		panel2.add(przycisk2);
		panel2.add(przycisk3);
		panel2.add(przycisk4);
		panel2.add(przycisk5);
		panel2.add(przycisk6);
		panel2.add(przycisk7);
		panel2.add(przycisk8);
		panel2.add(przycisk9);
		panel2.add(przycisk10);
		panel2.add(przycisk11);
		panel2.add(przycisk12);
	
		
		
		
		
		pole = new JTextArea(50,50);
		pole.setLineWrap(true);
		
		pole1 = new JTextArea(50,50);
		pole1.setLineWrap(true);
		
		
		
		JScrollPane przewijanie = new JScrollPane(pole);
		przewijanie.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		przewijanie.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel.add(przewijanie);
		

		JScrollPane przewijanie1 = new JScrollPane(pole1);
		przewijanie1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		przewijanie1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel2.add(przewijanie1);
		
		
		ramka.getContentPane().add(BorderLayout.NORTH, przycisk1);
		ramka.getContentPane().add(BorderLayout.CENTER,panel);
		
		//ramka.getContentPane().add(BorderLayout.AFTER_LAST_LINE, przycisk2);
		
		
		
		ramka.setSize(500, 300);
		ramka.setVisible(true);
		przycisk1.setBounds(40,40,100,100);
		przycisk2.setBounds(40,40,30,30); // ustawianie rozmiaru przycisku
		//przycisk1.setVisible(true);
	
		}
	
	
	
	 static Platnosc stworzPlatnosc() throws FileNotFoundException
	{
		//LOSOWANIE FIRMY KTORA STWORZY PLATNOSC
			Random gen=new Random();
			int ktorafirma=gen.nextInt((c.getFirmy()).size());
			//LOSOWANIE KARTY KTORA WYKONA PLATNOSC
			//1.losujemy bank
			int ktorybank=gen.nextInt((c.getBanki()).size());
			Bank banktmp=(c.getBanki()).get(ktorybank);
			//2.losujemy klienta
			int ktoryklient=gen.nextInt((banktmp.getKlienci()).size());
			Klient klienttmp=(banktmp.getKlienci()).get(ktoryklient);
			//3.losujemy karte 
		int ktorakarta=gen.nextInt((klienttmp.getKarty()).size());
		Karta kartatmp=(klienttmp.getKarty().get(ktorakarta));
		Platnosc platnosctmp=(c.getFirmy()).get(ktorafirma).wykonajTransakcje(kartatmp);
		return platnosctmp;
}
	 
	 
	 
	 class Przycisk1 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		try {
			c.autoryzacja(platnosctmp=stworzPlatnosc());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		przycisk1.setText("Odbierz transakcjê  ");
		
		pole.append(" \n");
		pole.append("STATUS: W realizacji  \n");
	
		pole.append("Rodzaj Karty: " + c.ZnajdzBankZawierajacy(platnosctmp.getNrKarty()).getKarta(platnosctmp.getNrKarty()).getRodzaj() +"\n");
		pole.append("Numer Karty: " +platnosctmp.getNrKarty()+"\n");
		pole.append("Numer KRS: " +platnosctmp.getKRS() +"\n");
		pole.append("" + c.wyszukajFirme(platnosctmp.getKRS()).getRodzaj()+"\n");
		pole.append("Kwota: " +platnosctmp.getKwota());
		pole.append("  " +platnosctmp.getWaluta() + "\n");
		pole.append("" +platnosctmp.getData()+"\n");
		pole.append("Trwa autoryzacja... \n");
		pole.append("STATUS : " +platnosctmp.getStatus() +"\n" );
				
	}
}

	 class Przycisk2 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		
		 		przycisk2.setText("Zapisano do pliku");
		 		try {
					c.zapiszBanki(c.getBanki());
				} catch (FileNotFoundException e) {
				
					e.printStackTrace();
				}
		 		
		 	}
	 }
	 class Przycisk3 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		
		 		przycisk3.setText("Zapisano do pliku");
		 		try {
					c.zapiszFirmy(c.getFirmy());
				} catch (FileNotFoundException e) {
			
					e.printStackTrace();
				}
		 		
		 	}
	 }
	 
	 class Przycisk4 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		 		
		 		przycisk4.setText("Zapisano do pliku");
		 		try {
					c.zapiszPlatnosci(c.getPlatnosci());
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
		 		
		 	}
	 }
	 
	 class Przycisk5 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		
		 		double kwota=500;
		 		
		 		pole1.setText(" ");
		 		int pom=0;
				for(Platnosc p: c.getPlatnosci())
				{
					if(p.getKwota()<kwota)
					{
					pom++;
					pole1.append(" \n");
					pole1.append("Rodzaj Karty: " + c.ZnajdzBankZawierajacy(platnosctmp.getNrKarty()).getKarta(platnosctmp.getNrKarty()).getRodzaj() +"\n");
					pole1.append("Numer Karty: " +p.getNrKarty()+"\n");
					pole1.append("Numer KRS: " +p.getKRS() +"\n");
					pole1.append("" + c.wyszukajFirme(platnosctmp.getKRS()).getRodzaj()+"\n");
					pole1.append("Kwota: " +p.getKwota());
					pole1.append("  " +p.getWaluta() + "\n");
					pole1.append("" +p.getData()+"\n");
					pole1.append("STATUS : " +p.getStatus() +"\n" );
					
					}	
				}
				if(pom==0)pole1.setText(" Brak takich transakcji.");
				
		 		
		 	}
	 }
	 
	 
	 class Przycisk6 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		 		pole1.setText(" ");
		 		int pom=0;
		 		for(Platnosc p: c.getPlatnosci())
				{
		 			String status= "Transakcja zaakceptowana";
					if(p.getStatus().equals(status))
					{pom++;
					pole1.append(" \n");
					pole1.append("Rodzaj Karty: " + c.ZnajdzBankZawierajacy(platnosctmp.getNrKarty()).getKarta(platnosctmp.getNrKarty()).getRodzaj() +"\n");
					pole1.append("Numer Karty: " +p.getNrKarty()+"\n");
					pole1.append("Numer KRS: " +p.getKRS() +"\n");
					pole1.append("" + c.wyszukajFirme(platnosctmp.getKRS()).getRodzaj()+"\n");
					pole1.append("Kwota: " +p.getKwota());
					pole1.append("  " +p.getWaluta() + "\n");
					pole1.append("" +p.getData()+"\n");
					pole1.append("STATUS : " +p.getStatus() +"\n" );
					}
				}
		 		if(pom==0)pole1.setText(" Brak takich transakcji.");
		 		
		 	}
	 }
	 
	 class Przycisk7 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		 		pole1.setText(" ");
		 		String waluta= "EUR";
		 		int pom=0;
		 		for(Platnosc p: c.getPlatnosci())
				{
					if(p.getWaluta().equals(waluta))
					{
						pom++;
					pole1.append(" \n");
					pole1.append("Rodzaj Karty: " + c.ZnajdzBankZawierajacy(platnosctmp.getNrKarty()).getKarta(platnosctmp.getNrKarty()).getRodzaj() +"\n");
					pole1.append("Numer Karty: " +p.getNrKarty()+"\n");
					pole1.append("Numer KRS: " +p.getKRS() +"\n");
					pole1.append("" + c.wyszukajFirme(platnosctmp.getKRS()).getRodzaj()+"\n");
					pole1.append("Kwota: " +p.getKwota());
					pole1.append("  " +p.getWaluta() + "\n");
					pole1.append("" +p.getData()+"\n");
					pole1.append("STATUS : " +p.getStatus() +"\n" );
					}
				}
		 		if(pom==0)pole1.setText(" Brak takich transakcji.");
		 	}
	 }
	 

	 class Przycisk8 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		 
		 		int pom=0;
		 		String status = "Transakcja odrzucona";
		 		double kwota = 1000;
		 		pole1.setText(" ");
		 		for(Platnosc p: c.getPlatnosci())
				{
					if(p.getKwota()<kwota&&p.getStatus().equals(status))
					{pom++;
						pole1.append(" \n");
						pole1.append("Rodzaj Karty: " + c.ZnajdzBankZawierajacy(platnosctmp.getNrKarty()).getKarta(platnosctmp.getNrKarty()).getRodzaj() +"\n");
					pole1.append("Numer Karty: " +p.getNrKarty()+"\n");
					pole1.append("Numer KRS: " +p.getKRS() +"\n");
					pole1.append("" + c.wyszukajFirme(platnosctmp.getKRS()).getRodzaj()+"\n");
					pole1.append("Kwota: " +p.getKwota());
					pole1.append("  " +p.getWaluta() + "\n");
					pole1.append("" +p.getData()+"\n");
					pole1.append("STATUS : " +p.getStatus() +"\n" );
					}
				}
		 		if(pom==0)pole1.setText(" Brak takich transakcji.");
		 		
		 	}
	 }
	 

	 class Przycisk9 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		 
		 		String status = "Transakcja zaakceptowana";
		 		double kwota = 4000;
		 		pole1.setText(" ");
		 		int pom=0;
		 		for(Platnosc p: c.getPlatnosci())
				{
					if(p.getKwota()>kwota&&p.getStatus().equals(status))
					{pom++;
						pole1.append(" \n");
						pole1.append("Rodzaj Karty: " + c.ZnajdzBankZawierajacy(platnosctmp.getNrKarty()).getKarta(platnosctmp.getNrKarty()).getRodzaj() +"\n");
					pole1.append("Numer Karty: " +p.getNrKarty()+"\n");
					pole1.append("Numer KRS: " +p.getKRS() +"\n");
					pole1.append("" + c.wyszukajFirme(platnosctmp.getKRS()).getRodzaj()+"\n");
					pole1.append("Kwota: " +p.getKwota());
					pole1.append("  " +p.getWaluta() + "\n");
					pole1.append("" +p.getData()+"\n");
					pole1.append("STATUS : " +p.getStatus() +"\n" );
					}
				}
		 		if(pom==0)pole1.setText(" Brak takich transakcji.");
		 	}
	 }
	 
	 class Przycisk10 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		 
		 		int pom=0;
		 		String waluta1 ="EUR";
		 		String waluta2 = "USD";
		 		pole1.setText(" ");
		 		for(Platnosc p: c.getPlatnosci())
				{
					if((p.getWaluta().equals(waluta1)) || (p.getWaluta().equals(waluta2)))
					{pom++;
						pole1.append(" \n");
						pole1.append("Rodzaj Karty: " + c.ZnajdzBankZawierajacy(platnosctmp.getNrKarty()).getKarta(platnosctmp.getNrKarty()).getRodzaj() +"\n");
					pole1.append("Numer Karty: " +p.getNrKarty()+"\n");
					pole1.append("Numer KRS: " +p.getKRS() +"\n");
					pole1.append("Kwota: " +p.getKwota());
					pole1.append("" + c.wyszukajFirme(platnosctmp.getKRS()).getRodzaj()+"\n");
					pole1.append("  " +p.getWaluta() + "\n");
					pole1.append("" +p.getData()+"\n");
					pole1.append("STATUS : " +p.getStatus() +"\n" );
					}
				}if(pom==0)pole1.setText(" Brak takich transakcji.");
				}
		 		
		 	}
	 
	 class Przycisk11 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		 
		 		int pom=0;
				String waluta1 ="EUR";
		 		String waluta2 = "USD";
		 		double kwota = 2000;
		 		String status = "Transakcja zaakceptowana";
		 		pole1.setText(" ");
		 		for(Platnosc p: c.getPlatnosci())
				{
					if (((p.getWaluta().equals(waluta1))&&(p.getKwota()>kwota))|| ((p.getWaluta().equals(waluta2))&&(p.getStatus().equals(status))))
					{
						pom++;
						pole1.append(" \n");
						pole1.append("Rodzaj Karty: " + c.ZnajdzBankZawierajacy(platnosctmp.getNrKarty()).getKarta(platnosctmp.getNrKarty()).getRodzaj() +"\n");
					pole1.append("Numer Karty: " +p.getNrKarty()+"\n");
					pole1.append("Numer KRS: " +p.getKRS() +"\n");
					pole1.append("" + c.wyszukajFirme(platnosctmp.getKRS()).getRodzaj()+"\n");
					pole1.append("Kwota: " +p.getKwota());
					pole1.append("  " +p.getWaluta() + "\n");
					pole1.append("" +p.getData()+"\n");
					pole1.append("STATUS : " +p.getStatus() +"\n" );
					}
				}
		 		if(pom==0)pole1.setText(" Brak takich transakcji.");
				}
		 	
				}
	 
	 class Przycisk12 implements ActionListener {
		 	public void actionPerformed(ActionEvent zdarzenie){
		 
		 		int pom=0;
		 		String waluta = "EUR";
		 		double kwota = 2000;
		 		double kwota1 = 5000;
		 		String status = "Transakcja zaakceptowana";
		 		String status1 = "Transakcja odrzucona";
		 		pole1.setText(" ");
		 		for(Platnosc p: c.getPlatnosci())
				{
		 			
					if ((((p.getStatus().equals(status))&&(p.getKwota()>kwota1)) || ((p.getStatus().equals(status1))&&(p.getKwota()<kwota))) && (p.getWaluta().equals(waluta)))
					{pom++;
					pole1.append(" \n");
					pole1.append("Rodzaj Karty: " + c.ZnajdzBankZawierajacy(platnosctmp.getNrKarty()).getKarta(platnosctmp.getNrKarty()).getRodzaj() +"\n");
					pole1.append("Numer Karty: " +p.getNrKarty()+"\n");
					pole1.append("Numer KRS: " +p.getKRS() +"\n");
					pole1.append("" + c.wyszukajFirme(platnosctmp.getKRS()).getRodzaj()+"\n");
					pole1.append("Kwota: " +p.getKwota());
					pole1.append("  " +p.getWaluta() + "\n");
					pole1.append("" +p.getData()+"\n");
					pole1.append("STATUS : " +p.getStatus() +"\n" );
					}
				}
		 		if(pom==0)pole1.setText(" Brak takich transakcji.");
				}
	 }
	 
		 	class Przycisk implements ActionListener {
			 	public void actionPerformed(ActionEvent zdarzenie){
			 		
			 		pole1.setText(" ");
			 		
			 		try {
						c.stworzBank();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			 		
			 		for( Bank b : c.getBanki())
					{
			 			pole1.append(b.getNazwa() + "\n");
			 			
			 		}
			 			
			 		
			 	}
		 	}
	 


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
