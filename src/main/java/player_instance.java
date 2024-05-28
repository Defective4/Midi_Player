import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class player_instance implements ActionListener {
    public JFrame wnd;
    public Sequencer sq;
    public JSlider pg;
    public JSlider pg2;
    public Exception ex;
    public boolean network;
    midi_play ps;

    public player_instance(String URL, String fname, boolean GUI, boolean network) {
        this.ex = null;
        this.network = network;
        if (GUI == true) {
            String framename = "Playing file " + fname;
            if (network == true) {
                framename = "Playing " + URL;
            }
            JFrame win = new JFrame(framename);
            win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Box box = Box.createVerticalBox();
            Box box2 = Box.createHorizontalBox();
            JButton exit = new JButton("Exit");
            JButton play = new JButton("Play");
            JButton pause = new JButton("Pause");
            JButton stop = new JButton("Stop");
            JButton fcot = new JButton("Choose another file");
            JButton ncot = new JButton("Open network location");
            JSlider pg = new JSlider();
            JSlider pg2 = new JSlider();
            this.pg = pg;
            this.pg2 = pg2;
            fcot.addActionListener(this);
            try {
                JFrame in = new JFrame("Initializing MIDI sequencer...");
                in.add(new JLabel("Initializing MIDI sequencer..."));
                in.pack();
                in.setVisible(true);
                Sequence inst = null;
                if (network) {
                    inst = MidiSystem.getSequence(new URL(URL));
                } else {
                    inst = MidiSystem.getSequence(new File(URL));
                }
                Sequencer seq = MidiSystem.getSequencer();
                seq.open();
                seq.setSequence(inst);
                midi_play ps = new midi_play(seq, play);
                new midi_pause(seq, pause);
                new midi_stop(seq, stop);
                this.ps = ps;
                this.sq = seq;
                in.setVisible(false);
            } catch (Exception e) {
                this.ex = e;
                String es = e.toString();
                JFrame ff = new JFrame("Error!");
                Box bx = Box.createVerticalBox();
                bx.add(new JLabel("An error occured while opening the file:"));
                bx.add(new JLabel(e.toString()));
                bx.add(new JLabel(" "));
                if (es.replace("InvalidMidiDataException", "").length() != es.length()) {
                    bx.add(new JLabel("It's possible your file is corrupt"));
                }
                if (es.replace("UnknownHostException", "").length() != es.length()) {
                    bx.add(new JLabel("Entered address does not exist"));
                }
                if (es.replace("ConnectException", "").length() != es.length()) {
                    bx.add(new JLabel("Connection refused"));
                }
                if (es.replace("IllegalArgumentException", "").length() != es.length()) {
                    bx.add(new JLabel("Invalid address!"));
                }
                if (es.replace("FileNotFoundException", "").length() != es.length()) {
                    bx.add(new JLabel("File does not exist"));
                }
                bx.add(new JLabel(" "));
                bx.add(new JLabel("Check console for more details"));
                JButton cb = new JButton("Ok");
                cl_button clb = new cl_button();
                clb.attach(cb);
                bx.add(cb);
                ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ff.add(bx);
                ff.pack();
                ff.setVisible(true);
            }
            cl_button cl = new cl_button();
            cl.attach(exit);
            net_fr_btn nb = new net_fr_btn();
            nb.attach(ncot, win, this.sq);
            box2.add(new JLabel(fname + "   "));
            box2.add(play);
            box2.add(pause);
            box2.add(stop);
            box2.add(exit);
            box2.add(fcot);
            box2.add(ncot);
            box.add(box2);
            box.add(this.pg);
            box.add(new JLabel("Tempo:"));
            box.add(this.pg2);
            win.add(box);
            win.pack();
            if (this.ex == null) {
                win.setVisible(GUI);
            }
            this.wnd = win;
            Thread t = new Thread(this::midi_len);
            t.start();
        } else {
            try {
                Sequence inst = MidiSystem.getSequence(new File(URL));
                Sequencer seq = MidiSystem.getSequencer();
                seq.open();
                Thread.sleep(1000);
                seq.setSequence(inst);
                seq.start();
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        }
    }

    public void midi_len() {
        new pos_change(this.sq, this.pg);
        int duration = (int) this.sq.getTickLength();
        int pos;
        this.pg2.setMinimum(0);
        this.pg2.setMaximum(500);
        this.pg2.setValue((int) this.sq.getTempoInBPM());
        this.pg.setMinimum(0);
        this.pg.setMaximum(duration);
        new temp_change(this.sq, this.pg2);
        this.ps.def(this.pg2);
        while (this.wnd.isVisible()) {
            try {
                Thread.sleep(1000);
                if (this.wnd.isVisible()) {
                    pos = (int) this.sq.getTickPosition();
                    this.pg.setValue(pos);
                }
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        this.wnd.setVisible(false);
        this.sq.stop();
        this.sq.close();
        this.sq = null;
        fchooser fcc = new fchooser();
        fcc.construct();
    }
}