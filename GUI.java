import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/**
 * The GUI.java contains the needed user interface of the application
 *
 *  @author 2010-47997 | Roxenne Lourdes D. Rosario
 *  @author 2011-60315 | Paul Alvin Sacedor
 *  @author 2010-35891 | Rochelle I. Salas
 *
 *  @version 1.0
 *
 */

public class GUI {

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

        //variables related to the assembly code
        protected JPanel assempanel;
        protected JLabel filename;

        protected JScrollPane inputpane;
        protected JTextArea inputarea;

        protected JScrollPane toolpane;
        protected JTextArea tooloutput;

        protected String[] MLA;

        //variables related to the machine code
      /*  protected JPanel machinepanel;
        protected JScrollPane outputpanel;
        protected JTextArea outputarea;
*/

        //variables related to execution
        protected JPanel executionpanel;
        protected JScrollPane logpanel;
        protected JTextArea logarea;

        protected JScrollPane consolepanel;
        protected JTextArea consolearea;

        protected JLabel[] stack = new JLabel[5];
        protected JButton executeb;

        /**
        * Default constructor of GUI
        */
        public GUI() {
                initializeGUI();
        }

        /**
        * Initializes the GUI
        */
        public void initializeGUI() {

                mainframe = new JFrame("Machine Problem 2b - MYHL Compiler ");
                mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainframe.setSize(1024,720);

                mainframe.setLocationRelativeTo(null);
                mainframe.setResizable(false);

                mainpanel = new JPanel();
                mainpanel.setOpaque(false);
                mainpanel.setLayout(null);


                JLabel setBG = new JLabel(new ImageIcon("images/try2.jpg"));
                setBG.setBounds(0, -7, 1024,700);

                menubar = new JMenuBar();
                menubar.setBounds(0,0,1050, 20);

                JMenu space = new JMenu("    ");
                space.setEnabled(false);
                menubar.add(space);

                //for file menu
                filemenu = new JMenu("File   ");
                filemenu.setHorizontalTextPosition(SwingConstants.CENTER);
                menubar.add(filemenu);

                newfile = new JMenuItem("New");
                newfile.setPreferredSize(new Dimension(80,27));
                newfile.addActionListener(new NewListener());
                filemenu.add(newfile);

                open = new JMenuItem("Open");
                open.setPreferredSize(new Dimension(100,27));
                open.addActionListener(new OpenListener());
                filemenu.add(open);

                filemenu.addSeparator();

                save = new JMenuItem("Save");
                save.setPreferredSize(new Dimension(100,27));
                save.addActionListener(new SaveListener());
                filemenu.add(save);

                filemenu.addSeparator();

                exit = new JMenuItem("Exit");
                exit.setPreferredSize(new Dimension(100,27));
                exit.addActionListener(new ExitListener());
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
                execute.addActionListener(new ExecuteListener());
                execute.setEnabled(false);
                toolsmenu.add(execute);


                //for HELP - JMenu
                help = new JMenu("Help");
                menubar.add(help);

                instructions = new JMenuItem("How To Use");
                instructions.setPreferredSize(new Dimension(100,27));
                instructions.addActionListener(new InstructListener());
                help.add(instructions);

                help.addSeparator();

                about = new JMenuItem("About");
                about.setPreferredSize(new Dimension(100,27));
                about.addActionListener(new AboutListener());
                help.add(about);

                mainframe.add(menubar);

                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                //BUTTONS

                JButton about = new JButton();
                about.setBounds(962, 70, 50, 25);
                about.setOpaque(false);
                about.setContentAreaFilled(false);
                about.setBorderPainted(false);
                about.addActionListener(new AboutListener());
                about.setToolTipText("How to Use");
                mainpanel.add(about);

                JButton howtouse = new JButton();
                howtouse.setBounds(845, 70, 90, 25);
                howtouse.setOpaque(false);
                howtouse.setContentAreaFilled(false);
                howtouse.setBorderPainted(false);
                howtouse.addActionListener(new InstructListener());
                howtouse.setToolTipText("About");
                mainpanel.add(howtouse);

                JButton build = new JButton(new ImageIcon("images/build.png"));
                build.setBounds(900, 225, 70, 70);
                build.setOpaque(false);
                build.setContentAreaFilled(false);
                build.setBorderPainted(false);
                build.addActionListener(new BuildListener());
                build.setToolTipText("Build");
                mainpanel.add(build);

                executeb = new JButton(new ImageIcon("images/execute.png"));
                executeb.setBounds(900, 305, 70, 70);
                executeb.setOpaque(false);
                executeb.setContentAreaFilled(false);
                executeb.setBorderPainted(false);
                executeb.addActionListener(new ExecuteListener());
                executeb.setToolTipText("Execute");
                executeb.setEnabled(false);
                mainpanel.add(executeb);

               JButton newfile = new JButton(new ImageIcon("images/new.png"));
               newfile.setBounds(30, 30, 40, 40);
               newfile.setOpaque(false);
               newfile.setContentAreaFilled(false);
               newfile.setBorderPainted(false);
               newfile.addActionListener(new NewListener());
               newfile.setToolTipText("New");
               mainpanel.add(newfile);

                JButton save = new JButton(new ImageIcon("images/save.png"));
                save.setToolTipText("Save");
                save.setBounds(130, 30, 40, 40);
                save.setOpaque(false);
                save.setContentAreaFilled(false);
                save.setBorderPainted(false);
                save.addActionListener(new SaveListener());
                mainpanel.add(save);

                JButton open = new JButton(new ImageIcon("images/open.png"));
                open.setToolTipText("Open");
                open.setBounds(80, 30, 40, 40);
                open.setOpaque(false);
                open.setContentAreaFilled(false);
                open.setBorderPainted(false);
                open.addActionListener(new OpenListener());
                mainpanel.add(open);


                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                assempanel = new JPanel();
                assempanel.setOpaque(false);
                assempanel.setLayout(null);
                assempanel.setBounds(10, 140, 1000, 550);

                filename = new JLabel("  Document");
                filename.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
                filename.setForeground(Color.WHITE);
                filename.setBounds(20, 115, 290, 30);

                inputarea = new JTextArea();
                inputpane = new JScrollPane(inputarea);
                inputarea.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
                inputpane.setBounds(20, 10, 850, 390);
                inputarea.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), new EmptyBorder(5,10, 0, 30)));
                assempanel.add(inputpane);

         /*       tooloutput = new JTextArea();
                toolpane = new JScrollPane(tooloutput);
                tooloutput.setFont(new Font("Consolas", Font.PLAIN, 14));
                tooloutput.setEditable(false);
                toolpane.setBounds(20, 475, 335, 60);
                assempanel.add(toolpane);
*/
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            /*    machinepanel = new JPanel();
                machinepanel.setOpaque(true);
                machinepanel.setLayout(null);
                machinepanel.setBackground(Color.blue);
                machinepanel.setBounds(480, 170, 385, 335);


                outputarea = new JTextArea();
                outputpanel = new JScrollPane(outputarea);
                outputpanel.setBounds(0, 0, 385, 335);
                outputarea.setFont(new Font("Consolas", Font.PLAIN, 18));
                outputarea.setEditable(false);
                outputarea.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), new EmptyBorder(5, 10, 0, 0)));
                machinepanel.add(outputpanel);
*/
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                JPanel stackpanel = new JPanel();
                stackpanel.setOpaque(false);
                stackpanel.setBounds(575, 120, 120, 340);

                int top = 280;
                for(int i=0; i<5; i++) {
                        stack[i] = new JLabel("" );
                        stack[i].setBounds(940, top, 90,15);
                        stack[i].setForeground(Color.white);
                        stack[i].setFont(new Font("Lucida Sans", Font.PLAIN, 18));
                        top += 50;
                        mainframe.add(stack[i]);
                }

                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                executionpanel = new JPanel();
                executionpanel.setOpaque(false);
                executionpanel.setLayout(null);
                executionpanel.setBounds(10, 570, 1000, 105);

        /*        logarea = new JTextArea();
                logpanel = new JScrollPane(logarea);
                logarea.setFont(new Font("Consolas", Font.PLAIN, 12));
                logpanel.setBounds(0, 0, 380, 105);
                logarea.setEditable(false);
                logarea.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), new EmptyBorder(5, 10, 0, 0)));
                executionpanel.add(logpanel);
*/
                consolearea = new JTextArea();
                consolepanel = new JScrollPane(consolearea);
                consolearea.setFont(new Font("Consolas", Font.PLAIN, 18));
               // consolepanel.setBounds(1, 1, 10, 100);
                consolepanel.setBounds(20, 10, 850, 390);
                consolepanel.setOpaque(false);
                consolearea.setEditable(false);
                consolearea.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2), new EmptyBorder(5, 10, 0, 0)));
                executionpanel.add(consolepanel);

                mainpanel.add(assempanel);
           //     mainpanel.add(machinepanel);
                mainpanel.add(executionpanel);
                mainpanel.add(stackpanel);
                mainpanel.add(setBG);
                mainframe.add(filename);
                mainframe.add(mainpanel);

                mainframe.setVisible(true);
        }


        /**
        * ActionListener for New File
        */
        public class NewListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                        inputarea.setText("");
                        filename.setText("   Document");

                        tooloutput.setText("");
