package it.univpm.Interface_Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Component;

import it.univpm.Model.Data_Model;

/**
 * quasta  classe computa url del dato in json e 
 * scarica il dataset che dopo setta il  dato della classe Data_set
 * e rida i dat del modello
 *  esteso a {@link Data_set}
 * la scansione  della classe  è data da 
 * {@link Component}
 *  @see       Data_set
 *  @see       Data_Model
 * @author gianmarco troni
 * @version 1.4
 *
 */

@Component("Parser")
/** 
 * annotazione di spring che scansiona la classe
 */

public class Parser extends Data_set{
	//atributi
	 // satici
	 private final  static String COMMA_DELIMITER = "\\t";//delimtatore file
	 private final  static String Cer = "if you are not automatically redirected";//stringa prima del download del file
	 private final  static String Browser = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0";//broswer user agent
	 
	 private String Url = new String();//url json
	 private String UrlD = new String();//url tsv scaricare
	 private String File_data = new String();//nome file

	 //metodi
	 
	 /** 
	    * Class constructor.
	    * * costruttore del parser dei dati
	     * legato una  classe astratta Data_Set
	     * @param  url file json
	     * @param  dir_attribute directory degli atributi dove si trova il jsonArray
	     * @param  file_data  nome file
	     * @param item_attribute attributo item formato dati
         * @param item elemento da trovare 
	     */
         public Parser(String url, String file_data,String[]dir_attribute,
			 String item_attribute,String item) {
		    super();//dati vuoti calls astratta
			this.Url = url;//url json
			this.File_data= file_data;//nome file
			load_file(this.Url,this.File_data,dir_attribute,item_attribute,item,"url");//carico dati
			this.setData(processing(this.File_data));//processa file data
		  }	
	 
	 
	 /** 
	    * Class constructor.
	    * costruttore dati vuoti 
	    */
	 public Parser() {
		    super();//costritore classe astratta vuoto
			this.Url = "";
			this.File_data= ""; 
			this.setData(new ArrayList<Data_Model>());
		  }
	 
		
	/** 
     * url json getter
     * @return url del file json
     */
	public String getUrl() {
		return this.Url;
	}
	
	
     /** 
     * url downlad getter
     * @return url file douwnload
     */
	public String getUrlD() {
		return UrlD;
	}
	
	
     /** 
     * name file getter
     * @return nome file
     */
	public String getFile_data() {
		return this.File_data;
	}
	
    /** 
     * scarica il json da parsare
     * @param url
     * @return dati file json
     * @throws java.io.IOException  rilava problemi presa connesione
     */
	private String url_json(String url) throws IOException{
		 //carico url
		 URL conn1=new URL(url);
		 //apro connesione
		 URLConnection openConnection =conn1.openConnection();
		 //finto brosewr
	     openConnection.addRequestProperty("User-Agent", Browser);
	     //apra flusso di dati tramite una connesione
		 InputStream in = openConnection.getInputStream();
		 String data = "";
		 String line = "";
		 try {
			  // lettura bufferizata
			  BufferedReader buf = new BufferedReader(new InputStreamReader( in ) );
			  while ( ( line = buf.readLine() ) != null ) {
			      data+= line;
			  }
		    } finally {//alla fine chidu il flusso
			  in.close();
		    }
		 
		 return data;
	}
	
	/** 
     * scarica il file da parsare
     * @param url url file json
     * @param file_data nome file
     * @param dir_attribute directory degli atributi dove si trova il jsonArray
     * @param item_attribute attributo item formato dati
     * @param item item da trovare 
     * @param attribute_return atributo item dove è il retun dei dati
     * @throws java.io.IOException  errore su douwload e paser json
     * @throws org.json.simple.parser.ParseException errore json pars
     */
	protected void load_file(String url ,String file_data, String[] dir_attribute , 
			String item_attribute, String item, String attribute_return)   {
	     if(!url.isEmpty() && !file_data.isEmpty()) {
	    	 String er = new String();//stringa HTML prima del download del file 
			 try {
				 String data = url_json(url);//carico json
				 er=data;
				 //crea json grazie all libreria 
				 JSONObject obj = (JSONObject)JSONValue.parseWithException(data);
				 int i =1;
				 boolean T = true;//var boolean per sapre se è sato trovato
				 //dove appogio json object per che atributi che ha
				 JSONObject test = new JSONObject();
				 //inzializzo
				 JSONObject app = (JSONObject) (obj.get(dir_attribute[0]));	
				 //dove sarà il jsonArray
				 JSONArray dir = new JSONArray();
				 if (app!=null) {
					while ((i<=dir_attribute.length-1) && T && app.containsKey(dir_attribute[i])) {
						//se i< grandezza atributi e T tre e app contiene elemento per arrivare al jsonArray
					 if((i==dir_attribute.length-1 )& T ) {
						 //se arrivato in findo da il jsonArray
						 dir = (JSONArray) (app.get(dir_attribute[i]));
					    }
					 if((i!=dir_attribute.length )& T & dir.isEmpty()) {
						 // se app contiene un altro elemento per arrivare al jsonArray
						 test=  (JSONObject) (app.get(dir_attribute[i]));
					 }
				     if (test!=null) {
				    	 //se test non nullo non è corripondente a 
				    	 //app non contiene elemento per arrivare al jsonArray
				     	app=test;
				     	i++;
				       }else {
				    	 //se test è nullo 
				    	 //non esistono elemeti per arrivare al jsonArray 
				    	 T=false;
				       }
				    } 
				 }
				 if (T) {
					 for(Object o: dir){
						  if ( o instanceof JSONObject ) {
							  JSONObject it = (JSONObject)o; 
						      String format = (String)it.get(item_attribute); //perndo atributo item
						      if(format.equals(item)) {//foramto dati uguale
						    	this.UrlD = (String)it.get(attribute_return);//setto url download
						        download(this.UrlD,file_data );//dowload file
						        }  
						  }
					 }
				 }
			 } catch (ParseException e) {
				  if(er.substring(490,529).contentEquals(Cer)) {
					  //
					  System.out.println(e.getMessage());
				  }
			 } catch (IOException e) {
									  //
					  System.out.println(e.getMessage());
				  
			 
			 }
				  
	     }
		   
   }
	
