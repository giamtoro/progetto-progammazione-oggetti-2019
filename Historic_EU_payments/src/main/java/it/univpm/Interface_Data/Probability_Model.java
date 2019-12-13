package it.univpm.Interface_Data;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.Model.Data_Model;


/**
 * quasta  classe computa i dati del datset completo dato della classe Data_set
 * e rida le statitiche del modello 
 * esteso a {@link Data_set}
 * la scansione  della classe  è data da 
 * {@link Component}
 *  @see       Data_set
 *  @see       Data_Model
 * @author gianmarco troni
 * @version 1.4
 *
 */

@Component("Probability_Model")
/** 
 * annotazione di spring che scansiona la classe
 */
public class Probability_Model  extends Data_set{

     //atributi
	 private int n;
	 private Data_Model filter =new Data_Model();
	 private List<Data_Model> data = new ArrayList<Data_Model>();
	 
	 //metodi

	 /** 
	    * Class constructor.
	    * costruttore del  processo del modello dei dati
	     * legato una  classe astratta Data_Set
	     * @param attribute atributi metadata dataset
	     * @param Data tutto il dataset
	     * @param in modello di ndati usato come filtro
	     */
	    
	 public Probability_Model( List<Data_Model> Data,String[] attribute ,Data_Model in) {
			super(Data,attribute);//costritore classe astratta
			this.setData(Data);// settagio dataset completo
			this.setAttribute_Data(attribute);// settagio atributi dataset
			this.data=get_data(Data, in); //filtraggio dati
			this.filter=in; //filtro
			this.n=this.data.size();//settagi dimensione
		}
	
	 /** 
	    * Class constructor.
	    * costruttore del  processo del modello dei dati
	    * ingresso solo daset completo
	    * legato una  classe astratta Data_Set
	    * @param attribute atributi metadata dataset
	     *@param Data tutto il dataset
	    */	
	 public Probability_Model( List<Data_Model> Data,String[] attribute ) {
			super(Data,attribute);//costritore classe astratta
			this.n=0;
		}
	
	 /** 
	    * Class constructor.
	    * costruttore dati vuoti 
	    */	
	public Probability_Model() {
			super();//costritore classe astratta vuoto
			this.n=0;
		}
	
	
	/** 
	 * dimensione dati filtrati getter
     * @return dimensione dati
     */
	public int getN() {
		return n;
	}
	
	
	/** 
	 * modello di filtro getter
     * @return modello dati che fa da filtro
     */
	protected Data_Model getFilter() {
		return filter;
	}

	/** 
	 * datset filtrato getter
     * @return datset filtrato
     */
	public List<Data_Model> get_Data() {
		return data;
	}
	
	/** 
     * setta i dati filtrati ma deve
     * avere datase completo e atributi dei metadata
     * esistenti della classe astratta legata
     * funzione legata alla clesse astratta
     * @param filter modello dati che fa da filtro
     */
	@Override
	public List<Data_Model> set_Model(Data_Model filter) {
			List<Data_Model> ris = new ArrayList<Data_Model>();
			if(this.data.isEmpty() & !filter.equals(new Data_Model())) {
				this.data = this.get_data(this.getData(), filter);
			    this.filter = filter;
		    	this.n = this.data.size();
			}
			ris=this.data;
			return ris;
	}
		//dataset non vuoto e atributi dei metadata esistenti
		
