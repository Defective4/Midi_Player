import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fchooser implements ActionListener {
    public JFileChooser jf;
    public JFrame win;

    public void construct() {
        JFrame w = new JFrame("Choose a MIDI file...");
        JFileChooser fc = new JFileChooser();
        String[] ext = {"mid", "midi"};
        fc.setFileFilter(new FileNameExtensionFilter("MIDI file", ext));
        fc.addActionListener(this);
        fc.setApproveButtonText("Open");
        w.add(fc);
        w.pack();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setVisible(true);
        this.win = w;
        this.jf = fc;
    }

    public void actionPerformed(ActionEvent event) {
        String e = event.toString();
        if (e.replace("CANCEL_OPTION", "").length() != e.length()) {
            System.exit(0);
        }
        if (e.replace("APPROVE_OPTION", "").length() != e.length()) {
            new player_instance(this.jf.getSelectedFile().getPath(), this.jf.getSelectedFile().getName(), true, false);
            this.win.setVisible(false);
        }
    }
}