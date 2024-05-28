import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cl_button implements ActionListener {
    public void attach(JButton btn) {
        btn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}