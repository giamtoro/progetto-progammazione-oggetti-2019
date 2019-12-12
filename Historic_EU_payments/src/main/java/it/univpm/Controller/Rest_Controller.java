package it.univpm.Controller;


import it.univpm.Controller.View_Controller;
import it.univpm.Model.Data_Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
public class Rest_Controller {
	
	static final String[] D_A = new String[] {};
	
	@Autowired
	View_Controller data;
	

	@RequestMapping(value = "/set_data", method = RequestMethod.GET)
	public Map<String, String>  set(@RequestParam(value="Url",defaultValue ="") String Url) {
		Map<String, String> d = new HashMap <String, String>();
		 d =data.set_data(Url);
		return d;
	}
	
	@RequestMapping(value = "/set_filtered", method = RequestMethod.POST)
	public int  set(@RequestBody Data_Model in) {
		return data.set_filtered_data(in);
	}
	
	@RequestMapping(value = "/filtered_visual", method = RequestMethod.POST)
	public List<Data_Model>  set_tmp(@RequestBody Data_Model in) {
		return data.filtered_data_tmp(in);
	}
	
	@RequestMapping(value = "/get_data/{Mode}", method = RequestMethod.POST)
	public List<Data_Model> get_data(@PathVariable String Mode) {
		if (Mode.contains("All"))return data.all_data() ;
		else if (Mode.contains("Filtered"))return data.filtered_data();
		else return new ArrayList<Data_Model>();
	}
	
	@RequestMapping(value = "/get_model/{Mode}", method = RequestMethod.GET)
	public Map<String, Map<String, ?>> get_Model(@PathVariable String Mode,@RequestParam(value="Field",defaultValue ="") String Field) {
		if (Mode.contains("All") & Field.contentEquals("")) return data.all_data_Model() ;		
		else if (Mode.contains("Filtered")& Field.contentEquals(""))return data.all_Filtered_Model();
		else if ((Mode.contains("Filtered") | Mode.contains("All")) &!Field.contentEquals("")) return data.get_Field(Field,Mode);
		else return new HashMap<String, Map<String, ?>>();
	}

}		
