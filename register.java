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
public class register extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField email;
    private JPasswordField passwordField;
    private JButton registrierButton;
    private JButton anButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    register frame = new register();
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

    public register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 190, 800, 700);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Registrierung");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(300, 52, 400, 50);
        contentPane.add(lblNewUserRegister);

        //email
        JLabel lblNewLabel = new JLabel("E-Mail Adresse");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(242, 243, 160, 50);
        contentPane.add(lblNewLabel);

        email = new JTextField();
        email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        email.setBounds(414, 235, 228, 50);
        email.setColumns(10);
        contentPane.add(email);
      

        //passwort
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(242, 324, 124, 36);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(414, 320, 228, 50);
        passwordField.setColumns(10);
        contentPane.add(passwordField);

        //reg-button
        registrierButton = new JButton("Registrieren");
        registrierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

              
                
                String emailId = email.getText();
                String password = passwordField.getText();
                int hashpassword = password.hashCode();
               
                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/foldingpaperstory", "root", "");

                 //Check if E-Mail input is valid

                    //pattern
                    final String EMAIL_PATTERN = 
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                    final String passwort_pattern = 
                    "([A-Za-z0-9\\?!,.-]+)";

                    //check if Mail is already used
                    String query = "SELECT Mail FROM tbl_user WHERE Mail = '" + email.getText() + "'  ";

                    Statement sta = connection.createStatement();
                    ResultSet res = sta.executeQuery(query);

    
                    if (!emailId.matches(EMAIL_PATTERN) || res.next() == true || password.matches(passwort_pattern) || password.length() >= 8 ) {
                        JOptionPane.showMessageDialog(registrierButton, "E-Mail Adresse wird entweder bereits gebraucht oder Eingaben entsprechen nicht den vorgaben");
                    }else{

                   
                    query = "INSERT INTO tbl_user (Password,Mail) values('" + hashpassword + "','" + emailId +  "')";

                    sta = connection.createStatement();
                    int x = sta.executeUpdate(query);

                    
                   

                    if (x == 0) {
                        JOptionPane.showMessageDialog(registrierButton, "This is alredy exist");
                    } else {

                        

                        //get id from user
                        query = "SELECT ID_User FROM tbl_user WHERE Password = '" + hashpassword + "' AND Mail = '" + emailId + "'  ";
                        sta = connection.createStatement();
                        ResultSet ids = sta.executeQuery(query);
                        ids.next();
                        String id = ids.getString(ids.findColumn("ID_User"));
                    
                        
                        expandhistory frame = new expandhistory(id);
                        frame.setVisible(true);
                        dispose();
                    }
                    connection.close();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            
        });
        registrierButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        registrierButton.setBounds(320, 600, 150, 30);
        contentPane.add(registrierButton);

        //an-Button
        anButton = new JButton("Anmelden");
        anButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            

                try {
                    login frame = new login();
                    frame.setVisible(true);
                  
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        anButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        anButton.setBounds(600, 10, 150, 30);
        contentPane.add(anButton);

    }
}