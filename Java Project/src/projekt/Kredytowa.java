package projekt;

public class Kredytowa extends Karta{

	private double dostepnaKwota;
	public Kredytowa(){
		super();
		dostepnaKwota=10000; //kwota dost�pna na karcie kredytowej pocz�tkowo zawsze wynosi 10000
	}
	
	public Kredytowa(String nr){
		super(nr);
		dostepnaKwota=10000;
	}
	public String getRodzaj()
	{
		return "Kredytowa";
	}
	public double getSaldo(){
		return dostepnaKwota;
	}
	public boolean sprawdzWyplacalnosc(double kwota)
	{
		if(kwota>dostepnaKwota)return false;
		else 
		{
			dostepnaKwota=dostepnaKwota-kwota;
			return true; 
		}
	}
}