	/** 
     * download del file
     * url download
     * @param url url del douwnload
     * @param filename nome file
	 * @throws java.io.IOException 
     */		   
	private static void download(String url, String fileName) throws java.io.IOException  {
		try (InputStream in = URI.create(url).toURL().openStream()) {
			//apre flusso di dati da un url
			//poi se non esiste crea il file o se no riscrive il file 
	        Files.copy(in, Paths.get(fileName));
	    } 
	}
	
	/** 
     * controlla se s è vuota
     * rida 0 in un artibuto int
     * @param s
     * @return "0" se s è vuota altrimeti rida s
     */
	private String test( String s) {
		if(!s.isEmpty()) return s;
		else return "0";
	}
	
	/** 
     * parasa il file tsv in una 
     * lista di modello dei dati
     * @param file nome file
     * @return lista di modello dei dati parsata
     * @throws java.lang.Exception
     * @throws java.lang.NumberFormatException
     */
	private List<Data_Model> processing(String file) {
		List<Data_Model> temp = new ArrayList<Data_Model>();
		if(!file.isEmpty()) {
			//se file è settato
			int i =0;
			try{
			//apro lettura bafferizata
			BufferedReader stream=new BufferedReader(new FileReader (file));
			//string di appogio dove ilbuffer metterà la lettura
			String line= new String();
			   while(( line = stream.readLine())!=null) {
				   //se la line letta è nulla
				   if(i!=0  && !(line.isEmpty())) { 
					   //se non è la prima linea
					   //splitta valore
				   String l[] =line.split(COMMA_DELIMITER);
				   //setto varibili di appogio per caricare i dati della
				   //classe del modello
				  //parametri string
				   String Country = l[0];
				   String NUTS1_ID = l[1];
				   String NUTS2_ID = l[2];
				   String NUTS2_name = l[3];
				   String Fund = l[4];
				   //parametri int
				   int Year = Integer.parseInt(l[5]);
				   //unico parametri int[]
				   String[] y=l[6].split("-");
				   int[] Progamming_Period = {Integer.parseInt(y[0]),Integer.parseInt(y[1])};
				   //
				   int EU_Payment_annual = Integer.parseInt(test(l[7]));
				   int Modelled_annual_expenditure = Integer.parseInt(test(l[8]));
				   int Standard_Deviation_of_annual_expenditure = Integer.parseInt(test(l[9]));
				   int Standard_Error_of_modelled_annual_expenditure = Integer.parseInt(test(l[10]));
				   //cre classe dati modello
				   Data_Model app =new Data_Model(Country,NUTS1_ID,NUTS2_ID,NUTS2_name,Fund,Year,Progamming_Period,
						   EU_Payment_annual,Modelled_annual_expenditure,Standard_Deviation_of_annual_expenditure,
						   Standard_Error_of_modelled_annual_expenditure); 
				   //aggiungo alla lista
				   temp.add(app);
				      } 
				   if (i==0) {
					   //la prim line del file setto atributi
					    this.setAttribute_Data(line.split(COMMA_DELIMITER));
				   }
				   i++;
			   }
			   //all fine del documeto i>1
			if(i!=1)stream.close();//chido flusso
		    } catch ( IOException e) {
		    	System.out.println(e.getMessage());
		    } catch ( java.lang.NumberFormatException b) {
		    	System.out.println(b.getMessage());
		    }
		}
		return temp;
	}

	/** 
     * funzione che crea le liste dei dati
	 * e ritorna le statitche di tutti dati
	 * funzione legata alla clesse astratta
     * @see Data_set#get_Model()
     * @return  ritorna le statiche del modello di tutti dati
     */
	@Override
	public Map<String, Map<String, ?>> get_Model() {
		Probability_Model app = new Probability_Model(this.getData(),this.getAttribute_Data(),new Data_Model());
		return app.get_Model();
	}
	
    
	/** 
     * ritorna dati filtrati
     * esistenti della classe astratta legata
     * funzione legata alla clesse astratta
     * @param filter modello dati che fa da filtro
     */
	@Override
	public  List<Data_Model> set_Model(Data_Model filter) {
		return this.get_data(this.getData(), filter);

	}
	

	 
}

