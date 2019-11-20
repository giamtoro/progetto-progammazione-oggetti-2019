package it.univpm.Model;

import java.util.Arrays;

public class Data_Model {
	
    private String Country ="";
    private String NUTS1_ID ="";
    private String NUTS2_ID ="";
    private String Fund ="";
    private int Year =0;
    private int[] Programming_Period = {0,0};
    private int EU_Payment_annual =0;
    private int Modelled_annual_expenditure =0;
    private int Standard_Deviation_of_annual_expenditure =0;
    private int Standard_Error_of_modelled_annual_expenditure =0;
    
    public Data_Model() {
		super();
		Country = "";
		NUTS1_ID = "";
		NUTS2_ID = "";
		Fund = "";
		Year = 0;
		int[] r = {0,0};
		Programming_Period = r;
		EU_Payment_annual = 0;
		Modelled_annual_expenditure = 0;
		Standard_Deviation_of_annual_expenditure = 0;
		Standard_Error_of_modelled_annual_expenditure = 0;
	}
    
    
	public Data_Model(String country, String nUTS1_ID, String nUTS2_ID, String fund, int year, int[] programming_Period,
			int eU_Payment_annual, int modelled_annual_expenditure, int standard_Deviation_of_annual_expenditure,
			int standard_Error_of_modelled_annual_expenditure) {
		super();
		Country = country;
		NUTS1_ID = nUTS1_ID;
		NUTS2_ID = nUTS2_ID;
		Fund = fund;
		Year = year;
		Programming_Period = programming_Period;
		EU_Payment_annual = eU_Payment_annual;
		Modelled_annual_expenditure = modelled_annual_expenditure;
		Standard_Deviation_of_annual_expenditure = standard_Deviation_of_annual_expenditure;
		Standard_Error_of_modelled_annual_expenditure = standard_Error_of_modelled_annual_expenditure;
	}


	protected String getCountry() {
		return Country;
	}


	protected void setCountry(String country) {
		Country = country;
	}


	protected String getNUTS1_ID() {
		return NUTS1_ID;
	}


	protected void setNUTS1_ID(String nUTS1_ID) {
		NUTS1_ID = nUTS1_ID;
	}


	protected String getNUTS2_ID() {
		return NUTS2_ID;
	}


	protected void setNUTS2_ID(String nUTS2_ID) {
		NUTS2_ID = nUTS2_ID;
	}


	protected String getFund() {
		return Fund;
	}


	protected void setFund(String fund) {
		Fund = fund;
	}


	protected int getYear() {
		return Year;
	}


	protected void setYear(int year) {
		Year = year;
	}


	protected int[] getProgramming_Period() {
		return Programming_Period;
	}


	protected void setProgramming_Period(int[] programming_Period) {
		Programming_Period = programming_Period;
	}


	protected int getEU_Payment_annual() {
		return EU_Payment_annual;
	}


	protected void setEU_Payment_annual(int eU_Payment_annual) {
		EU_Payment_annual = eU_Payment_annual;
	}


	protected int getModelled_annual_expenditure() {
		return Modelled_annual_expenditure;
	}


	protected void setModelled_annual_expenditure(int modelled_annual_expenditure) {
		Modelled_annual_expenditure = modelled_annual_expenditure;
	}


	protected int getStandard_Deviation_of_annual_expenditure() {
		return Standard_Deviation_of_annual_expenditure;
	}


	protected void setStandard_Deviation_of_annual_expenditure(int standard_Deviation_of_annual_expenditure) {
		Standard_Deviation_of_annual_expenditure = standard_Deviation_of_annual_expenditure;
	}


	protected int getStandard_Error_of_modelled_annual_expenditure() {
		return Standard_Error_of_modelled_annual_expenditure;
	}


	protected void setStandard_Error_of_modelled_annual_expenditure(int standard_Error_of_modelled_annual_expenditure) {
		Standard_Error_of_modelled_annual_expenditure = standard_Error_of_modelled_annual_expenditure;
	}


	@Override
	public String toString() {
		return "Data_Model [Country=" + getCountry() + ", NUTS1_ID=" + getNUTS1_ID() + ", NUTS2_ID="
				+ getNUTS2_ID() + ", Fund=" + getFund() + ", Year=" + getYear() + ", Programming_Period="
				+ Arrays.toString(getProgramming_Period()) + ", EU_Payment_annual=" + getEU_Payment_annual()
				+ ", Modelled_annual_expenditure=" + getModelled_annual_expenditure()
				+ ", Standard_Deviation_of_annual_expenditure=" + getStandard_Deviation_of_annual_expenditure()
				+ ", Standard_Error_of_modelled_annual_expenditure="
				+ getStandard_Error_of_modelled_annual_expenditure() + "]";
	}
    
    
    
    
}
