import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import BeerList_pkg.BeerList;
//import BeerData_pkg.BeerData;

public class MainPanel{

    public JPanel Window;
    public BeerList List;
    public BeerPresenter Presenter;

    private JPanel main_header, main_list, main_list1, main_list2, main_list3, main_button;
    private JTextField main_addName, main_name1, main_average1, main_name2, main_average2, main_name3, main_average3;
    private JLabel main_label1, main_label2, main_label3;
    private JButton main_addButton, main_listButton;

    public MainPanel(JPanel panel, BeerList list, BeerPresenter presenter){
	Window = panel;
	List = list;
	Presenter = presenter;
	this.setUpMainView();
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
	System.out.println("+ initialised");  

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
    
}
