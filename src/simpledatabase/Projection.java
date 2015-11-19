package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;
	private Tuple tuple1;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @param tuple the tuple retrieved by table's next() method
     * @param attributePredicate the selected column's name of SQL
     * @param newAttributeList an array list that stores attributes of tuple if it matches attributePredicate
     * @param tuple1 a new Tuple that stores newAttributeList
     * @return tuple
     * @exception return null if it reaches the end of the .csv file
     */
	@Override
	public Tuple next(){
		try{
			// if the tuple received by next() method matches attributePredicate, return that tuple
			Tuple tuple = child.next();
			for (int i = 0; i < tuple.getAttributeList().size(); i++){
				if (tuple.getAttributeName(i).equals(attributePredicate)){
					newAttributeList.add(tuple.getAttributeList().get(i));
					tuple1 = new Tuple(newAttributeList);
					newAttributeList = new ArrayList<Attribute>();
					return tuple1;
				}
			}
			return null;
			
		}
		catch (Exception e) {
			// return null if table reaches the end of .csv file
			return null;
		}
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}