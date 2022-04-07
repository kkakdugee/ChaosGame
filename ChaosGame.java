import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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
        dPanel.setPreferredSize(new Dimension(1000, 700));
        dPanel.add(drawingPanel);
        getContentPane().add(dPanel, BorderLayout.CENTER);
        setVisible(true);

        repaint();
    }

    public static void main(String[] args) {
        new ChaosGame();
    }

    private class DrawingPanel extends JPanel {

        int size = 1;
        Point[] initPoints = new Point[3];
        ArrayList<Point> points = new ArrayList<>();
        Point mostRecent;

        public DrawingPanel() {
            setOpaque(true);
            setBackground(Color.BLACK);
            setPreferredSize(new Dimension(1000, 700));
            setPoints();
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    System.out.println(e.getX() + " " + e.getY());
                }
            });
        }

        private void setPoints() {
            initPoints[0] = new Point(100, 600); // bottom left corner
            initPoints[1] = new Point(500, 50); // middle upmost
            initPoints[2] = new Point(900, 600); // bottom right corner
            for (int i = 0; i < initPoints.length; i++) {
                points.add(initPoints[i]);
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);

            for (int i = 0; i < points.size(); i++) {
                g.drawOval(points.get(i).x, points.get(i).y, size, size);
            }
            if (points.size() == 5000) {
                System.out.println("done");
                return;
            }
            if (points.size() != 3) {
                mostRecent = points.get(points.size() - 1);
                int randInitPoint = (int) (Math.random() * 3);
                Point midPoint = getMidPoint(initPoints[randInitPoint], mostRecent);
                points.add(midPoint);
                g.drawOval(midPoint.x, midPoint.y, size, size);
                repaint();
            } else {
                Point firstRand = getMidPoint(initPoints[1], initPoints[2]);
                points.add(firstRand);
                g.drawOval(firstRand.x, firstRand.y, size, size);
                repaint();
            }

        }

        public Point getMidPoint(Point one, Point two) {
            int x = (one.x + two.x) / 2;
            int y = (one.y + two.y) / 2;
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
