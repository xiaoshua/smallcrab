/**
 * 
 */
package com.google.code.smallcrab.swing.csv;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.google.code.smallcrab.config.chart.ChartConfig;

/**
 * @author seanlinwang at gmail dot com
 * @date 2011-7-18
 */
public class CsvChartConfigPanel extends JPanel {
	private static final long serialVersionUID = 6854447303405175550L;

	private JCheckBox drawFrequencyButton;

	private JCheckBox drawFrequencyPointButton;

	private JCheckBox drawFrequencyHistogramButton;

	private JCheckBox drawFrequencyLineButton;

	private JCheckBox drawFrequencyAverageButton;

	private JCheckBox drawFrequencyAverageHistogramButton;

	private JCheckBox drawFrequencyAverageLineButton;

	private JCheckBox drawYButton;

	private JCheckBox drawYPointButton;

	private JCheckBox drawYAverageButton;

	private JCheckBox drawYAverageHistogramButton;

	private JCheckBox drawYAverageLineButton;

	public ChartConfig createChartConfig() {
		ChartConfig cc = new ChartConfig();
		cc.setDrawFrequency(this.drawFrequencyButton == null ? false : this.drawFrequencyButton.isSelected());
		cc.setDrawFrequencyPoint(this.drawFrequencyPointButton == null ? false : this.drawFrequencyPointButton.isSelected());
		cc.setDrawFrequencyHistogram(this.drawFrequencyHistogramButton == null ? false : this.drawFrequencyHistogramButton.isSelected());
		cc.setDrawFrequencyLine(this.drawFrequencyLineButton == null ? false : this.drawFrequencyLineButton.isSelected());
		cc.setDrawFrequencyAverage(this.drawFrequencyAverageButton == null ? false : this.drawFrequencyAverageButton.isSelected());
		cc.setDrawFrequencyAverageHistogram(this.drawFrequencyAverageHistogramButton == null ? false : this.drawFrequencyAverageHistogramButton.isSelected());
		cc.setDrawFrequencyAverageLine(this.drawFrequencyAverageLineButton == null ? false : this.drawFrequencyAverageLineButton.isSelected());
		cc.setDrawY(this.drawYButton == null ? false : this.drawYButton.isSelected());
		cc.setDrawYPoint(this.drawYPointButton == null ? false : this.drawYPointButton.isSelected());
		cc.setDrawYAverage(this.drawYAverageButton == null ? false : this.drawYAverageButton.isSelected());
		cc.setDrawYAverageHistogram(this.drawYAverageHistogramButton == null ? false : this.drawYAverageHistogramButton.isSelected());
		cc.setDrawYAverageLine(this.drawYAverageLineButton == null ? false : this.drawYAverageLineButton.isSelected());
		return cc;
	}

	public void setChartConfigListener(ItemListener chartConfigListener) {
		this.drawFrequencyButton.addItemListener(chartConfigListener);
		this.drawFrequencyPointButton.addItemListener(chartConfigListener);
		this.drawFrequencyHistogramButton.addItemListener(chartConfigListener);
		this.drawFrequencyLineButton.addItemListener(chartConfigListener);
		this.drawFrequencyAverageButton.addItemListener(chartConfigListener);
		this.drawFrequencyAverageHistogramButton.addItemListener(chartConfigListener);
		this.drawFrequencyAverageLineButton.addItemListener(chartConfigListener);
		this.drawYButton.addItemListener(chartConfigListener);
		this.drawYPointButton.addItemListener(chartConfigListener);
		this.drawYAverageButton.addItemListener(chartConfigListener);
		this.drawYAverageLineButton.addItemListener(chartConfigListener);
		this.drawYAverageHistogramButton.addItemListener(chartConfigListener);
	}

