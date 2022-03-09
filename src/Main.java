import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int[][] queues = {// tests
			      new int[0], // G
			      new int[0], // 1
			      new int[0], // 2
			      new int[0], // 3
			      new int[0], // 4
			      new int[0], // 5
			      new int[0],	// 6
			      new int[0],	// 7
			      new int[0], //8
			      new int[0], //9
			      new int[0]}; // 10
	int cap = 5;// 0,1,2,3,4 = 5		
	for(var e: theLift(queues, cap))
		System.out.print(e+"\n");
	}
	 
	public static int[] theLift(final int[][] queues, final int capacity) {
		List<Integer> lift = new ArrayList<>(capacity);
		ArrayList<Integer> stopsAt = new ArrayList<>(); // aka solution
		ArrayList<List<Integer>> convertQue = new ArrayList<>();//List to remove actual queues

		convertQue = convertToObjTypeQue(convertQue, queues);
		// I have used 'final int[][]' array as a metaphor for some logical signal boxes
		// that have functions to read Up, Down buttons and set of floors that u can go to
			for(int i=0; i<convertQue.size(); ++i) {
				collectUpCall(convertQue, lift, stopsAt, capacity);
				collectDownCall(convertQue, lift, stopsAt, capacity);
				}	
				
				if(stopsAt.size()>0) {
					if(stopsAt.get(stopsAt.size()-1) != 0) {
						stopsAt.add(0);}// return to g floor
				}
				else stopsAt.add(0);// for empty test - stay at g floor
		// convert to int[] type
		int[] solution = new int[stopsAt.size()];
		for(int i=0; i<stopsAt.size(); ++i)
			solution[i] = stopsAt.get(i);
		
		return solution;
	}
	
	public static ArrayList<List<Integer>> convertToObjTypeQue (ArrayList<List<Integer>> convert, int[][] que) {
		for(int i=0; i<que.length; ++i) {
			convert.add(new ArrayList<>());
			for(int r=0; r<que[i].length; ++r) {
				convert.get(i).add(que[i][r]);
			}
		}
		return convert;
	}
	
	public static void collectUpCall(ArrayList<List<Integer>> convertQue, List<Integer> lift, ArrayList<Integer> stopsAt, int cap) {
		
		Set<Integer> set = new LinkedHashSet<>();
		
		for(int i=0; i<convertQue.size(); ++i) {
			// remove n que when lift arrives at floor n 
		   // and check where did it all stop - solution 
			if(lift.contains(i)) {	
				for(var e: set) {
					if(stopsAt.size() > 0) {
						if(!stopsAt.get(stopsAt.size()-1).equals(e))
							stopsAt.add(e);	}
					else if(stopsAt.size() == 0) {
						stopsAt.add(e);
					}
				}
				if(!stopsAt.get(0).equals(0)) {
					stopsAt.add(0, 0);}// starts from g floor
				if(!stopsAt.get(stopsAt.size()-1).equals(i)) {
					stopsAt.add(i);	}
				set.clear();
				do{
					//System.out.println("Removing from lift:"+i+"\n");
					lift.remove((Integer) i);}while(lift.contains(i));
			}
			// collect if going up
			if(convertQue.get(i).size()>0) {// if someone is in que
				for(int p=0; p<convertQue.get(i).size(); ++p) {// who's in que
					if(convertQue.get(i).get(p) > i) { // going up
						if(lift.size()<cap) {
							//System.out.println("Adding in lift:" +convertQue.get(i).get(p)+"\n"
									//+"And removing from queues"+ convertQue.get(i).get(p));
							lift.add(convertQue.get(i).get(p));// to go, add in lift
							convertQue.get(i).remove(p);
							--p; // because removal of element
							//System.out.println("At floor:"+i+"\n");
						}
						else {
							//System.out.println("Max Cap");
						}
						set.add(i);
					}
				}
			}	
		}
	}
	
	public static void collectDownCall(ArrayList<List<Integer>> convertQue, List<Integer> lift, ArrayList<Integer> stopsAt, int cap) {
		
		Set<Integer> set = new LinkedHashSet<>();
		
		for(int i=convertQue.size()-1; i>=0; --i) {
			// remove n que when lift arrives at floor n 
			// and check where did it all stop - solution 
			if(lift.contains(i)) {	
				for(var e: set) {
					if(stopsAt.size() > 0) {
						if(!stopsAt.get(stopsAt.size()-1).equals(e))
							stopsAt.add(e);	}
					else if(stopsAt.size() == 0) {
						stopsAt.add(e);
					}
				}
				if(!stopsAt.get(0).equals(0)) {
					stopsAt.add(0, 0);}// starts from g floor
				if(!stopsAt.get(stopsAt.size()-1).equals(i)) {
					stopsAt.add(i);	}
				set.clear();
				do{
					//System.out.println("Removing from lift:"+i+"\n");
					lift.remove((Integer) i);}while(lift.contains(i));
			}
			// collect if going down
			if(convertQue.get(i).size()>0) {
				for(int p=0; p<convertQue.get(i).size(); ++p) {
					if(convertQue.get(i).get(p) < i) { // going down
						if(lift.size()<cap) {
							//System.out.println("Adding in lift:" +convertQue.get(i).get(p)+"\n"
									//+"And removing from queues"+ convertQue.get(i).get(p));
							lift.add(convertQue.get(i).get(p));// to go
							convertQue.get(i).remove(p);
							--p;
							//System.out.println("At floor:"+i+"\n");

						}
						else{
							//System.out.println("Max Cap");
						}
						set.add(i);
					}
				}
			}
			
		}
	}
}
