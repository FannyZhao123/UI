import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetEvent;
import java.io.IOException;
import java.io.File;
import java.util.List;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;




public class LevelEditorViewMid extends JPanel implements Observer {
	private Model model;

    //top
    private JTextField horizontal;
    private JTextField vertical;
    private JTextField height;
    private JTextField width;    
    private JButton set;
    private JButton create;
    private JLabel level_editor;
    private JLabel world;
    private JLabel obstacles;
        
    private ImageIcon setbtn = new ImageIcon("buttons/add.png"); 
    private ImageIcon createbtn = new ImageIcon("buttons/add.png");

    //middle
	private JLabel drag_notice;
    private JLabel imageLabel;
    private Image image;

    //bottom
    private JButton undo;
    private JButton load;
    private JButton save_start;
        
    //private ImageIcon undobtn = new ImageIcon("buttons/undo.png"); 
    //private ImageIcon loadbtn = new ImageIcon("buttons/load.png");
    //private ImageIcon save_startbtn = new ImageIcon("buttons/savestart.png");

    /**
     * Create a new View.
     */
    public LevelEditorViewMid(Model model) {

        //top
        set = new JButton("");
        create = new JButton("");
        set.setIcon(setbtn); 
        create.setIcon(createbtn);

        level_editor = new JLabel("Level Editor");
        level_editor.setFont(new Font("Futura",Font.ITALIC+Font.BOLD,60));
        world = new JLabel("World");
        world.setFont(new Font("Futura",Font.ITALIC+Font.BOLD,40));
        obstacles = new JLabel("Obstacles");
        obstacles.setFont(new Font("Futura",Font.ITALIC+Font.BOLD,40));

        horizontal = new JTextField ( "horizontal") ;
        vertical = new JTextField ( "vertical") ;
        height = new JTextField ( "height") ;
        width = new JTextField ( "width") ;

        GridLayout strategy = new GridLayout(2, 4);
        JPanel p = new JPanel();
        p.setLayout(strategy);
        p.add(this.world); 
        p.add(this.horizontal); 
        p.add(this.vertical); 
        p.add(this.set); 
        p.add(this.obstacles); 
        p.add(this.height); 
        p.add(this.width); 
        p.add(this.create);
        p.setMaximumSize(new Dimension(1000, 100));
        p.setBackground(new Color(51,153,255));

        //middle
        drag_notice = new JLabel ( "Drag to movie obstacles");
        drag_notice.setFont(new Font("Futura",Font.ITALIC+Font.BOLD,40));
        
        imageLabel = new JLabel();
        imageLabel.setMinimumSize(new Dimension(1000, 100));
        imageLabel.setPreferredSize(new Dimension(1200, 150));
        imageLabel.setMaximumSize(new Dimension(2000, 400));
        imageLabel.setTransferHandler(new ImageHandler());

        DragGesture dg = new DragGesture();
        imageLabel.addMouseListener(dg); // For mouseDragged
        imageLabel.addMouseMotionListener(dg); // For mouseReleased
        imageLabel.setBackground( Color.BLACK);

        //bottom--resizeable   
        undo = new JButton("undo");
        undo.setBackground(new Color(0,153,0));
        undo.setOpaque(true);
        undo.setBorderPainted(false);
        undo.setFont(new Font("Futura", Font.PLAIN, 38));
        
        load = new JButton("load");
        load.setBackground(new Color(0,0,153));
        load.setOpaque(true);
        load.setBorderPainted(false);
        load.setFont(new Font("Futura", Font.PLAIN, 38));

        save_start = new JButton("save&start");
        save_start.setBackground(new Color(0,153,0));
        save_start.setOpaque(true);
        save_start.setBorderPainted(false);
        save_start.setFont(new Font("Futura", Font.PLAIN, 38));
        //undo.setIcon(undobtn); 
        //load.setIcon(loadbtn);
        //save_start.setIcon(save_startbtn);

        JPanel b = new JPanel();
        b.setLayout(new GridLayout(1, 3)); 
        b.add(this.undo);
        b.add(this.load);         
        b.add(this.save_start);
        b.setMaximumSize(new Dimension(1200, 70));
        b.setBackground(new Color(51,153,255));


        //main
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        this.add(this.level_editor); 
        this.add(p);
        this.add(this.drag_notice); 
        this.add(this.imageLabel); 
        this.add(b);        

        setBackground(new Color(51,153,255));

        // set the model 
		this.model = model;

		// setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)

        // setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
        set.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                //model.incrementCounter();
            }
        }); 

        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                //model.incrementCounter();
            }
        }); 

        horizontal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                //model.incrementCounter();
            }
        }); 

        vertical.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                //model.incrementCounter();
            }
        }); 

        height.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                //model.incrementCounter();
            }
        }); 

        width.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                //model.incrementCounter();
            }
        });

        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                //model.incrementCounter();
            }
        }); 

        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                //model.incrementCounter();
            }
        }); 

        save_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                //model.incrementCounter();
            }
        }); 
    }//con

    /**
     * Update with data from the model.
     */
    @Override

    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed!");
    }

    //new method from drag and drop
    ///////////////////////////////////////////////
    public class ImageHandler extends TransferHandler {

        protected Transferable createTransferable(JComponent c) {
            System.out.println("Creating Transferable");
            return new Transferable() {
                private Image img = ((ImageIcon)imageLabel.getIcon()).getImage();

                public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                    if (flavor.equals(DataFlavor.imageFlavor)) {
                        System.err.println("getTransferData: " + image);
                        //return ((ImageIcon)imageLabel.getIcon()).getImage();
                        return this.img;
                    }
                    throw new UnsupportedFlavorException(flavor);
                }

                public DataFlavor[] getTransferDataFlavors() {
                    return new DataFlavor[] { DataFlavor.imageFlavor };
                }

                public boolean isDataFlavorSupported(DataFlavor flavor) {
                    return flavor.equals(DataFlavor.imageFlavor);
                }
            };
        }

        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY;
        }

        public boolean importData(JComponent c, Transferable t) {
            System.err.print("importData: ");
            JLabel label = (JLabel)c;

            if (t.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                System.err.println("imageFlavour");

                try {
                    // Get the data and set our label's image icon to the new image.
                    // Save a copy of the image so we can support dragging it out
                    image = (Image)t.getTransferData(DataFlavor.imageFlavor);
                    label.setIcon(new ImageIcon(image));
                }
                catch (UnsupportedFlavorException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                return true;
            }
            else if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                System.err.println("javaFileListFlavour");

                try {
                    // Get the data and set our label's image icon to the new image.
                    java.util.List<File> files = (java.util.List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                    File f = files.get(0);

                    System.err.println("filePath: " + f.getAbsolutePath());
                    ImageIcon iIcon = new ImageIcon(f.getAbsolutePath());
                    image = iIcon.getImage();
                    imageLabel.setIcon(iIcon);
                }
                catch (UnsupportedFlavorException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }

            System.err.println("rejecting");
            return false;
        }

        public boolean canImport(JComponent c, DataFlavor[] transferFlavors) {
            System.err.print("canImport: ");
            for(int i=0; i<transferFlavors.length; i++) {
                DataFlavor df = transferFlavors[i];
                if (df.equals(DataFlavor.imageFlavor) || df.equals(DataFlavor.javaFileListFlavor)) {
                    System.err.println("true");
                    return true;
                }
            }
            System.err.println("false");
            return false;
        }

        protected void exportDone(JComponent c, Transferable data, int action) {
            System.out.println("exportDone");
        }

    }

    // A simple recogizer for the drag gesture
    // The mouseDragged method is called whenever the mouse button is down and
    // the mouse is moving. We only want to initiate drag & drop, for each drag
    // gesture. As such, we only take action the first time mouseDragged is called,
    // resetting whenever the mouse button is released.
    private class DragGesture extends MouseInputAdapter {
        private boolean armed = true;

        public void mouseDragged(MouseEvent e) {

            // Enter the conditional only once, at the start of the drag
            if (armed) {
                System.err.println("Drag starting");

                // Initiate drag and drop
                JComponent c = (JComponent)e.getSource();
                TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag(c, e, TransferHandler.COPY);

                armed = false;
            }
        }

        public void mouseReleased(MouseEvent e) {
            // Get ready for the next drag
            armed = true;
        }
    }
    ///////////////////////////////////////////////
}




