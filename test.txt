import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class test extends JPanel implements ActionListener {
   JPanel panel;
   JLabel user_label, password_label, message;
   JTextField userName_text;
   JPasswordField password_text;
   JButton submit, cancel;

   static JFrame frame;
	private JLabel PrgRunStatus;
	private String umg;
	private String DB;
    private String MausPressed = "000";
	private int MouseButtonLeft = 0;
	private int MouseButtonCenter = 0;
	private int MouseButtonRight = 0;
	private String Editor = "notepad.exe";

		
//	Startpfad initialisieren
	private String javaStart 	= "";
	private String javaData 	= "";
	private String perlGrundPfad= "";
	private String perlExe 		= "";
	private String perlData 	= "";
	private String perlpfad 	= "";
	private String StwTyp = "";
	private String StatTyp = "";
	private String Unterart = "PROD";
	
	private JButton buttonHelp;
	private JButton buttonRes2;
	private JButton buttonStart;
	private JButton buttonExit;
	private JButton ResAnz;

	public JComboBox StellwerkList;
	public JComboBox StatList;

   public test() {

  

      setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
     
      //Create the UI for displaying result.
 
     buttonExit = new JButton(" Exit ");
    buttonExit.setActionCommand("Exit");
    buttonExit.setPreferredSize(new Dimension(133,30));
    buttonExit.addActionListener(this);

   //      ===========================================================

   /*
   *		Dient nur als Beispiel, funktionell nicht verwendet 
   */
    JButton buttonStop = new JButton("");
    buttonStop.setActionCommand("Stop");
    buttonStop.setPreferredSize(new Dimension(30,30));
    buttonStop.addActionListener(this);
    buttonStop.setVisible(false);

    JButton buttonPH31 = new JButton("");
    buttonPH31.setActionCommand("PH31");
    buttonPH31.setPreferredSize(new Dimension(30,30));
    buttonPH31.addActionListener(this);
    buttonPH31.setVisible(false);
    
    JButton buttonLupe = new JButton("");
    buttonLupe.setActionCommand("Lupe");
    buttonLupe.setPreferredSize(new Dimension(30,30));
    buttonLupe.addActionListener(this);
    buttonLupe.setVisible(false);

   //-------------------------------------------------------------------
    
    JPanel HelpPanel = new JPanel(new GridLayout(0,4,5,0));
      HelpPanel.add(buttonHelp);
      HelpPanel.add(buttonStop);
      HelpPanel.add(buttonPH31);
      HelpPanel.add(buttonLupe);
      HelpPanel.setPreferredSize(new Dimension(133,30));
      HelpPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
   //      -------------------------------------------------------------------

    buttonRes2 = new JButton("Res2");
    buttonRes2.setActionCommand("Res2");
    buttonRes2.setPreferredSize(new Dimension(133,30));
    buttonRes2.addActionListener(this);
    buttonRes2.setEnabled(true);
    buttonRes2.setVisible(false);

       buttonStart = new JButton("Abfrage Starten");
     buttonStart.setActionCommand("buttonStart");
     buttonStart.setPreferredSize(new Dimension(133,30));
     buttonStart.addActionListener(this);
   

     JPanel startStop1 = new JPanel(new GridLayout(0,4,5,0));
      startStop1.add(buttonExit);
      startStop1.add(HelpPanel);
      startStop1.add(buttonRes2);
      startStop1.add(buttonStart);
      startStop1.setAlignmentX(Component.LEFT_ALIGNMENT);

      JPanel startStop = new JPanel(new BorderLayout());
      startStop.add(BorderLayout.CENTER, startStop1);
      startStop.setAlignmentX(Component.LEFT_ALIGNMENT);

   //================================================================

      //Create the UI for displaying result.
      JLabel resultLabel = new JLabel("Bedien-Status", JLabel.LEADING); //== LEFT
      PrgRunStatus = new JLabel("Bedien-Status", JLabel.LEADING);
      PrgRunStatus.setPreferredSize(new Dimension(410,30));
      PrgRunStatus.setForeground(Color.black);
      PrgRunStatus.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createLineBorder(Color.black),
           BorderFactory.createEmptyBorder(5,5,5,5)
      ));
     
    ResAnz = new JButton("Anzeigen");
    ResAnz.setActionCommand("Anzeigen");
    ResAnz.addActionListener(this);

//		buttonRes.setEnabled(false);
    ResAnz.setPreferredSize(new Dimension(133,32));
    ResAnz.setVisible(true);
    ResAnz.setEnabled(false);
    
      JPanel resultPanel = new JPanel(new BorderLayout());
      resultPanel.add(BorderLayout.NORTH, resultLabel);
      resultPanel.add(BorderLayout.WEST, PrgRunStatus);
      resultPanel.add(BorderLayout.EAST, ResAnz); 
      resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

              //      Lay out everything.
//      ========================================== 

   add(Box.createRigidArea(new Dimension(0, 10)));

   add(Box.createRigidArea(new Dimension(0, 20)));

   add(Box.createRigidArea(new Dimension(0, 20)));

      add(resultPanel);
      add(Box.createRigidArea(new Dimension(0, 50)));
      add(startStop);

      setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
   }

   private static void createAndShowGUI() {
      //Create and set up the window.
      frame = new JFrame("Login");
      frame.setLocation(400, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setIconImage(new ImageIcon(UartSuchen.class.getResource(resources.getString("PH31.icon"))).getImage());
      
      //Create and set up the content pane.
      JComponent newContentPane = new test();
      newContentPane.setOpaque(true); //content panes must be opaque
      frame.setContentPane(newContentPane);

      //Display the window.
      frame.pack();
      frame.setVisible(true);
//        frame.setSize(600, 350);
  }
   /*** 
   LoginDemo() {
      // Username Label
      user_label = new JLabel();
      user_label.setText("User Name :");
      userName_text = new JTextField();
      // Password Label
      password_label = new JLabel();
      password_label.setText("Password :");
      password_text = new JPasswordField();
      // Submit
      submit = new JButton("SUBMIT");
      panel = new JPanel(new GridLayout(3, 1));
      panel.add(user_label);
      panel.add(userName_text);
      panel.add(password_label);
      panel.add(password_text);
      message = new JLabel();
      panel.add(message);
      panel.add(submit);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Adding the listeners to components..
      submit.addActionListener(this);
      add(panel, BorderLayout.CENTER);
      setTitle("Please Login Here !");
      setSize(450,350);
      setVisible(true);
   } */
   public static void main(String[] args) {

      new Logint();
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
             createAndShowGUI();
         }
     } );
   }

   @Override
   public void actionPerformed(ActionEvent ae) {
      String userName = userName_text.getText();
      String password = password_text.getText();
      if (userName.trim().equals("admin") && password.trim().equals("admin")) {
         message.setText(" Hello " + userName + "");
      } else {
         message.setText(" Invalid user.. ");
      }
   }
}