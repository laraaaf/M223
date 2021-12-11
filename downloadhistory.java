import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.io.FileWriter;   
import java.io.IOException;
import java.nio.charset.Charset;


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
                    downloadhistory frame = new downloadhistory("0");
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

    public downloadhistory(String ID) {
        String id = ID;
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
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/foldingpaperstory", "root", "");

                    //check what rolle
                    String query = "SELECT Rolle FROM tbl_user WHERE ID_User = '" + id + "' ";
                    Statement sta = connection.createStatement();
                    ResultSet r = sta.executeQuery(query);
                    r.next();
                    String rolle = r.getString("Rolle");
                    System.out.println(rolle);

                    if (rolle.matches("0")){
                    query = "SELECT sentence1, sentence2 FROM tbl_story";}
                    else{ query = "SELECT `tbl_story`.`sentence1`, `tbl_story`.`sentence2`, `tbl_user`.`Mail` FROM `tbl_story` LEFT JOIN `tbl_userstory` ON `tbl_userstory`.`FK_StoryLine` = `tbl_story`.`ID_StoryLine` LEFT JOIN `tbl_user` ON `tbl_userstory`.`FK_User` = `tbl_user`.`ID_User`"; }

                    sta = connection.createStatement();
                    ResultSet x = sta.executeQuery(query);

                    if (x.next() == false) {
                        JOptionPane.showMessageDialog(downloadButton, "Die Geschichte hat noch keine Inhalt. Es wurden keine Sätze gefunden");
                    } else {

                        //create output file
                        try {
                            String home = System.getProperty("user.home");
                            FileWriter myWriter = new FileWriter(home + "/Downloads/Geschichte.txt", Charset.forName("UTF8"));
                                                    
                            if (rolle.matches("0")){
                                myWriter.write(x.getString("sentence1") + " ");
                                myWriter.write(x.getString("sentence2") + "\n");
    
                                while (x.next()){
                                                               
                                        myWriter.write(x.getString("sentence1") + " ");
                                        myWriter.write(x.getString("sentence2") + "\n");
                                    
                                }
                            }else{ 
                                myWriter.write(x.getString("sentence1") + " ");
                                myWriter.write(x.getString("sentence1") + " ");
                                myWriter.write(x.getString("Mail") + "\n");

                                while (x.next()){
                                                           
                                    myWriter.write(x.getString("sentence1") + " ");
                                    myWriter.write(x.getString("sentence1") + " ");
                                    myWriter.write(x.getString("Mail") + "\n");
                                
                                 }
                            
                            }                               
                           
                            myWriter.close();
                            JOptionPane.showMessageDialog(downloadButton, "Die Geschichte wurde heruntergeladen");
                         
                          } catch (IOException exception) {
                            System.out.println("An error occurred.");
                            exception.printStackTrace();
                          }
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
             
 
                time frame = new time(2);
                frame.setVisible(true);
                dispose();

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