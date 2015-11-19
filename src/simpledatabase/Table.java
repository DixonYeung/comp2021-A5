package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	
	// instantiate three string to store tuple's Name, Type and Value
	private String col;
	private String col1;
	private String col2;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @param getAttribute boolean that indicates if 'Name' and 'Type' of tuple is retrieved
     * @param col 'Name' of tuple
     * @param col1 'Type' of tuple
     * @param col2 'Value' of tuple
     * @param tuple the tuple to be returned
     * @return the tuple
     * @exception return null if it reaches the end of the .csv file
     */
	@Override
	public Tuple next(){
		
		try{
			
			// Name and Type need to be stored before storing each tuple's Value
			while(getAttribute != true){
				col = br.readLine();
				col1 = br.readLine();
				getAttribute = true;
			}
			
			// store each tuple's Value and use the information above to create a new tuple and return it
			col2 = br.readLine();
			tuple = new Tuple(col, col1, col2);
			tuple.setAttributeName();
			tuple.setAttributeType();
			tuple.setAttributeValue();
			return tuple;
		}
		catch (Exception e) {
			// return null if it reaches the end of .csv file
			return null;
		}
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}