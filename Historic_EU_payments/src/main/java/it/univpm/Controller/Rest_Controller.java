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


/** 
 * REST controller del modello dei dati
 * le finzioni sino implemntate nel View_Controller
 * classe autoscatsionta field e metodi da spring
 * 
 * classe dove contine le funzioni che del
 * controllo dei dati
 * con la scansione classe {@link Autowired}
 * {@link View_Controller} data   data classe controllo dati
* @see View_Controller
* @author Gian Marco Troni
* @version 1.4
*/

@RestController
/** 
 * rest controller
 */
public class Rest_Controller {

	// atributi
	@Autowired
	View_Controller data;
	
	// metodi
     
	/** 
     * metodo di settagio del dataset tramite un url 
     * parametri spring =@RequestParam(value="Url",defaultValue ="") String Url
     * @param  Url  stringa url
     * @return mappa metatata
     */
	@RequestMapping(value = "/set_data", method = RequestMethod.GET)
	public Map<String,Map<String,String>>  set(@RequestParam(value="Url",defaultValue ="") String Url) {
		Map<String,Map<String,String>> d = new HashMap <String, Map<String,String>>();//mappa dei  METATDATI
		 d =data.set_data(Url);
		return d;
	}
	
	/** 
     * metodo che ritorna i metadata
     * @return mappa metadati
     */
	@RequestMapping(value = "/get_metadata", method = RequestMethod.GET)
	public Map<String,Map<String,String>>  metadata() {
		return data.get_metadata();
	}
	
	
	/** 
     * metodo setta i dati filtrati da un body
     * paramtri spring =@RequestBody Data_Model in
     * @param in mdello che fa filtrare
     * @return numero elementi filtrati
     */
	@RequestMapping(value = "/set_filtered", method = RequestMethod.POST)
	public int  set_filtered(@RequestBody Data_Model in) {
		return data.set_filtered_data(in);
	}
	
	
	/** 
     * metodo prova i dati settati da un body
     * paramtri spring =@RequestBody Data_Model in
     * @param in mdello che fa filtrare
     * @return list di dati filtrati
     */
	@RequestMapping(value = "/filtered_visual", method = RequestMethod.POST)
	public List<Data_Model>  set_tmp(@RequestBody Data_Model in) {
		return data.filtered_data_tmp(in);
	}
	
	/** 
     * metodo ritorna i dati da tutti il data set o 
     * dati flitrati
     * paramtri spring =@PathVariable String Mode
     * funzioni 
     * @param  Mode scelta dato
     * @return list di dati filtrati
     */
	@RequestMapping(value = "/get_data/{Mode}", method = RequestMethod.GET)
	public List<Data_Model> get_data(@PathVariable String Mode) {
		if (Mode.contains("All"))return data.all_data() ;//dati da tutti 
		else if (Mode.contains("Filtered"))return data.filtered_data();//dati filtrati 
		else return new ArrayList<Data_Model>();
	}
	
	/** 
     * metodo ritorna i dati da tutti il stitistiche del modello o 
     * stitistiche del modello flitrati e  con la possibilità di fitrarlo
     * per i metatadi
     * paramtri spring =@PathVariable String Mode 
     * e ,@RequestParam(value="Field",defaultValue ="") String Field
     * @param Field di attributo da metadata
     * @param Mode scelta dato
     * @return list di statistche dei dato scelto e sclto anche dal field
     */ 
	@RequestMapping(value = "/get_model/{Mode}", method = RequestMethod.GET)
	public Map<String, Map<String, ?>> get_Model(@PathVariable String Mode,@RequestParam(value="Field",defaultValue ="") String Field) {
		if (Mode.contains("All") & Field.contentEquals("")) return data.all_data_Model() ;//dati da tutti il stitistiche del modello		
		else if (Mode.contains("Filtered")& Field.contentEquals(""))return data.all_Filtered_Model();//dati filtrati il stitistiche del modello
		else if ((Mode.contains("Filtered") | Mode.contains("All")) &!Field.contentEquals("")) return data.get_Field(Field,Mode);
		else return new HashMap<String, Map<String, ?>>();
	}
	

}		
