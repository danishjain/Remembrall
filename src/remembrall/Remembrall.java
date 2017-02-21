package remembrall;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

class Pic extends JFrame {

    JTextArea tf = null;
    JButton b = null;
    String text = "";

    public Pic() throws Exception {

        tf = new JTextArea(5, 40);
        b = new JButton("Post");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                text = tf.getText();
                Random rand = new Random(System.currentTimeMillis());
                int value = rand.nextInt(4) + 1;

                String path = "C:\\Users\\**\\Downloads\\remeberall\\"; // your location for the backgrounds
                String path2 = path + "back" + value + ".jpg";	//my backgrounds werenamed back1, back2 and so on.

                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File(path2));
                } catch (IOException e) {
                    System.out.println(e);
                }

                int width = image.getWidth();
                int height = image.getHeight();

                Color whi = new Color(255, 255, 255);
                Font f = new Font("Roboto", Font.BOLD, 45);

                Graphics g = image.getGraphics();

                FontMetrics metrics = g.getFontMetrics(f);
                int x = (width - metrics.stringWidth(text)) / 2;
                int y = (height - metrics.getHeight()) / 2 + metrics.getAscent();

                g.setColor(whi);
                g.setFont(f);
                g.drawString(text, x, y);
                System.out.println(text);
                g.dispose();

                String timeStamp = new SimpleDateFormat("HH.mm.ss.dd.MM.yyyy").format(new Date());
                // to providea unique name to the files generated.
                try {
                    ImageIO.write(image, "png", new File(path + "remembrall\\" + timeStamp + ".png"));
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });

        setLayout(new FlowLayout());
        setSize(500, 200);

        add(tf);
        add(b);
    }
}

public class Remembrall {

    public static void main(String[] args) throws Exception {

        Pic p = new Pic();
        p.setVisible(true);

    }
}
