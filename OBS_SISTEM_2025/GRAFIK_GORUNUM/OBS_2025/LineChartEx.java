package OBS_2025;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;


import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChartEx extends JInternalFrame{

    public LineChartEx() {

        initUI();
    }

    private void initUI() {
    	  XYDataset dataset = createDataset();
          JFreeChart chart = createChart(dataset);
          ChartPanel chartPanel = new ChartPanel(chart);
          chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
          chartPanel.setBackground(Color.white);
          
          add(chartPanel);

          pack();
          setTitle("Line chart");
       
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {

    	 var series1 = new XYSeries("2014");
         series1.add(18, 530);
         series1.add(20, 580);
         series1.add(25, 740);
         series1.add(30, 901);
         series1.add(40, 1300);
         series1.add(50, 2219);

         var series2 = new XYSeries("2016");
         series2.add(18, 567);
         series2.add(20, 612);
         series2.add(25, 800);
         series2.add(30, 980);
         series2.add(40, 1210);
         series2.add(50, 2350);

         var dataset = new XYSeriesCollection();
         dataset.addSeries(series1);
         dataset.addSeries(series2);

         return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

    	 JFreeChart chart = ChartFactory.createXYLineChart(
                 "Average salary per age",
                 "Age",
                 "Salary (�)",
                 dataset,
                 PlotOrientation.VERTICAL,
                 true,
                 true,
                 false
         );

         XYPlot plot = chart.getXYPlot();

         var renderer = new XYLineAndShapeRenderer();
         
              

         renderer.setSeriesPaint(0, Color.RED);
         renderer.setSeriesStroke(0, new BasicStroke(2.0f));
         renderer.setSeriesPaint(1, Color.BLUE);
         renderer.setSeriesStroke(1, new BasicStroke(2.0f));

         plot.setRenderer(renderer);
         plot.setBackgroundPaint(Color.white);
         plot.setRangeGridlinesVisible(false);
         plot.setDomainGridlinesVisible(false);

       

         chart.setTitle(new TextTitle("Average Salary per Age",
                         new Font("Serif", Font.BOLD, 18)
                 )
         );

         return chart;
    }
}
