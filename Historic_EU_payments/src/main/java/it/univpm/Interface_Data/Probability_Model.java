package it.univpm.Interface_Data;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

import it.univpm.Model.Data_Model;




@Component("Probability_Model")
public class Probability_Model  extends Data_set{


	 private int n;
	 private Data_Model filter =new Data_Model();
	 private List<Data_Model> data = new ArrayList<Data_Model>();
	 
	 public Probability_Model( List<Data_Model> Data,String[] attribute ,Data_Model in) {
			super(Data,attribute);
			this.setData(Data);
			this.setAttribute_Data(attribute);
			this.data=get_data(Data, in);
			this.filter=in;
			this.n=this.data.size();
		}
		
	 public Probability_Model( List<Data_Model> Data,String[] attribute ) {
			super(Data,attribute);
			this.n=0;
		}
		
	public Probability_Model() {
			super();
			this.n=0;
		}
	
	public int getN() {
		return n;
	}
	
	protected Data_Model getFilter() {
		return filter;
	}
	
	public List<Data_Model> get_Data() {
		return data;
	}
	
	public  List<Data_Model> set_Model(Data_Model in) {
		List<Data_Model> ris = new ArrayList<Data_Model>();
		if(n==0 & this.data.isEmpty() & this.filter.equals(new Data_Model()) 
				& !in.equals(new Data_Model())) {
			this.data = this.get_data(this.getData(), in);
		    this.filter = in;
	    	this.n = this.data.size();
		}
		ris=this.data;
		return ris;
	}
	
	private Map<String,Map<String,?>> set_data(List<List<Integer>> d_set){
		Map<String,Map<String,?>> ris = new HashMap<String,Map<String,?>>();
		int o =0;
		int j =0;
		int i =0;
	    for( String at : this.getAttribute_Data()) {
	        if(o>4 & o<=this.getAttribute_Data().length-1) {
	        	j =i+o-5;
	        	if( at.contentEquals("Year") & (this.filter.getYear()==0)) {
	        		Map<String,Integer> app =new HashMap<String,Integer>();
	        		app.put("mean", (int)this.mean(d_set.get(j), 0));
	        		app.put("min", (int)this.min(d_set.get(j), 0));
	        		app.put("max", (int)this.max(d_set.get(j), 0));
	        		app.put("std", (int)this.std(d_set.get(j), 0));
	        		app.put("count", getN());
	        		ris.put(at, app);
	        	} else if(at.contentEquals("Programming_Period") & (this.filter.getProgramming_Period()[0]==0 &
	        			this.filter.getProgramming_Period()[1]==0)) {
	        		Map<String,List<?>> app =new HashMap<String,List<?>>();
	        		List<Map<List<Integer>,Integer>> F=count_intv(this.getD_int(),0);
	        		app.put("count",  F);
	        		List<Integer> intv_m = new ArrayList<Integer>();
	        		intv_m.add((int)this.min(d_set.get(j), 0));
	        		intv_m.add((int)this.min(d_set.get(j+1), 0));
	        		List<Integer> intv_M = new ArrayList<Integer>();
	        		intv_M.add((int)this.max(d_set.get(j), 0));
	        		intv_M.add((int)this.max(d_set.get(j+1), 0));
	        		app.put("min", intv_m);
	        		app.put("max", intv_M);
	        		ris.put(at, app);
	        		i++;
	        	} else if(at.contentEquals("Standard_Deviation_of_annual_expenditure")) {
	        		Map<String,Integer> app =new HashMap<String,Integer>();
	        		app.put("mean", (int)this.mean(d_set.get(j), 0));
	        		app.put("min", (int)this.min(d_set.get(j), 0));
	        		app.put("max", (int)this.max(d_set.get(j), 0));
	        		app.put("std", (int)this.std(d_set.get(j), 0));
	        		app.put("count", getN());
	        		ris.put(at, app);
	        	} else if (j>=2){
	        		Map<String,Integer> app =new HashMap<String,Integer>();
	        		app.put("mean", (int)this.mean(d_set.get(j), 0));
	        		app.put("min", (int)this.min(d_set.get(j), 0));
	        		app.put("max", (int)this.max(d_set.get(j), 0));
	        		app.put("std", (int)this.std(d_set.get(j), 0));
	        		app.put("sum", (int)this.sum(d_set.get(j), 0));
	        		app.put("count", getN());
	        		ris.put(at, app);
	        	}
	        	
	        } else {
	        	Map<String,List<?>> app =new HashMap<String,List<?>>();
	        	List<Map<String,Integer>> c_str=count(this.getD_str(),o,0);
	        	app.put("count", c_str);
	        	ris.put(at, app);
	        }
	    	o++;
	    }
		return ris;
	}
	
