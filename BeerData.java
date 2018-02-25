package BeerData_pkg;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BeerData{

    private JLabel name;
    private Vector criteria = new Vector();
    private Vector grades   = new Vector();
    private Vector weights  = new Vector();
    private float  average;
    private int    rank     = -1;
    private int    n_crit;
    private String note;
    
    public BeerData(String title){
	name = new JLabel(title);
	criteria.add("Optik (10%)");
	criteria.add("Geruch (35%)");
	criteria.add("Geschmack (55%)");
	grades.add(0);
	grades.add(0);
	grades.add(0);
	weights.add(0.1f);
	weights.add(0.35f);
	weights.add(0.55f);
	n_crit   = criteria.size();
	actualiseAverage();
    }

    public void makeNote(String str){
	note=str;
    }

    public String getNote(){
	return note;
    }

    public int getNumberOfCriteria(){
	return n_crit;
    }
    
    public void actualiseAverage(){
	float tmp = 0;
	for(int i=0; i<grades.size(); ++i)
	    tmp+=((int)grades.get(i)*(float)weights.get(i));
	average=tmp;
    }

    public void setGrade(String title, int grade){
	int pos = criteria.indexOf(title);
	grades.set(pos,grade);
	this.actualiseAverage();
    }

    public void setRank(int rank_in){
	rank=rank_in;
    }

    public int getRank(){
	return rank;
    }

    public int getGrade(String title){
	int pos = criteria.indexOf(title);
	return (int)grades.get(pos);
    }

    public float getAverage(){
	return average;
    }

    public JLabel getName(){
	return name;
    }

    public String getCriterion(int entry){
	try{
	    return (String)criteria.get(entry);
	}
	catch(Exception e){
	    return "error";
	}
    }

    public int getGrade(int entry){
	try{
	    return (int)grades.get(entry);
	}
	catch(Exception e){
	    return -1;
	}
    }
}
