package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.fonts.*;
import javax.swing.JScrollPane;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"serial","unused"})
public class PRINT_JASPER extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	static List<ETIKET_ISIM> etISIM = new ArrayList<ETIKET_ISIM>();
	static List<Ekstre_Detay> eDetay = new ArrayList<Ekstre_Detay>();
	private static JasperViewer jviewer ;
	private static JasperPrint jp;
	private static JScrollPane scrollPane;

	public PRINT_JASPER() throws PropertyVetoException {
		
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("YAZDIRMA");
		setBounds(100, 100, 800, 600);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		scrollPane =  new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setMaximum(true);
	}
	public static  void hisset(String nerden,String nasil)
	{
		try 
		{
			if (nerden.equals("etiket"))
			{
				File file = new File("C:\\OBS_SISTEM\\ETIKET.jrxml");
				JasperDesign jasper = JRXmlLoader.load(file);
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
				JRElement[] eleMENT = bands[0].getElements();
				designSection.removeBand(bands[0]);
				designSection.addBand(qweBand);
				JasperReport jr = JasperCompileManager.compileReport(jasper);
				ResultSet rSet = a_Access.adr_etiket("Adi");
				//
				etISIM.clear();
				if(ETIKET.orTabbedPane.getSelectedIndex() == 0)
				{
					satir_kontrol();	
				}
				else
				{
					satir_kontrol_tek();
				}
				JRBeanCollectionDataSource qazBe = new JRBeanCollectionDataSource(etISIM);
				jp = new JasperPrint();
				jp = JasperFillManager.fillReport(jr,null, qazBe);
			}
			else if (nerden.equals("ekstre"))
			{
				
				File file = new File("C:\\OBS_SISTEM\\Ekstre.jrxml");
				JasperDesign jasper = JRXmlLoader.load(file);
				JasperReport jr = JasperCompileManager.compileReport(jasper);
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("kOD", FILTRE.txtkodu.getText());
				parameters.put("uNVAN",  FILTRE.lblNewLabel_1.getText().trim() + "   /  " + FILTRE.lblNewLabel_2.getText().trim());
				parameters.put("pERIYOT","Periyot :" + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser)  + " - " + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_1));
				ResultSet rs ;
				if(BAGLAN.cariDizin.hAN_SQL.equals("MS SQL"))
				{
					rs = c_Access.ekstre(FILTRE.txtkodu.getText(), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_1),false);
				}
				else {
					rs = c_Access.ekstre(FILTRE.txtkodu.getText(), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_1),true);

				}
				jp = new JasperPrint();
				jp = JasperFillManager.fillReport(jr,parameters,new JRResultSetDataSource(rs));
			
				jp.setLocaleCode("UTF-8");
			}
			else if (nerden.equals("ekstre_kisa"))
			{
				File file = new File("C:\\OBS_SISTEM\\Ekstre_Kisa.jrxml");
				JasperDesign jasper = JRXmlLoader.load(file);
				JasperReport jr = JasperCompileManager.compileReport(jasper);
				ekstre_kisa();
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("kOD", FILTRE.txtkodu.getText());
				parameters.put("uNVAN",  FILTRE.lblNewLabel_1.getText().trim() + "   /  " + FILTRE.lblNewLabel_2.getText().trim());
				parameters.put("pERIYOT","Periyot :" + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser)  + " - " + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_1));
				parameters.put("bORC", EKSTRE.lblNewLabel_5_1.getText());
				parameters.put("aLACAK",EKSTRE.lblNewLabel_4_1.getText());
				JRBeanCollectionDataSource qazBe = new JRBeanCollectionDataSource(eDetay);
				jp = new JasperPrint();
				jp.setLocaleCode("UTF-8");
				jp = JasperFillManager.fillReport(jr,parameters, qazBe);
			}
			scrollPane.setViewportView(new JRViewer(jp));
		} catch (Exception ex) 
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Yazdirma", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void ekstre_kisa()
	{
		try 
		{
			DefaultTableModel modell = (DefaultTableModel)EKSTRE.table.getModel();
			String tARIH,eVRAK;
			for (int  i = 0; i <=  modell.getRowCount() - 1;i++)
			{
				if (i==0)
				{
					tARIH = "";
					eVRAK="";
				}
				else 
				{
					tARIH = TARIH_CEVIR.tarih_ters(modell.getValueAt(i, 0).toString());
					eVRAK = modell.getValueAt(i, 1).toString();
				}
				Ekstre_Detay eDTY  = new Ekstre_Detay(tARIH,
						eVRAK,
						modell.getValueAt(i, 2).toString(),
						modell.getValueAt(i, 3).toString(),
						Double.parseDouble(modell.getValueAt(i, 5).toString()),
						Double.parseDouble(modell.getValueAt(i, 6).toString()),
						Double.parseDouble(modell.getValueAt(i, 7).toString()));
				eDetay.add(eDTY);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Etiket Yazdirma", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void satir_kontrol()
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
	private static void satir_kontrol_tek()
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
		///
		//File outputFile = new File("rapor.xls");
		//ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		//FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		//JRPdfExporter exporter = new JRPdfExporter();
		//exporter.setExporterInput(new SimpleExporterInput(jp));
		//exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		//exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");

		//exporter.exportReport();
		//byteArrayOutputStream.writeTo(fileOutputStream);
		//byteArrayOutputStream.close();
		//ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream .toByteArray());
		//ByteArrayDataSource ds = new ByteArrayDataSource(inputStream, "application/x-any");
		//return ds;
		///
		jp.setLocaleCode("UTF-8");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jp, baos);
		ByteArrayDataSource ds =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
		//JasperExportManager.exportReportToPdfFile(jp, "C:\\OBS_SISTEM\\invoice.pdf");
		return ds;
	}
	public static ByteArrayDataSource export_xls() throws JRException, IOException
	{
		File outputFile = new File("rapor.xls");
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
		fileOutputStream.close();
		outputFile.delete();
		return ds;
	}
	public static ByteArrayDataSource export_docx() throws IOException, JRException
	{
		File outputFile = new File("rapor.doc");
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
		fileOutputStream.close();
		outputFile.delete();
		return ds;
	}
}
