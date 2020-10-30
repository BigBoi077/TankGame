package cegepst.engine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RenderingEngine {

    private static RenderingEngine instance;
    private JFrame frame;
    private JPanel panel;
    private BufferedImage bufferedImage;

    public void start() {
        frame.setVisible(true);
    }

    public void addInputListener(KeyListener listener) {
        panel.addKeyListener(listener);
    }

    public static RenderingEngine getInstance() {
        if (instance == null) {
            instance = new RenderingEngine();
        }
        return instance;
    }


    public void stop() {
        frame.setVisible(false);
        frame.dispose();
    }

    public Buffer getRenderingBuffer() {
        bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHints(getOptimalRenderingHints());
        return new Buffer(graphics);
    }

    public void renderBufferOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }

    private RenderingEngine() {
        initialiseFrame();
        initialisePanel();
    }

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints  rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }

    private void initialisePanel() {
        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        frame.add(panel);
    }

    private void initialiseFrame() {
        frame = new JFrame();
        frame.setSize(800, 600);
        // Pour centrer
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Moving Rectangle Game");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
