import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 190, 800, 700);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Todo: abfrage vorheriger Satz
        JLabel lblsmattittle = new JLabel("Vorheriger Satz");
        lblsmattittle.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblsmattittle.setBounds(350, 52, 400, 50);
        contentPane.add(lblsmattittle);

        JLabel lbltitle = new JLabel("Schreibe weiter...");
        lbltitle.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lbltitle.setBounds(350, 152, 400, 50);
        contentPane.add(lbltitle);

        //email
        JLabel s1label = new JLabel("Satz 1");
        s1label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        s1label.setBounds(242, 243, 160, 50);
        contentPane.add(s1label);

        s1 = new JTextField();
        s1.setFont(new Font("Tahoma", Font.PLAIN, 32));
        s1.setBounds(414, 235, 228, 50);
        s1.setColumns(10);
        contentPane.add(s1);
      

        //passwort
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
        sendenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
     

                try {
                    downloadhistory frame = new downloadhistory();
                    frame.setVisible(true);
                  
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //Todo: abspeichern der Sätze              

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/127.0.0.1/foldingpaperstory", "root", "");

                    String query = "SELECT Mail FROM tbl_user WHERE Password ='";

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(sendenButton, "Passwort oder E-Mail Adresse Falsch");
                    } else {
                        JOptionPane.showMessageDialog(sendenButton,
                            "Hallo,Erfolgreich angemeldet");
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
                    register frame = new register();
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