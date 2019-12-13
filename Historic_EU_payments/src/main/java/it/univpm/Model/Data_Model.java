package it.univpm.Model;

import java.util.Arrays;


/**
 * quasta  classe è il modello dei dati
 * @author gianmarco troni
 * @version 1.4
 *
 */
public class Data_Model {
	//atirbuti
	
    private String Country ="";//nazione di provenienza
    private String NUTS1_ID ="";//ID nazione
    private String NUTS2_ID ="";//ID regione
    private String NUTS2_name ="";//nome regione
    private String Fund ="";//fondo
    private int Year =0;//anno
    private int[] Programming_Period = {0,0};//periodo progammato
    private int EU_Payment_annual =0;//pagamento annuale EU
    private int Modelled_annual_expenditure =0;//Spesa annua modellata
    private int Standard_Deviation_of_annual_expenditure =0;//Deviazione standard della spesa annuale
    private int Standard_Error_of_modelled_annual_expenditure =0;//Errore standard della spesa annuale modellata
    
    //metodi
    
    /** 
	    * Class constructor.
	    * costruttore modello vuoto
	     */
    public Data_Model() {
		super();
		this.Country = "";
		this.NUTS1_ID = "";
		this.NUTS2_ID = "";
		this.NUTS2_name ="";
		this.Fund = "";
		this.Year = 0;
		int[] r = {0,0};
		this.Programming_Period = r;
		this.EU_Payment_annual = 0;
		this.Modelled_annual_expenditure = 0;
		this.Standard_Deviation_of_annual_expenditure = 0;
		this.Standard_Error_of_modelled_annual_expenditure = 0;
	}
    
    /** 
	    * Class constructor.
	    * * costruttore modello dei dati
	     * @param  country nazione di provenienza
	     * @param  nUTS1_ID ID nazione
	     * @param  nUTS2_ID ID regione
	     * @param  nUTS2_name nome regione
	     * @param  fund fondo
	     * @param  year anno
	     * @param  programming_Period periodo progammato
	     * @param  eU_Payment_annual pagamento annuale EU
	     * @param  modelled_annual_expenditure Spesa annua modellata
	     * @param  standard_Deviation_of_annual_expenditure Deviazione standard della spesa annuale
	     * @param  standard_Error_of_modelled_annual_expenditure Errore standard della spesa annuale modellata
	     */
	public Data_Model(String country, String nUTS1_ID, String nUTS2_ID,String nUTS2_name, String fund, int year, int[] programming_Period,
			int eU_Payment_annual, int modelled_annual_expenditure, int standard_Deviation_of_annual_expenditure,
			int standard_Error_of_modelled_annual_expenditure) {
		super();
		this.Country = country;
		this.NUTS1_ID = nUTS1_ID;
		this.NUTS2_ID = nUTS2_ID;
		this.NUTS2_name = nUTS2_name;
		this.Fund = fund;
		this.Year = year;
		this.Programming_Period = programming_Period;
		this.EU_Payment_annual = eU_Payment_annual;
		this.Modelled_annual_expenditure = modelled_annual_expenditure;
		this.Standard_Deviation_of_annual_expenditure = standard_Deviation_of_annual_expenditure;
		this.Standard_Error_of_modelled_annual_expenditure = standard_Error_of_modelled_annual_expenditure;
	}
	
	/** 
     * nazione di provenienza getter
     * @return Country
     */
	public String getCountry() {
		return Country;
	}

	/** 
     * nazione di provenienza setter
     * @param  country nazione di provenienza
     */
	protected void setCountry(String country) {
		Country = country;
	}

	/** 
     * ID nazione getter
     * @return NUTS1_ID ID nazione
     */
	public String getNUTS1_ID() {
		return NUTS1_ID;
	}

	/** 
     * ID nazione setter
     * @param  nUTS1_ID ID nazione 
     */
	protected void setNUTS1_ID(String nUTS1_ID) {
		NUTS1_ID = nUTS1_ID;
	}

	/** 
     * ID regione getter
     * @return NUTS2_ID
     */
	public String getNUTS2_ID() {
		return NUTS2_ID;
	}

	/** 
     * ID regione setter
     * @param  nUTS2_ID ID regione
     */
	protected void setNUTS2_ID(String nUTS2_ID) {
		NUTS2_ID = nUTS2_ID;
	}

	/** 
     * nome regione getter
     * @return NUTS2_name
     */
	public String getNUTS2_name() {
		return NUTS2_name;
	}

	/** 
     * nome regione setter
     * @param  nUTS2_name nome regione
     */
	protected void setNUTS2_name(String nUTS2_name) {
		NUTS2_name = nUTS2_name;
	}

	/** 
     * fondo getter
     * @return Fund
     */
	public String getFund() {
		return Fund;
	}

	/** 
     * fondo setter
     * @param  fund fondo
     */
	protected void setFund(String fund) {
		Fund = fund;
	}

	/** 
     * anno getter
     * @return Year
     */
	public int getYear() {
		return Year;
	}

	/** 
     * anno setter
     * @param  year anno
     */ 
	protected void setYear(int year) {
		Year = year;
	}

