package OBS_2025;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import OBS_C_2025.GLOBAL;


public class GRAFIK extends JInternalFrame {
	public static  JFreeChart chart;
	
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
		    chart = ChartFactory.createLineChart(  
		        GLOBAL.g_baslik, // Chart title  
		        GLOBAL.g_legends , // X-Axis Label  
		       GLOBAL.g_setNumbersAxisTitleText, // Y-Axis Label  
		        dataset ,PlotOrientation.VERTICAL,
                true,
                true,
                false 
		        );  
		    CategoryPlot plot = (CategoryPlot) chart.getPlot();
		    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		    rangeAxis.setRange(GLOBAL.max_value, GLOBAL.min_value);
		    rangeAxis.setUpperMargin(0.50);
		    
		    
		    
		    //
		 	  //
		    NumberFormat formatter = DecimalFormat.getInstance();
		    formatter.setMinimumFractionDigits(3);
		    rangeAxis.setNumberFormatOverride(formatter);
		    //
		     
		    
		    //
		    Font font3 = new Font("Arial", Font.BOLD, 25); 
		    
		    plot.getDomainAxis().setLabelFont(font3);
		    plot.getRangeAxis().setLabelFont(font3);
		    
		    //
		  
		    TextTitle t2 = new TextTitle(  GLOBAL.g_baslik); // BASLIK
		    t2.setFont(new Font("Serif", Font.BOLD, 9));
		    
		    chart.setTitle(t2);
		    chart.getTitle().setPaint(Color.BLUE);
			  
		    //
		   // var renderer = new XYLineAndShapeRenderer();
	       // renderer.setSeriesPaint(0, Color.RED);
	       // renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		    //
		    ChartPanel panel = new ChartPanel(chart);  
		    setContentPane(panel);  
	        
	    }

	
	 public static  void kaydet()
	  {
		  try {
		  UIManager.put("FileChooser.cancelButtonText", "Vazgec");
		  UIManager.put("FileChooser.saveButtonText", "Kaydet");
		  JFileChooser fileChooser = new JFileChooser();
		  fileChooser.resetChoosableFileFilters();
		  fileChooser.setAcceptAllFileFilterUsed(false);
		  fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Resim Dosyalari", "jpg"));
		  fileChooser.setCurrentDirectory(new java.io.File("."));
		  fileChooser.setApproveButtonText("Kaydet");
		  fileChooser.setDialogTitle("Grafik Kayit");   
		  File outputfile = new File(GLOBAL.g_baslik);
		  fileChooser.setSelectedFile(outputfile);
		  
		  
		  BufferedImage lbImage = chart.createBufferedImage( 600, 400, null); 
		    
		  int returnVal = fileChooser.showSaveDialog(null);
		  if ( returnVal == JFileChooser.APPROVE_OPTION )
		  {
		      File file = new File(fileChooser.getSelectedFile() + ".jpg");
		      ImageIO.write( lbImage , "jpg", file);
		  }
		  
		  /////
//		   ByteArrayDataSource ds = null ;
//		   BufferedImage objBufferedImage= chart.createBufferedImage(600,800);
//		   ByteArrayOutputStream bas = new ByteArrayOutputStream();
//		   ImageIO.write(objBufferedImage, "png", bas);
//		   byte[] byteArray= bas.toByteArray();
//		   InputStream in = new ByteArrayInputStream(byteArray);
//		   ds = new ByteArrayDataSource(in, "application/x-any");
		  ////
		  }
		  catch (Exception ex)
		  {
			     JOptionPane.showMessageDialog(null, ex.getMessage(), "Grafik", JOptionPane.PLAIN_MESSAGE);
		  }
	  }
	
		 
 		
}


 
