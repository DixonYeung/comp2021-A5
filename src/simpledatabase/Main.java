package simpledatabase;

public class Main {
	
	public static void main(String[] args){
		String selectText;
	    String fromText;
	    String joinText;
	    String whereText;
	    String orderText;
	    FormRAtree tree;
	    Operator root;
	    Tuple tuple;
		boolean next = true;
		
		/*SQL Statement: SELECT Name 
		*				 FROM Student 
		*				 JOIN CourseEnroll 
		*				 WHERE CourseEnroll.courseID="COMP2021" 
		*				 ORDER BY id;*/

		   int cnt = 0;
		   selectText = "Name";
	       fromText = "Student";
	       joinText = "CourseEnroll";
	       whereText = "CourseEnroll.courseID=\"COMP2021\"";
	       orderText = "id";
	       
	       tree = new FormRAtree(selectText,fromText,joinText,whereText,orderText);
	       root = tree.structureTree();
	       
	       tuple = root.next();
	       while(tuple != null){
	       	if(next == false)
	       		tuple = root.next();
				
				if(cnt == 0){
					//eric
					System.out.println(tuple.getAttributeValue(0));
					cnt++;
				}
				else if(cnt == 1){
					//chris
					System.out.println(tuple.getAttributeValue(0));
					cnt++;
				}
				else if(cnt == 2){
					//chr
					System.out.println(tuple.getAttributeValue(0));
					cnt++;
				}
				
				next = false;
	       }
		}

	}