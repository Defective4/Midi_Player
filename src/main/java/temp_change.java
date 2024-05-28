import javax.sound.midi.Sequencer;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class temp_change implements ChangeListener {
    public Sequencer seq;
    public JSlider sd;

    public temp_change(Sequencer seq, JSlider sd) {
        sd.addChangeListener(this);
        this.seq = seq;
        this.sd = sd;
    }

    public void stateChanged(ChangeEvent e) {
        this.seq.setTempoInBPM(sd.getValue());

    }
}