	/** 
     * funzione che crea il modello di dato
     * per ogni parametro del modello
     * @param d_set dati int filtrati da filtro
     * @return statistiche del dataset filtrato
     */
	private Map<String,Map<String,?>> set_data(List<List<Integer>> d_set){
		Map<String,Map<String,?>> ris = new HashMap<String,Map<String,?>>();//mappa che ritonerà la funzione
		int o =0;
		int j =0;
		int i =0;
	    for( String at : this.getAttribute_Data()) { //cicli degli atributi
	        if(o>4 & o<=this.getAttribute_Data().length-1) {//atributi di tipo in e in[]
	        	j =i+o-5;
	        	if( at.contentEquals("Year") & (this.filter.getYear()==0)) {
	        		Map<String,Integer> app =new HashMap<String,Integer>();
	        		app.put("mean", (int)this.mean(d_set.get(j), 0));//media
	        		app.put("min", (int)this.min(d_set.get(j), 0));//minimo
	        		app.put("max", (int)this.max(d_set.get(j), 0));//massimo
	        		app.put("std", (int)this.std(d_set.get(j), 0));//derivata standard
	        		app.put("count", getN());//numero elementi
	        		ris.put(at, app);
	        	} else if(at.contentEquals("Programming_Period") & (this.filter.getProgramming_Period()[0]==0 &
	        			this.filter.getProgramming_Period()[1]==0)) {
	        		Map<String,List<?>> app =new HashMap<String,List<?>>();
	        		//conto il numero di elemnti unici
	        		List<Map<List<Integer>,Integer>> F=count_intv(this.getD_int(),0);
	        		app.put("count",  F);
	        		//minimo
	        		List<Integer> intv_m = new ArrayList<Integer>();
	        		intv_m.add((int)this.min(d_set.get(j), 0));
	        		intv_m.add((int)this.min(d_set.get(j+1), 0));
	        		//massimo
	        		List<Integer> intv_M = new ArrayList<Integer>();
	        		intv_M.add((int)this.max(d_set.get(j), 0));//minimo
	        		intv_M.add((int)this.max(d_set.get(j+1), 0));//minimo
	        		app.put("min", intv_m);
	        		app.put("max", intv_M);
	        		ris.put(at, app);
	        		i++;//il su dataset di Programming_Period è compsto da due liste 
	        		// faccio un iterazione in più 
	        	} else if(at.contentEquals("Standard_Deviation_of_annual_expenditure")) {
	        		Map<String,Integer> app =new HashMap<String,Integer>();
	        		app.put("mean", (int)this.mean(d_set.get(j), 0));//media
	        		app.put("min", (int)this.min(d_set.get(j), 0));//minimo
	        		app.put("max", (int)this.max(d_set.get(j), 0));//massimo
	        		app.put("std", (int)this.std(d_set.get(j), 0));//derivata standard
	        		app.put("count", getN());//numero elementi
	        		ris.put(at, app);
	        	} else if (j>=2){
	        		Map<String,Integer> app =new HashMap<String,Integer>();
	        		app.put("mean", (int)this.mean(d_set.get(j), 0));//media
	        		app.put("min", (int)this.min(d_set.get(j), 0));//minimo
	        		app.put("max", (int)this.max(d_set.get(j), 0));//massimo
	        		app.put("std", (int)this.std(d_set.get(j), 0));//derivata standard
	        		app.put("sum", (int)this.sum(d_set.get(j), 0));//somma
	        		app.put("count", getN());//numero elementi
	        		ris.put(at, app);
	        	}
	        	
	        } else {
	        	Map<String,List<?>> app =new HashMap<String,List<?>>();
	        	List<Map<String,Integer>> c_str=count(this.getD_str(),o,0);
	        	//conto il numero di elemnti unici
	        	app.put("count", c_str);
	        	ris.put(at, app);
	        }
	    	o++;
	    }
		return ris;
	}

	 /** 
	 * funzione che crea le liste dei dati
	 * e ritorna le statitche dei dati
	 * funzione legata alla clesse astratta
     * @see Data_set#get_Model()
	 * @return le statistiche del datse flitrato
	 */
   public  Map<String,Map<String,?>> get_Model(){
   	Data_Model in =this.getFilter();//prendo filtro
   	List<Data_Model> data =this.getData();//prendo data
   	//mappa che ritonera il modello
   	Map<String,Map<String,?>> ris = new HashMap<String,Map<String,?>>();
   	boolean v = true;//var boolean per sapre se è sato trovato
   	//dataset int
   	List<List<Integer>> d_set = new ArrayList<List<Integer>>();
   	 if (!data.isEmpty() | !in.equals(new Data_Model() )) {
		    List<Integer> d_in  = new ArrayList<Integer>();
		    //dati filtro int
		    //Year 
		    d_in.add(in.getYear());
		    //dati filtro int[]
		    //progam_period 
		    d_in.add(in.getProgramming_Period()[0]);
		    d_in.add(in.getProgramming_Period()[1]);
   	    //ciclo dei dati int del dataset
   	    for(List<Integer> d : this.getD_int()) {
   	    	if(v) {
   	    		int i=0;
   	    		//ciclo dati int dove crea le liste per ogni parametro
   	    		for(int f=0;f<=d.size()-1;f++) {
   	    			List<Integer> d_tmp = new ArrayList<Integer>();
   	    			//le crea la lista se il dato int e in[] che occupa due posizioni
   	    			// se 0 sul filtro o quando ha finto i dati int e in[] che occupa due posizioni
   	    			if((d_in.get(i)==0)| (f>2)){
   	    			 d_tmp.add(d.get(f));
   	    			 d_set.add(d_tmp);
   	    			 if(f<2)i++;
   	    			} else { 
   	    			// crea lista vuota per ogni altro
   	    			//dato int
   	    			 d_tmp.add(0);
      	    			 d_set.add(d_tmp);  	
   	    			 i++;
   	    			}
   	    		}
 		        } else {
 		        	int i=0;
   	    		for(int f=0;f<=d.size()-1;f++) {
   	    		    //inserisce la lista se il dato int e in[] che occupa due posizioni
   	    			// se 0 sul filtro o quando ha finto i dati int e in[] che occupa due posizioni
   	    			if((d_in.get(i)==0) | (f>2)){
   	    				d_set.get(f).add(d.get(f));
   	    				if(f<2)i++;
   	    			} else {
   	    			    // inserisce nella lista per ogni altro
   	   	    			//dato int
   	    				d_set.get(f).add(0);
   	    				i++;
   	    			}
   	    		}
 		        }
   	    	if(v)v=false;
   	    }
        ris=this.set_data(d_set);//funzione crea statistiche del modello
   	}
		return ris;
   }
   	
