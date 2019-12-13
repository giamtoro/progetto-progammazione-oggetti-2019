package it.univpm.Interface_Data;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.univpm.Model.Data_Model;


/** 
* quasta  classe è astratta contie il dataset principale e
* gli atributi
* la quale vandono utliazii per spacchetare per tipi i
* del mdello di dati e la funzione del filto dei dato
* clssi estese {@link Parser} ,
* {@link Probability_Model} 
*  
*  @see       Data_set
*  @see       Data_Model
 * @author gianmarco troni
 * @version 1.4
 *
 */


public abstract class Data_set {
	

    private List<List<Integer>> d_int = new ArrayList<List<Integer>>();//dati tipo int
    private List<List<String>> d_str = new ArrayList<List<String>>();//dati tipo  string
    private List<Data_Model> Data = new ArrayList<Data_Model>();// datase completo
    private String[] Attribute_Data = new String[]{};//atributi dei meta dati
    

    /** 
     * Class constructor.
     * costruttore del dataset principale
     * di una  classe astratta
     * @param  attribute_data atributi dataset
     * @param  data tutto il datset
     */
	public Data_set(List<Data_Model> data,String[] attribute_data) {
		super();
		if(!data.isEmpty() & attribute_data.length==0) {
			this.Data = data;//setto dataset
		    this.Attribute_Data = attribute_data;//setto atributo
		} 
		
	}
	
	/** 
     * Class constructor.
     * costrittore dati vuoti
     */
	public Data_set() {//settagio dati vuoti
	   this.Data = new ArrayList<Data_Model>();
	   this.Attribute_Data = new String[]{};
	}
	 
	
	/** 
	 * dati tipo int setter
     * @param d_int dati modello int
     */
	protected void setD_int(List<List<Integer>> d_int) {
		this.d_int = d_int;
	}
    
	/** 
	 * dati tipo string setter
     * @param d_str dati modello string
     */
	protected void setD_str(List<List<String>> d_str) {
		this.d_str = d_str;
	}
    
	/** 
	 * dataset setter
     * @param data tutto il dataset 
     */
	protected void setData(List<Data_Model> data) {
		Data = data;
	}

	
	/** 
	 * attributi del dataset setter
     * @param attribute_Data atribui del dataset
     */
	protected void setAttribute_Data(String[] attribute_Data) {
		Attribute_Data = attribute_Data;
	}
	
	/** 
	 * dati tipo int getter
     * @return dati tipo int
     */
	public List<List<Integer>> getD_int() {
		return d_int;
	}

	/** 
	 * dati tipo String getter
     * @return dati tipo String
     */
	public List<List<String>> getD_str() {
		return d_str;
	}

	/** 
	 * attributi dataset getter
     * @return atributi
     */
	public String[] getAttribute_Data() {
		return Attribute_Data;
	}
    
	/** 
	 * dataset getter
     * @return tutto il dataset
     */
	public List<Data_Model> getData() {
		return Data;
	}

    /** 
     * ritorna statitiche dei dati
     * dichiarazione funzione astratta
     * @return mappa dell statistiche al ogetto che chimato
     */
	abstract public  Map<String,Map<String,?>> get_Model();
	

	/** 
     * ritorna dati filtrati
     * dichiarazione funzione astratta
     * @param filter modello che funziona da filtro
     * @return lista del modello del dato al ogetto che chimato
     */
	abstract public  List<Data_Model> set_Model(Data_Model filter);
	
	
	/** 
     * costruttore del dataset principale
     * dei dati int e string
     * funzione di una  classe astratta
     * @param in modello che funziona da filtro
     * @param data dati da filtrare
     * @return lista modello fitrato
     */
	protected List<Data_Model> get_data (List<Data_Model> data ,Data_Model in ) {
		List<Data_Model> ris = new ArrayList<Data_Model>();//mappa che ritorno
		List<List<Integer>> d_int_tmp = new ArrayList<List<Integer>>();//mappa appogio data int
	    List<List<String>> d_str_tmp = new ArrayList<List<String>>();//mappa appogio data String
		if(!data.isEmpty()) {
			for (Data_Model g :data) {
				if(in.equals(g) | in.equals(new Data_Model() )) {//filtro dati 
					// dati string
		    		List<String> s = new ArrayList<String>();
		    		s.add(g.getCountry());
			    	s.add(g.getNUTS1_ID());
			    	s.add(g.getNUTS2_ID());
			    	s.add(g.getNUTS2_name());
			    	s.add(g.getFund());
			    	d_str_tmp.add(s);
			    	// dati int + int[]
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
	
	
	
	
	
     
     
}



