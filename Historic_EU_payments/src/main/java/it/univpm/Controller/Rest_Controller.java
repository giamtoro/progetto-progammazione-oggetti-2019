package it.univpm.Controller;


import it.univpm.Controller.View_Controller;
import it.univpm.Interface_Data.Data_set;
import it.univpm.Model.Data_Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@RestController
public class Rest_Controller {
	
	@Autowired
	View_Controller data;
	

	@RequestMapping(value = "/set_data", method = RequestMethod.GET)
	public Map<String, String>  set(@RequestParam(value="Url",defaultValue ="m") String Url) {
		Map<String, String> d = new HashMap <String, String>();
		 d =data.set_data(Url);
		return d;
	}
	
	@RequestMapping(value = "/get_all data", method = RequestMethod.GET)
	public List<Data_Model> get_all() {
		return data.all_data() ;
	}

}
