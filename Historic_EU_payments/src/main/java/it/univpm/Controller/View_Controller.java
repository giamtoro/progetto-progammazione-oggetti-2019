package it.univpm.Controller;
import it.univpm.Interface_Data.Data_set;
import it.univpm.Interface_Data.Parser;
import it.univpm.Interface_Data.Probability_Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.univpm.Model.Data_Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Component
public class View_Controller {
	
	private  final String[]dir = {"result","resources"};
	private  final String it_at = "format";
	private  final String Format = "http://publications.europa.eu/resource/authority/file-type/TSV";
	
	public List<Data_Model> data = new ArrayList<Data_Model>();
	
	@Autowired
	private Parser D;
	
	@Autowired
	private Probability_Model F;
	
	public Map<String,String>  set_data(String Url) {
		Map<String,String>  Attribute = new HashMap<String,String>();
		this.D=new Parser(Url,"data.tsv",dir,it_at,Format);
		//Probability_Model app =  new Probability_Model(this.D.getData(),this.D.getAttribute_Data(),new Data_Model());
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
	}
	
	@Bean
	public List<Data_Model> all_data() {
		return  this.D.getData();
	}

	
	
	

	

	 
}
