import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

public class Agent extends Thread{
	List<String> x = new ArrayList<>();
	List<String> it = new ArrayList<>();
	int z,time;
	Random ran;
	String name;
	
	public Agent(String na,int z,List<String> x) {  // Constructor
		super();
		ran = new Random();
		name=na;
		this.z=z;
		this.x=x;        // Stock
		List<String> it; // Items Booked ArrayList 
	}
	
	public List<String> getIt() {
		return it;
	}

	public String getAName() {
		return name;
	}

	public void setAName(String name) {
		this.name = name;
	}
	
	public void Buying() {  // Function to Book items and Buy them
		long time = System.currentTimeMillis();
		while(!x.isEmpty()) {  // while items ArrayList has elements
			try {
				it.add(x.get(0));       // Adds Item from ArrayList to 
				x.remove(0);            // Removes Item from Stock
				sleep(ran.nextInt(z));  // Sleeps Thread for random time within 0 and z 
				System.out.println(getAName()+" booked "+getIt());
			}
			catch(InterruptedException e){
				System.out.println("Agent: "+e.getMessage());
			}
			catch(NullPointerException e){
				System.out.println("Agent: "+e.getMessage());
			}
		}
		if(!getIt().isEmpty()) {
			int buy=JOptionPane.showOptionDialog(null, getAName()+" booked "+getIt()+", do you wish to buy?"
					,"Items Booked Status",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
					, null, null, null);     // Option Pane to ask Agent if he will Buy or Not
			if((System.currentTimeMillis()-time)>(z*1000) && buy==JOptionPane.YES_OPTION) { // I yes is chosen AND Time is more than max wait time
				JOptionPane.showMessageDialog(null, getAName()+" TIMED OUT \n"+"Items will be returned to stock",
						"Items Booked Status",JOptionPane.INFORMATION_MESSAGE); //Error Message
				x.addAll(getIt());  // Returns Items to Stock
				it.clear();         // Clears items booked ArrayList
			}
			if(buy==JOptionPane.NO_OPTION) { // If no is chosen
				x.addAll(getIt()); // Returns Items to Stock
				it.clear();        // Clears items booked ArrayList
			}
		}else { // If Agent couldn't book any items
			JOptionPane.showMessageDialog(null, getAName()+" couldn't buy any item.","Items Booked Status",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void run() {
		Buying(); // Calls Function
	}
}// Ahmed Sameh Yousry Mohamed Ahmed 119200098 