	/** 
     * periodo progammato getter
     * @return Programming_Period
     */
	public int[] getProgramming_Period() {
		return Programming_Period;
	}

	/** 
     * periodo progammato setter
     * @param  programming_Period periodo progammato
     */ 
	protected void setProgramming_Period(int[] programming_Period) {
		Programming_Period = programming_Period;
	}

	/** 
     * pagamento annuale EU getter
     * @return EU_Payment_annual
     */
	public int getEU_Payment_annual() {
		return EU_Payment_annual;
	}

	/** 
     * pagamento annuale EU setter
     * @param  eU_Payment_annual pagamento annuale EU
     */ 
	protected void setEU_Payment_annual(int eU_Payment_annual) {
		EU_Payment_annual = eU_Payment_annual;
	}

	/** 
     * Spesa annua modellata getter
     * @return Modelled_annual_expenditure
     */
	public int getModelled_annual_expenditure() {
		return Modelled_annual_expenditure;
	}

	/** 
     * Spesa annua modellata setter
     * @param  modelled_annual_expenditure Spesa annua modellata
     */ 
	protected void setModelled_annual_expenditure(int modelled_annual_expenditure) {
		Modelled_annual_expenditure = modelled_annual_expenditure;
	}

	/** 
     * Deviazione standard della spesa annuale getter
     * @return Standard_Deviation_of_annual_expenditure
     */
	public int getStandard_Deviation_of_annual_expenditure() {
		return Standard_Deviation_of_annual_expenditure;
	}

	/** 
     * Deviazione standard della spesa annuale setter
     * @param  standard_Deviation_of_annual_expenditure Deviazione standard della spesa annuale
     */ 
	protected void setStandard_Deviation_of_annual_expenditure(int standard_Deviation_of_annual_expenditure) {
		Standard_Deviation_of_annual_expenditure = standard_Deviation_of_annual_expenditure;
	}

	/** 
     * Errore standard della spesa annuale modellata getter
     * @return Standard_Error_of_modelled_annual_expenditure
     */
	public int getStandard_Error_of_modelled_annual_expenditure() {
		return Standard_Error_of_modelled_annual_expenditure;
	}

	/** 
     * Errore standard della spesa annuale modellata setter
     * @param  standard_Error_of_modelled_annual_expenditure Errore standard della spesa annuale modellata
     */ 
	protected void setStandard_Error_of_modelled_annual_expenditure(int standard_Error_of_modelled_annual_expenditure) {
		Standard_Error_of_modelled_annual_expenditure = standard_Error_of_modelled_annual_expenditure;
	}

	/** 
     * toString
     * @return stringa della classe
     */
	@Override
	public String toString() {
		return "Data_Model [Country=" + getCountry() + ", NUTS1_ID=" + getNUTS1_ID() + ", NUTS2_ID="
				+ getNUTS2_ID() +", NUTS_name="+ getNUTS2_name() +", Fund=" + getFund() + ", Year=" + getYear() + ", Programming_Period="
				+ Arrays.toString(getProgramming_Period()) + ", EU_Payment_annual=" + getEU_Payment_annual()
				+ ", Modelled_annual_expenditure=" + getModelled_annual_expenditure()
				+ ", Standard_Deviation_of_annual_expenditure=" + getStandard_Deviation_of_annual_expenditure()
				+ ", Standard_Error_of_modelled_annual_expenditure="
				+ getStandard_Error_of_modelled_annual_expenditure() + "]";
	}
    
	
	/** 
     * toString
     * controllo parametri uguali se paramtro null nin controlla
	 * se tutto null passa datset ma dove esce il data finale
	 * ha la sicurezza che se dim datse == dim filtrat dat errore
     * @return true se ha trivato dei paramtri fale con tutti paramtri diversi da vuoti il primo
     * divvero 
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;//ogetto uguale
		if (obj == null)
			return false;//alro ogetto nullo
		if (getClass() != obj.getClass())
			return false;//alro ogetto diversa classe
		Data_Model other = (Data_Model) obj;
		//controllo parametri uguali se paramtro null nin controlla
		// se tutto null passa datset ma dove esce il data finale
		//ha la sicurezza che se dim datse == dim filtrat dat errore
		//Country nazione di provenienza
		if (!Country.equals(other.Country) & !Country.equals(""))
			return false;
		//Fund fondo
	    if (!Fund.equals(other.Fund)& !Fund.equals(""))
			return false;
		//NUTS1_ID ID nazione
		 if (!NUTS1_ID.equals(other.NUTS1_ID) & !NUTS1_ID.equals(""))
			return false;
		//NUTS2_ID ID regione
		 if (!NUTS2_ID.equals(other.NUTS2_ID) & !NUTS2_ID.equals(""))
			return false;
		//NUTS2_name nome regione
		if (!NUTS2_name.equals(other.NUTS2_name) & !NUTS2_name.equals(""))
			return false;
		//programming_Period periodo progammato
		int []r ={0,0} ;
		if (!Arrays.equals(Programming_Period, other.Programming_Period) & !Arrays.equals(Programming_Period,r) ) 
			return false;
		//Year anno
		if (Year != other.Year & Year != 0)
			return false;
		
		return true;
	}
    
    
}
