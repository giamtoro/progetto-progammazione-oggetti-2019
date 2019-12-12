package it.univpm.Controller;
import it.univpm.Interface_Data.Parser;
import it.univpm.Interface_Data.Probability_Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.univpm.Model.Data_Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;



@Component
public class View_Controller {
	
	private  final String[]dir = {"result","resources"};
	private  final String it_at = "format";
	private  final String Format = "http://publications.europa.eu/resource/authority/file-type/TSV";
	private  final String acceptable = "http://data.europa.eu/euodp/data/api/3/action/";
	
	public List<Data_Model> data = new ArrayList<Data_Model>();
	
	@Autowired
	private Parser D;
	
	@Autowired
	private Probability_Model F;
	
	public Map<String,String>  set_data(String Url) {
		
		if (Url.substring(0,acceptable.length()).contentEquals(acceptable)) {
			Map<String,String>  Attribute = new HashMap<String,String>();
		    this.D=new Parser(Url,"data.tsv",dir,it_at,Format);
		    int o =0;
	    	for (String f : this.D.getAttribute_Data()) {
	    		if(o<=4) {
		    		Attribute.put(f,"String");
		    	} else if (f.contentEquals("Year") & o==5) {
		    		Attribute.put(f,"int");
		    	} else if (f.contentEquals("Programming_Period") & o==6) {
		    		Attribute.put(f,"int[]");
	    		} else {
		    		Attribute.put(f,"int");
		    	}
		    	o++;
    		}
	    	return  Attribute;
	    } else if (Url.contains("") | Url.length()<acceptable.length()) throw new ResponseStatusException(HttpStatus.LENGTH_REQUIRED);
	    else throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@Bean
	public List<Data_Model> all_data() {
			return  this.D.getData();
	}

	
	public Map<String, Map<String, ?>> all_data_Model() {
		if (!this.D.getData().isEmpty()) {
			return  this.D.get_Model();
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
	}
	
	
	public int  set_filtered_data(Data_Model in) {
		this.F=new Probability_Model(this.D.getData(),this.D.getAttribute_Data(),in);
		if (!in.equals(new Data_Model()) | this.F.getN()>1) {
			return  this.F.getN();
		} if(this.F.getN() ==1) new ResponseStatusException(HttpStatus.LENGTH_REQUIRED);
		else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return 0;
	}
	
	@Bean
	public List<Data_Model> filtered_data() {
			return  this.F.getData();
	}
	
	public Map<String, Map<String, ?>> all_Filtered_Model() {
		if (!this.F.getData().isEmpty()) {
			return  this.F.get_Model();
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
	}
	

	public List<Data_Model> filtered_data_tmp(Data_Model in){
		List<Data_Model> d=this.D.set_Model(in);
		if (!in.equals(new Data_Model()) | !this.D.getData().isEmpty()) {
			return  d;
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	public Map<String, Map<String, ?>> get_Field(String Atribute_model,String Mode){
		 Map<String, Map<String, ?>> ret = new HashMap<String,Map<String,?>>();
		 Map<String, Map<String, ?>> app = new HashMap<String,Map<String,?>>();
		if(!Atribute_model.contentEquals("")) {
			if(Mode.contentEquals("All") & !this.D.getData().isEmpty()) {
				app=this.D.get_Model();
			} else if(Mode.contentEquals("Filtered") & !this.F.getData().isEmpty()) {
				app=this.D.get_Model();
			}
			 if (app.size()>1) {
	        	for (String A : this.D.getAttribute_Data()) {
	         		if (Atribute_model.contentEquals(A)) {
	        			ret.put(A, app.get(A));
	         		}
    	    	}
		    } 
		}
		return ret;
	}
	

	

	 
}
