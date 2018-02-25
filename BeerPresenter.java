import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import BeerList_pkg.*;
import BeerData_pkg.BeerData;

public class BeerPresenter implements ActionListener{

    private BeerWindow view;
    private BeerList list;

    public BeerPresenter(BeerWindow w){
	view=w;
	list = view.getList();
    }

    public void actionPerformed(ActionEvent evt){
	if(evt.getActionCommand().equals("show list")){//From Main to List
	    view.showListView();
	    System.out.println("BP Main->List");
	}

	if(evt.getActionCommand().equals("add beer")){//Add Beer from Main
	    String beer = view.getBeerNameToAdd();
	    if(beer.equals("") || beer.equals("Name") || list.containsBeer(beer)){
		view.showMainView();
	    }
	    else{
		list.addBeer(beer);
		int pos = list.getPositionOf(beer);
		//for(int i=0;i<list.size();++i)
		    //System.out.println(((BeerData)list.get(i)).getName().getText());
		view.setGrade(((BeerData)list.get(list.size()-1)).getGrade(view.getCounter()));
		view.showAddView((BeerData)list.get(pos));
	    }
	    System.out.println("BP Add");
	}

	if(evt.getActionCommand().equals("to main")){//From List to Main
	    view.showMainView();
	    System.out.println("BP List->Main");	    
	}
	try{
	    if(Integer.parseInt(evt.getActionCommand())>=0){//From List to specific Add
		view.setGrade(((BeerData)list.getBeerDataAt(Integer.parseInt(evt.getActionCommand()))).getGrade(view.getCounter()));
		//view.showAddView((BeerData)list.get(Integer.parseInt(evt.getActionCommand())));
		view.showAddView(list.getBeerDataAt(Integer.parseInt(evt.getActionCommand())));
		System.out.println("BP Edit Button");
	    }
	}
	catch(Exception e){
	}

	if(evt.getActionCommand().equals("next criterion")){
	    //read out data
	    String current_beer;
	    int current_beer_pos;
	    String old_crit;
	    int old_grade;
	    current_beer = view.getCurrentBeer().getText();
	    current_beer_pos = list.getPositionOf(current_beer);
	    old_crit = ((BeerData)list.get(current_beer_pos)).getCriterion(view.getCounter());
	    old_grade = view.getGrade();
	    if(old_grade>3 ||old_grade<-3)
		view.showAddView((BeerData)list.get(current_beer_pos));
	    else{
		//save data
		((BeerData)list.get(current_beer_pos)).setGrade(old_crit,old_grade);
		((BeerData)list.get(current_beer_pos)).actualiseAverage();
		view.countUp();
		//set new output
		int new_grade;
		new_grade = ((BeerData)list.get(current_beer_pos)).getGrade(view.getCounter());
		view.setGrade(new_grade);
		//draw new window
		System.out.println("BP Next");
		view.showAddView((BeerData)list.get(current_beer_pos));	    
	    }
	}
	
	if(evt.getActionCommand().equals("previous criterion")){
	    //read out data
	    String current_beer;
	    int current_beer_pos;
	    String old_crit;
	    int old_grade;
	    current_beer = view.getCurrentBeer().getText();
	    current_beer_pos = list.getPositionOf(current_beer);
	    old_crit = ((BeerData)list.get(current_beer_pos)).getCriterion(view.getCounter());
	    old_grade = view.getGrade();
	    if(old_grade>3 ||old_grade<-3)
		view.showAddView((BeerData)list.get(current_beer_pos));
	    else{
		//save data
		((BeerData)list.get(current_beer_pos)).setGrade(old_crit,old_grade);
		((BeerData)list.get(current_beer_pos)).actualiseAverage();
		view.countDown();
		//set new output
		int new_grade;
		new_grade = ((BeerData)list.get(current_beer_pos)).getGrade(view.getCounter());
		view.setGrade(new_grade);
		//draw new window
		System.out.println("BP Previous");
		view.showAddView((BeerData)list.get(current_beer_pos));	    	    
	    }
	}

	if(evt.getActionCommand().equals("save grades")){//From Add to Main, I think...
	    String current_beer;
	    int current_beer_pos;
	    String current_crit;
	    int current_grade;
	    current_beer = view.getCurrentBeer().getText();
	    //System.out.println(current_beer);
	    current_beer_pos = list.getPositionOf(current_beer);
	    //System.out.println(String.valueOf(current_beer_pos));
	    current_crit = ((BeerData)list.get(current_beer_pos)).getCriterion(view.getCounter());
	    //System.out.println(current_crit);
	    current_grade = view.getGrade();
	    //System.out.println(String.valueOf(current_grade));
	    if(current_grade>3 ||current_grade<-3)
		view.showAddView((BeerData)list.get(current_beer_pos));
	    else{
		((BeerData)list.get(current_beer_pos)).setGrade(current_crit,current_grade);
		((BeerData)list.get(current_beer_pos)).actualiseAverage();
		System.out.println(String.valueOf(((BeerData)list.get(current_beer_pos)).getAverage()));
		list.evaluateRanking();
		//list.sortVector();
		System.out.println("BP Save");
		list.writeToFile();
		view.showMainView();
	    }
	}

	if(evt.getActionCommand().startsWith("note ")){
	    String pos = evt.getActionCommand();
	    pos = pos.substring(5,pos.length());
	    view.showNoteView((BeerData)list.get(Integer.parseInt(pos)));
	}

	if(evt.getActionCommand().startsWith("save note ")){
	    String pos = evt.getActionCommand();
	    pos = pos.substring(10,pos.length());
	    ((BeerData)list.get(Integer.parseInt(pos))).makeNote(view.getNote());
	    list.writeToFile();
	    view.showListView();
	}
    }
}
