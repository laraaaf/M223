import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class time extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton weiterButton;
    private JButton abmeldenButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    time frame = new time(0);
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

    public time(long Diff) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 190, 800, 700);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //abfrage der Zeit
        JLabel lbltitle = new JLabel("Schreibe in " + Diff + " h weiter");
        lbltitle.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        lbltitle.setBounds(200, 52, 400, 400);
        contentPane.add(lbltitle);


         //weiter-Button
         weiterButton = new JButton("fertig");
         weiterButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             
 
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