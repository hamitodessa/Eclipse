package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.GLOBAL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({"serial","unused","static-access"})
public class ETIKET_PRINT extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(oac._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	List<ETIKET_ISIM> etISIM = new ArrayList<ETIKET_ISIM>();
	private static JasperViewer jviewer ;
	private static JasperPrint jp;
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			doldur();
		
	}
	private void doldur()
	{
		try {
			
			//JasperDesign jasper = JRXmlLoader.load(this.getClass().getClassLoader().getResourceAsStream("RPT\\ADRES_RPT\\Etiket2.jrxml"));
			File file = new File("C:\\OBS_SISTEM\\ETIKET.jrxml");
			JasperDesign jasper = JRXmlLoader.load(file);
			//System.out.println( jasper.getColumnWidth()+"=spacing=="+jasper.getColumnSpacing());
			//
			//jasper.setPageHeight(842);
			jasper.setColumnWidth(Integer.valueOf( GLOBAL.setting_oku("ETIKET_GEN")));
			jasper.setColumnSpacing(Integer.valueOf( GLOBAL.setting_oku("ETIKET_ARA_BOSLUK")));
			jasper.setLeftMargin( Integer.valueOf( GLOBAL.setting_oku("SOL_BOSLUK")));
			jasper.setRightMargin(Integer.valueOf( GLOBAL.setting_oku("SAG_BOSLUK")));
			jasper.setTopMargin(Integer.valueOf( GLOBAL.setting_oku("UST_BOSLUK")));
			jasper.setBottomMargin(Integer.valueOf( GLOBAL.setting_oku("ALT_BOSLUK")));

			JRDesignSection designSection = (JRDesignSection) jasper.getDetailSection();
			JRBand[] bands =  jasper.getDetailSection().getBands();
			JRDesignBand qweBand = (JRDesignBand) bands[0].clone();
			qweBand.setHeight(Integer.valueOf( GLOBAL.setting_oku("ETIKET_YUK")));
			//**************************ADI*************************************************
			 qweBand.getElementByKey("Adi").setForecolor(Color.BLUE);
			 qweBand.getElementByKey("Adi").getStyle().setFontName("Arial");
			 qweBand.getElementByKey("Adi").getStyle().setBold(true);
			//**************************ADRES1**********************************************
			 
			//**************************ADRES2**********************************************
			 
			//**************************SEMT************************************************
			 
			//**************************SEHIR***********************************************

			 
			 
			JRElement[] eleMENT = bands[0].getElements();
			for(int i=0;i< eleMENT.length;i++)
			{
			//System.out.println(eleMENT[i].getKey() +"==="+eleMENT[i].getWidth() + "==" + eleMENT[i].getHeight());
			}
			designSection.removeBand(bands[0]);
			designSection.addBand(qweBand);
			
			//JasperReport jr = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("RPT\\ADRES_RPT\\Etiket.jrxml"));
			JasperReport jr = JasperCompileManager.compileReport(jasper);
			ResultSet rSet = a_Access.adr_etiket("Adi");
			//
			satir_kontrol();	
			JRBeanCollectionDataSource qazBe = new JRBeanCollectionDataSource(etISIM);
			jp = JasperFillManager.fillReport(jr,null, qazBe);
		
			//
			//JasperPrint jp = JasperFillManager.fillReport(jr,null, new JRResultSetDataSource(rSet));
			getContentPane().add(new JRViewer(jp), BorderLayout.CENTER);
			
		} catch (SQLException | JRException  | NumberFormatException | ClassNotFoundException | IOException ex) 
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Etiket Yazdirma", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void satir_kontrol()
	{
		DefaultTableModel modell = (DefaultTableModel)ETIKET.table.getModel();
		for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
		{
			if ( modell.getValueAt(i,5) != null) 
			{
				if (  (boolean) modell.getValueAt(i,5) )
				{
					ETIKET_ISIM ets1  = new ETIKET_ISIM(modell.getValueAt(i, 0).toString(),
							modell.getValueAt(i, 1).toString(),
							modell.getValueAt(i, 2).toString(),
							modell.getValueAt(i, 3).toString(),
							modell.getValueAt(i, 4).toString());
					etISIM.add(ets1);
				}
			}
		}
	}
	public static ByteArrayDataSource export_to(String forMAT) throws IOException, JRException
	{
		 //JasperExportManager.exportReportToPdfFile(jp, "C:\\OBS_SISTEM\\invoice.pdf");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ByteArrayInputStream byteArrayInputStream = null;
		ByteArrayDataSource ds = null ;
		MimeBodyPart messagePart = null ;
		InputStream inputStream = null ;
		
		JasperExportManager.exportReportToPdfStream(jp,byteArrayOutputStream);
		byteArrayOutputStream.close();
		//
		//JRPdfExporter exporter = new JRPdfExporter();
		//exporter.setExporterInput(new SimpleExporterInput(jp));
		//exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		//exporter.exportReport();
		//
		
		byte byteArray[] = byteArrayOutputStream.toByteArray();
		InputStream is = new ByteArrayInputStream(byteArray);
		
		ds = new ByteArrayDataSource(is, "application/x-any");
		return ds;
		

		///
	}
	
}