   public  Map<String,Map<String,?>> get_Model(){
   	Data_Model in =this.getFilter();
   	List<Data_Model> data =this.getData();
   	Map<String,Map<String,?>> ris = new HashMap<String,Map<String,?>>();
   	boolean v = true;
   	List<List<Integer>> d_set = new ArrayList<List<Integer>>();
   	 if (!data.isEmpty() | !in.equals(new Data_Model() )) {
		    List<Integer> d_in  = new ArrayList<Integer>();
		    d_in.add(in.getYear());
		    d_in.add(in.getProgramming_Period()[0]);
		    d_in.add(in.getProgramming_Period()[1]);
   	   
   	    for(List<Integer> d : this.getD_int()) {
   	    	if(v) {
   	    		int i=0;
   	    		for(int f=0;f<=d.size()-1;f++) {
   	    			List<Integer> d_tmp = new ArrayList<Integer>();
   	    			if((d_in.get(i)==0)| (f>2)){
   	    			 d_tmp.add(d.get(f));
   	    			 d_set.add(d_tmp);
   	    			 if(f<2)i++;
   	    			} else {
   	    			 d_tmp.add(0);
      	    			 d_set.add(d_tmp);  	
   	    			 i++;
   	    			}
   	    		}
 		        } else {
 		        	int i=0;
   	    		for(int f=0;f<=d.size()-1;f++) {
   	    			if((d_in.get(i)==0) | (f>2)){
   	    				d_set.get(f).add(d.get(f));
   	    				if(f<2)i++;
   	    			} else {
   	    				d_set.get(f).add(0);
   	    				i++;
   	    			}
   	    		}
 		        }
   	    	if(v)v=false;
   	    }
        ris=this.set_data(d_set);
   	}
		return ris;
   }
   	

   private List<Map<String,Integer>> count(List<List<String>> dataSet,int sel, int s) {
	   List<Map<String,Integer>> ris = new ArrayList <Map<String,Integer>>();   
   	if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;
			for(int i=0;i<=s;i++) {
				String el=dataSet.get(i).get(sel);
                boolean A = true;
				if (!ris.isEmpty() & ris.size()>1) {
					for( int j=0;j<=ris.size()-1;j++) {
				   	if(ris.get(j).containsKey(el)) {
				    	int old = ris.get(j).get(el);
				    	ris.get(j).replace(el,old+1);
				    	A=false;
				   	  }
				  	}
				  } else if (!ris.isEmpty() & ris.size()==1) {
				  if(ris.get(0).containsKey(el)) {
				    	int old = ris.get(0).get(el);
				    	ris.get(0).replace(el,old+1);
				    	A=false;
				  } 
				}
				if (A){
					Map<String,Integer> app =new HashMap<String,Integer>();
				    app.put(el, 1);
				    ris.add(app);
				}
			  }
			}
   	
   	return ris;
   }
   

	private List<Map<List<Integer>,Integer>> count_intv(List<List<Integer>> dataSet, int s) {
	   List<Map<List<Integer>,Integer>> ris = new ArrayList <Map<List<Integer>,Integer>>();   
   	if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;
			for(int i=0;i<=s;i++) {
				List<Integer> el=dataSet.get(i).subList(1, 3);
                boolean A = true;
				if (!ris.isEmpty() & ris.size()>1) {
					for( int j=0;j<=ris.size()-1;j++) {
				   	if(ris.get(j).containsKey(el)) {
				    	int old = ris.get(j).get(el);
				    	ris.get(j).replace(el,old+1);
				    	A=false;
				   	  }
				  	}
				  } else if (!ris.isEmpty() & ris.size()==1) {
				  if(ris.get(0).containsKey(el)) {
				    	int old = ris.get(0).get(el);
				    	ris.get(0).replace(el,old+1);
				    	A=false;
				  } 
				}
				if (A){
					Map<List<Integer>,Integer> app =new HashMap<List<Integer>,Integer>();
				    app.put(el, 1);
				    ris.add(app);
				}
			  }
			}
   	
   	return ris;
   }
   
   private int sum(List<Integer> dataSet, int s) {
   	int sum =0;
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;
			for(int i=0;i<=s;i++) {
				sum += dataSet.get(i);
			}
		}
		return sum;	
   }
   
   private int max(List<Integer> dataSet, int s) {
		int max =0;
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;
			for(int i=0;i<=s;i++) {
				if(max<dataSet.get(i))max=dataSet.get(i);
			}
		}
		return max;
	}
	
   private int min(List<Integer> dataSet, int s) {
		int min =0;
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;
			for(int i=0;i<=s;i++) {
				if(i==0)min=dataSet.get(i);
				if((min>dataSet.get(i)) & (Math.abs(dataSet.get(i)))>=0)min=dataSet.get(i);
			}
		}
		return min;
	}
	
   private double mean(List<Integer> dataSet, int s) {
		double mean =0;
		int sum = sum(dataSet,s);
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;
		 mean=Math.abs(sum)/s;
		}
		return mean;
	}
	
   private double var(List<Integer> dataSet, int s) {
		double var =0;
		double mean=mean(dataSet,s);
		double app =0;
		int sum = 0;
		if (s<this.n-1 | s>0 | this.n>0) {
			if(s==0)s=this.n-1;
			for(int i=0;i<=s;i++) {
				app = Math.pow(2,dataSet.get(i)-mean);
				sum += app;
			}
			var=sum/(s-1);
		}
		return var;
	}
	
   private double std(List<Integer> dataSet, int s) {
		double var=var(dataSet,s);
		return Math.sqrt(var);
	}
	
}
