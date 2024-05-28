import javax.sound.midi.Sequencer;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class midi_play implements ActionListener {
    public Sequencer s;
    public JSlider sw;

    public midi_play(Sequencer seq, JButton btn) {
        btn.addActionListener(this);
        this.s = seq;
    }

    public void def(JSlider sw) {
        this.sw = sw;
    }

    public void actionPerformed(ActionEvent e) {
        this.s.start();
        this.sw.setValue((int) this.s.getTempoInBPM());
    }
}