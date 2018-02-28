//package BeerList_pkg;
 
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import BeerData_pkg.BeerData;

public class BeerList extends Vector{

    private Vector BeerNames = new Vector();
    private Vector ranking   = new Vector();
    private Vector ranked_positions;
    private int n_criteria=-1;
    private boolean isWindows = false;
    
    public BeerList(){
	super();
	if(this.saveFileExists())
	    this.readInFile();
    }

    public void addBeer(String title){
	BeerNames.add(title);
	this.add(new BeerData(title));
	if(this.size()==1)
	    n_criteria=((BeerData)this.get(0)).getNumberOfCriteria();
    }

    public int getPositionOf(String str){
	int size = this.size();
	for(int i = 0; i<size; ++i){
	    if(((BeerData)this.get(i)).getName().getText().equals(str)){
		return i;
	    }
	}
	return -1;
    }
    
    public int getNumberOfCriteria(){
	return ((BeerData)this.get(0)).getNumberOfCriteria();

    }

    public void evaluateRanking(){
	ranked_positions = new Vector();
	ranked_positions.add(0);
	int size = this.size();
	System.out.println("size: ".concat(String.valueOf(size)));
	//int counter = 0;
	boolean bool = false;
	if(size>1){
	    for(int i=1; i<size; ++i){
		System.out.println(String.valueOf(((BeerData)this.get(i)).getAverage()));
		if(((BeerData)this.get(i)).getAverage()>=((BeerData)this.get((int)ranked_positions.get(0))).getAverage()){
		    ranked_positions.insertElementAt(i,0);
		    continue;
		}
		for(int j=1; j<ranked_positions.size(); ++j){
		    if(((BeerData)this.get(i)).getAverage()>=((BeerData)this.get((int)ranked_positions.get(j))).getAverage()){
			ranked_positions.insertElementAt(i,j);
			bool = true;
			break;
		    }
		}
		if(bool){
		    continue;
		}
		ranked_positions.add(i);
		bool = false;
	    }
	}
    }


    public boolean containsBeer(String str){
	if(BeerNames.contains(str))
	    return true;
	else
	    return false;	    
    }
    
    public int getRankedAt(int entry){
	try{
	    return (int)ranked_positions.get(entry);
	}
	catch(Exception e){
	    return -1;
	}
    }

    public String getNameAt(int entry){
	try{
	    return (String)BeerNames.get((int)ranked_positions.get(entry));
	}
	catch(Exception e){
	    return "error";
	}
    }

    public float getAverageAt(int entry){
	try{
	    return (float)((BeerData)this.get((int)ranked_positions.get(entry))).getAverage();
	}
	catch(Exception e){
	    return -1;
	}
    }
	
    public BeerData getBeerDataAt(int entry){
	try{
	    return (BeerData)this.get((int)ranked_positions.get(entry));
	}
	catch(Exception e){
	    return new BeerData("error");
	}
    }

    public boolean writeToFile(){
	File saverDir;
	if(isWindows)
	    saverDir = new File(".\\SavedBeer");
	else
	    saverDir = new File("./SavedBeer");
	if(!saverDir.isDirectory()){
	    saverDir.mkdir();
	}
	File saverFile;
	if(isWindows)
	    saverFile = new File(".\\SavedBeer\\savedBeer.dat");
	else
	    saverFile = new File("./SavedBeer/savedBeer.dat");

	try{
	    PrintWriter outs = new PrintWriter(saverFile);
	    int currentNumberOfBeers = this.size();
	    if(currentNumberOfBeers==0)
		return false;
	    String preambel = "Name ";
	    for(int h=0; h<((BeerData)this.get(0)).getNumberOfCriteria(); ++h){
		preambel = preambel.concat(((BeerData)this.get(0)).getCriterion(h));
		preambel = preambel.concat(" ");
	    }
	    System.out.println(preambel);
	    preambel = preambel.concat("Average Bemerkung");
	    System.out.println(preambel);
	    try{
		outs.println(/*(byte[])*/preambel/*.getBytes()*/);
	    }
	    catch(Exception e){
		System.out.println("write preambel failed");
		return false;
	    }
	    
	    for(int i = 0; i<currentNumberOfBeers; ++i){
		BeerData data = this.getBeerDataAt(i);
		String line = data.getName().getText();
		for(int j=0; j<data.getNumberOfCriteria(); ++j){
		    line = line.concat(" ");
		    line = line.concat(String.format("%02d",data.getGrade(j)));
		    System.out.println(line);
		}
		line = line.concat(" ");
		line = line.concat(String.valueOf(data.getAverage()));
		try{
		    String tmp = data.getNote();
		    tmp = tmp.replaceAll("\r"," ").replaceAll("\n"," ");
		    line = line.concat(" #");
		    line = line.concat(tmp);
		}
		catch(Exception e){
		}
		
		System.out.println(line);
		//byte[] b = line.getBytes();
		try{
		    outs.println(line);
		}
		catch(Exception e){
		    System.out.println("write line ".concat(String.valueOf(i)).concat(" failed"));
		    return false;
		}
	    }
	    
	    try{
		outs.flush();
	    }
	    catch(Exception e){
		System.out.println("flush failed");
		return false;
	    }

	    try{
		outs.close();
	    }
	    catch(Exception e){
		System.out.println("close failed");
		return false;
	    }
	}
	catch(Exception e){
	    System.out.println("defintion failed");
	}

	return true;
    }

    private boolean saveFileExists(){
	File file;
	if(isWindows)
	    file = new File(".\\SavedBeer\\savedBeer.dat");
	else
	    file = new File("./SavedBeer/savedBeer.dat");
	if(file.exists())
	    return true;
	else
	    return false;
    }

    private void readInFile(){
	File file;
	if(isWindows)
	    file = new File(".\\SavedBeer\\savedBeer.dat");
	else
	    file = new File("./SavedBeer/savedBeer.dat");
	try{
	    BufferedReader b = new BufferedReader(new FileReader(file));
	
	    String readLine = "";

	    while ((readLine = b.readLine()) != null) {
		
		if(readLine.startsWith("Name ")){
		    System.out.println("found first line");
		    continue;
		}
		
		int nameEnds = readLine.indexOf(" ");
		System.out.println(String.valueOf(nameEnds));
		String read_name = readLine.substring(0,nameEnds);
		System.out.println(read_name);
		
		this.addBeer(read_name);
		int num = ((BeerData)this.get(0)).getNumberOfCriteria();
		
		Vector read_criteria = new Vector();
		for(int i=0; i<num; ++i){
		    String line2 = readLine.substring(nameEnds+1+i*3,nameEnds+3+i*3);
		    read_criteria.add(line2);
		    System.out.println(line2);
		}
		
		int pos = this.getPositionOf(read_name);
		System.out.println(String.valueOf(pos));
		BeerData data = (BeerData)this.get(pos);
		for(int i=0; i<num; ++i){
		    data.setGrade(data.getCriterion(i),Integer.parseInt((String)read_criteria.get(i)));
		    System.out.println(String.valueOf(data.getGrade(data.getCriterion(i))));
		}

		try{
		    int hashpos = readLine.lastIndexOf("#");
		    String comment = readLine.substring(hashpos+1,readLine.length());
		    data.makeNote(comment);
		}
		catch(Exception e){
		    System.out.println("no comment");
		}
	    }
	    this.evaluateRanking();
	}
	catch(Exception e){
	    return;
	}
	
    }
}
