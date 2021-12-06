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
public class downloadhistory extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField s1;
    private JTextField s2;
    private JButton downloadButton;
    private JButton weiterButton;
    private JButton abmeldenButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    downloadhistory frame = new downloadhistory();
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

    public downloadhistory() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 190, 800, 700);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbltitle = new JLabel("Danke \n für deine \n\n Ergänzung :)");
        lbltitle.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        lbltitle.setBounds(200, 52, 400, 400);
        contentPane.add(lbltitle);


        //download-button
        downloadButton = new JButton("Geschichte downloaden");
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
     

                //Todo: herunterladen geschichte in txt              

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/127.0.0.1/foldingpaperstory", "root", "");

                    String query = "SELECT Mail FROM tbl_user WHERE Password ='";

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(downloadButton, "Passwort oder E-Mail Adresse Falsch");
                    } else {
                        JOptionPane.showMessageDialog(downloadButton,
                            "Hallo,Erfolgreich angemeldet");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        downloadButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        downloadButton.setBounds(260, 450, 280, 30);
        contentPane.add(downloadButton);

         //weiter-Button
         weiterButton = new JButton("weiter");
         weiterButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             
 
                 try {
                     time frame = new time();
                     frame.setVisible(true);
                   
                 } catch (Exception exception) {
                     exception.printStackTrace();
                 }
             }
         });
         weiterButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
         weiterButton.setBounds(320, 600, 150, 30);
         contentPane.add(weiterButton);

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