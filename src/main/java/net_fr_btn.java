import javax.sound.midi.Sequencer;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class net_fr_btn implements ActionListener {
    public JFrame fr;
    public Sequencer sq;

    public void attach(JButton btn, JFrame fr, Sequencer sq) {
        btn.addActionListener(this);
        this.fr = fr;
        this.sq = sq;
    }

    public void actionPerformed(ActionEvent e) {
        this.sq.stop();
        this.sq.close();
        this.sq = null;
        fr.setVisible(false);
        new network_open(true);
    }
}