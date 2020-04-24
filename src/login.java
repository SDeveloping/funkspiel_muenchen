import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

public class login extends JFrame {
    private JTextField username_tf;
    private JPasswordField pass_tf;
    private JButton loginButton;
    private JPanel rootPanel;
    private JButton registrierenButton;
    static String uname;

    Var v = new Var();

    public login(){
        add(rootPanel);

        setTitle("Login");
        setSize(500,250);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final int[] upass = new int[1];
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(rootPanel,"Erfolgreich eingeloggt!","Funkspiel München",JOptionPane.INFORMATION_MESSAGE);
                uname = username_tf.getText();

                String upass = String.valueOf(pass_tf.getPassword());
                String passhash = "";
                PwHash pwh = new PwHash();

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen","root","");
                    //here sonoo is database name, root is username and password
                    System.out.println("Con =" + con);
                    Statement stmt=con.createStatement();
                    passhash = pwh.hash_pw(upass);
                    String com = "select * from users where u_name = '" + uname + "' and u_pass = '" + passhash +"'";
                    //String com = "select * from users where u_name = 'admin' and u_pass = '1234'";
                    System.out.println(com);
                    ResultSet rs=stmt.executeQuery(com);

                    if(rs.next()){
                        //rs.next();
                        System.out.println(rs.getInt("u_id")+"  "+rs.getString("u_name")+"  "+rs.getString("u_email")+"  "+rs.getString("u_pass"));
                    }

                    int rscount = rs.getRow();
                    System.out.println(rscount);

                    if(rscount == 1){
                        System.out.println("Loging in");
                        JOptionPane.showMessageDialog(rootPanel,"Sie werden eingeloggt.","Funkspiel München",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new home();
                    } else {
                        JOptionPane.showMessageDialog(rootPanel,"Dieser Benutzer ist nicht in unserer Datenbank!","Funkspiel München",JOptionPane.ERROR_MESSAGE);
                    }

                    con.close();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new register();
                dispose();
            }
        });
    }
}
