package it.univpm.Interface_Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Component;

import it.univpm.Model.Data_Model;

@Component("Parser")
public class Parser extends Data_set{
	 private final  static String COMMA_DELIMITER = "\\t";
	 private final  static String Browser = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0";
	 
	 private String Url = new String();
	 private String UrlD = new String();
	 private String File_data = new String();
	 
	 public Parser(String url, String file_data,String[]dir_attribute,
			 String item_attribute,String item) {
		    super();
			this.Url = url;
			this.File_data= file_data;
			load_file(this.Url,this.File_data,dir_attribute,item_attribute,item,"url"); 
			this.setData(processing(this.File_data));
		  }	
	 
	 public Parser() {
		    super();
			this.Url = "";
			this.File_data= ""; 
			this.setData(new ArrayList<Data_Model>());
		  }

	public String getUrl() {
		return this.Url;
	}
	
	public String getUrlD() {
		return UrlD;
	}

	public String getFile_data() {
		return this.File_data;
	}

	private String url_json(String url) throws Exception{
		 URL conn1=new URL(url);
		 URLConnection openConnection =conn1.openConnection();
	     openConnection.addRequestProperty("User-Agent", Browser);
		 InputStream in = openConnection.getInputStream();
		 String data = "";
		 String line = "";
		 try {
			  BufferedReader buf = new BufferedReader(new InputStreamReader( in ) );
			  while ( ( line = buf.readLine() ) != null ) {
			      data+= line;
			  }
		    } finally {
			  in.close();
		    }
		 
		 return data;
	}
	
	public void load_file(String url ,String file_data, String[] dir_attribute , 
			String item_attribute, String item, String attribute_return)   {

	     if(!url.isEmpty() && !file_data.isEmpty()) {
			 try {
				 String data = url_json(url);
				 JSONObject obj = (JSONObject)JSONValue.parseWithException(data);
				 int i =1;
				 boolean T = true;
				 JSONObject test = new JSONObject();
				 JSONObject app = (JSONObject) (obj.get(dir_attribute[0]));	
				 JSONArray dir = new JSONArray();
				 if (app!=null) {
					while ((i<=dir_attribute.length-1) && T && app.containsKey(dir_attribute[i])) {
					 if((i==dir_attribute.length-1 )& T ) {
						 dir = (JSONArray) (app.get(dir_attribute[i]));
					    }
					 if((i!=dir_attribute.length )& T & dir.isEmpty()) {
						 test=  (JSONObject) (app.get(dir_attribute[i]));
					 }
				     if (test!=null) {
				     	app=test;
				     	i++;
				       }else {
				    	 T=false;
				       }
				    } 
				 }
				 if (T) {
					 for(Object o: dir){
						  if ( o instanceof JSONObject ) {
							  JSONObject it = (JSONObject)o; 
						      String format = (String)it.get(item_attribute);
						      if(format.equals(item)) {
						    	this.UrlD = (String)it.get(attribute_return);  
						        download(this.UrlD,file_data );
						        }  
						  }
					 }
				 }
			 } catch (ParseException e) {
				  e.printStackTrace();//
			 } catch (Exception e) {
			     e.printStackTrace();//
			 }
			 
	     }
		   
   }
			   
	@SuppressWarnings("unused")
	private static void download(String url, String fileName) throws Exception {
		if ( Files.notExists(Paths.get(fileName))) {
			final String folder = System.getProperty("user.dir");
			File D =  new File(folder,fileName);
		}
		try (InputStream in = URI.create(url).toURL().openStream()) {
	        Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
	    } 
	} 
	
	private String test( String s) {
		if(!s.isEmpty()) return s;
		else return "0";
	}
	
	private List<Data_Model> processing(String file) {
		List<Data_Model> temp = new ArrayList<Data_Model>();
		
		if(!file.isEmpty()) {
			int i =0;
			try{
			BufferedReader stream=new BufferedReader(new FileReader (file));
			String line= new String();
			   while(( line = stream.readLine())!=null) {
				   if(i!=0  && !(line.isEmpty())) {  
				   String l[] =line.split(COMMA_DELIMITER);
				   String Country = l[0];
				   String NUTS1_ID = l[1];
				   String NUTS2_ID = l[2];
				   String NUTS2_name = l[3];
				   String Fund = l[4];
				   int Year = Integer.parseInt(l[5]);
				   String[] y=l[6].split("-");
				   int[] Progamming_Period = {Integer.parseInt(y[0]),Integer.parseInt(y[1])};
				   int EU_Payment_annual = Integer.parseInt(test(l[7]));
				   int Modelled_annual_expenditure = Integer.parseInt(test(l[8]));
				   int Standard_Deviation_of_annual_expenditure = Integer.parseInt(test(l[9]));
				   int Standard_Error_of_modelled_annual_expenditure = Integer.parseInt(test(l[10]));
				   Data_Model app =new Data_Model(Country,NUTS1_ID,NUTS2_ID,NUTS2_name,Fund,Year,Progamming_Period,
						   EU_Payment_annual,Modelled_annual_expenditure,Standard_Deviation_of_annual_expenditure,
						   Standard_Error_of_modelled_annual_expenditure); 
				   temp.add(app);
				      } 
				   if (i==0) {
					    this.setAttribute_Data(line.split(COMMA_DELIMITER));
				   }
				   i++;
			   }
			if(i!=1)stream.close();
		    } catch ( IOException e) {
				e.printStackTrace();
		    } catch ( java.lang.NumberFormatException b) {
		    	b.printStackTrace();
		    }
		}
		return temp;
	}

	@Override
	public Map<String, Map<String, ?>> get_Model() {
		Probability_Model app = new Probability_Model(this.getData(),this.getAttribute_Data(),new Data_Model());
		return app.get_Model();
	}

	@Override
	public  List<Data_Model> set_Model(Data_Model filter) {
		Probability_Model app = new Probability_Model(this.getData(),this.getAttribute_Data());
		return app.set_Model(filter);

	}

   

	 
}

