package it.univpm.Model;

import java.util.Arrays;

public class Data_Model {
	
    private String Country ="";
    private String NUTS1_ID ="";
    private String NUTS2_ID ="";
    private String NUTS2_name ="";
    private String Fund ="";
    private int Year =0;
    private int[] Programming_Period = {0,0};
    private int EU_Payment_annual =0;
    private int Modelled_annual_expenditure =0;
    private int Standard_Deviation_of_annual_expenditure =0;
    private int Standard_Error_of_modelled_annual_expenditure =0;
    
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
	

	public String getCountry() {
		return Country;
	}


	protected void setCountry(String country) {
		Country = country;
	}


	public String getNUTS1_ID() {
		return NUTS1_ID;
	}


	protected void setNUTS1_ID(String nUTS1_ID) {
		NUTS1_ID = nUTS1_ID;
	}


	public String getNUTS2_ID() {
		return NUTS2_ID;
	}


	protected void setNUTS2_ID(String nUTS2_ID) {
		NUTS2_ID = nUTS2_ID;
	}


	public String getNUTS2_name() {
		return NUTS2_name;
	}


	protected void setNUTS2_name(String nUTS2_name) {
		NUTS2_name = nUTS2_name;
	}


	public String getFund() {
		return Fund;
	}


	protected void setFund(String fund) {
		Fund = fund;
	}


	public int getYear() {
		return Year;
	}


	protected void setYear(int year) {
		Year = year;
	}


	public int[] getProgramming_Period() {
		return Programming_Period;
	}


	protected void setProgramming_Period(int[] programming_Period) {
		Programming_Period = programming_Period;
	}


	public int getEU_Payment_annual() {
		return EU_Payment_annual;
	}


	protected void setEU_Payment_annual(int eU_Payment_annual) {
		EU_Payment_annual = eU_Payment_annual;
	}


	public int getModelled_annual_expenditure() {
		return Modelled_annual_expenditure;
	}


	protected void setModelled_annual_expenditure(int modelled_annual_expenditure) {
		Modelled_annual_expenditure = modelled_annual_expenditure;
	}


	public int getStandard_Deviation_of_annual_expenditure() {
		return Standard_Deviation_of_annual_expenditure;
	}


	protected void setStandard_Deviation_of_annual_expenditure(int standard_Deviation_of_annual_expenditure) {
		Standard_Deviation_of_annual_expenditure = standard_Deviation_of_annual_expenditure;
	}


	public int getStandard_Error_of_modelled_annual_expenditure() {
		return Standard_Error_of_modelled_annual_expenditure;
	}


	protected void setStandard_Error_of_modelled_annual_expenditure(int standard_Error_of_modelled_annual_expenditure) {
		Standard_Error_of_modelled_annual_expenditure = standard_Error_of_modelled_annual_expenditure;
	}


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
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data_Model other = (Data_Model) obj;
		
		
		
		if (!Country.equals(other.Country) & !Country.equals(""))
			return false;
		
	    if (!Fund.equals(other.Fund)& !Fund.equals(""))
			return false;
		
		 if (!NUTS1_ID.equals(other.NUTS1_ID) & !NUTS1_ID.equals(""))
			return false;
		
		 if (!NUTS2_ID.equals(other.NUTS2_ID) & !NUTS2_ID.equals(""))
			return false;
		
		if (!NUTS2_name.equals(other.NUTS2_name) & !NUTS2_name.equals(""))
			return false;
		
		int []r ={0,0} ;
		if (!Arrays.equals(Programming_Period, other.Programming_Period) & !Arrays.equals(Programming_Period,r) ) 
			return false;
		
		if (Year != other.Year & Year != 0)
			return false;
		
		return true;
	}
    
    
}
