package OBS_PACKAGE;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sourceforge.chart2d.Chart2D;
import net.sourceforge.chart2d.Chart2DProperties;
import net.sourceforge.chart2d.GraphChart2DProperties;
import net.sourceforge.chart2d.GraphProperties;
import net.sourceforge.chart2d.LBChart2D;
import net.sourceforge.chart2d.LegendProperties;
import net.sourceforge.chart2d.MultiColorsProperties;
import net.sourceforge.chart2d.Object2DProperties;

public class GRAFIK extends JInternalFrame {
	public static  Chart2D chart2D;
	
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
		 chart2D = null;
		 chart2D =    (Chart2D)getChart2DDemoF();
		getContentPane().add(chart2D, BorderLayout.CENTER);
		setMaximum(true);
		chart2D.setVisible(true);
		Dimension maxSize = new Dimension (1000, 600);
		chart2D.setSize (maxSize);
		chart2D.setPreferredSize (maxSize);
		 
	}
		  private static Chart2D getChart2DDemoF() 
		  {
			  try {
		    Object2DProperties object2DProps = new Object2DProperties();
		    object2DProps.setObjectTitleText (GLOBAL.g_baslik);
		    Chart2DProperties chart2DProps = new Chart2DProperties();
		    chart2DProps.setChartDataLabelsPrecision (4);
		    LegendProperties legendProps = new LegendProperties();
		    legendProps.setLegendLabelsTexts (GLOBAL.g_legends);
		    GraphChart2DProperties graphChart2DProps = new GraphChart2DProperties();
		    graphChart2DProps.setLabelsAxisLabelsTexts (GLOBAL.g_labelsAxisLabels);
		    graphChart2DProps.setLabelsAxisTitleText (GLOBAL.g_LabelsAxisTitleText);
		    graphChart2DProps.setNumbersAxisTitleText (GLOBAL.g_setNumbersAxisTitleText);
		 
		   // graphChart2DProps.setChartDatasetCustomizeLeastValue (true);
		    //graphChart2DProps.setChartDatasetCustomLeastValue ((float) GLOBAL.min_value);
		    graphChart2DProps.setLabelsAxisTicksAlignment (graphChart2DProps.CENTERED);
		    graphChart2DProps.setNumbersAxisNumLabels(5);
		    GraphProperties graphProps = new GraphProperties();
		    graphProps.setGraphBarsExistence (false);
		    graphProps.setGraphLinesExistence (true);
		    //graphProps.setGraphLinesThicknessModel (4);
		    graphProps.setGraphDotsExistence (true);
		    graphProps.setGraphOutlineComponentsExistence (true);
		    graphProps.setGraphAllowComponentAlignment (true);
		    
		    MultiColorsProperties multiColorsProps = new MultiColorsProperties();
	
		    LBChart2D chart2D = new LBChart2D();
		    chart2D.setObject2DProperties (object2DProps);
		    chart2D.setChart2DProperties (chart2DProps);
		    chart2D.setLegendProperties (legendProps);
		    chart2D.setGraphChart2DProperties (graphChart2DProps);
		    chart2D.addGraphProperties (graphProps);
		    JOptionPane.showMessageDialog(null, "1 ", "Grafik", JOptionPane.PLAIN_MESSAGE);
		    chart2D.addDataset (GLOBAL.g_dataSet);
		    chart2D.addMultiColorsProperties (multiColorsProps);

		    if (!chart2D.validate (false)) chart2D.validate (true);
			  }
			  catch (Exception ex)
			  {
				     JOptionPane.showMessageDialog(null, "Grafik ", "Grafik", JOptionPane.PLAIN_MESSAGE);
			  }
			return chart2D;
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
			  BufferedImage lbImage = chart2D.getImage();     
			  int returnVal = fileChooser.showSaveDialog(null);
			  if ( returnVal == JFileChooser.APPROVE_OPTION )
			  {
			      File file = new File(fileChooser.getSelectedFile() + ".jpg");
			      ImageIO.write( lbImage , "jpg", file);
			  }
			  
			  }
			  catch (Exception ex)
			  {
				     JOptionPane.showMessageDialog(null, "Grafik ", "Grafik", JOptionPane.PLAIN_MESSAGE);
			  }
		  }
 		
}

 
