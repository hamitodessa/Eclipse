package OBS_2025;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import OBS_C_2025.GLOBAL;


public class GRAFIK extends JInternalFrame {
	public static  JFreeChart chart2D;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GRAFIK frame = new GRAFIK();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	public GRAFIK() throws PropertyVetoException {
		
		setTitle("GRAFIK");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1000, 600);
		
		
		setMaximum(true);
		  var ex = new LineChartEx();
          ex.setVisible(true);
          
		initUI();
		 
	}
	 private void initUI() {
		  DefaultCategoryDataset dataset = createDataset();  
		    // Create chart  
		    JFreeChart chart = ChartFactory.createLineChart(  
		        "Site Traffic", // Chart title  
		        "Date", // X-Axis Label  
		        "Number of Visitor", // Y-Axis Label  
		        dataset ,PlotOrientation.VERTICAL,
                true,
                true,
                false 
		        );  
		  
		    ChartPanel panel = new ChartPanel(chart);  
		    setContentPane(panel);  
	        
	    }

	 private DefaultCategoryDataset createDataset() {  
		  
		    String series1 = "Visitor";  
		    String series2 = "Unique Visitor";  
		  
		    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		  
		    dataset.addValue(200, series1, "2016-12-19");  
		    dataset.addValue(150, series1, "2016-12-20");  
		    dataset.addValue(100, series1, "2016-12-21");  
		    dataset.addValue(210, series1, "2016-12-22");  
		    dataset.addValue(240, series1, "2016-12-23");  
		    dataset.addValue(195, series1, "2016-12-24");  
		    dataset.addValue(245, series1, "2016-12-25");  
		  
		    dataset.addValue(150, series2, "2016-12-19");  
		    dataset.addValue(130, series2, "2016-12-20");  
		    dataset.addValue(95, series2, "2016-12-21");  
		    dataset.addValue(195, series2, "2016-12-22");  
		    dataset.addValue(200, series2, "2016-12-23");  
		    dataset.addValue(180, series2, "2016-12-24");  
		    dataset.addValue(230, series2, "2016-12-25");  
		  
		    return dataset;  
		  }  
	
		 
 		
}


 
