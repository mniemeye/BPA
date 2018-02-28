import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import BeerList_pkg.BeerList;
//import BeerData_pkg.BeerData;

public class NotePanel{

    public JPanel Window;
    public BeerList List;
    public BeerPresenter Presenter;

    JPanel note_header, note_text, note_button;
    JTextArea note_textField;
    JButton note_save;

    public NotePanel(JPanel panel, BeerList list, BeerPresenter presenter){
	Window = panel;
	List   = list;
	Presenter = presenter;
	setUpNoteView();
    }
    
    private void setUpNoteView(){
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
	try{
	    System.out.println(String.valueOf(note.length()));
	    for(int i = 0;i+50<note.length()+(note.length()/50)*2;i=i+51){
		String tmp = note.substring(0,i+50);
		tmp = tmp.concat("\n").concat(note.substring(i+50,note.length()));
		//counter+=2;
		note = tmp;
	    }
	}
	catch(Exception e){
	    System.out.println("no note!?");
	    note="";
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
