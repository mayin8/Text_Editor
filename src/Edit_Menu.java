
public class Edit_Menu {
    GUI gui;
    public Edit_Menu(GUI gui)
    {
        this.gui = gui;
    }
    public void undo_text()
    {
        gui.um.undo();
    }
    public void redo_text()
    {
        gui.um.redo();
    }
}
