import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class home extends JFrame{
    private JPanel rootPanel;
    private JButton logoutButton;
    private JButton adminbutton;
    private JButton dispobutton;
    GetData dat = new GetData();
    Var v = new Var();

    public home(){
        add(rootPanel);

        setTitle("Home");
        setSize(800,500);
        setResizable(false);
        adminbutton.setVisible(false);
        dispobutton.setVisible(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        boolean admin = dat.get_rank(login.uname).contains("1");
        boolean disponent = dat.get_dispo(login.uname).get(0).equals("Y");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new login();
            }
        });
        if(admin) {
            adminbutton.setVisible(true);
            adminbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new admin();
                }
            });
        }
        if(disponent) {
            dispobutton.setVisible(true);
            dispobutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new dispo();
                }
            });
        }
    }
}
