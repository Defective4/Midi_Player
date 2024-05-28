import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class net_btn implements ActionListener {
    public JFrame fr;

    public void attach(JButton btn, JFrame fr) {
        btn.addActionListener(this);
        this.fr = fr;
    }

    public void actionPerformed(ActionEvent e) {
        fr.setVisible(false);
        new network_open(true);
    }
}