//                        outputarea.setText("");
                        logarea.setText("");
                        consolearea.setText("");

                        for(int i=0; i<5; i++) {
                                                        stack[i].setText("");

                        }
                }
        }

        /**
        * ActionListener for Open File
        */
        public class OpenListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {

                        JFileChooser chooser = new JFileChooser("MPGui.java");
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
//                                        outputarea.setText("");
                                        logarea.setText("");
                                        consolearea.setText("");

                                        for(int i=0; i<5; i++) {
                                                stack[i].setText("");
                                        }
                                } catch(Exception ex) {
                                        ex.printStackTrace();
                                }
                        }
                }
        }


        /**
        * ActionListener for Save File
        */
        public class SaveListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser("MPGui.java");
                        chooser.setMultiSelectionEnabled(false);
                        chooser.setSelectedFile(new File(filename.getText().trim()));
                        int option = chooser.showSaveDialog(mainframe);
                        if (option == JFileChooser.APPROVE_OPTION) {
                                try {
                                        FileWriter fw = new FileWriter(chooser.getSelectedFile());
                                        fw.write(inputarea.getText().toString());
                                        filename.setText("   " + chooser.getSelectedFile().getName());
                                        fw.close();
                                } catch (Exception ex) {
                                        ex.printStackTrace();
                                }
                        }
                }
        }

        /**
        * ActionListener for Exit
        */
        public class ExitListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                        if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                System.exit(0);
                        }
                }
        }

        /**
        * ActionListener for Build
        */
        public class BuildListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                       try {
                               logarea.setText("");
                               consolearea.setText("");
//                               outputarea.setText("");

                               
                       } catch (Exception ex) {
                               ex.printStackTrace();
                       }
                }
        }

        public class Swingwork extends SwingWorker {
                protected Object doInBackground () throws Exception {
                        //Executor execu = new Executor(MLA, GUI.this);
                        //execu.execute();
                        return 0;
                }
        }

        /**
        * ActionListener for Execute
        */
        public class ExecuteListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                        logarea.setText("");
                        consolearea.setText("");
                        for(int i=0; i<5; i++) {
                                stack[i].setText("");

                        }
                        //Executor execu = new Executor(MLA, GUI.this);
                        //execu.execute();
                        Swingwork work = new Swingwork();
                        work.execute();
                }
        }

        /**
        * ActionListener for getting Instructions
        */
        public class InstructListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                       try{
                               Process p = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler D:/CMSC124/HOW TO USE.pdf");
                               p.waitFor();

                       }catch(Exception s){
                       }
                }
        }

        /**
        * ActionListener for About the Programmers
        */
        public class AboutListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "   Rosario, Roxenne Lourdes  D.\n Sacedor, Paul Alvin\n Salas, Rochelle  ");
                }
        }



        public static void main(String[] args) {
                GUI a = new GUI();
        }

}

//http://zqua-x-.deviantart.com/art/The-Techno-7966039


