import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar
{

    private final Frame parent; // the frame, only used as parent for the Dialogs
    private final Presentation presentation; // Commands are given to the presentation

    private static final long serialVersionUID = 227L;

    protected static final String ABOUT = "About";
    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String GOTO = "Go to";
    protected static final String HELP = "Help";
    protected static final String NEW = "New";
    protected static final String NEXT = "Next";
    protected static final String OPEN = "Open";
    protected static final String PAGENR = "Page number?";
    protected static final String PREV = "Prev";
    protected static final String SAVE = "Save";
    protected static final String VIEW = "View";

    protected static final String TESTFILE = "test.xml";
    protected static final String SAVEFILE = "dump.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    public MenuController(Frame frame, Presentation pres)
    {
        this.parent = frame;
        this.presentation = pres;
        MenuItem menuItem;
        Menu fileMenu = new Menu(FILE);
        fileMenu.add(menuItem = this.mkMenuItem(OPEN));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                MenuController.this.presentation.clear();
                Accessor xmlAccessor = new XMLAccessor();
                try
                {
                    xmlAccessor.loadFile(MenuController.this.presentation, TESTFILE);
                    MenuController.this.presentation.setSlideNumber(0);
                } catch(IOException exc)
                {
                    JOptionPane.showMessageDialog(MenuController.this.parent, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
                }
                MenuController.this.parent.repaint();
            }
        });
        fileMenu.add(menuItem = this.mkMenuItem(NEW));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                MenuController.this.presentation.clear();
                MenuController.this.parent.repaint();
            }
        });
        fileMenu.add(menuItem = this.mkMenuItem(SAVE));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Accessor xmlAccessor = new XMLAccessor();
                try
                {
                    xmlAccessor.saveFile(MenuController.this.presentation, SAVEFILE);
                } catch(IOException exc)
                {
                    JOptionPane.showMessageDialog(MenuController.this.parent, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(menuItem = this.mkMenuItem(EXIT));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                MenuController.this.presentation.exit(0);
            }
        });
        this.add(fileMenu);
        Menu viewMenu = new Menu(VIEW);
        viewMenu.add(menuItem = this.mkMenuItem(NEXT));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                MenuController.this.presentation.nextSlide();
            }
        });
        viewMenu.add(menuItem = this.mkMenuItem(PREV));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                MenuController.this.presentation.prevSlide();
            }
        });
        viewMenu.add(menuItem = this.mkMenuItem(GOTO));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
                int pageNumber = Integer.parseInt(pageNumberStr);
                MenuController.this.presentation.setSlideNumber(pageNumber - 1);
            }
        });
        this.add(viewMenu);
        Menu helpMenu = new Menu(HELP);
        helpMenu.add(menuItem = this.mkMenuItem(ABOUT));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                AboutBox.show(MenuController.this.parent);
            }
        });
        this.setHelpMenu(helpMenu);        // needed for portability (Motif, etc.).
    }

    // create a menu item
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
