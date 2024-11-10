
import java.awt.Color;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class Function_File {

    GUI gui;
    String fileName;
    String fileAddress;


    String[] keyWords = {
        "abstract", "continue", "for", "new", "switch",
        "assert", "default", "goto", "package", "synchronized",
        "boolean", "do", "if", "private", "this",
        "break", "double", "implements", "protected", "throw",
        "byte", "else", "import", "public", "throws",
        "case", "enum", "instanceof", "return", "transient",
        "catch", "extends", "int", "short", "try",
        "char", "final", "interface", "static", "void",
        "class", "finally", "long", "strictfp", "volatile",
        "const", "float", "native", "super", "while"
    };

    public Function_File() {

    }

    public Function_File(GUI gui) {
        this.gui = gui;

    }

    public void new_file() {
        gui.textArea.setText("");
        gui.textArea.setBackground(Color.white);
        gui.window.setTitle("Untitle");
        fileName = null;
        fileAddress = null;
    }

    public void open_file() {
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        fileName = fd.getFile();
        fileAddress = fd.getDirectory();
        System.out.println(fileName);

        // needHighLighter();//In this method first we check is the file .java then on/off hightlight
        if (fd.getFile() != null) {
            gui.window.setTitle(fileName);
            gui.textArea.setText("");

            try {

                String line = null;
                BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
                while ((line = br.readLine()) != null) {
                    gui.textArea.append(line + "\n");
                }
                br.close();

                boolean needHighLight = checkJavaFile(fileName);
                new KeywordHighlighter(gui.textArea, keyWords, needHighLight);

            } catch (Exception e) {
                System.out.println("File Not Open");
            }
        }

    }

    public void save_as_file() {
        FileDialog fd = new FileDialog(gui.window, "Save As", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
            // needHighLighter();//Again check

            try {
                int result = JOptionPane.showConfirmDialog(gui.window, "Do you want to save changes?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    FileWriter fw = new FileWriter(fileAddress + fileName);
                    fw.write(gui.textArea.getText());
                    fw.close();
                    boolean needHighLight = checkJavaFile(fileName);
                new KeywordHighlighter(gui.textArea, keyWords, needHighLight);

                } else if (result == JOptionPane.NO_OPTION) {
                    // Don't save changes
                    System.out.println("Not Saved");
                } else {
                    System.out.println(result);
                    // Cancel
                }

            } catch (Exception e) {
                System.out.println("Some thing went wrong");
            }

        }

    }

    public void save_file() {
        if (fileName == null) {
            save_as_file();

        } else {

            try {
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                fw.close();
                JOptionPane.showMessageDialog(gui.window, "File saved successfully.", "Save Success", JOptionPane.PLAIN_MESSAGE);
                boolean needHighLight = checkJavaFile(fileName);
                new KeywordHighlighter(gui.textArea, keyWords, needHighLight);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(gui.window, "Error saving file: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void exit_app() {
        System.exit(0);
    }

    public void delete_file() {
        if (fileName == null && fileAddress == null) {
            new_file();
        } else {
            int result = JOptionPane.showConfirmDialog(gui.window, "Are you sure you want to delete the file?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    Path filePath = Paths.get(fileAddress + fileName);
                    Files.deleteIfExists(filePath);
                    JOptionPane.showMessageDialog(gui.window, "File deleted successfully.", "Success", JOptionPane.PLAIN_MESSAGE);
                    new_file();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(gui.window, "Error deleting file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private boolean checkJavaFile(String fileName) {
        return fileName != null && fileName.toLowerCase().endsWith(".java");
    }

}
