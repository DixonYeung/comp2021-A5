package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	// variables that is needed for next() method
	private boolean getjoin = true;
	int size = 0;
	int count = 0;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @param getjoin boolean indicate if all tuple retrieved have been stored already
     * @param tuplesResult an ArrayList that stores every tuple retrieved
     * @param size the number of row of retrieved table
     * @param position the column that the table is ordered by
     * @param temp an ArrayList that stores all value of that column
     * @param count an integer counting if all tuple is returned
     * @param newAttributeList ArrayList that stores the sorted tuple in sequence
     * @return tuple
     */
	@Override
	public Tuple next(){
		
		// retrieve all tuple from Table and save the number of row in this Table
		while(getjoin != false){
			Tuple tuple1 = child.next();
			while (tuple1 != null){
				tuplesResult.add(tuple1);
				tuple1 = child.next();
				size++;
			}
			getjoin = false;
		}
		
		/*
		for (int i = 0; i < size; i++){
			System.out.println(tuplesResult.get(i).getAttributeValue(2));
		}
		*/
		
		// saves the position of column that is ordered by
		int position = 0;
		for(int i = 0; i < tuplesResult.get(0).getAttributeList().size(); i++){
			if (tuplesResult.get(0).getAttributeName(i).equals(orderPredicate)){
				position = i;
			}
		}
		
		// get every cell of the column that is ordered by and sort them in ascending order
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < size; i++){
			temp.add(tuplesResult.get(i).getAttributeList().get(position).getAttributeValue().toString());
		}
		temp.sort(null);
		
		/*
		System.out.println();
		for (int i = 0; i < size; i++){
			System.out.println(temp.get(i));
		}
		System.out.println();
		*/
		
		// put each tuple that matches the sorted elements in sequence into newAttributeList and return a new tuple that stores that arrayList
		while (count < temp.size()){
			for (int j = 0; j < tuplesResult.size(); j++){
				if(temp.get(count).equals(tuplesResult.get(j).getAttributeValue(position).toString())){
					newAttributeList = tuplesResult.get(j).getAttributeList();
					
					count++;
					Tuple tupleresult = new Tuple(newAttributeList);
					newAttributeList = new ArrayList<Attribute>();
					
					return tupleresult;
				}	
			}
		}
		
		
		
		return null;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}