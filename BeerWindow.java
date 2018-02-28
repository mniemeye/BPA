import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import BeerList_pkg.BeerList;
//import BeerData_pkg.BeerData;
//import BeerPresenter_pkg.BeerPresenter;

public class BeerWindow extends JFrame{

    private  JPanel Window;
    private BeerList List;
    private BeerPresenter Presenter;
    
    private String previousPanel;

    public  MainPanel mainPanel;
    public  ListPanel listPanel;
    public  AddPanel  addPanel;
    public  NotePanel notePanel;
    
    public BeerWindow(){
	super();
	this.getContentPane().setLayout(new BorderLayout());
	Window = new JPanel();
	List = new BeerList();
	Presenter = new BeerPresenter(this);
	this.getContentPane().add(Window, BorderLayout.CENTER);

	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(450,225);
	setResizable(true);

	mainPanel = new MainPanel(Window,List,Presenter);
	listPanel = new ListPanel(Window,List,Presenter);
	addPanel  = new AddPanel(Window,List,Presenter);
	notePanel = new NotePanel(Window,List,Presenter);

       	showMainView();
	setVisible(true);
    }

    public BeerList getList(){
	return List;
    }

    public void showMainView(){
	       mainPanel.showMainView();
    }
    
    public String getBeerNameToAdd(){
	return mainPanel.getBeerNameToAdd();
    }    


    public void showListView(){
	       listPanel.showListView();
    }
    

    public void showAddView(BeerData data){
	       addPanel.showAddView(data);
    }
	       
    public int getGrade(){
	return addPanel.getGrade();
    }

    public void setGrade(int g){
	       addPanel.setGrade(g);
    }

    public void setLabel(String str){
	       addPanel.setLabel(str);
    }

    public void setLabel(JLabel lbl){
	       addPanel.setLabel(lbl);
    }
    
    public void countUp(){
	       addPanel.countUp();
    }

    public void countDown(){
	       addPanel.countUp();
    }

    public int getCounter(){
	return addPanel.getCounter();
    }
    
    public JLabel getCurrentBeer(){
	return addPanel.getCurrentBeer();
    }


    public void showNoteView(BeerData data){
	       notePanel.showNoteView(data);
    }

    public String getNote(){
	return notePanel.getNote();
    }

    
}
