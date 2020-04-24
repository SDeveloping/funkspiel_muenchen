import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                login l = new login();
                l.setVisible(true);
                Var v = new Var();
                //register r = new register();
                //r.setVisible(true);
            }
        });
    }
}
