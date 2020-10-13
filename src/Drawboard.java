import javax.swing.JPanel;

import javax.swing.JFrame;
import java.awt.*;

public class Drawboard extends JPanel {
    static final long serialVersionUID = 1357997531;
    private static Drawboard db;

    public static Drawboard getInstance() {
        if (db == null) {
            db = new Drawboard();
        }
        return db;
    }

    private Drawboard() {
        this.setBackground(Color.WHITE);
        this.drawUI();
        this.bindEvent();
    }

    private void drawUI() {
        JFrame window = new JFrame("画板");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(new Toolbar(), BorderLayout.NORTH);
        window.add(this, BorderLayout.CENTER);
        window.setVisible(true);
    }

    private void bindEvent() {
        EventListener el = EventListener.GetInstance();
        this.addMouseListener(el);
        this.addMouseMotionListener(el);
        this.addKeyListener(el);
        el.setPen(this.getGraphics());
    }

    public void paint(Graphics p) {
        super.paint(p);
        EventListener el = EventListener.GetInstance();
        for (Shape item : el.getHistory()) {
            item.draw(p);
        }
    }

}
