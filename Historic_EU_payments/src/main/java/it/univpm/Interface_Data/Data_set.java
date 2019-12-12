package it.univpm.Interface_Data;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.univpm.Model.Data_Model;


/** 

 * @author gianmarco troni
 *
 */


public abstract class Data_set {
	

    private List<List<Integer>> d_int = new ArrayList<List<Integer>>();//dati tipo int
    private List<List<String>> d_str = new ArrayList<List<String>>();//dati tipo  string
    private List<Data_Model> Data = new ArrayList<Data_Model>();// datase completo
    private String[] Attribute_Data = new String[]{};//atributi dei meta dati
    
    /** 
     * @param private List<List<Integer>> d_int
     * @param private List<List<String>> d_str
     * @param private List<Data_Model> Data
     * @param  private String[] Attribute_Data
     */
     
	public Data_set(List<Data_Model> data,String[] attribute_data) {
		super();
		if(!data.isEmpty() & attribute_data.length==0) {
			this.Data = data;//setto dataset
		    this.Attribute_Data = attribute_data;//setto atributo
		} 
		
	}
	/** 
     * costruttore del dataset principale
     * di una  classe astratta
     * @param String[] attribute_data
     * @param List<Data_Model> data
     */
	
	public Data_set() {//settagio dati vuoti
	   this.Data = new ArrayList<Data_Model>();
	   this.Attribute_Data = new String[]{};
	}
	 /** 
     * costruttore dati vuoti
     */
	
	
	protected void setD_int(List<List<Integer>> d_int) {
		this.d_int = d_int;
	}
	/** 
	 * @param List<List<Integer>> d_int
     * dati tipo int setter
     */

	protected void setD_str(List<List<String>> d_str) {
		this.d_str = d_str;
	}
	/** 
	 * @param List<List<String>> d_str
     * dati tipo string setter
     */

	protected void setData(List<Data_Model> data) {
		Data = data;
	}
	/** 
	 * @param List<Data_Model> data
     * dataset setter
     */
	
	protected void setAttribute_Data(String[] attribute_Data) {
		Attribute_Data = attribute_Data;
	}
	/** 
	 * @param String[] attribute_Data
     *  attributi del dataset setter
     */
	
	public List<List<Integer>> getD_int() {
		return d_int;
	}
	 /** 
     * dati tipo int getter
     * @return List<List<Integer>>
     */


	public List<List<String>> getD_str() {
		return d_str;
	}
	 /** 
     * dati tipo String getter
     * @return List<List<String>>
     */


	public String[] getAttribute_Data() {
		return Attribute_Data;
	}
	/** 
     * attributi dataset getter
     * @return String[]
     */

	public List<Data_Model> getData() {
		return Data;
	}
	/** 
     * dataset getter
     * @return List<Data_Model>
     */

    
	abstract public  Map<String,Map<String,?>> get_Model();
	/** 
     * ritorna modello dei dati
     * dichiarazione funzione astratta
     * @return Map<String, Map<String, ?>>
     */

	
	abstract public  List<Data_Model> set_Model(Data_Model filter);
	/** 
     * ritorna dati filtrati
     * dichiarazione funzione astratta
     * @return List<Data_Model>
     */
	
	
	protected List<Data_Model> get_data (List<Data_Model> data ,Data_Model in ) {
		List<Data_Model> ris = new ArrayList<Data_Model>();//mappa che ritorno
		List<List<Integer>> d_int_tmp = new ArrayList<List<Integer>>();//mappa appogio data int
	    List<List<String>> d_str_tmp = new ArrayList<List<String>>();//mappa appogio data String
		if(!data.isEmpty()) {
			for (Data_Model g :data) {
				if(in.equals(g) | in.equals(new Data_Model() )) {//filtro dati 
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
		this.setD_str(d_str_tmp);//setto dati tipo int
		this.setD_int(d_int_tmp);//setto dati tipo String
		return ris;
	}
	/** 
     * costruttore del dataset principale
     * di una  classe astratta
     * @param Data_Model in
     * @param List<Data_Model> data
     * @return List<Data_Model>
     */
	
	
	
	
     
     
}



