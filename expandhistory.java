import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
                    expandhistory frame = new expandhistory();
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

    public expandhistory() {

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
            lbltitle.setBounds(350, 152, 400, 50);
            contentPane.add(lbltitle);

            //s1
            JLabel s1label = new JLabel("Satz 1");
            s1label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            s1label.setBounds(242, 243, 160, 50);
            contentPane.add(s1label);

            s1 = new JTextField();
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

            //anmelden-button
            sendenButton = new JButton("senden");
        } catch (Exception exception) {
            exception.printStackTrace();
            }

            sendenButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {



                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/foldingpaperstory", "root", "");
                
                    String sent1 = s1.getText();
                    String sent2 = s2.getText();
                
                  //Todo: abspeichern der Sätze              

               

                    String query = "INSERT INTO tbl_story (sentence1,sentence2) values('" + sent1 + "','" + sent2 +  "')";

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