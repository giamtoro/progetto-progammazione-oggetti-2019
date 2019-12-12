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
* 
* @author Gian Marco Troni
*/

@RestController
/** 
 * rest controlle
 */
public class Rest_Controller {

	@Autowired
	View_Controller data;
	/** 
     * classe autosatsionta field e metodi da spring
     */
	

	@RequestMapping(value = "/set_data", method = RequestMethod.GET)
	public Map<String,Map<String,String>>  set(@RequestParam(value="Url",defaultValue ="") String Url) {
		Map<String,Map<String,String>> d = new HashMap <String, Map<String,String>>();//mappa dei  METATDATI
		 d =data.set_data(Url);
		return d;
	}
	/** 
     * metodo di settagio del dataset tramite un url 
     * @param @RequestParam(value="Url",defaultValue ="") String Url
     * @return Map<String,Map<String,String>>
     */
	
	@RequestMapping(value = "/get_metadata", method = RequestMethod.GET)
	public Map<String,Map<String,String>>  metadata() {
		Map<String,Map<String,String>> d = new HashMap <String, Map<String,String>>();//mappa dei  METATDATI
		return data.get_metadata();
	}
	/** 
     * metodo che ritorna i metadata
     * @return Map<String,Map<String,String>>
     */
	
	
	@RequestMapping(value = "/set_filtered", method = RequestMethod.POST)
	public int  set(@RequestBody Data_Model in) {
		return data.set_filtered_data(in);
	}
	/** 
     * metodo setta i dati filtrati da un body
     * @param @RequestBody Data_Model in
     * @return int
     */
	
	
	@RequestMapping(value = "/filtered_visual", method = RequestMethod.POST)
	public List<Data_Model>  set_tmp(@RequestBody Data_Model in) {
		return data.filtered_data_tmp(in);
	}
	/** 
     * metodo prova i dati settati da un body
     * @param @RequestBody Data_Model in
     * @return List<Data_Model>
     */
	
	@RequestMapping(value = "/get_data/{Mode}", method = RequestMethod.GET)
	public List<Data_Model> get_data(@PathVariable String Mode) {
		if (Mode.contains("All"))return data.all_data() ;//dati da tutti 
		else if (Mode.contains("Filtered"))return data.filtered_data();//dati filtrati 
		else return new ArrayList<Data_Model>();
	}
	/** 
     * metodo ritorna i dati da tutti il data set o 
     * dati flitrati
     * @param @PathVariable String Mode
     * @return List<Data_Model>
     */
	
	@RequestMapping(value = "/get_model/{Mode}", method = RequestMethod.GET)
	public Map<String, Map<String, ?>> get_Model(@PathVariable String Mode,@RequestParam(value="Field",defaultValue ="") String Field) {
		if (Mode.contains("All") & Field.contentEquals("")) return data.all_data_Model() ;//dati da tutti il stitistiche del modello		
		else if (Mode.contains("Filtered")& Field.contentEquals(""))return data.all_Filtered_Model();//dati filtrati il stitistiche del modello
		else if ((Mode.contains("Filtered") | Mode.contains("All")) &!Field.contentEquals("")) return data.get_Field(Field,Mode);
		else return new HashMap<String, Map<String, ?>>();
	}
	/** 
     * metodo ritorna i dati da tutti il stitistiche del modello o 
     * stitistiche del modello flitrati e  con la possibilità di fitrarlo
     * per i metatadi
     * @param @PathVariable String Mode
     * @param @RequestParam(value="Field",defaultValue ="") String Field
     * @return List<Data_Model>
     */

}		
