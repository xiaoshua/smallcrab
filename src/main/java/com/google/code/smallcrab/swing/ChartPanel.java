package com.google.code.smallcrab.swing;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class ChartPanel extends JPanel {

	private static final long serialVersionUID = -5545955161976746807L;

	public ChartPanel() {
		super(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 400));
		setBackground(Color.white);
	}

	private double xMaxValue;

	public void setxMaxValue(double xMaxValue) {
		this.xMaxValue = xMaxValue;
	}

	private double yMaxValue;

	public void setyMaxValue(double yMaxValue) {
		this.yMaxValue = yMaxValue;
	}

	private double xMinValue = 0;

	private double yMinValue = 0;

	public void setxMinValue(double xMinValue) {
		this.xMinValue = xMinValue;
	}

	public void setyMinValue(double yMinValue) {
		this.yMinValue = yMinValue;
	}

	private int xMinCount = 0;

	private int xMaxCount = 0;

	public void setxMinCount(int xMinCount) {
		this.xMinCount = xMinCount;
	}

	public void setxMaxCount(int xMaxCount) {
		this.xMaxCount = xMaxCount;
	}

	private Insets borderInsets = new Insets(20, 40, 40, 20);

	public void paintPoint(int x, int y) {

	}

	private List<List<Double>> result;

	private Map<Double, Integer> xCount;

	public void setResult(List<List<Double>> result2) {
		this.result = result2;
	}

	public void setxCount(Map<Double, Integer> xCount) {
		this.xCount = xCount;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (result != null) {
			drawAxis(g);
			drawxCount(g);
			drawData(g);
		}
	}

	private void drawAxis(Graphics g) {
		int xEnd = this.getWidth() - borderInsets.right;
		int yEnd = this.getHeight() - borderInsets.bottom;
		// draw axis
		g.setColor(Color.black);
		g.drawLine(borderInsets.left, borderInsets.top, borderInsets.left, yEnd);
		g.drawLine(borderInsets.left, borderInsets.top, borderInsets.left - 3, borderInsets.top + 3);
		g.drawLine(borderInsets.left, borderInsets.top, borderInsets.left + 3, borderInsets.top + 3);
		g.drawLine(borderInsets.left, yEnd, xEnd, yEnd);
		g.drawLine(xEnd, yEnd, xEnd - 3, yEnd + 3);
		g.drawLine(xEnd, yEnd, xEnd - 3, yEnd - 3);
		// draw mark
		g.drawLine(borderInsets.left, borderInsets.top, borderInsets.left - 5, borderInsets.top);
		g.drawLine(xEnd, yEnd, xEnd, yEnd + 5);
		// draw zero point
		// g.setColor(Color.blue);
		// g.drawRect(borderInsets.left, yEnd, 1, 1);
		// draw axis label
		g.setColor(Color.black);
		String xLabel = "x-axis";
		String yLabel = "y-axis";
		g.setFont(this.getFont());
		FontMetrics metrics = g.getFontMetrics();
		int width = metrics.stringWidth(xLabel);
		g.drawString(xLabel, borderInsets.left + (this.getWidth() - borderInsets.left - borderInsets.right - width) / 2, yEnd + (borderInsets.bottom + metrics.getHeight()) / 2);
		AffineTransform transform = new AffineTransform();
		width = metrics.stringWidth(yLabel);
		transform.setToTranslation((borderInsets.left - metrics.getHeight()) / 2, borderInsets.top + (this.getHeight() - borderInsets.top - borderInsets.bottom - width) / 2);
		transform.rotate(Math.PI / 2);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setTransform(transform);
		g.drawString(yLabel, 0, 0);

	}

	private void drawData(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = new AffineTransform();
		transform.setToTranslation(borderInsets.left, this.getHeight() - borderInsets.bottom);
		g2d.setTransform(transform);
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f);
		g2d.setColor(Color.red);
		g2d.setComposite(ac);
		int xAxisLength = this.getWidth() - borderInsets.right - borderInsets.left;
		int yAxisLength = this.getHeight() - borderInsets.bottom - borderInsets.top;
		for (List<Double> points : result) {
			double x = points.get(0);
			int pointX = (int) ((x - xMinValue) / (xMaxValue - xMinValue) * xAxisLength);
			for (int i = 1; i < points.size(); i++) {
				double y = points.get(i);
				int pointY = -(int) ((y - yMinValue) / (yMaxValue - yMinValue) * yAxisLength);
				int[] pointXs = new int[] { pointX, pointX + 1, pointX, pointX - 1 };
				int[] pointYs = new int[] { pointY + 1, pointY, pointY - 1, pointY };
				g2d.drawPolygon(pointXs, pointYs, 4);
			}
		}
	}

	private void drawxCount(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform transform = new AffineTransform();
		transform.setToTranslation(borderInsets.left, this.getHeight() - borderInsets.bottom);
		g2d.setTransform(transform);
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		g2d.setComposite(ac);
		Object[] countArray = xCount.entrySet().toArray();
		Arrays.sort(countArray, new Comparator<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(Object o1, Object o2) {
				return (int)(((Entry<Double, Integer>) o1).getKey() - ((Entry<Double, Integer>) o2).getKey());
			}
		});
		GeneralPath line = new GeneralPath();
		int xAxisLength = this.getWidth() - borderInsets.right - borderInsets.left;
		int yAxisLength = this.getHeight() - borderInsets.bottom - borderInsets.top;
		boolean isLineStart = true;
		for (Object entry : countArray) {
			@SuppressWarnings("unchecked")
			double x = ((Entry<Double, Integer>) entry).getKey();
			int pointX = (int) (1.0 * (x - xMinValue) / (xMaxValue - xMinValue) * xAxisLength);
			@SuppressWarnings("unchecked")
			int y = ((Entry<Double, Integer>) entry).getValue();
			int pointY = -(int) (1.0 * (y - xMinCount) / (xMaxCount - xMinCount) * yAxisLength);
			int[] pointXs = new int[] { pointX, pointX + 1, pointX, pointX - 1 };
			int[] pointYs = new int[] { pointY + 1, pointY, pointY - 1, pointY };
			g2d.setColor(Color.blue);
			g2d.drawPolygon(pointXs, pointYs, 4);
			g2d.fillPolygon(pointXs, pointYs, 4);
			if (isLineStart) {
				line.moveTo(pointX, pointY);
				isLineStart = false;
			} else {
				line.lineTo(pointX, pointY);
			}
		}
		ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
		g2d.setComposite(ac);
		g2d.draw(line);

	}

}