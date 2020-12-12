import javax.swing.*;
import java.awt.*;

public class Picture {
    static class MyComponent2 extends JComponent {
        private String path;

        MyComponent2(String path) {
            this.path = path;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Image image = new ImageIcon(path).getImage();
            g2.drawImage(image, 0, 0, null);
        }

    }

    static JFrame getFrame() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Picture");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        jFrame.setBounds(0, 0, toolkit.getScreenSize().width, toolkit.getScreenSize().height);

        return jFrame;
    }

    public static void main(String[] args) {
        JFrame jFrame2 = getFrame();
        jFrame2.add(new MyComponent2(args[0]));

    }

}