import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import BeerList_pkg.BeerList;
import BeerData_pkg.BeerData;
//import BeerPresenter_pkg.BeerPresenter;

public class BeerWindow extends JFrame{

    private JPanel Window;
    private BeerList List;
    private BeerPresenter Presenter;

    //MainView Parameter

    private JPanel main_header, main_list, main_list1, main_list2, main_list3, main_button;
    private JTextField main_addName, main_name1, main_average1, main_name2, main_average2, main_name3, main_average3;
    private JLabel main_label1, main_label2, main_label3;
    private JButton main_addButton, main_listButton;

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
	
	setUpMainView();
	setUpListView();
	setUpAddView();
	setupNoteView();
	showMainView();
	setVisible(true);
    }

    public BeerList getList(){
	return List;
    }

    private void setUpMainView(){
	main_header = new JPanel();
	main_list   = new JPanel();
	main_list1  = new JPanel();
	main_list2  = new JPanel();
	main_list3  = new JPanel();
	main_button = new JPanel();
	main_header.setLayout(new BorderLayout());
	main_list.setLayout(new BorderLayout());
	main_list1.setLayout(new BorderLayout());
	main_list2.setLayout(new BorderLayout());
	main_list3.setLayout(new BorderLayout());
	main_button.setLayout(new BorderLayout());

	main_addName = new JTextField(20);
	main_addName.setText("Name");
	main_name1 = new JTextField(20);
	main_name2 = new JTextField(20);
	main_name3 = new JTextField(20);
	main_average1 = new JTextField(10);
	main_average2 = new JTextField(10);
	main_average3 = new JTextField(10);
	main_name1.setEditable(false);
	main_name2.setEditable(false);
	main_name3.setEditable(false);
	main_average1.setEditable(false);
	main_average2.setEditable(false);
	main_average3.setEditable(false);	

	main_addButton = new JButton("+");
	main_addButton.addActionListener(Presenter);//add action command in view
	main_addButton.setActionCommand("add beer");

	main_header.add(main_addName,BorderLayout.WEST);
	main_header.add(main_addButton,BorderLayout.EAST);

	main_list1.add(new JLabel("1."),BorderLayout.WEST);
	main_list1.add(main_name1,BorderLayout.CENTER);
	main_list1.add(main_average1,BorderLayout.EAST);
	main_list2.add(new JLabel("2."),BorderLayout.WEST);
	main_list2.add(main_name2,BorderLayout.CENTER);
	main_list2.add(main_average2,BorderLayout.EAST);
	main_list3.add(new JLabel("3."),BorderLayout.WEST);
	main_list3.add(main_name3,BorderLayout.CENTER);
	main_list3.add(main_average3,BorderLayout.EAST);

	main_list.add(main_list1,BorderLayout.NORTH);
	main_list.add(main_list2,BorderLayout.CENTER);
	main_list.add(main_list3,BorderLayout.SOUTH);

	main_listButton = new JButton("Liste");
	main_listButton.addActionListener(Presenter);
	main_listButton.setActionCommand("show list");

	main_button.add(main_listButton,BorderLayout.CENTER);
    }

    public String getBeerNameToAdd(){
	return main_addName.getText();
    }
    
    public void showMainView(){
	Window.removeAll();
	int size = List.size();

	switch(size){
	case 0:
	    break;
	case 1:
	    main_name1.setText(List.getNameAt(0));
	    main_average1.setText(String.valueOf(List.getAverageAt(0)));
	    break;
	case 2:
	    main_name1.setText(List.getNameAt(0));
	    main_average1.setText(String.valueOf(List.getAverageAt(0)));
	    main_name2.setText(List.getNameAt(1));
	    main_average2.setText(String.valueOf(List.getAverageAt(1)));
	    break;
	default:
	    main_name1.setText(List.getNameAt(0));
	    main_average1.setText(String.valueOf(List.getAverageAt(0)));
	    main_name2.setText(List.getNameAt(1));
	    main_average2.setText(String.valueOf(List.getAverageAt(1)));
	    main_name3.setText(List.getNameAt(2));
	    main_average3.setText(String.valueOf(List.getAverageAt(2)));
	    break;
	}

	Window.setLayout(new BorderLayout());
	Window.add(main_header,BorderLayout.NORTH);
	Window.add(main_list,BorderLayout.CENTER);
	Window.add(main_button,BorderLayout.SOUTH);
	Window.repaint();
    }

    //ListView Parameter

    private JLabel list_label = new JLabel("Liste");
    private JPanel list_header, list_fullList, list_button;
    private Vector list_panelVector, list_subPanelVector, list_buttonVector, list_noteVector, list_nameVector, list_averageVector;
    private JButton list_mainButton;
    private JScrollPane list_scroll;
    
    private void setUpListView(){
	list_header   = new JPanel();
	list_fullList = new JPanel();
	list_button   = new JPanel();
	list_header.setLayout(new BorderLayout());
	list_fullList.setLayout(new BoxLayout(list_fullList,BoxLayout.Y_AXIS));
	list_button.setLayout(new BorderLayout());
	list_header.add(list_label,BorderLayout.CENTER);
	
	list_panelVector  = new Vector();
	list_subPanelVector = new Vector();
	list_buttonVector = new Vector();
	list_noteVector   = new Vector();
	list_nameVector   = new Vector();
	list_averageVector= new Vector();
	
	list_mainButton = new JButton("Hauptfenster");
	list_mainButton.addActionListener(Presenter);
	list_mainButton.setActionCommand("to main");
	list_button.add(list_mainButton,BorderLayout.CENTER);
    }

    public void showListView(){
	list_panelVector.removeAllElements();
	list_buttonVector.removeAllElements();
	list_noteVector.removeAllElements();
	list_nameVector.removeAllElements();
	list_averageVector.removeAllElements();
	list_subPanelVector.removeAllElements();
	list_fullList.removeAll();
	Window.removeAll();
	/*list_panelVector  = new Vector();
	list_buttonVector = new Vector();
	list_nameVector   = new Vector();
	list_averageVector= new Vector();*/
	
	for(int i=0; i<List.size(); ++i){
	    list_panelVector.add(new JPanel());
	    list_subPanelVector.add(new JPanel());
	    list_buttonVector.add(new JButton("Edit"));
	    list_noteVector.add(new JButton("Notiz"));
	    list_nameVector.add(new JTextField(20));
	    list_averageVector.add(new JTextField(10));

	    ((JPanel)list_panelVector.get(i)).setLayout(new BoxLayout(((JPanel)list_panelVector.get(i)),BoxLayout.X_AXIS));
	    ((JPanel)list_subPanelVector.get(i)).setLayout(new BoxLayout(((JPanel)list_subPanelVector.get(i)),BoxLayout.Y_AXIS));
	    ((JTextField)list_nameVector.get(i)).setEditable(false);
	    ((JTextField)list_averageVector.get(i)).setEditable(false);
	    ((JTextField)list_nameVector.get(i)).setText(List.getNameAt(i));
	    ((JTextField)list_averageVector.get(i)).setText(String.valueOf(List.getAverageAt(i)));

	    String position_string = String.valueOf(i+1);
	    String action_command  = String.valueOf(i);

	    ((JButton)list_buttonVector.get(i)).addActionListener(Presenter);	    
	    ((JButton)list_buttonVector.get(i)).setActionCommand(action_command);

	    ((JButton)list_noteVector.get(i)).addActionListener(Presenter);	    
	    ((JButton)list_noteVector.get(i)).setActionCommand("note ".concat(action_command));

	    position_string = position_string.concat(".");

	    ((JPanel)list_panelVector.get(i)).add(new JLabel(position_string));
	    ((JPanel)list_panelVector.get(i)).add((JTextField)list_nameVector.get(i));
	    ((JPanel)list_panelVector.get(i)).add((JTextField)list_averageVector.get(i));
	    ((JPanel)list_panelVector.get(i)).add((JPanel)list_subPanelVector.get(i));
	    ((JPanel)list_subPanelVector.get(i)).add((JButton)list_buttonVector.get(i));
	    ((JPanel)list_subPanelVector.get(i)).add((JButton)list_noteVector.get(i));

	    list_fullList.add((JPanel)list_panelVector.get(i));
	}
	
	list_scroll = new JScrollPane(list_fullList);
	list_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	list_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	Window.setLayout(new BorderLayout());
	Window.add(list_header,BorderLayout.NORTH);
	Window.add(list_scroll,BorderLayout.CENTER);
	Window.add(list_button,BorderLayout.SOUTH);
	Window.revalidate();
	Window.repaint();	
    }

    //AddView Parameter

    private JPanel add_header, add_criterion, add_button;
    private JButton add_nextButton, add_previousButton, add_saveButton;
    private JTextField add_grade;
    private JLabel add_label;
    private int add_criterionCounter = 0;
    private JLabel add_currentBeer;
    
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

    public JLabel getCurrentBeer(){
	return add_currentBeer;
    }

    //noteView

    JPanel note_header, note_text, note_button;
    JTextArea note_textField;
    JButton note_save;

    private void setupNoteView(){
	note_header = new JPanel();
	note_header.setLayout(new BorderLayout());
	note_text = new JPanel();
	note_text.setLayout(new BorderLayout());
	note_button = new JPanel();
	note_button.setLayout(new BorderLayout());

	note_save = new JButton("Save");
    }

    public void showNoteView(BeerData data){

	Window.removeAll();
	note_header.removeAll();
	note_text.removeAll();
	note_button.removeAll();
	
	note_header.add(data.getName(),BorderLayout.CENTER);
	int pos = List.getPositionOf(data.getName().getText());
	String note = data.getNote();
	//int counter = 0;
	for(int i = 0;i+50<note.length()+(note.length()/50)*2;i=i+51){
	    String tmp = note.substring(0,i+50);
	    tmp = tmp.concat("\n").concat(note.substring(i+50,note.length()));
	    //counter+=2;
	    note = tmp;
	}

	note_textField = new JTextArea();
	note_textField.insert(note,0);
	note_text.add(note_textField,BorderLayout.CENTER);
	
	note_button.add(note_save,BorderLayout.CENTER);
	note_save.addActionListener(Presenter);
	note_save.setActionCommand("save note ".concat(String.valueOf(pos)));

	Window.setLayout(new BorderLayout());
	Window.add(note_header,BorderLayout.NORTH);
	Window.add(note_text,BorderLayout.CENTER);
	Window.add(note_button,BorderLayout.SOUTH);
	Window.revalidate();
	Window.repaint();	


    }

    public String getNote(){
	return note_textField.getText();
    }

    
}
