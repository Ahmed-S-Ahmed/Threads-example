import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ASYa2 extends JFrame implements ActionListener,Runnable{
	private JLabel ni,na,wt;
	private JTextField numitem,numagent,waittime;
	private JButton create,good;
	private static List<String> items = new ArrayList<>(); 
	private int x,y,z;
	
	public ASYa2(){
		setLayout(null);
		//Initializes integers
		x=0;
		y=0;
		z=0;
		
		//Initializing the JLabels and setting their size and location
		ni= new JLabel("Number of Items");
		ni.setSize(100,20);
		ni.setLocation(20,30);
		
		na= new JLabel("Number of Agents");
		na.setSize(115,20);
		na.setLocation(20,60);
		
		wt= new JLabel("Maximum Waiting Time");
		wt.setSize(150,20);
		wt.setLocation(20,90);
		
		//Initializing the JTextField and setting its size and location
		numitem= new JTextField();
		numitem.setSize(100,25);
		numitem.setLocation(200,30);
		
		numagent= new JTextField();
		numagent.setSize(100,25);
		numagent.setLocation(200,60);
		
		waittime= new JTextField();
		waittime.setSize(100,25);
		waittime.setLocation(200,90);
				
		//Initializing the JButtons and setting their size, location and linking with ActionListener
		create= new JButton("Create");
		create.setSize(90,25);
		create.setLocation(100,120);
		create.addActionListener(this);//linking with ActionListener
		
		good= new JButton("Good");
		good.setSize(90,25);
		good.setLocation(200,120);
		good.addActionListener(this);//linking with ActionListener
		
		//Adding JLabel JTextField JButtons to JFrame
		add(ni);
		add(na);
		add(wt);
		add(numitem);
		add(numagent);
		add(waittime);
		add(create);
		add(good);
		
		
		setSize(400,200);
		setTitle("Assignment 2 - Ahmed Sameh");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ASYa2();
	}

	@Override
	public void run() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==create) {   // if Create Button is pressed
			try {
			x=Integer.parseInt(numitem.getText().trim());  // Takes number entered and equals to x
			y=Integer.parseInt(numagent.getText().trim()); // Takes number entered and equals to y
			z=Integer.parseInt(waittime.getText().trim()); // Takes number entered and equals to z
			Items(x);                                      // Calls function to create items taking x as parameter
			
			//Resets Text fields
			numitem.setText("");                        
			numagent.setText("");
			waittime.setText("");
			}
			catch(NumberFormatException exc) {
				System.out.println(exc.getMessage());
			}
		}
		if(e.getSource()==good) {
			Agents(y);                      // Calls function to create threads using y as parameter
		}
	}
	
	public List<String> Items(int x) {   // Function to create items
		for(int i=1;i<=x;i++) {
			items.add("Item "+i);     // Creates items
		}
			System.out.println(items);
		return items;
	}
	
	public void Agents(int y) {        // Function to create Agent threads
		for(int i=1;i<=y;i++) {
			Agent a=new Agent("Agent "+i,z,items); //Creates Threads
			a.start();                             //Starts Threads
		}                    
	}

}
