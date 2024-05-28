import javax.sound.midi.Sequencer;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class midi_stop implements ActionListener {
    public Sequencer s;

    public midi_stop(Sequencer seq, JButton btn) {
        btn.addActionListener(this);
        this.s = seq;
    }

    public void actionPerformed(ActionEvent e) {
        this.s.stop();
        this.s.setTickPosition(0);
    }
}