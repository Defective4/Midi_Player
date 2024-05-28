import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class network_open implements ActionListener {
    public JTextField tf;
    public JFrame win;

    public network_open(boolean linked) {
        JFrame win = new JFrame("Choose a network location");
        this.win = win;
        Box box = Box.createVerticalBox();
        JTextField tf = new JTextField();
        tf.setText("http://");
        this.tf = tf;
        JButton btn = new JButton("Open");
        btn.addActionListener(this);
        JButton cbtn = new JButton("Cancel");
        cl_button cb = new cl_button();
        cb.attach(cbtn);
        box.add(new JLabel("Enter MIDI URL:"));
        box.add(tf);
        box.add(btn);
        box.add(cbtn);
        win.add(box);
        win.pack();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        this.win.setVisible(false);
        new player_instance(this.tf.getText(), "WEB location", true, true);
    }
}