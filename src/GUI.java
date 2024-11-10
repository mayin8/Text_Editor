
import java.awt.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
//import javax.swing.JFrame;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class GUI extends JFrame implements ActionListener {

    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu[] menus = new JMenu[4];
    
    boolean wordWrapOn = false;
    
    
    Function_File functionClass = new Function_File(this);
    
    
    Format_Menu formatMenu = new Format_Menu(this);
    
    Edit_Menu editMenu = new Edit_Menu(this);

    String[] menuTitles = {"File", "Edit", "Format", "Color"};    // leave lol ok
    String[][] menuElements = { {"New", "Open", "Save", "Save As", "Delete", "Exit"} , {"Undo", "Redo"}, {"Word Wrap", "Font", "Font Size"}, {"Black", "Green", "Yellow"}};
    String[] fontSize = {"F-12", "F-13", "F-15", "F-20", "F-25"};
    String[] fontAll = {"Arial", "Comic Sans MS", "Times New Roman"};
    UndoManager um = new UndoManager();

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        create_window();
        create_text_area();
        create_menu_bar();

        // new KeywordHighlighter(textArea, keywords);
        // System.out.println(menuElements[0][2]);
        window.setVisible(true);
    }

    public void create_window() {
        window = new JFrame("Note Pad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       window.setVisible(true);
       
    }

    public void create_text_area() {

        textArea = new JTextArea();
        
        //undo
        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        }
        );

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //window.add(textArea);
         scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        window.add(scrollPane);

    }

    public void create_menu_bar() {

        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        for (int i = 0; i < menus.length; i++) {

            menus[i] = new JMenu(menuTitles[i]);
            
            menuBar.add(menus[i]);

            if (i < menuElements.length) {
                for (int j = 0; j < menuElements[i].length; j++) {

                    JMenuItem item = null;
                    if (menuElements[i][j].equals("Word Wrap")) {
                        item = new JMenuItem("Word Wrap: Off");
                        item.addActionListener(this);
                        item.setActionCommand(menuElements[i][j]);

                        menus[i].add(item);

                    } else if (menuElements[i][j].equals("Font")) {

                        JMenu fontMenu = new JMenu(menuElements[i][j]);
                        menus[i].add(fontMenu);

                        for (int k = 0; k < fontAll.length; k++) {
                            JMenuItem fontItem = new JMenuItem(fontAll[k]);
                            fontItem.addActionListener(this);
                            fontItem.setActionCommand(fontAll[k]);
                            fontMenu.add(fontItem);

                        }
                        continue;

                    } else if (menuElements[i][j].equals("Font Size")) {

                        JMenu fontMenu = new JMenu(menuElements[i][j]);
                        menus[i].add(fontMenu);

                        for (int k = 0; k < fontSize.length; k++) {
                            JMenuItem fontSz = new JMenuItem(fontSize[k]);
                            fontSz.addActionListener(this);
                            fontSz.setActionCommand(fontSize[k]);
                            fontMenu.add(fontSz);

                        }
                        continue;

                    } else {
                        item = new JMenuItem(menuElements[i][j]);
                        item.addActionListener(this);
                        item.setActionCommand(menuElements[i][j]);//New

                        menus[i].add(item);

                    }

                }

            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New":
                System.out.println("New");
                functionClass.new_file();
                break;
            case "Open":
                System.out.println("Open");
                functionClass.open_file();
                break;
            case "Save":
                System.out.println("Save");
                functionClass.save_file();
                break;
            case "Save As":
                System.out.println("Save As");
                functionClass.save_as_file();
                break;
            case "Delete":
                System.out.println("Delete");
                functionClass.delete_file();
                break;
            case "Exit":
                System.out.println("Exit");
                functionClass.exit_app();
                break;
            case "Word Wrap":
                System.out.println("Word Wrap");
                formatMenu.word_wrap();
                break;
            case "Arial":
                formatMenu.set_font(command);
                break;
            case "Comic Sans MS":
                formatMenu.set_font(command);
                break;
            case "Times New Roman":
                formatMenu.set_font(command);
                break;
            case "F-12":
                formatMenu.set_font_size(12);
                break;
            case "F-13":
                formatMenu.set_font_size(13);
                break;
            case "F-15":
                formatMenu.set_font_size(15);
                break;
            case "F-20":
                formatMenu.set_font_size(20);
                break;
            case "F-25":
                formatMenu.set_font_size(25);
                break;
            case "Undo":
                editMenu.undo_text();
                break;
            case "Redo":
                editMenu.redo_text();
                break;

        }
    }

}