	public CsvChartConfigPanel() {
		// ========= draw frequency
		final JCheckBox frequencyButton = new JCheckBox("draw frequency", true);
		this.drawFrequencyButton = frequencyButton;

		// point chart checkbox for frequency
		final JCheckBox frequencyPointButton = new JCheckBox("point", true);
		this.drawFrequencyPointButton = frequencyPointButton;

		// histogram chart checkbox for frequency
		final JCheckBox frequencyHistogramButton = new JCheckBox("histogram", false);
		this.drawFrequencyHistogramButton = frequencyHistogramButton;

		// line chart checkbox for frequency
		final JCheckBox frequencyLineButton = new JCheckBox("line", false);
		this.drawFrequencyLineButton = frequencyLineButton;

		// only choose one from point, histogram and line charts
		ButtonGroup frequencyButtonGroup = new ButtonGroup();
		frequencyButtonGroup.add(frequencyPointButton);
		frequencyButtonGroup.add(frequencyHistogramButton);
		frequencyButtonGroup.add(frequencyLineButton);

		// ========== draw frequency average
		final JCheckBox frequencyAverageButton = new JCheckBox("draw frequency average", false);
		this.drawFrequencyAverageButton = frequencyAverageButton;

		final JCheckBox frequencyAverageHistogramButton = new JCheckBox("histogram", false);
		this.drawFrequencyAverageHistogramButton = frequencyAverageHistogramButton;

		final JCheckBox frequencyAverageLineButton = new JCheckBox("Line", true);
		this.drawFrequencyAverageLineButton = frequencyAverageLineButton;

		ButtonGroup frequencyAverageButtonGroup = new ButtonGroup();
		frequencyAverageButtonGroup.add(frequencyAverageHistogramButton);
		frequencyAverageButtonGroup.add(frequencyAverageLineButton);

		// ========== draw y
		final JCheckBox yButton = new JCheckBox("draw y", true);
		this.drawYButton = yButton;

		final JCheckBox yPointButton = new JCheckBox("point", true);
		this.drawYPointButton = yPointButton;
		
		ButtonGroup yButtonGroup = new ButtonGroup();
		yButtonGroup.add(yPointButton);

		// ========== draw y average
		final JCheckBox yAverageButton = new JCheckBox("draw y average", false);
		this.drawYAverageButton = yAverageButton;

		final JCheckBox yAverageHistogramButton = new JCheckBox("histogram", false);
		this.drawYAverageHistogramButton = yAverageHistogramButton;

		final JCheckBox yAverageLineButton = new JCheckBox("line", true);
		this.drawYAverageLineButton = yAverageLineButton;

		ButtonGroup yAverageButtonGroup = new ButtonGroup();
		yAverageButtonGroup.add(yAverageHistogramButton);
		yAverageButtonGroup.add(yAverageLineButton);

		// ========== init panel
		JPanel checkPanel = new JPanel(new GridLayout(4, 1));
		Border border = BorderFactory.createTitledBorder("chart settings");
		checkPanel.setPreferredSize(new Dimension(460, 200));
		checkPanel.setBorder(border);

		// ========== init frequency
		JPanel frequencyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		frequencyPanel.add(frequencyButton);
		frequencyPanel.add(frequencyPointButton);
		frequencyPanel.add(frequencyHistogramButton);
		frequencyPanel.add(frequencyLineButton);
		checkPanel.add(frequencyPanel);

		// ========== init frequency average
		JPanel frequencyAveragePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		frequencyAveragePanel.add(frequencyAverageButton);
		frequencyAveragePanel.add(frequencyAverageHistogramButton);
		frequencyAveragePanel.add(frequencyAverageLineButton);
		checkPanel.add(frequencyAveragePanel);

		// ========== init y
		JPanel yPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		yPanel.add(yButton);
		yPanel.add(yPointButton);
		checkPanel.add(yPanel);

		// ========== init y average
		JPanel yAveragePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		yAveragePanel.add(yAverageButton);
		yAveragePanel.add(yAverageHistogramButton);
		yAveragePanel.add(yAverageLineButton);
		checkPanel.add(yAveragePanel);

		this.add(checkPanel);
	}
}
