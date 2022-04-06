import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChaosGame extends JFrame {

    public ChaosGame() {
        setUpFrame();
        setUpCG();
    }

    private void setUpFrame() {
        setSize(1000, 700);
        setResizable(false);
        setTitle("ChaosGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())),
                getWidth(), getHeight());
    }

    private void setUpCG() {
        JPanel dPanel = new JPanel();
        DrawingPanel drawingPanel = new DrawingPanel();

        dPanel.setOpaque(true);
        dPanel.setPreferredSize(new Dimension(1000, 500));
        dPanel.add(drawingPanel);
        getContentPane().add(dPanel, BorderLayout.CENTER);
        setVisible(true);

        repaint();
    }

    public static void main(String[] args) {
        new ChaosGame();
    }

    private class DrawingPanel extends JPanel {

        boolean init = true;
        int size = 2;
        Point[] initPoints = new Point[3];
        Point mostRecent;

        public DrawingPanel() {
            setOpaque(true);
            setBackground(Color.BLACK);
            setPreferredSize(new Dimension(1000, 600));
            setPoints();
        }

        private void setPoints() {
            initPoints[0] = new Point(100, 550); // bottom left corner
            initPoints[1] = new Point(getWidth() / 2, 50); // middle upmost
            initPoints[2] = new Point(getWidth() - 100, 550); // bottom right corner
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            if (!init) {
                mostRecent = getMidPoint(init);
                g.drawOval(mostRecent.x, mostRecent.y, size, size);
                repaint();
            } else {
                for (int i = 0; i < initPoints.length; i++) {
                    g.drawOval(initPoints[i].x, initPoints[i].x, size, size);
                }
                Point midPoint = getMidPoint(init);
                g.drawOval(midPoint.x, midPoint.y, size, size);
                mostRecent = midPoint;
                init = false;
                repaint();
            }
        }

        public Point getMidPoint(boolean init) {
            int x, y = 0;
            if (!init) {
                int randomPoint = (int) (Math.random() * 3); 
                x = (initPoints[randomPoint].x + mostRecent.x) / 2;
                y = (initPoints[randomPoint].y + mostRecent.y) / 2;
            } else {
                x = (initPoints[0].x + initPoints[1].x) / 2;
                y = (initPoints[0].y + initPoints[1].y) / 2;
            }
            return new Point(x, y);
        }

    }

    private class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
