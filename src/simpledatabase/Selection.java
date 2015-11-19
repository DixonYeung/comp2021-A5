package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @param tuple the tuple retrieved from the Table
     * @param whereTablePredicate the string that stores the table name of SQL
     * @param whereAttributePredicate the string that stores the 'Name' of designated column
     * @param whereValuePredicate the string that stores the 'Value' of designated cell
     * @return the tuple
     * @exception return null if it reaches the end of the .csv file
     */
	@Override
	public Tuple next(){
		try{
			// Get the tuple from Table, if the Table doesn't match the wheretablePredicate, return everything of that Table
			Tuple tuple = child.next();
			if (!(child.from.equals(whereTablePredicate))){
				return tuple;
			}
			
			// if the tuple is not null, return the tuple if it matches the where condition
			while (tuple != null){
				for (int i = 0; i < tuple.getAttributeList().size(); i++){
					if (tuple.getAttributeName(i).equals(whereAttributePredicate)){
						if (tuple.getAttributeValue(i).equals(whereValuePredicate)){
							return tuple;
						}
					}
				}
				// Get next tuple from Table
				tuple = child.next();
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}