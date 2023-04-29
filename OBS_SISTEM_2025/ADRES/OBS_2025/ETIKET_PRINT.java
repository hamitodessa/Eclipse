package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import OBS_C_2025.ADRES_ACCESS;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseElementGroup;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JPanel;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.compress.harmony.pack200.BandSet.BandAnalysisResults;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ETIKET_PRINT extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(oac._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);

	private static JasperViewer jviewer ;
	JPanel panel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ETIKET_PRINT frame = new ETIKET_PRINT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws JRException 
	 * @throws SQLException 
	 */
	public ETIKET_PRINT()  {
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("ETIKET PRINT");
		setBounds(100, 100, 800, 600);
		
			doldur();
		
	}
	private void doldur()
	{
		try {
			
			JasperDesign jasper = JRXmlLoader.load(this.getClass().getClassLoader().getResourceAsStream("RPT\\ADRES_RPT\\Etiket.jrxml"));
			System.out.println( jasper.getColumnWidth()+"=spacing=="+jasper.getColumnSpacing());
			
			JRDesignSection designSection = (JRDesignSection) jasper.getDetailSection();
			JRBand[] bands =  jasper.getDetailSection().getBands();
			JRDesignBand qweBand = (JRDesignBand) bands[0].clone();
			qweBand.setHeight(100);
			
			qweBand.getElementByKey("Adi").setForecolor(Color.RED);
			JRElement[] column = bands[0].getElements();
			
			for(int i=0;i< column.length;i++)
			{
			System.out.println(column[i].getKey() +"==="+column[i].getWidth() + "==" + column[i].getHeight());
			}
			
			designSection.removeBand(bands[0]);
			designSection.addBand(qweBand);
			//JasperReport jr = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("RPT\\ADRES_RPT\\Etiket.jrxml"));
			JasperReport jr = JasperCompileManager.compileReport(jasper);
			
			ResultSet rSet = a_Access.adr_etiket();
			JasperPrint jp = JasperFillManager.fillReport(jr,null, new JRResultSetDataSource(rSet));
			getContentPane().add(new JRViewer(jp), BorderLayout.CENTER);
			
		} catch (SQLException | JRException  | ClassNotFoundException ex) {
		
		}
	}

}
