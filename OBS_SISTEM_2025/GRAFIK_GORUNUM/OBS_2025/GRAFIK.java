package OBS_2025;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


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
		
		grfk();
		 
	}
         public void grfk ()
         {
        	 
        	 try {
                 
                 /* Step - 1: Define the data for the line chart  */
                 DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
                 line_chart_dataset.addValue(15, "schools", "1970");
                 line_chart_dataset.addValue(30, "schools", "1980");
                 line_chart_dataset.addValue(60, "schools", "1990");
                 line_chart_dataset.addValue(120, "schools", "2000");
                 line_chart_dataset.addValue(240, "schools", "2010");                
                 
                 /* Step -2:Define the JFreeChart object to create line chart */
                 JFreeChart lineChartObject=ChartFactory.createLineChart("Schools Vs Years","Year","Schools Count",line_chart_dataset,PlotOrientation.VERTICAL,true,true,false);                
                           
                 /* Step -3 : Write line chart to a file */               
                  int width=640; /* Width of the image */
                  int height=480; /* Height of the image */                
                  File lineChart=new File("line_Chart_example.png");              
                  ChartUtilities.saveChartAsPNG(lineChart,lineChartObject,width,height); 
          }
          catch (Exception i)
          {
              System.out.println(i);
          }
         }
	
	
		 
 		
}


 
