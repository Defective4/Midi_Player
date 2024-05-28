import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    public JFrame win;

    public Main() {
        JFrame w = new JFrame("MIDI Player");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton btn = new JButton("Choose a MIDI file");
        JButton netbtn = new JButton("Open WEB location");
        net_btn nb = new net_btn();
        nb.attach(netbtn, w);
        btn.addActionListener(this);
        Box box = Box.createVerticalBox();
        box.add(btn);
        box.add(netbtn);
        JButton e_btn = new JButton("Wyjdź");
        cl_button cl = new cl_button();
        cl.attach(e_btn);
        box.add(e_btn);
        w.add(box);
        w.pack();
        w.setVisible(true);
        this.win = w;

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            new Main();
        } else {
            new player_instance(args[0], args[0], false, false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        this.win.setVisible(false);
        fchooser fc = new fchooser();
        fc.construct();
    }

}