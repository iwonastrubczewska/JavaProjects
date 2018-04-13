package projekt;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Platnosc {

	public Karta k;
	public String waluta, KRS, status, data;
	public double kwota;
	
	public Platnosc(){}
	
	public Platnosc(double kw, String wal, String date, String krs, Karta karta)
	{
		kwota=kw;
		waluta=wal;
		data=date;
		KRS=krs;
		status="W trakcie realizacji.";
		k=karta;//W 
	}
	
	private  double round(double value, int places) {///Metoda która zaokr¹gla liczbê miejsc po  przecinku zmiennej double.
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public String getNrKarty(){
		return k.getNr();
	}
	
	public double getKwota(){
		return round(kwota,2);
	}
	
	public String getWaluta(){
		return waluta;
	}
	
	public String getKRS(){
		return KRS;
	}
	public String getStatus(){
		return status;
	}
	
	public String getData(){
		return data;
	}
	
	public void setStatus(String stat){
		this.status=stat;
	}
	
	public String toString(){
	
		return "[Kwota: "+round(kwota,2)+"] [Waluta: "+waluta+"] [KRS firmy: "+KRS+"] [Data: "+data+ "] [Nr karty: " +k.getNr()+"] [Status: "+status+"]";
		
	}
}
