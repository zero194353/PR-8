import javax.swing.*;
import java.awt.*;

public class Animation extends JFrame {
    static int directionCounter = 0;

    Animation(String s) {
        super(s);
        DrawPanel panel = new DrawPanel();
        Dimension dim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

        add(panel);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Animation("Animation");
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
    }

    public class DrawPanel extends JComponent implements Runnable {

        private long t = System.nanoTime();

        public DrawPanel() {
            super();
            new Thread(this).start();

        }

        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

            long tm = System.nanoTime() - t;
            double angle = tm / 3000000.0;

            int w = (int) angle / d.width;
            int h = (int) angle / d.height;
            int x = (w % 2 == 0) ? ((w + 1) * d.width - (int) angle) : ((int) angle - w * d.width);
            int y = (h % 2 == 0) ? ((h + 1) * d.height - (int) angle) : ((int) angle - h * d.height);

            if (x == 0) {
                directionCounter = 0;
            } else if (y == 0) {
                directionCounter = 1;
            } else if (x == d.width) {
                directionCounter = 2;
            } else if (y == d.height) {
                directionCounter = 3;
            } else {
                if (directionCounter == 0) {
                    g2d.setColor(Color.BLUE);
                } else if (directionCounter == 1) {
                    g2d.setColor(Color.CYAN);
                } else if (directionCounter == 2) {
                    g2d.setColor(Color.GREEN);
                } else if (directionCounter == 3) {
                    g2d.setColor(Color.YELLOW);
                }
            }


            g2d.fillOval(x, y, 40, 40);
        }
    }
}