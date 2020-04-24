import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.text.SimpleDateFormat;

public class register extends JFrame {
    private JTextField username_tf;
    private JPasswordField pass_1;
    private JTextField email_tf;
    private JPasswordField pass_2;
    private JPanel rootPanel;
    private JButton zurückZumLoginButton;
    private JButton registerButton;

    public register() {
        add(rootPanel);

        setTitle("Registrieren");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 350);
        setVisible(true);
        zurückZumLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = username_tf.getText();
                String uemail = email_tf.getText();
                String upass = String.valueOf(pass_1.getPassword());
                String urepass = String.valueOf(pass_2.getPassword());
                boolean password = (upass.equals(urepass));
                boolean nameavailable = false;
                String upass_hash = "";
                GetData dat = new GetData();
                LocalDate regDate = LocalDate.now();
                PwHash pwh = new PwHash();
                if(dat.get_all_names().contains(uname)){
                    JOptionPane.showMessageDialog(rootPanel,"Dieser Username existiert bereits!","Funkspiel München", JOptionPane.ERROR_MESSAGE);
                }else if(dat.get_all_emails().contains(uemail)){
                    JOptionPane.showMessageDialog(rootPanel,"Ein Account mit dieser Email existiert bereits!","Funkspiel München", JOptionPane.ERROR_MESSAGE);
                }
                else if(upass.equals(urepass)) {
                    try {
                        upass_hash = pwh.hash_pw(upass);
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen", "root", "");
                        System.out.println("Con =" + con);
                        Statement stmt = con.createStatement();
                        String sql = "INSERT INTO users (u_name, u_email, u_pass, regDate) " + "VALUES ('" + uname + "','" + uemail + "','" + upass_hash + "','" + regDate + "')";
                        System.out.println(sql);
                        stmt.execute(sql);
                        con.close();
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(upass + "==" + urepass);
                }else{
                    JOptionPane.showMessageDialog(rootPanel,"Die Passwörter stimmen nicht überein!","Funkspiel München", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}