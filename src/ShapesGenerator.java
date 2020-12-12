import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class ShapesGenerator {
    static Shape render() {
        Random r = new Random();
        int shapeNumber = r.nextInt(3);
        if (shapeNumber == 1) {
            return new Rectangle(r.nextInt(1000), r.nextInt(600), r.nextInt(70), r.nextInt(100));
        } else if (shapeNumber == 2) {
            int radius = r.nextInt(200);
            return new Ellipse2D.Double(r.nextInt(1000), r.nextInt(600), radius, radius);
        } else {
            int side = r.nextInt(70);
            return new Rectangle(r.nextInt(1000), r.nextInt(600), side, side);
        }
    }

    static class MyComponent extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Random rand = new Random();
            int c = rand.nextInt(4);
            Graphics2D g2d = (Graphics2D) g;

            for (int i = 0; i < 20; i++) {
                g2d.fill(render());
                float red = rand.nextFloat();
                float green = rand.nextFloat();
                float blue = rand.nextFloat();
                Color randomColor = new Color(red, green, blue);
                g2d.setColor(randomColor);
            }

        }

    }

    public static void main(String[] args) {
        JFrame jFrame = getFrame();
        jFrame.add(new MyComponent());

    }

    static JFrame getFrame() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("ShapesGenerator");
        jFrame.getContentPane().setBackground(Color.WHITE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        jFrame.setBounds(toolkit.getScreenSize().width / 4, toolkit.getScreenSize().height / 4, 1000, 600);


        return jFrame;
    }
}