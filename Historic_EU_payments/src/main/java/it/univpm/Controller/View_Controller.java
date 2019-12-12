package it.univpm.Controller;
import it.univpm.Interface_Data.Parser;
import it.univpm.Interface_Data.Probability_Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.univpm.Model.Data_Model;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

/** 

 * @author gianmarco troni
 *
 */

@Component
/** 
 * annotazione di spring che scansiona la classe
 */
public class View_Controller {
	
	private  final String[]dir = {"result","resources"};//directory json
	private  final String it_at = "format";//atributo
	private  final String Format = "http://publications.europa.eu/resource/authority/file-type/TSV";//formato da prendere
	private  final String acceptable = "http://data.europa.eu/euodp/data/api/3/action/";// url acettabile
	private  final String[]discrition = {"nazione di provenienza","ID nazione","ID regione","nome regione","fondo","anno",
			"periodo progammato","pagamrto annuale EU","Spesa annua modellata","Deviazione standard della spesa annuale",
			"Errore standard della spesa annuale modellata"}; // discrizione die metadati
	
	
	
	/** 
     * classe del parser
     * @param private  final String[]dir
     * @param private  final String it_at
     * @param private  final String Format
     * @param private  final String acceptable
     * @param private  private  final String[]discrition
     */
	
	@Autowired
	private Parser D;
	/** 
     * classe del parser
     * @param Parser D
     */

	@Autowired
	private Probability_Model F;
	/** 
     * classe del Probability_Model
     * @param Probability_Model D
     */
	
	public Map<String,Map<String,String>>  set_data(String Url) {
		if (Url.substring(0,acceptable.length()).contentEquals(acceptable)) {
		    this.D=new Parser(Url,"data.tsv",dir,it_at,Format); //parso dati
             return D_metadata();
	    } else if (Url.contains("") | Url.length()<acceptable.length()) throw new ResponseStatusException(HttpStatus.LENGTH_REQUIRED);//lunghezza non acettabile
	    else throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);//url non acettabile
	}
	/** 
     * metodo di settagio del dataset tramite un url 
     * @param String Url
     * @return Map<String,Map<String,String>>
     */
	
	@Bean
	/** 
     * annotazion di spring
     */
	public List<Data_Model> all_data() {
			return  this.D.getData();
	}
	/** 
     * metodo ritorna tutta la data
     * @return List<Data_Model>
     */
	
	@Bean
	/** 
     * annotazione di spring
     */
	public Map<String,Map<String,String>> get_metadata() {
			return D_metadata();
	}
	/** 
     * metodo ritorna i metadati
     * @return Map<String,Map<String,String>>
     */
	
	public Map<String, Map<String, ?>> all_data_Model() {
		if (!this.D.getData().isEmpty()) {
			return  this.D.get_Model();
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND);//nessuno elemento ottenuto del filtro	
	}
	/** 
     * metodo ritorna il modello dei dati
     * @return Map<String, Map<String, ?>>
     */
	
	public int  set_filtered_data(Data_Model in) {
		this.F=new Probability_Model(this.D.getData(),this.D.getAttribute_Data(),in);
		if (!in.equals(new Data_Model()) | this.F.getN()>1 |!(this.D.getData().size()==this.F.get_Data().size()) ) {
			return  this.F.getN();// rida cardinalità dei dati
		} if(this.F.getN() ==1) new ResponseStatusException(HttpStatus.LENGTH_REQUIRED);//lunghezza troppo piccola dei dat
		else throw new ResponseStatusException(HttpStatus.NOT_FOUND);//nessundato trovato per quasto filtro
		return 0;
	}
	/** 
     * metodo filtra dati e setta il dato filtrato
     * @param Data_Model in
     * @return List<Data_Model>
     */
	
	@Bean
	/** 
     * annotazion di spring
     */
	public List<Data_Model> filtered_data() {
			return  this.F.get_Data();
	}
	/** 
     * metodo ritorna i dati filtrati
     * @return List<Data_Model>
     */
	
	
	public Map<String, Map<String, ?>> all_Filtered_Model() {
		if (!this.F.getData().isEmpty()) {
			return  this.F.get_Model();
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND);//nessun dato trovato 
			
	}
	/** 
     * metodo ritorna il modello dei dati flitrati
     * @return Map<String, Map<String, ?>>
     */
	

	public List<Data_Model> filtered_data_tmp(Data_Model in){
		List<Data_Model> d = this.D.set_Model(in);//filtro dati
		if (!in.equals(new Data_Model()) | !this.D.getData().isEmpty() |!(this.D.getData().size()==d.size()) ) {
			return  d;
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND);//nessundato trovato per quasto filtro
	}
	/** 
     * metodo fitra dati per visualizarli
     * @param Data_Model in
     * @return List<Data_Model>
     */
	
	
	public Map<String, Map<String, ?>> get_Field(String Atribute_model,String Mode){
		 Map<String, Map<String, ?>> ret = new HashMap<String,Map<String,?>>();// mappa che ritorno
		 Map<String, Map<String, ?>> app = new HashMap<String,Map<String,?>>();//mappa di di supporto
		if(!Atribute_model.contentEquals("")) {
			if(Mode.contentEquals("All") & !this.D.getData().isEmpty()) {
				app=this.D.get_Model();//dataset completo
			} else if(Mode.contentEquals("Filtered") & !this.F.getData().isEmpty()) {
				app=this.D.get_Model();//dataset filtrato
			}
			 if (app.size()>1) {
	        	for (String A : this.D.getAttribute_Data()) {
	         		if (Atribute_model.contentEquals(A)) {
	        			ret.put(A, app.get(A));//seleziono field
	         		}
    	    	}
		    } 
		}
		return ret;
	}
	/** 
     * metodo fitra che seleziona i modello di statistiche da ritornare e sceglie
     * il parametro di filtro dei field
     * @param String Atribute_model
     * @param String Mode
     * @returnMap<String, Map<String, ?>>
     */
	
  
    public Map<String,Map<String,String>> D_metadata(){
    	Map<String,Map<String,String>>  Attribute = new HashMap<String,Map<String,String>>();// mappa che ritorno
	    int o =0;
    	for (String f : this.D.getAttribute_Data()) {
    		if(o<=4) {
    			Map<String,String> app =  new HashMap<String,String>();
    		    app.put("type", "String");//dati tipo int
    		    app.put("descrizione", discrition[o]);// descrizione
    		    Attribute.put(f,app);
	    	} else if (f.contentEquals("Year") & o==5) {
	    		Map<String,String> app =  new HashMap<String,String>();
    		    app.put("type", "int");//dati tipo int
    		    app.put("descrizione", discrition[o]);// descrizione
    		    Attribute.put(f,app);
	    	} else if (f.contentEquals("Programming_Period") & o==6) {
	    		Map<String,String> app =  new HashMap<String,String>();
    		    app.put("type", "int[]");//dati tipo int[]
    		    app.put("descrizione", discrition[o]);// descrizione
    		    Attribute.put(f,app);
    		} else {
    			Map<String,String> app =  new HashMap<String,String>();
    		    app.put("type", "int");//dati tipo int
    		    app.put("descrizione", discrition[o]);// descrizione
    		    Attribute.put(f,app);
	    	}
	    	o++;
		}
    	return  Attribute;
    }
    /** 
     * metodo che ritorno la mappa dei metadati
     * @return Map<String,Map<String,String>>
     */

	 
}
