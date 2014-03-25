import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * The MyHLGui.java contains the needed user interface of the application
 *
 *  @author 2010-47997 | Roxenne Lourdes D. Rosario
 *  @author 2011-60315 | Paul Alvin Sacedor
 *  @author 2010-35891 | Rochelle I. Salas
 *
 *  @version 1.0
 *
 */

public class MyHLGui {

	protected JFrame mainframe;
	protected JPanel mainpanel;

	//variables related to the menubar
    protected JMenuBar menubar;

    protected JMenu filemenu;
    protected JMenuItem newfile;
    protected JMenuItem open;
    protected JMenuItem save;
    protected JMenuItem exit;

    protected JMenu toolsmenu;
    protected JMenuItem build;
    protected JMenuItem execute;

    protected JMenu help;
    protected JMenuItem instructions;
    protected JMenuItem about;
    /////variables related to menubar

    protected JButton executeb;

    //variables related to the code
    protected JPanel codepanel;
    protected JLabel filename;

    protected JScrollPane inputpane;
    protected JTextArea inputarea;

    protected JScrollPane toolpane;
    protected JTextArea tooloutput;

	/**
    * Default constructor of MyHLGui
    */
    public MyHLGui() {
    	initializeGUI();
    }

    /**
    * Initializes the MyHLGui
    */
    public void initializeGUI() {

        mainframe = new JFrame("Machine Problem 2 - MYHL Compiler ");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setSize(1024,720);

        mainframe.setLocationRelativeTo(null);
        mainframe.setResizable(false);

        mainpanel = new JPanel();
        mainpanel.setOpaque(false);
        mainpanel.setLayout(null);



        /********** MENUBAR **********/
        menubar = new JMenuBar();
        menubar.setBounds(0,0,1050, 20);

        JMenu space = new JMenu("    ");
        space.setEnabled(false);
        menubar.add(space);

        //for FILE - JMenu
        filemenu = new JMenu("File   ");
        filemenu.setHorizontalTextPosition(SwingConstants.CENTER);
        menubar.add(filemenu);

        	newfile = new JMenuItem("New");
            newfile.setPreferredSize(new Dimension(80,27));
            //newfile.addActionListener(new NewListener());
            filemenu.add(newfile);

            open = new JMenuItem("Open");
            open.setPreferredSize(new Dimension(100,27));
            open.addActionListener(new OpenListener());
            filemenu.add(open);

            filemenu.addSeparator();

            save = new JMenuItem("Save");
            save.setPreferredSize(new Dimension(100,27));
            //save.addActionListener(new SaveListener());
            filemenu.add(save);

            filemenu.addSeparator();

            exit = new JMenuItem("Exit");
            exit.setPreferredSize(new Dimension(100,27));
            //exit.addActionListener(new ExitListener());
            filemenu.add(exit);

        //for TOOLS - JMenu
        toolsmenu = new JMenu("Tools   ");
        menubar.add(toolsmenu);

            build = new JMenuItem("Build");
            build.setPreferredSize(new Dimension(100,27));
            build.addActionListener(new BuildListener());
            toolsmenu.add(build);

            execute = new JMenuItem("Execute");
            execute.setPreferredSize(new Dimension(100,27));
            //execute.addActionListener(new ExecuteListener());
            execute.setEnabled(false);
            toolsmenu.add(execute);


        //for HELP - JMenu
        help = new JMenu("Help");
        menubar.add(help);

            instructions = new JMenuItem("How To Use");
            instructions.setPreferredSize(new Dimension(100,27));
            //instructions.addActionListener(new InstructListener());
            help.add(instructions);

            help.addSeparator();

            about = new JMenuItem("About");
            about.setPreferredSize(new Dimension(100,27));
            //about.addActionListener(new AboutListener());
            help.add(about);

        mainframe.add(menubar);
        /********** end MENUBAR **********/

        /********** BUTTONS **********/

		JButton newfile = new JButton(new ImageIcon("images/new.png"));
		newfile.setBounds(30, 30, 45, 45);
		newfile.setOpaque(false);
		newfile.setContentAreaFilled(false);
		newfile.setBorderPainted(false);
		//newfile.addActionListener(new NewListener());
		newfile.setToolTipText("New");
		mainpanel.add(newfile);

		JButton open = new JButton(new ImageIcon("images/open.png"));
		open.setToolTipText("Open");
		open.setBounds(80, 30, 45, 45);
		open.setOpaque(false);
		open.setContentAreaFilled(false);
		open.setBorderPainted(false);
		open.addActionListener(new OpenListener());
		mainpanel.add(open);


		JButton save = new JButton(new ImageIcon("images/save.png"));
		save.setToolTipText("Save");
		save.setBounds(130, 30, 45, 45);
		save.setOpaque(false);
		save.setContentAreaFilled(false);
		save.setBorderPainted(false);
		//save.addActionListener(new SaveListener());
		mainpanel.add(save);


		JButton build = new JButton(new ImageIcon("images/build.png"));
        build.setBounds(230, 30, 45, 45);
        build.setOpaque(false);
        build.setContentAreaFilled(false);
        build.setBorderPainted(false);
        build.addActionListener(new BuildListener());
        build.setToolTipText("Build");
        mainpanel.add(build);

        executeb = new JButton(new ImageIcon("images/execute.png"));
        executeb.setBounds(280, 30, 45, 45);
        executeb.setOpaque(false);
        executeb.setContentAreaFilled(false);
        executeb.setBorderPainted(false);
        //executeb.addActionListener(new ExecuteListener());
        executeb.setToolTipText("Execute");
        executeb.setEnabled(false);
        mainpanel.add(executeb);

        /********** end BUTTONS **********/


        /*code panel*/
        codepanel = new JPanel();
        codepanel.setOpaque(false);
        codepanel.setLayout(null);
        codepanel.setBounds(10, 140, 1004, 550);

        filename = new JLabel("  Document");
        filename.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        filename.setForeground(Color.GRAY);
        filename.setBounds(20, 115, 290, 30);

        inputarea = new JTextArea();
        inputpane = new JScrollPane(inputarea);
        inputarea.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        inputpane.setBounds(20, 10, 944, 390);
        inputarea.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), new EmptyBorder(5,10, 0, 30)));
        codepanel.add(inputpane);

        JLabel toolname = new JLabel("  Tool Output");
        toolname.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        toolname.setForeground(Color.GRAY);
        toolname.setBounds(20, 545, 290, 30);
        mainframe.add(toolname);

        tooloutput = new JTextArea();
        toolpane = new JScrollPane(tooloutput);
        tooloutput.setFont(new Font("Consolas", Font.PLAIN, 14));
        tooloutput.setEditable(true);
        toolpane.setBounds(20, 430, 944, 100);
        toolpane.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), new EmptyBorder(0,0, 0, 0)));
        codepanel.add(toolpane);

		mainpanel.add(codepanel); 
		mainframe.add(filename);
		
		mainframe.add(mainpanel);
        mainframe.setVisible(true);
    }

    public class OpenListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser("MyHLGui.java");
            chooser.setMultiSelectionEnabled(false);

            int option = chooser.showOpenDialog(mainframe);
            if (option == JFileChooser.APPROVE_OPTION) {
                inputarea.setText("");

                try {
                    File file = new File(chooser.getSelectedFile().getName());

                    FileReader fread = new FileReader(file);
                    BufferedReader bread =new BufferedReader(fread);

                    String readString = bread.readLine();

                    while(readString != null) {
                        inputarea.setText(inputarea.getText()+readString+"\n");
                        readString = bread.readLine();
                    }

                    filename.setText("   " + chooser.getSelectedFile().getName());

                    tooloutput.setText("");

                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        } 
    } //end of OPENLISTENER

    public class BuildListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                tooloutput.setText("");

                Compile instance = new Compile();
                instance.readCode(inputarea.getText());

                String errormessage = instance.compile();

                if(errormessage.equals("")) { //sakto
                    tooloutput.setText("Tool completed successfully");
                    executeb.setEnabled(true);
                    //set execute button to clickable
                } else {
                    executeb.setEnabled(false);
                    tooloutput.setText(errormessage);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }





    public static void main(String[] args) {
        MyHLGui a = new MyHLGui();
    }
}