   /** 
    * funzione che conta gli elementi unici
    * datset delle stringhe
    * @param d_set dataset string
    * @param sel elemento del atributo del metadata selezionato
    * @return mappa elementi unici contati
    */
   private List<Map<String,Integer>> count(List<List<String>> dataSet,int sel, int s) {
	   //mappa dove inserisce elementi unici e come valore ha il numero di volte che 
	   //vengono trovati
	   List<Map<String,Integer>> ris = new ArrayList <Map<String,Integer>>(); 
   	if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;
			for(int i=0;i<=s;i++) {
				String el=dataSet.get(i).get(sel);
                boolean A = true;//var boolean per inzializare liste o aggungere liste
				if (!ris.isEmpty() & ris.size()>1) {
					 //sel lista ris ha più un elemento
					 //viene confrontato con gli atri elem. unici
					for( int j=0;j<=ris.size()-1;j++) {
				   	if(ris.get(j).containsKey(el)) {
				    	int old = ris.get(j).get(el);
				    	//aggiorna elemto unico trovato
				    	ris.get(j).replace(el,old+1);
				    	A=false;
				   	  }
				  	}
				  } else if (!ris.isEmpty() & ris.size()==1) {
					  //sel lista ris ha solo un elemento
					  //viene confrontato solo con il primo
				  if(ris.get(0).containsKey(el)) {
				    	int old = ris.get(0).get(el);
				    	//aggiorna elemto unico trovato
				    	ris.get(0).replace(el,old+1);
				    	A=false;
				  } 
				}
				if (A){
					//se la lita ris è vuota o la stringa 
					//non esiste negli elemti unici
					Map<String,Integer> app =new HashMap<String,Integer>();
					//viene aggunta una elemto alla lista
				    app.put(el, 1);
				    ris.add(app);
				}
			  }
			}
   	
