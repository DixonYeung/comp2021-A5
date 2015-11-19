package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	private boolean getTuple = true;
	// size is the number of row of the left-table
	private int size = 0;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @param tuple2 the tuple retrieved from right-table
     * @param tuple1 the tuple retrieved from left-table
     * @param size the number of rows of left-table
     * @param getTuple boolean that indicate if every tuple of left-table have been stored already
     * @param tuples1 ArrayList that stores every tuple of left-table
     * @param leftcolumn the position of column of left-table that two tables both share
     * @param joincolumn the position of column of right-table that two tables both share
     * @param newAttributeList ArrayList that stores the attributes of each tuple after joining two tables
     * @param tuple3 a new tuple that stores the attribute of newAttributeList
     * @return the new joined tuple
     * @exception return null if it reaches the end of the .csv file
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		try{
			
			// each tuple retrieved from right table
			Tuple tuple2 = rightChild.next();
			
			// store every tuple of the left table into array list : tuples1
			while(getTuple != false){
				Tuple tuple1 = leftChild.next();
				while (tuple1 != null){
					tuples1.add(tuple1);
					tuple1 = leftChild.next();
					size++;
				}
				getTuple = false;
			}
			
			// mark the position of column of corresponding table that two tables both share
			int leftcolumn = 0;
			int joincolumn = 0;
			for (int i = 0; i < tuple2.getAttributeList().size(); i++){
				for (int j = 0; j < tuples1.get(0).getAttributeList().size(); j++){
					if (tuple2.getAttributeName(i).equals(tuples1.get(0).getAttributeName(j))){
						joincolumn = i;
						leftcolumn = j;
					}
				}
			}
			
			// join each tuple of two table together and store them into array list : newAttributeList
			for (int i = 0; i < size; i++){
				if (tuple2.getAttributeValue(joincolumn).equals(tuples1.get(i).getAttributeValue(leftcolumn))){
					for (int j = 0; j < tuple2.getAttributeList().size()-1; j++){
						newAttributeList.add(tuple2.getAttributeList().get(j));
					}
					for(int k = 0; k < tuples1.get(i).getAttributeList().size(); k++){
						newAttributeList.add(tuples1.get(i).getAttributeList().get(k));
					}
				}
			}
			
			// return the tuple after joining and wipe out the array list to store next joins
			Tuple tuple3 = new Tuple(newAttributeList);
			newAttributeList = new ArrayList<Attribute>();
			return tuple3;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}