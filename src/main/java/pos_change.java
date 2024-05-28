import javax.sound.midi.Sequencer;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class pos_change implements ChangeListener {
    public Sequencer seq;
    public JSlider sd;

    public pos_change(Sequencer seq, JSlider sd) {
        sd.addChangeListener(this);
        this.seq = seq;
        this.sd = sd;
    }

    public void stateChanged(ChangeEvent e) {
        if (this.sd.getValueIsAdjusting()) {
            float bpm = this.seq.getTempoInBPM();
            this.seq.setTickPosition(sd.getValue());
            this.seq.setTempoInBPM(bpm);
        }
    }
}