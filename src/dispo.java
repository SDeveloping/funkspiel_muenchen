import javax.swing.*;

public class dispo extends JFrame {
    private JPanel rootPanel;

    public dispo(){
        add(rootPanel);

        setTitle("Disposition");
        setSize(800,500);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
