import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import BeerList_pkg.BeerList;
//import BeerData_pkg.BeerData;

public class ListPanel{

    public JPanel Window;
    public BeerList List;
    public BeerPresenter Presenter;
    
    private JLabel list_label = new JLabel("Liste");
    private JPanel list_header, list_fullList, list_button;
    private Vector list_panelVector, list_subPanelVector, list_buttonVector, list_noteVector, list_nameVector, list_averageVector;
    private JButton list_mainButton;
    private JScrollPane list_scroll;

    public ListPanel(JPanel panel, BeerList list, BeerPresenter presenter){
	Window = panel;
	List   = list;
	Presenter = presenter;
	this.setUpListView();
    }

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
}
    
