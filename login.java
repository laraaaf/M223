import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class login extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField email;
    private JPasswordField passwordField;
    private JButton anmeldenButton;
    private JButton regButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login frame = new login();
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

    public login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 190, 800, 700);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Login");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(350, 52, 400, 50);
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

        //anmelden-button
        anmeldenButton = new JButton("Anmelden");
        anmeldenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
     
                

                String password = passwordField.getText();
                
            

                String msg = "" + email;
                msg += " \n";
               

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/foldingpaperstory", "root", "");

                    
                    //get id from user
                    String query = "SELECT ID_User FROM tbl_user WHERE Password = '" + password.hashCode() + "' AND Mail = '" + email.getText() + "'  ";
                    Statement sta = connection.createStatement();
                    ResultSet x = sta.executeQuery(query);
                   
                    if (x.next() == false) {
                        JOptionPane.showMessageDialog(anmeldenButton, "Passwort oder E-Mail Adresse Falsch");
                    } else {
                        String id = x.getString(x.findColumn("ID_User"));

                        //check if last expand was 2 hours ago
                        query = "SELECT timestamp FROM tbl_userstory WHERE FK_User = '" + id + "' ORDER BY ID_StoryLine DESC";
                        sta = connection.createStatement();
                        ResultSet l = sta.executeQuery(query);
                        if (l.next() == true){
                        
                        LocalDateTime startTime = LocalDateTime.parse(l.getString(l.findColumn("timestamp")),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                         
                        LocalDateTime now = LocalDateTime.now();           
                        long difference = ChronoUnit.HOURS.between(startTime, now);
                        
                        
                        if (difference>= 2){
                        
                            expandhistory frame = new expandhistory(id);
                            frame.setVisible(true);
                            dispose();

                        }else{

                            long wait = 2 - difference;
                            time frame = new time(wait);
                            frame.setVisible(true);
                            dispose();
                        }
                    }else{

                        expandhistory frame = new expandhistory(id);
                        frame.setVisible(true);
                        dispose();}
                            
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        anmeldenButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        anmeldenButton.setBounds(320, 600, 150, 30);
        contentPane.add(anmeldenButton);

        //reg-Button
        regButton = new JButton("Registrieren");
        regButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            

                try {
                    register frame = new register();
                    frame.setVisible(true);
                    dispose();
                  
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        regButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        regButton.setBounds(600, 10, 150, 30);
        contentPane.add(regButton);

    }
}