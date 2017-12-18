package nasp.lab.graphs;

import nasp.lab.modules.Route;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class RoutesGraph extends JPanel {

    private int padding = 50;
    private int labelPadding = 75;
    private Color originalLineColor = new Color(100, 100, 100, 180);
    private Color crossModifiedLineColor = new Color(255, 0, 0, 180);
    private Color selectModifiedLineColor = new Color(0, 0, 255, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 15;
    private List<Route> originalRoute;
    private List<Route> crossModifiedRoute;
    private List<Route> selectModifiedRoute;

    public RoutesGraph(List<Route> originalRoute, List<Route> crossModifiedRoute, List<Route> selectModifiedRoute) {
        this.originalRoute = originalRoute;
        this.crossModifiedRoute = crossModifiedRoute;
        this.selectModifiedRoute = selectModifiedRoute;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (originalRoute.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore());

        List<Point> originalGraphPoints = new ArrayList<>();
        for (int i = 0; i < originalRoute.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - originalRoute.get(i).getDistance()) * yScale + padding);
            originalGraphPoints.add(new Point(x1, y1));
        }

        List<Point> crossModifiedGraphPoints = new ArrayList<>();
        for (int i = 0; i < crossModifiedRoute.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - crossModifiedRoute.get(i).getDistance()) * yScale + padding);
            crossModifiedGraphPoints.add(new Point(x1, y1));
        }

        List<Point> selectModifiedGraphPoints = new ArrayList<>();
        for (int i = 0; i < selectModifiedRoute.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - selectModifiedRoute.get(i).getDistance()) * yScale + padding);
            selectModifiedGraphPoints.add(new Point(x1, y1));
        }

        // graphs white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (originalRoute.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) (((getMaxScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < originalRoute.size(); i++) {
            if (originalRoute.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (originalRoute.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((originalRoute.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        g2.setColor(originalLineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < originalGraphPoints.size() - 1; i++) {
            g2.setColor(originalLineColor);
            int x1 = originalGraphPoints.get(i).x;
            int y1 = originalGraphPoints.get(i).y;
            int x2 = originalGraphPoints.get(i + 1).x;
            int y2 = originalGraphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);

            g2.setColor(selectModifiedLineColor);
            x1 = selectModifiedGraphPoints.get(i).x;
            y1 = selectModifiedGraphPoints.get(i).y;
            x2 = selectModifiedGraphPoints.get(i + 1).x;
            y2 = selectModifiedGraphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);

            g2.setColor(crossModifiedLineColor);
            x1 = crossModifiedGraphPoints.get(i).x;
            y1 = crossModifiedGraphPoints.get(i).y;
            x2 = crossModifiedGraphPoints.get(i + 1).x;
            y2 = crossModifiedGraphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        //graphs description
        String originalGraphDescription = "ORIGINAL GRAPH";
        String crossModifiedGraphDescription = "CROSS MODIFIED";
        String selectModifiedGraphDescription = "SELECT MODIFIED";
        g2.setColor(originalLineColor);
        g2.drawString(originalGraphDescription, padding, getHeight() - (2 * padding) + 20);
        g2.setColor(crossModifiedLineColor);
        g2.drawString(crossModifiedGraphDescription, padding, getHeight() -  (2 * padding) + 50);
        g2.setColor(selectModifiedLineColor);
        g2.drawString(selectModifiedGraphDescription, padding, getHeight() -  (2 * padding) + 80);
    }

    private double getMinScore() {

        double minScore = Double.MAX_VALUE;
        for (Route tempRoute : originalRoute) {
            minScore = Math.min(minScore, tempRoute.getDistance());
        }

        return minScore;
    }

    private double getMaxScore() {

        double maxScore = Double.MIN_VALUE;
        for (Route tempRoute : originalRoute) {
            maxScore = Math.max(maxScore, tempRoute.getDistance());
        }

        return maxScore;
    }
}