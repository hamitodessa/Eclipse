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
import java.security.spec.DSAGenParameterSpec;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.HashMap;
import java.util.Map;

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
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({"serial","unused"})
public class ETIKET_PRINT extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	List<ETIKET_ISIM> etISIM = new ArrayList<ETIKET_ISIM>();
	private static JasperViewer jviewer ;
	private static JasperPrint jp;
	JPanel panel;
	
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
			//
			//jasper.setPageHeight(842);
			jasper.setColumnWidth(Integer.valueOf( GLOBAL.setting_oku("ETIKET_GEN")));
			jasper.setColumnSpacing(Integer.valueOf( GLOBAL.setting_oku("ETIKET_ARA_BOSLUK")));
			jasper.setLeftMargin( Integer.valueOf( GLOBAL.setting_oku("SOL_BOSLUK")));
			jasper.setRightMargin(Integer.valueOf( GLOBAL.setting_oku("SAG_BOSLUK")));
			jasper.setTopMargin(Integer.valueOf( GLOBAL.setting_oku("UST_BOSLUK")));
			jasper.setBottomMargin(Integer.valueOf( GLOBAL.setting_oku("ALT_BOSLUK")));
			
			if(GLOBAL.setting_oku("ETIKET_YAZIM").toString().equals("Yatay"))
			{
				jasper.setPrintOrder(PrintOrderEnum.HORIZONTAL);
			}
			else 
			{
				jasper.setPrintOrder(PrintOrderEnum.VERTICAL);
			}
			JRDesignSection designSection = (JRDesignSection) jasper.getDetailSection();
			JRBand[] bands =  jasper.getDetailSection().getBands();
			JRDesignBand qweBand = (JRDesignBand) bands[0].clone();
			qweBand.setHeight(Integer.valueOf( GLOBAL.setting_oku("ETIKET_YUK")));
			//**************************ADI*************************************************
			 //qweBand.getElementByKey("Adi").setForecolor(Color.BLUE);
			 //qweBand.getElementByKey("Adi").getStyle().setFontName("Arial");
			 //qweBand.getElementByKey("Adi").getStyle().setBold(true);
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
			
	          
			
			//System.out.println( jasper.getColumnWidth()+"=spacing=="+jasper.getColumnSpacing());
			//System.out.println(jasper.getPageWidth() + "==" + jasper.getBottomMargin()+ "==" + jasper.getTopMargin());
			
			//JasperReport jr = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("RPT\\ADRES_RPT\\Etiket.jrxml"));
			JasperReport jr = JasperCompileManager.compileReport(jasper);
			ResultSet rSet = a_Access.adr_etiket("Adi");
			//
			if(ETIKET.orTabbedPane.getSelectedIndex() == 0)
			{
				satir_kontrol();	
			}
			else
			{
				satir_kontrol_tek();
			}
			
			JRBeanCollectionDataSource qazBe = new JRBeanCollectionDataSource(etISIM);
			
		
			//Map parameters = new HashMap();
		    //parameters.put("ReportTitle", "List of Contacts");
		    //parameters.put("Author", "Prepared By Manisha");
			//Map<String, Object> parameters = new HashMap<String, Object>();
			//parameters.put("kOD", "120.01.0000");
			//jp = JasperFillManager.fillReport(jr,parameters, qazBe);
			
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
		int i = 0 ;
		String kodString = "";
		try 
		{
		DefaultTableModel modell = (DefaultTableModel)ETIKET.table.getModel();
		for (  i = 0; i <=  modell.getRowCount() - 1;i++)
		{
			if ( modell.getValueAt(i,6) != null) 
			{
				if (  (boolean) modell.getValueAt(i,6) )
				{
					kodString =modell.getValueAt(i, 0).toString();
					String iSIM ="";
					String aDR1 = "" ;
					String aDR2 = "" ;
					String tELEF = "" ;
					String sEMT = "" ;
					String sEHIR = "";
					
					if (modell.getValueAt(i, 0) != null)
					{
						iSIM = modell.getValueAt(i, 0).toString();
					}
					if (modell.getValueAt(i, 1) != null)
					{
						aDR1 = modell.getValueAt(i, 1).toString();
					}
					if (modell.getValueAt(i, 2) != null)
					{
						aDR2 = modell.getValueAt(i, 2).toString();
					}
					if (modell.getValueAt(i, 3) != null)
					{
						tELEF = modell.getValueAt(i, 3).toString();
					}
					if (modell.getValueAt(i, 4) != null)
					{
						sEMT = modell.getValueAt(i, 4).toString();
					}
					if (modell.getValueAt(i, 5) != null)
					{
						sEHIR = modell.getValueAt(i, 5).toString();
					}
					ETIKET_ISIM ets1  = new ETIKET_ISIM(iSIM,aDR1,aDR2 ,sEMT,sEHIR,tELEF);
					etISIM.add(ets1);
				}
			}
		}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, i + " Nolu Satir da Kodu =" + kodString + " Problemli Veri", "Etiket Yazdirma", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void satir_kontrol_tek()
	{
		try 
		{
			String iSIM = ETIKET.textField_1.getText();
			String aDR1 = ETIKET.textField_2.getText();
			String aDR2 = ETIKET.textField_3.getText();
			String tELEF = ETIKET.textField_4.getText();
			String sEMT = ETIKET.textField_5.getText();
			String sEHIR = ETIKET.textField_6.getText();
			for (int i = 0;i < (int) ETIKET.spinner.getValue() ;i++)
			{
				ETIKET_ISIM ets1  = new ETIKET_ISIM(iSIM,aDR1,aDR2 ,sEMT,sEHIR,tELEF);
				etISIM.add(ets1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Etiket Yazdirma", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static ByteArrayDataSource export_to() throws IOException, JRException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    JasperExportManager.exportReportToPdfStream(jp, baos);
	    ByteArrayDataSource ds =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
	 //JasperExportManager.exportReportToPdfFile(jp, "C:\\OBS_SISTEM\\invoice.pdf");
		return ds;
	}
	public static ByteArrayDataSource export_xls() throws JRException, IOException
	{
		File outputFile = new File("etiket.xls");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		exporter.exportReport();
   	    byteArrayOutputStream.writeTo(fileOutputStream);
		byteArrayOutputStream.close();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream .toByteArray());
		ByteArrayDataSource ds = new ByteArrayDataSource(inputStream, "application/x-any");
		return ds;
	}
	public static ByteArrayDataSource export_docx() throws IOException, JRException
	{
		File outputFile = new File("etiket.doc");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
	    JRDocxExporter exporter = new JRDocxExporter();   
	    exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		exporter.exportReport();
	   	byteArrayOutputStream.writeTo(fileOutputStream);
		byteArrayOutputStream.close();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream .toByteArray());
		ByteArrayDataSource ds = new ByteArrayDataSource(inputStream, "application/x-any");
		return ds;
	}
}
