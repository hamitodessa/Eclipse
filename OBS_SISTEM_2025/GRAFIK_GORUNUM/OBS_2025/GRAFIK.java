package OBS_2025;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import org.jfree.chart.labels.StandardCategoryLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import OBS_C_2025.GLOBAL;


@SuppressWarnings("serial")
public class GRAFIK extends JInternalFrame {
	public static  JFreeChart chart;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
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
	@SuppressWarnings("static-access")
	private void initUI() 
	{
		DefaultCategoryDataset dataset = GLOBAL.dataset;  
		chart = ChartFactory.createLineChart(  
				GLOBAL.g_baslik, // Chart title  
				GLOBAL.g_legends , // X-Axis Label  
				GLOBAL.g_setNumbersAxisTitleText, // Y-Axis Label  
				dataset ,PlotOrientation.VERTICAL,
				true,	true,	false 	);  
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setRange(GLOBAL.min_value, GLOBAL.max_value);
		rangeAxis.setUpperMargin(0.50);
		rangeAxis.setLabelPaint(new Color(0, 0, 128));  // Sol Dikine Metin
		rangeAxis.setLabelFont(new Font("Arial Narrow", Font.BOLD, 25));
		rangeAxis.setTickLabelFont(new Font("Arial Narrow", Font.PLAIN, 10)); //Soldaki Dikine DEgerler
	    rangeAxis.setTickLabelPaint(new Color(72, 112, 132));  //Soldaki Dikine DEgerler  
		plot.setBackgroundPaint(new Color(192, 225, 240));//Grafik Alani
		
		//
		//
		NumberFormat formatter = DecimalFormat.getInstance();
		formatter.setMinimumFractionDigits(GLOBAL.gkusurat);
		rangeAxis.setNumberFormatOverride(formatter);
		//
		//
		Font font3 = new Font("Arial Narrow", Font.BOLD, 25); 
		plot.getDomainAxis().setLabelFont(font3);
		plot.getDomainAxis().setLabelPaint(new Color(0, 0, 128)); // Alt Bolum AYLAR
		plot.getDomainAxis().setTickLabelFont(new Font("Arial Narrow", Font.PLAIN, 10));// ALT Bolum Degerler
		plot.getDomainAxis().setTickLabelPaint(new Color(72, 112, 132));// ALT Bolum Degerler
		
		//plot.getRangeAxis().setLabelFont(font3);
		
		//
		TextTitle t2 = new TextTitle(  GLOBAL.g_baslik); // BASLIK
		t2.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		chart.setTitle(t2);
		chart.getTitle().setPaint(new Color(0, 0, 128));
		
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
	    renderer.setShapesVisible(true);
	    formatter.setMinimumFractionDigits(GLOBAL.gkusurat);
	    formatter.setMaximumFractionDigits(GLOBAL.gkusurat);
	    renderer.setLabelGenerator(new StandardCategoryLabelGenerator("{2}", formatter));
	    renderer.setItemLabelFont(new Font("Arial Narrow", Font.PLAIN, 8));
	    String deger = "";
	    try {
			deger = oac.glb.setting_oku("GRAFIK_DEGER_GOSTER").toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (! deger.equals("-1"))
		{
			 renderer.setItemLabelsVisible(true);
		}
	    renderer.setItemLabelPaint(new Color(0, 0, 128));
	    renderer.setSeriesVisible(true);
	    chart.setBackgroundPaint(new Color(189, 209, 219));//
	    
        
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
			BufferedImage lbImage = chart.createBufferedImage( 1000, 500, null); 
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