   	return ris;
   }
  
   /** 
    * funzione che conta gli elementi unici
	* datset delle int[]
    * @param d_set dataset int[]
	* @param s indica dimensoni minori della grandezza dlella lista
    * @return mappa elementi unici contati
    */
	private List<Map<List<Integer>,Integer>> count_intv(List<List<Integer>> dataSet, int s) {
		   //mappa dove inserisce elementi unici e come valore ha il numero di volte che 
		   //vengono trovati
	   List<Map<List<Integer>,Integer>> ris = new ArrayList <Map<List<Integer>,Integer>>();  
	   //controlla sel il valore grandezza  a controllare è
	   // minore della grandezza della lista o è maggire di zero o  è
	   //maggiore di zero
   	if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;//se s==0 fa tutta la lista
			for(int i=0;i<=s;i++) {
				List<Integer> el=dataSet.get(i).subList(1, 3);
                boolean A = true;
				if (!ris.isEmpty() & ris.size()>1) {
					//sel lista ris ha più un elemento
					 //viene confrontato con gli atri elem. unici
					for( int j=0;j<=ris.size()-1;j++) {
				   	if(ris.get(j).containsKey(el)) {
				    	int old = ris.get(j).get(el);
				    	//aggiorna elemto unico trovato
				    	ris.get(j).replace(el,old+1);
				    	A=false;
				   	  }
				  	}
				  } else if (!ris.isEmpty() & ris.size()==1) {
				  if(ris.get(0).containsKey(el)) {
						//se la lita ris è vuota o la stringa 
						//non esiste negli elemti unici
				    	int old = ris.get(0).get(el);
				    	//aggiorna elemto unico trovato
				    	ris.get(0).replace(el,old+1);
				    	A=false;
				  } 
				}
				if (A){
					//se la lita ris è vuota o la stringa 
					//non esiste negli elemti unici
					Map<List<Integer>,Integer> app =new HashMap<List<Integer>,Integer>();
					//viene aggunta una elemto alla lista
				    app.put(el, 1);
				    ris.add(app);
				}
			  }
			}
   	
   	return ris;
   }
   
	/** 
	* funzione che somma elementi
    * di datset delle int
	* @param d_set lista dataset int
	* @param s indica dimensoni minori della grandezza dlella lista
	* @return la somma delgli elementi del dataset 
	*/
   private int sum(List<Integer> dataSet, int s) {
   	int sum =0;
       //controlla sel il valore grandezza  a controllare è
	   // minore della grandezza della lista o è maggire di zero o  è
	   //maggiore di zero
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;//se s==0 fa tutta la lista
			for(int i=0;i<=s;i++) {
				sum += dataSet.get(i);
			}
		}
		return sum;	
   }
   
   /** 
	* funzione trova massimo
    * di datset delle int
	* @param d_set lista dataset int
	* @param s indica dimensoni minori della grandezza dlella lista
	* @return massimo elemento del dataset 
	*/
   private int max(List<Integer> dataSet, int s) {
		int max =0;
		//controlla sel il valore grandezza  a controllare è
		// minore della grandezza della lista o è maggire di zero o  è
		//maggiore di zero
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;//se s==0 fa tutta la lista
			for(int i=0;i<=s;i++) {
				if(max<dataSet.get(i))max=dataSet.get(i);
			}
		}
		return max;
	}
 
   /** 
	* funzione trova minimo
    * di datset delle int
	* @param d_set lista dataset int
	* @param s indica dimensoni minori della grandezza dlella lista
	* @return minimo elemento del dataset 
	*/
   private int min(List<Integer> dataSet, int s) {
		int min =0;
		//controlla sel il valore grandezza  a controllare è
		// minore della grandezza della lista o è maggire di zero o  è
		//maggiore di zero
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;//se s==0 fa tutta la lista
			for(int i=0;i<=s;i++) {
				//inziliza minimo
				if(i==0)min=dataSet.get(i);
				//setta minimo se anche il dato è maggiore di zero
				if((min>dataSet.get(i)) & (Math.abs(dataSet.get(i)))>=0)min=dataSet.get(i);
			}
		}
		return min;
	}

   /** 
	* funzione calcola media
    * di datset delle int
	* @param d_set lista dataset int
	* @param s indica dimensoni minori della grandezza dlella lista
	* @return la media del dataset 
	*/
   private double mean(List<Integer> dataSet, int s) {
		double mean =0;
		int sum = sum(dataSet,s);//somma valori
		//controlla sel il valore grandezza  a controllare è
		// minore della grandezza della lista o è maggire di zero o  è
		//maggiore di zero
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;//se s==0 fa tutta la lista
		 mean=Math.abs(sum)/s;
		}
		return mean;
	}

   /** 
	* funzione calcola varianza
    * di datset delle int
	* @param d_set lista dataset int
	* @param s indica dimensoni minori della grandezza dlella lista
	* @return la varianza del dataset 
	*/
   private double var(List<Integer> dataSet, int s) {
		double var =0;
		double mean=mean(dataSet,s);//media del dataset 
		double app =0;
		int sum = 0;
		//controlla sel il valore grandezza  a controllare è
		// minore della grandezza della lista o è maggire di zero o  è
		//maggiore di zero
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;//se s==0 fa tutta la lista
			for(int i=0;i<=s;i++) {
				app = Math.pow(2,dataSet.get(i)-mean);
				sum += app;
			}
			var=sum/(s-1);
		}
		return var;
	}
  
   /** 
  	* funzione calcola derivata standard
    * di datset delle int
  	* @param d_set lista dataset int
  	* @param s indica dimensoni minori della grandezza dlella lista
  	* @return la derivata standard del dataset 
  	*/
   private double std(List<Integer> dataSet, int s) {
		double var=var(dataSet,s);//calcolo varianza
		return Math.sqrt(var);
	}

	
}
