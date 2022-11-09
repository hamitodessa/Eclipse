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
		  DefaultCategoryDataset dataset = GLOBAL.dataset;  
		    // Create chart  
		    JFreeChart chart = ChartFactory.createLineChart(  
		        GLOBAL.g_baslik, // Chart title  
		       GLOBAL.g_labelsAxisLabels[0] , // X-Axis Label  
		       GLOBAL.g_setNumbersAxisTitleText, // Y-Axis Label  
		        dataset ,PlotOrientation.VERTICAL,
                true,
                true,
                false 
		        );  
	
		    ChartPanel panel = new ChartPanel(chart);  
		    setContentPane(panel);  
	        
	    }

	 
	
		 
 		
}


 
