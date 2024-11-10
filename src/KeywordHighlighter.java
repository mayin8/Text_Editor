import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.util.Set;

public class KeywordHighlighter {
    private JTextArea textArea;
     private String[] keyWords;
    private Highlighter.HighlightPainter painter;
    private boolean needHighLight;

    public KeywordHighlighter(JTextArea textArea, String[] keywords, boolean needHighLight) {
        this.textArea = textArea;
        this.keyWords = keywords;
        this.painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
        this.needHighLight = needHighLight;
        this.textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                highlightKeywords();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                highlightKeywords();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                highlightKeywords();
            }
        });

        highlightKeywords();
    }

    void highlightKeywords() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Highlighter highlighter = textArea.getHighlighter();
                    highlighter.removeAllHighlights();

                    if (needHighLight) {
                        String text = textArea.getText();
                        for (String keyword : keyWords) {
                            int index = 0;
                            while (index >= 0) {
                                index = text.indexOf(keyword, index);
                                if (index >= 0) {
                                    int endIndex = index + keyword.length();
                                    highlighter.addHighlight(index, endIndex, painter);
                                    index = endIndex;
                                }
                            }
                        }
                    }
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}