import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

/**
 * User Registration using Swing
 * @author javaguides.net
 *
 */
public class expandhistory extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField s1;
    private JTextField s2;
    private JButton sendenButton;
    private JButton abmeldenButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    expandhistory frame = new expandhistory("1");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    
    public expandhistory(String ID) {
     
        final String SentenceInput_Pattern = 
        "([A-Za-z0-9\\?! ,.-]+)";
       

         String id = ID;
         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/foldingpaperstory", "root", "");


            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(250, 190, 800, 700);
            setResizable(false);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            //abfrage vorheriger Satz
            String query = "SELECT sentence2 FROM `tbl_story` ORDER BY ID_StoryLine DESC";

            Statement sta = connection.createStatement();
            ResultSet x = sta.executeQuery(query);

            if (x.next() == false) {
                JLabel lblsmattittle = new JLabel("Schreibe die ersätzen Sätze der Geschichte...");
                lblsmattittle.setFont(new Font("Times New Roman", Font.PLAIN, 30));
                lblsmattittle.setBounds(350, 52, 400, 50);
                contentPane.add(lblsmattittle);
            } else {
                
                String s2 = x.getString(x.findColumn("sentence2"));
                JLabel lblsmattittle = new JLabel(s2);
                lblsmattittle.setFont(new Font("Times New Roman", Font.PLAIN, 30));
                lblsmattittle.setBounds(350, 52, 400, 50);
                contentPane.add(lblsmattittle);
                    
            }

            JLabel lbltitle = new JLabel("Schreibe weiter...");
            lbltitle.setFont(new Font("Times New Roman", Font.PLAIN, 42));
            lbltitle.setBounds(300, 152, 400, 50);
            contentPane.add(lbltitle);

            //s1
            JLabel s1label = new JLabel("Satz 1");
            s1label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            s1label.setBounds(242, 243, 160, 50);
            contentPane.add(s1label);

           
            s1 = new JTextField(2);
            s1.setFont(new Font("Tahoma", Font.PLAIN, 32));
            s1.setBounds(414, 235, 228, 50);
            s1.setColumns(10);
            contentPane.add(s1);
        

            //s2
            JLabel s2label = new JLabel("Satz 2");
            s2label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            s2label.setBounds(242, 324, 124, 36);
            contentPane.add(s2label);

            s2 = new JTextField();
            s2.setFont(new Font("Tahoma", Font.PLAIN, 32));
            s2.setBounds(414, 320, 228, 50);
            s2.setColumns(10);
            contentPane.add(s2);

            
           
            //counter
            JLabel counter = new JLabel("Du hast nur 2 min!!");
            counter.setFont(new Font("Tahoma", Font.PLAIN, 20));
            counter.setForeground(Color.RED);
            counter.setBounds(300, 400, 400, 50);
            contentPane.add(counter);
            Timer timer = new Timer(2*60*1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    login frame = new login();
                    frame.setVisible(true);
                    dispose();
                    
                }
              });
    
              timer.setRepeats(false); // Only execute once
              timer.start();
              
              System.out.println(timer.getDelay()/1000/60 + "min");
                    
                
              
              
            

            //anmelden-button
            sendenButton = new JButton("senden");
         } catch (Exception exception) {
            exception.printStackTrace();
            }

            sendenButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {

                if (!s2.getText().matches(SentenceInput_Pattern) || !s1.getText().matches(SentenceInput_Pattern) || s2.getText().length() > 25 || s1.getText().length() > 25 ){
                    JOptionPane.showMessageDialog(sendenButton, "Eingabe invalide");
                }else{
                    
                


                 try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/foldingpaperstory?allowMultiQueries=true", "root", "");
                
                    String sent1 = s1.getText();
                    String sent2 = s2.getText();
                    
                    //abspeichern der Sätze         
                    String query = "INSERT INTO tbl_story (sentence1,sentence2) values('" + sent1 + "','" + sent2 +  "');INSERT INTO `tbl_userstory` (`ID_StoryLine`, `timestamp`, `FK_User`, `FK_StoryLine`) VALUES (NULL, NOW(),'" + id + "', (SELECT ID_StoryLine FROM `tbl_story` WHERE sentence1 = '" + sent1 + "' AND sentence2 = '" + sent2 + "') )";
                   
                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(sendenButton, "konnte Geschichte nicht erweitern, verusche es erneut.");
                    } else {
                        downloadhistory frame = new downloadhistory();
                        frame.setVisible(true);
                        dispose();
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    }
                }
            }
         }); 

            sendenButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
            sendenButton.setBounds(320, 600, 150, 30);
            contentPane.add(sendenButton);

            //reg-Button
            abmeldenButton = new JButton("abmelden");
            abmeldenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                

                    try {
                        login frame = new login();
                        frame.setVisible(true);
                        dispose();
                    
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            abmeldenButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
            abmeldenButton.setBounds(600, 10, 150, 30);
            contentPane.add(abmeldenButton);
        

    }
}