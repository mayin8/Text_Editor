
import java.awt.Component;
import java.awt.Font;
import javax.swing.JMenuItem;

public class Format_Menu {

    GUI gui;
    String selectedFont = "Arial";
    int size =11;
    Font[] allFonts = new Font[3];
    String[] font = {"Arial", "Comic Sans MS", "Times New Roman"};

    public Format_Menu(GUI gui) {
        this.gui = gui;
        //Default Font Arail size 11
        

    }

    public void word_wrap() {

        if (gui.wordWrapOn == false) {
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
           
            for (Component component : gui.menus[2].getMenuComponents()) {
                if (component instanceof JMenuItem) {
                    ((JMenuItem) component).setText("Word Wrap : On");
                    break;
                }
            }

        } else {
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            
            for (Component component : gui.menus[2].getMenuComponents()) {
                if (component instanceof JMenuItem) {
                    ((JMenuItem) component).setText("Word Wrap : OFF");
                    break;
                }
            }

        }
    }

    public void set_font(String sFont) {
         gui.textArea.setFont(new Font(sFont,Font.PLAIN,size));
         selectedFont = sFont;
    }

    public void set_font_size(int len) {

        gui.textArea.setFont(new Font(selectedFont, Font.PLAIN, len));
        size = len;

    }
}
