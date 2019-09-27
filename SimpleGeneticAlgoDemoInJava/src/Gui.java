
import java.awt.*;  
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class Gui extends JFrame implements ActionListener{
	public JFrame f;
	public JPanel genesPanel, popuPanel, buttonPanel, textPanel, notePanel;
	public JScrollPane scroll;
	public JLabel genesLabel, popuLabel, jlb3;
	public JButton sbutton;
	public JTextField jtf1, jtf2;
	public JTextArea jt;
	public int chromosomesNumber;
	public int genenesNumber;
	public boolean populationValid;
	public boolean chromosomeValid;
	public String showInformation;

	public Gui() {
		JFrame f = new JFrame("Genetic Algorithm Simple Demo");
		f.setPreferredSize(new Dimension(1000, 800));
		f.setLayout(new GridLayout(4, 1));
		
	    genesPanel = new JPanel();
        popuPanel = new JPanel();
        buttonPanel = new JPanel();
        textPanel = new JPanel();
        
        genesLabel = new JLabel("How many genes in each chromosome: ");
        jtf1 = new JTextField(10);
        jtf1.setText("Maximum 8");
        jt = new JTextArea("Show the calculation process");
        scroll = new JScrollPane (jt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        popuLabel = new JLabel("How many chromosomes in population: ");
        jtf2 = new JTextField(10);
        jtf2.setText("Maximum 10");
        sbutton = new JButton("Show the magic ");
        sbutton.addActionListener(this); 
        //f.setLayout(new GridLayout(4, 1));
        		
        genesPanel.add(genesLabel);
        genesPanel.add(jtf1);
        popuPanel.add(popuLabel);
        popuPanel.add(jtf2);
        buttonPanel.add(sbutton);
        
        f.add(genesPanel);
        f.add(popuPanel);
        f.add(buttonPanel);
        f.getContentPane().add(scroll);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
	}
	
public void actionPerformed(ActionEvent e){ 
	populationValid = false;
	chromosomeValid = false;
	showInformation="";
	try {
		if(Integer.parseInt(jtf1.getText())>8 || Integer.parseInt(jtf1.getText())<1) {
			JOptionPane.showMessageDialog(f,"The legal integer value for genes in each chromosome is between [1,7].");
			jtf1.setText("0");
			chromosomeValid = false;
		}else {
			jt.replaceSelection(""); 
    		genenesNumber = Integer.parseInt(jtf1.getText());
    		chromosomeValid = true;
    	}
	}catch(NumberFormatException ex) {
		JOptionPane.showMessageDialog(f,"The value for genes in each chromosome should be a integer number.");
		jtf1.setText("0");
		chromosomeValid = false;
	}

	try {
		if(Integer.parseInt(jtf2.getText())>10 || Integer.parseInt(jtf2.getText())<1) {
    		JOptionPane.showMessageDialog(f,"The legal integer value for chromosome in population is between [1,9].");
    		jtf2.setText("0");
    		populationValid = false;
    	}else {
    		chromosomesNumber = Integer.parseInt(jtf2.getText());
    		populationValid = true;
    	}
	}catch(NumberFormatException ex) {
		JOptionPane.showMessageDialog(f,"The value for chromosome in population should be an integer number.");
		jtf2.setText("0");
		populationValid = false;
	}
	if(chromosomeValid && populationValid) {
		GA_SimpleDemo gsd = new GA_SimpleDemo(chromosomesNumber,genenesNumber);
		showInformation = gsd.ToString();
		showInformation += gsd.search();
		jt.setText(showInformation); 		
	}
}	
    
public static void main(String[] args) {
	new Gui();
}

public int getchromosomesNumber() {
	return chromosomesNumber;
}

public int getGenensNumber() {
	return Integer.parseInt(jtf1.getText());
}

}


