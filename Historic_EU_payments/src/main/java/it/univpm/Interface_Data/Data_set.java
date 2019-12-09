package it.univpm.Interface_Data;


import java.util.ArrayList;
import java.util.List;

import it.univpm.Model.Data_Model;




public abstract class Data_set {
	

    private List<List<Integer>> d_int = new ArrayList<List<Integer>>();
    private List<List<String>> d_str = new ArrayList<List<String>>();
    private List<Data_Model> Data = new ArrayList<Data_Model>();
    private String[] Attribute_Data = new String[]{};
     
	public Data_set(List<Data_Model> data,String[] attribute_data) {
		super();
		if(!data.isEmpty()) {
			this.Data = data;
		    this.Attribute_Data = attribute_data;
		}
		
	}
	
	
	
	protected List<List<Integer>> getD_int() {
		return d_int;
	}



	protected void setD_int(List<List<Integer>> d_int) {
		this.d_int = d_int;
	}



	protected List<List<String>> getD_str() {
		return d_str;
	}



	protected void setD_str(List<List<String>> d_str) {
		this.d_str = d_str;
	}



	protected String[] getAttribute_Data() {
		
		return Attribute_Data;
	}

	
	protected List<Data_Model> getData() {
		return Data;
	}


	
	protected List<Data_Model> get_data (List<Data_Model> data ,Data_Model in ) {
		List<Data_Model> ris = new ArrayList<Data_Model>();
		List<List<Integer>> d_int_tmp = new ArrayList<List<Integer>>();
	    List<List<String>> d_str_tmp = new ArrayList<List<String>>();
		if(!data.isEmpty()) {
			for (Data_Model g :data) {
				if(in.equals(g) | in.equals(new Data_Model() )) {
		    		List<String> s = new ArrayList<String>();
		    		s.add(g.getCountry());
			    	s.add(g.getNUTS1_ID());
			    	s.add(g.getNUTS2_ID());
			    	s.add(g.getNUTS2_name());
			    	s.add(g.getFund());
			    	d_str_tmp.add(s);
	    	    	List<Integer> d = new ArrayList<Integer>();
	    	    	d.add(g.getYear());
			    	d.add(g.getProgramming_Period()[0]);
			    	d.add(g.getProgramming_Period()[1]);
			    	d.add(g.getEU_Payment_annual());
			    	d.add(g.getModelled_annual_expenditure());
			    	d.add(g.getStandard_Deviation_of_annual_expenditure());
				    d.add(g.getStandard_Error_of_modelled_annual_expenditure());
				    d_int_tmp.add(d);
				    ris.add(g);
				}
			}
		}
		this.setD_str(d_str);
		this.setD_int(d_int_tmp);
		return ris;
	}
	
	
	abstract public  List<Data> get_Model();
	
     
     
}

