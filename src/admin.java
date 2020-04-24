import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class admin extends JFrame {
    private JPanel rootPanel;
    private JList datalist;
    private JButton getData;
    private JTextField user_email;
    private JTextField user_regDate;
    private JTextField user_pass;
    private JTextField user_name;
    private JButton editData;
    private JButton exit;
    private JTextArea textArea1;
    GetData dat = new GetData();
    String selected = "";

    public admin(){
        add(rootPanel);

        setTitle("Admin Interface");
        setSize(800,500);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PwHash pwh = new PwHash();

        DefaultListModel lm = new DefaultListModel();
        lm.clear();
        for(int i = 0; i < dat.get_all_names().size(); i++){
            lm.addElement(dat.get_all_names().get(i));
        }
        datalist.setModel(lm);

        /*getData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = list1.getSelectedValue().toString();
                String email = dat.get_email(selected).get(0);
                user_email.setText(email);
                Date regDate = dat.get_regDate(selected).get(0);
                user_regDate.setText(String.valueOf(regDate));
                user_regDate.setEditable(false);
                user_name.setText(selected);
                lm.clear();
                for(int i = 0; i < dat.get_all_names().size(); i++){
                    lm.addElement(dat.get_all_names().get(i));
                }
                list1.setModel(lm);
                list1.setSelectedValue(selected, true);
            }
        });*/

        editData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String email = user_email.getText();
                    String pass = user_pass.getText();
                    String username = user_name.getText();
                    String hashpass = "";
                    boolean password = (pass.equals(""));
                    System.out.println(email);
                    System.out.println(pass);
                    System.out.println(username);
                    if(password=false){
                        hashpass = pwh.hash_pw(pass);

                        try{
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen", "root", "");
                            System.out.println("Con =" + con);
                            Statement stmt = con.createStatement();
                            String sql = "UPDATE users SET u_name ='" + username + "', u_email='" + email + "', u_pass = '" + hashpass + "' WHERE u_name = '" + selected + "'";
                            System.out.println(sql);
                            stmt.execute(sql);
                            con.close();
                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }else{

                        try{
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funkspiel_muenchen", "root", "");
                            System.out.println("Con =" + con);
                            Statement stmt = con.createStatement();
                            String name = datalist.getSelectedValue().toString();
                            String sql = "UPDATE users SET u_name ='" + username + "', u_email ='" + email + "' WHERE u_name = '" + name + "'";
                            System.out.println(sql);
                            stmt.execute(sql);
                            con.close();
                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }

                        lm.clear();
                        for(int i = 0; i < dat.get_all_names().size(); i++){
                            lm.addElement(dat.get_all_names().get(i));
                        }
                        datalist.setModel(lm);
                        datalist.setSelectedValue(username, true);
                    }
                }

        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new home();
            }
        });

        datalist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                    String selected = datalist.getSelectedValue().toString();
                    String email = dat.get_email(selected).get(0);
                    user_email.setText(email);
                    Date regDate = dat.get_regDate(selected).get(0);
                    user_regDate.setText(String.valueOf(regDate));
                    user_regDate.setEditable(false);
                    user_name.setText(selected);
                    lm.clear();
                    for(int i = 0; i < dat.get_all_names().size(); i++){
                        lm.addElement(dat.get_all_names().get(i));
                    }
                datalist.setModel(lm);
                datalist.setSelectedValue(selected, true);
            }
        });
    }
}
