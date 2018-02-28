import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import BeerList_pkg.BeerList;
//import BeerData_pkg.BeerData;

public class AddPanel{

    public JPanel Window;
    public BeerList List;
    public BeerPresenter Presenter;
    
    private JPanel add_header, add_criterion, add_button;
    private JButton add_nextButton, add_previousButton, add_saveButton;
    private JTextField add_grade;
    private JLabel add_label;
    private int add_criterionCounter = 0;
    private JLabel add_currentBeer;

    public AddPanel(JPanel panel, BeerList list, BeerPresenter presenter){
	Window = panel;
	List   = list;
	Presenter = presenter;
	this.setUpAddView();
    }
    
    private void setUpAddView(){
	add_header = new JPanel();
	add_criterion = new JPanel();
	add_button = new JPanel();
	add_header.setLayout(new BorderLayout());
	add_criterion.setLayout(new BorderLayout());
	add_button.setLayout(new BorderLayout());
	
	add_nextButton     = new JButton(">");
	add_previousButton = new JButton("<");
	add_saveButton     = new JButton("Speichern");
	add_nextButton.addActionListener(Presenter);
	add_previousButton.addActionListener(Presenter);
	add_saveButton.addActionListener(Presenter);
	add_nextButton.setActionCommand("next criterion");
	add_previousButton.setActionCommand("previous criterion");
	add_saveButton.setActionCommand("save grades");

	add_grade = new JTextField(2);
	add_grade.setEditable(true);
    }
    
    public void showAddView(BeerData data){
	Window.removeAll();
	Window.setLayout(new BorderLayout());

	try{
	    add_header.remove(add_currentBeer);
	}
	catch(Exception e){}
	add_currentBeer = data.getName();
	add_header.add(add_currentBeer,BorderLayout.CENTER);
	setLabel(data.getCriterion(add_criterionCounter));
	add_criterion.add(add_label,BorderLayout.NORTH);
	add_criterion.add(add_grade,BorderLayout.SOUTH);
	add_button.add(add_saveButton);

	Window.add(add_header,BorderLayout.NORTH);
	Window.add(add_previousButton,BorderLayout.WEST);
	Window.add(add_nextButton,BorderLayout.EAST);
	Window.add(add_criterion,BorderLayout.CENTER);
	Window.add(add_button,BorderLayout.SOUTH);
	Window.revalidate();
	Window.repaint();
    }

    public int getGrade(){
	return Integer.parseInt(add_grade.getText());
    }

    public void setGrade(int g){
	add_grade.setText(String.valueOf(g));
    }

    public void setLabel(String str){
	try{
	    add_criterion.remove(add_label);
	    add_criterion.revalidate();
	}
	catch(Exception e){}
	add_label = new JLabel(str);
    }

    public void setLabel(JLabel lbl){
	try{
	add_criterion.remove(add_label);
	add_criterion.revalidate();
	}
	catch(Exception e){}
	add_label = lbl;
    }
    
    public void countUp(){
	++add_criterionCounter;
	add_criterionCounter = add_criterionCounter%(List.getNumberOfCriteria());
    }

    public void countDown(){
	if(--add_criterionCounter<0)
	    add_criterionCounter+=(List.getNumberOfCriteria());
    }

    public int getCounter(){
	return add_criterionCounter%List.getNumberOfCriteria();
    }  

    public JLabel getCurrentBeer(){
	return add_currentBeer;
    }
}
