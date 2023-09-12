package OBS_2025;

import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import com.crystaldecisions.ReportViewer.ReportViewerBean;
import com.crystaldecisions.sdk.occa.report.application.OpenReportOptions;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.application.ReportSectionPropertyEnum;
import com.crystaldecisions.sdk.occa.report.definition.Alignment;
import com.crystaldecisions.sdk.occa.report.definition.IArea;
import com.crystaldecisions.sdk.occa.report.definition.IDetailAreaFormat;
import com.crystaldecisions.sdk.occa.report.definition.IFieldObject;
import com.crystaldecisions.sdk.occa.report.definition.IFontColor;
import com.crystaldecisions.sdk.occa.report.definition.IReportObject;
import com.crystaldecisions.sdk.occa.report.definition.ISection;
import com.crystaldecisions.sdk.occa.report.definition.ISectionFormat;
import com.crystaldecisions.sdk.occa.report.definition.ITextObject;
import com.crystaldecisions.sdk.occa.report.definition.Paragraph;
import com.crystaldecisions.sdk.occa.report.definition.ParagraphElementKind;
import com.crystaldecisions.sdk.occa.report.definition.ParagraphElements;
import com.crystaldecisions.sdk.occa.report.definition.ParagraphTextElement;
import com.crystaldecisions.sdk.occa.report.definition.Paragraphs;
import com.crystaldecisions.sdk.occa.report.definition.ReportObjects;
import com.crystaldecisions.sdk.occa.report.document.IPageMargins;
import com.crystaldecisions.sdk.occa.report.document.IPrintOptions;
import com.crystaldecisions.sdk.occa.report.lib.ReportObjectKind;
import java.awt.BorderLayout;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.CRY_TEXT_WIDTH;
import OBS_C_2025.Degisken;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.sayiyiYaziyaCevir;

@SuppressWarnings("static-access")
public class PRINT_YAPMA extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	public static ReportViewerBean reportViewer ;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static CARI_ACCESS c_Access = new CARI_ACCESS(oac._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	static KAMBIYO_ACCESS ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo , OBS_SIS_2025_ANA_CLASS._IKambiyo_Loger);
	static STOK_ACCESS fa_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(oac._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static GUNLUK_ACCESS g_Access = new GUNLUK_ACCESS(oac._IGunluk , OBS_SIS_2025_ANA_CLASS._IGunluk_Loger);
	public static ReportClientDocument clientDoc ;
	private static ResultSet rs ;
	private static ArrayList<Degisken>  students = new ArrayList<Degisken>();
	static List<Ekstre_Detay> eDetay = new ArrayList<Ekstre_Detay>();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PRINT_YAPMA frame = new PRINT_YAPMA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public PRINT_YAPMA() throws PropertyVetoException {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				//OBS_MAIN.btnNewButton_23.setEnabled(false);
			}
		});
		setResizable(true);
		setTitle("RAPOR YAZDIRMA");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0, 0, 1075, 600);

		reportViewer = new ReportViewerBean();	
		reportViewer.setHasStatusBar(true);
		reportViewer.setShowGroupTree(false);
		reportViewer.setHasRefreshButton(false);
		getContentPane().add(reportViewer, BorderLayout.CENTER);
	}
	public static   void hisset (String nerden,String nasil) 
	{
		try 
		{
			clientDoc = new ReportClientDocument();
			if (nerden.equals("hsppln"))
			{
				//**************************************************************************
				// InputStream stream = PRINT_YAPMA.class.getClassLoader().getResourceAsStream("RPT/HSP_PLN.rpt");
				//byte[] buffer = new byte[stream.available()];
				// stream.read(buffer);
				// stream.mark(buffer.length);
				//stream.reset();
				//System.out.println(stream.available());
				//PRINT_YAPMA qwe ;
				//qwe = new PRINT_YAPMA();
				//InputStream is =qwe.getClass().getClassLoader().getResourceAsStream("RPT/HSP_PLN.rpt");
				//System.out.println(is.available());
				//**************************************************************************
				//File file = new File("src/RPT/HSP_PLN.rpt");
				File file = new File("C:\\OBS_SISTEM\\HSP_PLN.rpt");
				clientDoc.open(file.getPath(), 0);
				//clientDoc.open(is, 0);
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				//**************************************
				rs = c_Access.hp_pln();
				clientDoc.getDatabaseController().setDataSource(rs);
				ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.text);
				for(int i=0; i< reportObjects.size();i++)
				{
					ITextObject textObject = (ITextObject)reportObjects.get(i);
					if (textObject.getText().equals("Plast Bak"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(BAGLAN.cariDizin.fIRMA_ADI);
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						IFontColor fc = oTextObject.getFontColor();
						fc.setColor(java.awt.Color.red);
						oTextObject.setFontColor(fc);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
				}
			}
			else if (nerden.equals("ekstre"))
			{
				//**************************************************************************
				File file = new File("C:\\OBS_SISTEM\\EKSTRE.rpt");
				clientDoc.open(file.getPath(), 0);
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				//**************************************************************************
				if (nasil.equals("normal"))
				{
					rs = c_Access.ekstre(FILTRE.txtkodu.getText(), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_1));
				}
				else if (nasil.equals("aratarih"))
				{
					rs = c_Access.ekstre_sqlite();
				}
				clientDoc.getDatabaseController().setDataSource(rs);
				com.crystaldecisions.sdk.occa.report.definition.ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.text);
				for(int i=0; i< reportObjects.size();i++)
				{
					ITextObject textObject = (ITextObject)reportObjects.get(i);
					if (textObject.getText().equals("100.01.0001"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(FILTRE.txtkodu.getText());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("KASA"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText("");
						oParagraphTextElement.setText( FILTRE.lblNewLabel_1.getText().trim() + "   /  " + FILTRE.lblNewLabel_2.getText().trim());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("PERIYOT"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						String yazi = "Periyot :" + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser)  + " - " + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_1);
						oParagraphTextElement.setText("        " + yazi );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getName().equals("txtborc"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						oParagraph.setAlignment(Alignment.right);
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();

						oParagraphTextElement.setText(EKSTRE.lblNewLabel_5_1.getText());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);

						IFontColor newFontColor = oTextObject.getFontColor();
						Font fnt = new Font("Arial Narrow", Font.PLAIN, 9);
						newFontColor.setFont(fnt);
						oTextObject.setFontColor(newFontColor);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getName().equals("txtalacak"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						oParagraph.setAlignment(Alignment.right);
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();

						oParagraphTextElement.setText(EKSTRE.lblNewLabel_4_1.getText());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);

						IFontColor newFontColor = oTextObject.getFontColor();
						Font fnt = new Font("Arial Narrow", Font.PLAIN, 9);
						newFontColor.setFont(fnt);
						oTextObject.setFontColor(newFontColor);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
				}
			}
			else if (nerden.equals("mizan"))
			{
				//**************************************************************************
				File file = new File("C:\\OBS_SISTEM\\MIZAN.rpt");
				clientDoc.open(file.getPath(), 0);
				//**************************************************************************
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				String hangi_tur = FILTRE.comboBox.getItemAt(FILTRE.comboBox.getSelectedIndex());
				String o1 = "" ;
				String o2 = "" ;
				if (hangi_tur.equals("Borclu Hesaplar") )
				{ o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) < 0 " ; }
				else if (hangi_tur.equals("Alacakli Hesaplar")) 
				{o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) > 0 " ;}
				else if (hangi_tur.equals( "Bakiyesi 0 Olanlar" )) 
				{ o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) = 0" ;}
				else if (hangi_tur.equals( "Bakiyesi 0 Olmayanlar" ))
				{ o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) <> 0" ;}
				o2 = " ORDER BY SATIRLAR.HESAP ASC " ;
				//**************
				rs = c_Access.mizan(FILTRE.txtilk.getText(),FILTRE.txtson.getText() ,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) ,
						FILTRE.txticins.getText(),FILTRE.txtscins.getText() ,
						FILTRE.txtikarton.getText(),FILTRE.txtskarton.getText() ,
						o1 , o2);
				clientDoc.getDatabaseController().setDataSource(rs);
				com.crystaldecisions.sdk.occa.report.definition.ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.text);
				for(int i=0; i< reportObjects.size();i++)
				{
					ITextObject textObject = (ITextObject)reportObjects.get(i);
					if (textObject.getText().equals("FIRMA"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(BAGLAN.cariDizin.fIRMA_ADI);
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("PERIYOT"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						String yazi = "Periyot :" + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2)  + " - " + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2_1);
						oParagraphTextElement.setText("        " + yazi );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
				}
			}
			else if (nerden.equals("ozel_mizan"))
			{
				//**************************************************************************
				File file = new File("C:\\OBS_SISTEM\\OZEL_MIZAN.rpt");
				clientDoc.open(file.getPath(), 0);
				//**************************************************************************
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				String hangi_tur = FILTRE.comboBox.getItemAt(FILTRE.comboBox.getSelectedIndex());
				String o1 = "" ;
				String o2 = "" ;
				if (BAGLAN.cariDizin.hAN_SQL.equals("MS SQL"))
				{
					if (hangi_tur.equals("Borclu Hesaplar") )
					{ o1 = "HAVING  (ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM SATIRLAR  WITH (INDEX(IX_SATIRLAR))   "
							+ "WHERE   SATIRLAR.HESAP    = s.HESAP     			 ) ,0),2)  )  < 0 " ; }
					else if (hangi_tur.equals("Alacakli Hesaplar")) 
					{o1 =  "HAVING  (ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM SATIRLAR  WITH (INDEX(IX_SATIRLAR))   "
							+ "WHERE   SATIRLAR.HESAP    = s.HESAP     			 ) ,0),2)  )  > 0 " ;}
					else if (hangi_tur.equals( "Bakiyesi 0 Olanlar" )) 
					{ o1 =  "HAVING  (ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM SATIRLAR  WITH (INDEX(IX_SATIRLAR))   "
							+ "WHERE   SATIRLAR.HESAP    = s.HESAP     			 ) ,0),2)  )  = 0" ;}
					else if (hangi_tur.equals( "Bakiyesi 0 Olmayanlar" ))
					{ o1 = "HAVING  (ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM SATIRLAR  WITH (INDEX(IX_SATIRLAR))   "
							+ "WHERE   SATIRLAR.HESAP    = s.HESAP     			 ) ,0),2)  )  <> 0" ;}
					o2 = " ORDER BY s.HESAP ASC " ;
				}
				else 	if (BAGLAN.cariDizin.hAN_SQL.equals("MY SQL"))
				{
					if (hangi_tur.equals("Borclu Hesaplar") )
					{ 
						o1= " HAVING BAKIYE  < 0 " ;
					}
					else if (hangi_tur.equals("Alacakli Hesaplar"))  
					{
						o1= " HAVING BAKIYE  > 0 " ;
					}
					else if (hangi_tur.equals( "Bakiyesi 0 Olanlar" ))     
					{
						o1= " HAVING BAKIYE  =  0 " ;
					}
					else if (hangi_tur.equals( "Bakiyesi 0 Olmayanlar" ))
					{ 
						o1= " HAVING BAKIYE  <>  0 " ;
					}
					o2 = " ORDER BY s.HESAP  " ;  
				}
				//**************
				rs = c_Access.ozel_mizan(FILTRE.txtilk.getText(),FILTRE.txtson.getText() ,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) ,
						FILTRE.txticins.getText(),FILTRE.txtscins.getText() ,
						FILTRE.txtikarton.getText(),FILTRE.txtskarton.getText() ,
						o1 , o2);
				clientDoc.getDatabaseController().setDataSource(rs);
				com.crystaldecisions.sdk.occa.report.definition.ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.text);
				for(int i=0; i< reportObjects.size();i++)
				{
					ITextObject textObject = (ITextObject)reportObjects.get(i);
					if (textObject.getText().equals("FIRMA"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(BAGLAN.cariDizin.fIRMA_ADI);
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}

					else if (textObject.getText().equals("PERIYOT"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						String yazi = "Periyot :" + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2)  + " - " + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_2_1);
						oParagraphTextElement.setText("          " + yazi );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);

					}
				}
			}
			else if (nerden.equals("dvzcev"))
			{
				//**************************************************************************
				File file = new File("C:\\OBS_SISTEM\\DVZ_CEVIRME.rpt");
				clientDoc.open(file.getPath(), 0);
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				//*******************
				String islem  = "" ;
				if (  GLOBAL.setting_oku("PRG_PARA").toString().equals(FILTRE.lblNewLabel_2_1.getText()))
				{
					islem = "/" ;
				}
				else
				{
					islem = "*" ;
				}
				ResultSet	rs = null;
				String hKURString = "";
		        if (FILTRE.chckbxNewCheckBox_4.isSelected())
		            {
		            	hKURString = "Kayitli";
		            }
				rs = c_Access.dvz_cevirme(FILTRE.comboBox_2.getItemAt(FILTRE.comboBox_2.getSelectedIndex()),
						FILTRE.txtdvz.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_3),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_4),
						FILTRE.comboBox_1.getItemAt(FILTRE.comboBox_1.getSelectedIndex()), islem,hKURString);
				clientDoc.getDatabaseController().setDataSource(rs);
				com.crystaldecisions.sdk.occa.report.definition.ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.text);
				for(int i=0; i< reportObjects.size();i++)
				{
					ITextObject textObject = (ITextObject)reportObjects.get(i);
					if (textObject.getText().equals("HESAP"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(DVZ_CEVIRME.lblkod.getText()+ " / " + DVZ_CEVIRME.lblunvan.getText() + " / " + DVZ_CEVIRME.lblcins.getText());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("KUR"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(DVZ_CEVIRME.lblcevrilen.getText());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
				}
			}
//*********************************************************ETIKET *******************
			// inch to TWIP
			// 1 == 1440
			else if (nerden.equals("etiket"))
			{
				//**************************************************************************
				File file = new File("C:\\OBS_SISTEM\\ETIKET.rpt");
				clientDoc.open(file.getPath(), 0);
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				//*******************
				
				ResultSet	rs = null;
				rs = a_Access.adr_etiket("Adi");
					//com.crystaldecisions.sdk.occa.report.definition.ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.field);
				com.crystaldecisions.sdk.occa.report.application.PrintOutputController printOutputController = clientDoc.getPrintOutputController();
				 IPrintOptions printOptions = printOutputController.getPrintOptions();
				 //System.out.println( printOptions.getPageMargins().getLeft() +"="+  printOptions.getPageMargins().getRight() + "=" +
				//		 printOptions.getPageMargins().getTop() +"="+  printOptions.getPageMargins().getBottom());
				 IPrintOptions newPrintOptions = (IPrintOptions) printOptions.clone(true);
				 IPageMargins newMargins = newPrintOptions.getPageMargins();
				
				 newMargins.setLeft(300);
				 newMargins.setRight(Integer.valueOf( GLOBAL.setting_oku("SAG_BOSLUK")));
				 //newMargins.setTop(Integer.valueOf( GLOBAL.setting_oku("UST_BOSLUK")));
				 newMargins.setTop(300);
				 newMargins.setBottom(Integer.valueOf( GLOBAL.setting_oku("ALT_BOSLUK")));
				 newPrintOptions.setPageMargins(newMargins);
				 printOutputController.modifyPrintOptions(newPrintOptions);
				 
				 //
					String detailBolumString = clientDoc.getReportDefController().getReportDefinition().getAreas().getArea(2).getName();
					IArea areaqw =clientDoc.getReportDefController().getReportDefinition().getAreas().getArea(2);
					IDetailAreaFormat kokAreaFormat = (IDetailAreaFormat) areaqw.getFormat();
					System.out.println(detailBolumString + "===HorizontalGap===="+ kokAreaFormat.getHorizontalGap()+ "===VerticalGap==" + kokAreaFormat.getVerticalGap()+"  Detail Genislik ===="+ kokAreaFormat.getDetailWidth()  );
				 //
					
					
			
					
				IArea detArea =clientDoc.getReportDefController().getReportDefinition().getAreas().getArea(2);
			
				IArea yenArea = (IArea) detArea.clone(true);
				IDetailAreaFormat xswAreaFormat = (IDetailAreaFormat) detArea.getFormat();
				//xswAreaFormat.setDetailWidth((Integer.valueOf( GLOBAL.setting_oku("ETIKET_GEN"))));
				//xswAreaFormat.setVerticalGap((Integer.valueOf( GLOBAL.setting_oku("ETIKET_ARA_BOSLUK"))));
				xswAreaFormat.setDetailWidth(5000);
				xswAreaFormat.setVerticalGap(400);
				xswAreaFormat.setHorizontalGap(400);
				
				yenArea.setFormat(xswAreaFormat);
				clientDoc.getReportDefController().getReportDefinition().getDetailArea().setFormat(xswAreaFormat);
				
				//clientDoc.getReportDefController().getReportDefinition().getAreas().remove(2);
				
				//clientDoc.getReportDefController().getReportDefinition().getAreas().add(yenArea);
				
				
				//*****************************************************************
					detailBolumString = clientDoc.getReportDefController().getReportDefinition().getAreas().getArea(2).getName();
					areaqw =clientDoc.getReportDefController().getReportDefinition().getAreas().getArea(2);
					kokAreaFormat = (IDetailAreaFormat) areaqw.getFormat();
					System.out.println(detailBolumString + "===HorizontalGap===="+ kokAreaFormat.getHorizontalGap()+ "===VerticalGap==" + kokAreaFormat.getVerticalGap()+"  Detail Genislik ===="+ kokAreaFormat.getDetailWidth()  );
				//******************************************************************
				clientDoc.getDatabaseController().setDataSource(rs);

					
			}
//*********************************************************************************************************************
			else if (nerden.equals("cekg"))
			{
				//**************************************************************************
				File file = new File("C:\\OBS_SISTEM\\BORDRO.rpt");
				clientDoc.open(file.getPath(), 0);
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				//**************************************************************************
				ResultSet	rs = null;
				rs = ka_Access.kam_bordno("CEK",nasil,"Giris_Bordro");
				clientDoc.getDatabaseController().setDataSource(rs);
				com.crystaldecisions.sdk.occa.report.definition.ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.text);
				for(int i=0; i< reportObjects.size();i++)
				{
					ITextObject textObject = (ITextObject)reportObjects.get(i);
					if (textObject.getText().equals("KOD"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(CEK_GIRIS.textField_1.getText());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("UNVA"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText("");
						oParagraphTextElement.setText( CEK_GIRIS.lblNewLabel_2.getText());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("BNO"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(nasil );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("GIRIS"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(nasil.equals("cekg") ? "GIRIS" : "CIKIS" );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("TARIH"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						String yazi = TARIH_CEVIR.tarih_dt_ddMMyyyy(CEK_GIRIS.dateChooser)  ;
						oParagraphTextElement.setText("     " + yazi );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("ADET"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(CEK_GIRIS.lblNewLabel_12.getText() );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("YAZI"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						sayiyiYaziyaCevir cevir = new sayiyiYaziyaCevir();
						double aqw =DecimalFormat.getNumberInstance().parse(CEK_GIRIS.label.getText()).doubleValue();
						String qwe = Double.toString(aqw);
						String cnt  = "" ;
						if ( CEK_GIRIS.textField_4.getText().equals(GLOBAL.setting_oku("PRG_PARA").toString()))
						{
							cnt = "KURUŞ" ;
						}
						else
						{
							cnt = "Cent" ;
						}
						String yaziyla= cevir.sayiyiYaziyaCevir(qwe, 2, CEK_GIRIS.textField_4.getText(), cnt , "#", null, null, null);
						oParagraphTextElement.setText(yaziyla );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
				}
			}
			else if (nerden.equals("cekc"))
			{
				//**************************************************************************
				File file = new File("C:\\OBS_SISTEM\\BORDRO.rpt");
				clientDoc.open(file.getPath(), 0);
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				//**************************************************************************
				ResultSet	rs = null;
				rs = ka_Access.kam_bordno("CEK",nasil,"Cikis_Bordro");
				clientDoc.getDatabaseController().setDataSource(rs);
				com.crystaldecisions.sdk.occa.report.definition.ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.text);
				for(int i=0; i< reportObjects.size();i++)
				{
					ITextObject textObject = (ITextObject)reportObjects.get(i);
					if (textObject.getText().equals("KOD"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(CEK_CIKIS.textField_1.getText());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("UNVA"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText("");
						oParagraphTextElement.setText( CEK_CIKIS.lblNewLabel_2.getText());
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("BNO"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(nasil );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("GIRIS"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(nasil.equals("cekg") ? "GIRIS" : "CIKIŞ" );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("TARIH"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						String yazi = TARIH_CEVIR.tarih_dt_ddMMyyyy(CEK_CIKIS.dateChooser)  ;
						oParagraphTextElement.setText("     " + yazi );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("ADET"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						oParagraphTextElement.setText(CEK_CIKIS.lblNewLabel_12.getText() );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
					else if (textObject.getText().equals("YAZI"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						sayiyiYaziyaCevir cevir = new sayiyiYaziyaCevir();
						double aqw =DecimalFormat.getNumberInstance().parse(CEK_CIKIS.label.getText()).doubleValue();
						String qwe = Double.toString(aqw);
						String cnt  = "" ;
						if ( CEK_CIKIS.textField_4.getText().equals(GLOBAL.setting_oku("PRG_PARA").toString()))
						{
							cnt = "KURUŞ" ;
						}
						else
						{
							cnt = "Cent" ;
						}
						String yaziyla= cevir.sayiyiYaziyaCevir(qwe, 2, CEK_CIKIS.textField_4.getText() , cnt , "#", null, null, null);
						oParagraphTextElement.setText(yaziyla );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
				}
			}
			else if (nerden.equals("stok"))
			{
				//**************************************************************************
				File file = new File("C:\\OBS_SISTEM\\STOK.rpt");
				clientDoc.open(file.getPath(), 0);
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				//**************************************************************************
				rs = fa_Access.envanter_rapor_u_kodu(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_16),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_17),
						FILTRE.textField_19.getText(),FILTRE.textField_20.getText() ,
						FILTRE.textField_18.getText(),FILTRE.textField_24.getText() ,
						"","",GLOBAL.yazici[0], GLOBAL.yazici[1], GLOBAL.yazici[2],GLOBAL.yazici[3],
						GLOBAL.yazici[4],GLOBAL.yazici[5],GLOBAL.yazici[6] );
				clientDoc.getDatabaseController().setDataSource(rs);
				com.crystaldecisions.sdk.occa.report.definition.ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.text);
				for(int i=0; i< reportObjects.size();i++)
				{
					ITextObject textObject = (ITextObject)reportObjects.get(i);
					if (textObject.getText().equals("PERIYOT"))
					{
						ITextObject oTextObject =  (ITextObject) textObject.clone(true);
						Paragraphs oParagraphs = new Paragraphs();
						Paragraph oParagraph = new Paragraph();
						ParagraphElements oParagraphElements = new ParagraphElements();
						ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
						String yazi = "Periyot :" + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_16)  + " - " + TARIH_CEVIR.tarih_dt_ddMMyyyy(FILTRE.dateChooser_17);
						oParagraphTextElement.setText("        " + yazi );
						oParagraphTextElement.setKind(ParagraphElementKind.text);
						oParagraphs.add(oParagraph);
						oParagraph.setParagraphElements(oParagraphElements);
						oParagraphElements.add(oParagraphTextElement);
						oTextObject.setParagraphs(oParagraphs);
						clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
					}
				}
			}
			else if (nerden.equals("fatura"))
			{
				//**************************************************************************
				File file = new File("C:\\OBS_SISTEM\\FATURA.rpt");
				clientDoc.open(file.getPath(),  OpenReportOptions._openAsReadOnly);
				clientDoc.getDatabaseController().logon(BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
				int gen = 0 ;
				int kdvyuz = 0 ;
				String yazi = "" ;
				String deger;
				String[] parts;
				Font baslikFont;
				Font altFont;
				Font detailFont;
				Font yileFont;
				String[] bilgi = new String[6];
				String[] gyer_bilgi = new String[6];
				String unvan = "";
				//***
				deger = GLOBAL.setting_oku("STK_FAT_FIR_BILGI").toString();
				if (deger.equals("Adres_Dosya"))
				{
					if (FATURA.txtadres.getText().equals("")) return;
					bilgi = a_Access.adres_oku(FATURA.txtadres.getText());
					unvan = FATURA.lblNewLabel_6.getText();
				}
				else   if (deger.equals("Cari_Dosya"))
				{
					if (FATURA.txtcari.getText().equals("")) return;
					bilgi = c_Access.cari_adres_oku(FATURA.txtcari.getText());
					unvan = FATURA.lblNewLabel_3.getText();
				}
				//*******GIDECEGI YER ***********
				if ( ! FATURA.textField_8.getText().equals(""))
				{
					gyer_bilgi = a_Access.adres_oku(FATURA.txtadres.getText());
				}
				else
				{
					boolean varmi = OBS_MAIN.pencere_bak("GIDECEGI_YER");
					if (varmi) 
					{
						gyer_bilgi[0] = GIDECEGI_YER.textField_1.getText();
						gyer_bilgi[1] = GIDECEGI_YER.textField_2.getText();
						gyer_bilgi[2] = GIDECEGI_YER.textField_3.getText();
						gyer_bilgi[3] = GIDECEGI_YER.textField_4.getText();
					}
					else
					{
						gyer_bilgi[0] = "";
						gyer_bilgi[1] = "";
						gyer_bilgi[2] = "";
						gyer_bilgi[3] = "";
					}
				}
				doldur();
				//**** BASLIK BOLUMU - PageHeaderSection1
				ISection baslik = (ISection)clientDoc.getReportDefController().getReportDefinition().getPageHeaderArea().getSections().getSection(0);
				clientDoc.getReportDefController().getReportSectionController().setProperty( (ISection) baslik, ReportSectionPropertyEnum.height,(int) students.get(9).fat_sat);
				//**** ALT BOLUM -  PageFooterSection1
				ISection alt_bolum = (ISection)clientDoc.getReportDefController().getReportDefinition().getPageFooterArea().getSections().getSection(0);
				clientDoc.getReportDefController().getReportSectionController().setProperty( (ISection) alt_bolum, ReportSectionPropertyEnum.height,(int) students.get(33).fat_sat);

				ReportObjects reportObjects = clientDoc.getReportDefController().getReportObjectController().getAllReportObjects();
				if (reportObjects != null )
				{
					deger = GLOBAL.setting_oku("STK_FAT_BASLIK").toString();
					deger = deger.substring(1, deger.length()-1);
					parts = deger.split(",");
					baslikFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));

					deger = GLOBAL.setting_oku("STK_FAT_ABILGI").toString();
					deger = deger.substring(1, deger.length()-1);
					parts = deger.split(",");
					altFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));

					deger = GLOBAL.setting_oku("STK_YAZI_ILE").toString();
					deger = deger.substring(1, deger.length()-1);
					parts = deger.split(",");
					yileFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));

					deger = GLOBAL.setting_oku("STK_FAT_DETAY").toString();
					deger = deger.substring(1, deger.length()-1);
					parts = deger.split(",");
					detailFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
					String cins = 	  FATURA.cmbcins.getItemAt(FATURA.cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C" :"G";

					rs = fa_Access.fatura_oku_printer(FATURA.textField.getText(), cins);
					clientDoc.getDatabaseController().setDataSource(rs);

					for (int i = 0; i < reportObjects.size(); i++)
					{
						IReportObject reportObject = reportObjects.getReportObject(i);
						//System.out.println(reportObject.getSectionName() + "===" + reportObject.getName());
						if (reportObject.getName().equals("txttarih"))
						{
							ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
							oTextObject.setLeft((int) students.get(0).fat_sut);
							oTextObject.setTop((int) students.get(0).fat_sat);
							yazi = TARIH_CEVIR.tarih_dt_ddMMyyyy(FATURA.dtc)  ;
							Paragraphs oParagraphs = new Paragraphs();
							Paragraph oParagraph = new Paragraph();
							ParagraphElements oParagraphElements = new ParagraphElements();
							ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
							IFontColor newFontColor ;
							oParagraphTextElement.setText(yazi);
							oParagraph.setAlignment(Alignment.right);
							oParagraphTextElement.setKind(ParagraphElementKind.text);
							oParagraphs.add(oParagraph);
							oParagraph.setParagraphElements(oParagraphElements);
							oParagraphElements.add(oParagraphTextElement);
							oTextObject.setParagraphs(oParagraphs);
							newFontColor = oTextObject.getFontColor();
							newFontColor.setFont(baslikFont);
							oTextObject.setFontColor(newFontColor);
							clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
						}
						else  if (reportObject.getName().equals("txtkodu"))
						{
							if (students.get(1).fat_sut == 0 && students.get(1).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(1).fat_sut);
								oTextObject.setTop((int) students.get(1).fat_sat);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(FATURA.txtcari.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtadi"))
						{
							if (students.get(2).fat_sut == 0 && students.get(2).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(2).fat_sut);
								oTextObject.setTop((int) students.get(2).fat_sat);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(unvan);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtadr1"))
						{
							if (students.get(2).fat_sut == 0 && students.get(2).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(2).fat_sut);
								oTextObject.setTop((int) students.get(2).fat_sat + 315);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(bilgi[0]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtadr2"))
						{
							if (students.get(2).fat_sut == 0 && students.get(2).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(2).fat_sut);
								oTextObject.setTop((int) students.get(2).fat_sat + 630);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(bilgi[1]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtsemt"))
						{
							if (students.get(2).fat_sut == 0 && students.get(2).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(2).fat_sut);
								oTextObject.setTop((int) students.get(2).fat_sat + 945);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(bilgi[2]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk(bilgi[2],baslikFont));
								gen = oTextObject.getWidth() ;
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtsehir"))
						{
							if (students.get(2).fat_sut == 0 && students.get(2).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(2).fat_sut + gen + 50);
								oTextObject.setTop((int) students.get(2).fat_sat + 945);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(bilgi[3]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk(bilgi[3],baslikFont));
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtvd"))
						{
							if (students.get(3).fat_sut == 0 && students.get(3).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(3).fat_sut );
								oTextObject.setTop((int) students.get(3).fat_sat);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(bilgi[4]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtvn"))
						{
							if (students.get(4).fat_sut == 0 && students.get(4).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(4).fat_sut );
								oTextObject.setTop((int) students.get(4).fat_sat);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(bilgi[5]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtgyer1"))
						{
							if (students.get(5).fat_sut == 0 && students.get(5).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(5).fat_sut );
								oTextObject.setTop((int) students.get(5).fat_sat);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(gyer_bilgi[0]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtgyer2"))
						{
							if (students.get(5).fat_sut == 0 && students.get(5).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(5).fat_sut );
								oTextObject.setTop((int) students.get(5).fat_sat + 315);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(gyer_bilgi[1]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtgyer3"))
						{
							if (students.get(5).fat_sut == 0 && students.get(5).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(5).fat_sut );
								oTextObject.setTop((int) students.get(5).fat_sat + 630);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(gyer_bilgi[2]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtgyer4"))
						{
							if (students.get(5).fat_sut == 0 && students.get(5).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(5).fat_sut );
								oTextObject.setTop((int) students.get(5).fat_sat + 945);
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(gyer_bilgi[3]);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtnot1"))
						{
							if (students.get(6).fat_sut == 0 && students.get(6).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(6).fat_sut );
								oTextObject.setTop((int) students.get(6).fat_sat );
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(FATURA. textField_5.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtnot2"))
						{
							if (students.get(7).fat_sut == 0 && students.get(7).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(7).fat_sut );
								oTextObject.setTop((int) students.get(7).fat_sat );
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(FATURA. textField_6.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtnot3"))
						{
							if (students.get(8).fat_sut == 0 && students.get(8).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(8).fat_sut );
								oTextObject.setTop((int) students.get(8).fat_sat );
								oParagraphTextElement.setText("");
								oParagraphTextElement.setText(FATURA. textField_7.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(baslikFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
						//************************************ DETAILS BOLUM****************************************************************************
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Barkod"))
						{
							if (students.get(10).fat_sut == 0 && students.get(10).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(10).fat_sut);
								fTextObject.setTop((int) students.get(10).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Kodu"))
						{
							if (students.get(11).fat_sut == 0 && students.get(11).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(11).fat_sut);
								fTextObject.setTop((int) students.get(11).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Adi"))
						{
							if (students.get(12).fat_sut == 0 && students.get(12).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(12).fat_sut);
								fTextObject.setTop((int) students.get(12).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Depo"))
						{
							if (students.get(13).fat_sut == 0 && students.get(13).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(13).fat_sut);
								fTextObject.setTop((int) students.get(13).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Izahat"))
						{
							if (students.get(14).fat_sut == 0 && students.get(14).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(14).fat_sut);
								fTextObject.setTop((int) students.get(14).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Birim"))
						{
							if (students.get(15).fat_sut == 0 && students.get(15).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(15).fat_sut);
								fTextObject.setTop((int) students.get(15).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Fiat"))
						{
							if (students.get(16).fat_sut == 0 && students.get(16).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(16).fat_sut);
								fTextObject.setTop((int) students.get(16).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Iskonto"))
						{
							if (students.get(17).fat_sut == 0 && students.get(17).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(17).fat_sut);
								fTextObject.setTop((int) students.get(17).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Miktar"))
						{
							if (students.get(18).fat_sut == 0 && students.get(18).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(18).fat_sut);
								fTextObject.setTop((int) students.get(18).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Kdv"))
						{
							if (students.get(19).fat_sut == 0 && students.get(19).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(19).fat_sut);
								fTextObject.setTop((int) students.get(19).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						else  if (reportObject.getName().equals("SQL_Scrapbook_0Tutar"))
						{
							if (students.get(20).fat_sut == 0 && students.get(20).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (IFieldObject) reportObject);
							}
							else
							{
								IFieldObject fTextObject =  (IFieldObject) reportObject.clone(true);
								IFontColor newFontColor ;
								fTextObject.setLeft((int) students.get(20).fat_sut);
								fTextObject.setTop((int) students.get(20).fat_sat);
								newFontColor = fTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								fTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, fTextObject);
							}
						}
						//************************************ MIKTAR TOPLAM****************************************************************************
						else  if (reportObject.getName().equals("txtmiktop"))
						{
							ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
							if (FATURA.chckbxNewCheckBox.isSelected())
							{
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setLeft((int) students.get(18).fat_sut );
								oTextObject.setTop(0);
								oParagraphTextElement.setText("0.000");
								oParagraphTextElement.setText(FATURA. label_8.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(detailFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
							else
							{
								ISectionFormat ifor =clientDoc.getReportDefController().getReportDefinition().getReportFooterArea().getSections().getSection(0).getFormat();
								ifor.setEnableSuppress(true);
								clientDoc.getReportDefController().getReportSectionController().setProperty( clientDoc.getReportDefController().getReportDefinition().getReportFooterArea().getSections().getSection(0), 
										ReportSectionPropertyEnum.format,ifor);
							}
						}
						//********************************************ALT BOLUM ************************************************************    	  
						else  if (reportObject.getName().equals("txttutar"))
						{
							if (students.get(21).fat_sut == 0 && students.get(21).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txttutar"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("Tutar :",baslikFont) * 21);
								oTextObject.setLeft((int) students.get(21).fat_sut  -   (oTextObject.getWidth() + 200 ));
								oTextObject.setTop((int) students.get(21).fat_sat );
								oParagraphTextElement.setText("Tutar :");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
								//****
								ITextObject textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txttutar2");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();
								oTextObject.setLeft((int) students.get(21).fat_sut );
								oTextObject.setTop((int) students.get(21).fat_sat );
								oParagraphTextElement.setText("0.00");
								oParagraphTextElement.setText(FATURA. label_9.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
							}	
						}
						else  if (reportObject.getName().equals("txtiskonto"))
						{
							if (students.get(22).fat_sut == 0 && students.get(22).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txtiskonto2"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("Iskonto :",baslikFont) * 21);
								oTextObject.setLeft((int) students.get(22).fat_sut -  (oTextObject.getWidth() + 200 ));
								oTextObject.setTop((int) students.get(22).fat_sat );
								oParagraphTextElement.setText("Iskonto :");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
								//****
								ITextObject textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txtiskonto2");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();

								oTextObject.setLeft((int) students.get(22).fat_sut );
								oTextObject.setTop((int) students.get(22).fat_sat );
								oParagraphTextElement.setText("0.00");
								oParagraphTextElement.setText(FATURA. label_6.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txttoplam"))
						{
							if (students.get(23).fat_sut == 0 && students.get(23).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txttoplam2"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("Toplam :",baslikFont) * 21);
								oTextObject.setLeft((int) students.get(23).fat_sut -    (oTextObject.getWidth() + 200 ));
								oTextObject.setTop((int) students.get(23).fat_sat );
								oParagraphTextElement.setText("Toplam :");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
								//****
								ITextObject textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txttoplam2");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();


								oTextObject.setLeft((int) students.get(23).fat_sut);
								oTextObject.setTop((int) students.get(23).fat_sat );
								oParagraphTextElement.setText("0.00");
								oParagraphTextElement.setText(FATURA. label_7.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
							}
						}
						else  if (reportObject.getName().equals("txtkdvtut"))
						{
							if (students.get(24).fat_sut == 0 && students.get(24).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txtkdvtut2"));
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txtkdvoran"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("K.D.V. Toplami :",baslikFont) * 21);
								oTextObject.setLeft((int) students.get(24).fat_sut -    (oTextObject.getWidth() + 200 ));
								kdvyuz =  oTextObject.getLeft();
								oTextObject.setTop((int) students.get(24).fat_sat );
								oParagraphTextElement.setText("K.D.V. Toplami :");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
								//****
								ITextObject textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txtkdvtut2");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();

								oTextObject.setLeft((int) students.get(24).fat_sut);
								oTextObject.setTop((int) students.get(24).fat_sat );
								oParagraphTextElement.setText("0.00");
								oParagraphTextElement.setText(FATURA. label_3.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
								//
								textObject= null;
								textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txtkdvoran");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();

								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("%            ",baslikFont) * 21);
								oTextObject.setLeft(kdvyuz -     (oTextObject.getWidth() + 200 ));
								oTextObject.setTop((int) students.get(24).fat_sat );
								DefaultTableModel model = (DefaultTableModel) FATURA.table.getModel();
								oParagraphTextElement.setText("%  " + FORMATLAMA.doub_2( Double.parseDouble(model.getValueAt(0, 7).toString())));
								model = null;
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
							}
						}

						else  if (reportObject.getName().equals("txttevk"))
						{
							if (students.get(26).fat_sut == 0 && students.get(26).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txttevk2"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("Tevkifat Orani :",baslikFont) * 21);
								oTextObject.setLeft((int) students.get(26).fat_sut -    (oTextObject.getWidth() + 200 ));
								oTextObject.setTop((int) students.get(26).fat_sat );
								oParagraphTextElement.setText("Tevkifat Orani :");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
								//****
								ITextObject textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txttevk2");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();


								oTextObject.setLeft((int) students.get(26).fat_sut);
								oTextObject.setTop((int) students.get(26).fat_sat );
								oParagraphTextElement.setText(FATURA. txttev.getText() + "/10");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
							}

						}
						else  if (reportObject.getName().equals("txtaltevedkdv"))
						{
							if (students.get(27).fat_sut == 0 && students.get(27).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txtaltevedkdv2"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("Alici Tarafindan Tevkif Edilecek K.D.V.:",baslikFont) * 21);
								oTextObject.setLeft((int) students.get(27).fat_sut -    (oTextObject.getWidth() + 200 ));
								oTextObject.setTop((int) students.get(27).fat_sat );
								oParagraphTextElement.setText("Alici Tarafindan Tevkif Edilecek K.D.V.:");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
								//****
								ITextObject textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txtaltevedkdv2");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();


								oTextObject.setLeft((int) students.get(27).fat_sut);
								oTextObject.setTop((int) students.get(27).fat_sat );
								oParagraphTextElement.setText("0.00");
								oParagraphTextElement.setText(FATURA. label_1.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
							}

						}
						else  if (reportObject.getName().equals("txttevdahtoptut"))
						{
							if (students.get(28).fat_sut == 0 && students.get(28).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txttevdahtoptut2"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("Tevkifat Dahil Toplam Tutar :",baslikFont) * 21);
								oTextObject.setLeft((int) students.get(28).fat_sut -    (oTextObject.getWidth() + 200 ));
								oTextObject.setTop((int) students.get(28).fat_sat );
								oParagraphTextElement.setText("Tevkifat Dahil Toplam Tutar :");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
								//****
								ITextObject textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txttevdahtoptut2");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();


								oTextObject.setLeft((int) students.get(28).fat_sut );
								oTextObject.setTop((int) students.get(28).fat_sat );
								oParagraphTextElement.setText("0.00");
								oParagraphTextElement.setText(FATURA. label_2.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);

							}
						}
						else  if (reportObject.getName().equals("txtbeyanedilkdv"))
						{
							if (students.get(29).fat_sut == 0 && students.get(29).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txtbeyanedilkdv2"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("Beyan Edilecek K.D.V.:",baslikFont) * 21);
								oTextObject.setLeft((int) students.get(29).fat_sut -    (oTextObject.getWidth() + 200 ));
								oTextObject.setTop((int) students.get(29).fat_sat );
								oParagraphTextElement.setText("Beyan Edilecek K.D.V.:");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
								//****
								ITextObject textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txtbeyanedilkdv2");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();


								oTextObject.setLeft((int) students.get(29).fat_sut );
								oTextObject.setTop((int) students.get(29).fat_sat );
								oParagraphTextElement.setText("0.00");
								oParagraphTextElement.setText(FATURA. lblNewLabel_20.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);

							}
						}
						else  if (reportObject.getName().equals("txttevhartoptup"))
						{
							if (students.get(30).fat_sut == 0 && students.get(30).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txttevhartoptup2"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								IFontColor newFontColor ;
								oTextObject.setWidth(CRY_TEXT_WIDTH.uzunluk("Tevkifat Haric Toplam Tutar :",baslikFont) * 21);
								oTextObject.setLeft((int) students.get(30).fat_sut -    (oTextObject.getWidth() + 200 ));
								oTextObject.setTop((int) students.get(30).fat_sat );
								oParagraphTextElement.setText("Tevkifat Haric Toplam Tutar :");
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
								//****
								ITextObject textObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("txttevhartoptup2");
								oTextObject =null ;
								oTextObject =  (ITextObject) textObject.clone(true);
								oParagraphs = new Paragraphs();
								oParagraph = new Paragraph();
								oParagraph.setAlignment(Alignment.right);
								oParagraphElements = new ParagraphElements();
								oParagraphTextElement = new ParagraphTextElement();


								oTextObject.setLeft((int) students.get(30).fat_sut);
								oTextObject.setTop((int) students.get(30).fat_sat );
								oParagraphTextElement.setText("0.00");
								oParagraphTextElement.setText(FATURA. label.getText());
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(altFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(textObject, oTextObject);
							}
						}



						//***************
						else  if (reportObject.getName().equals("txtyaziile"))
						{
							if (students.get(31).fat_sut == 0 && students.get(31).fat_sat == 0 )
							{
								clientDoc.getReportDefController().getReportObjectController().remove( (ITextObject) reportObject);
								clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("txttevhartoptup2"));
							}
							else
							{
								ITextObject oTextObject =  (ITextObject) reportObject.clone(true);
								Paragraphs oParagraphs = new Paragraphs();
								Paragraph oParagraph = new Paragraph();
								ParagraphElements oParagraphElements = new ParagraphElements();
								ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
								oTextObject.setLeft((int) students.get(31).fat_sut);
								oTextObject.setTop((int) students.get(31).fat_sat );
								oParagraphTextElement.setText("");
								sayiyiYaziyaCevir cevir = new sayiyiYaziyaCevir();
								double aqw =DecimalFormat.getNumberInstance().parse(FATURA.label.getText()).doubleValue();
								String qwe = Double.toString(aqw);
								String cnt  = "" ;
								if ( FATURA.txtdoviz.getText().equals(GLOBAL.setting_oku("PRG_PARA").toString()))
								{
									cnt = "KURUŞ" ;
								}
								else
								{
									cnt = "CENT" ;
								}
								String yaziyla= cevir.sayiyiYaziyaCevir(qwe, 2,FATURA.txtdoviz.getText() , cnt , "#", null, null, null);
								oParagraphTextElement.setText(yaziyla);
								oParagraphTextElement.setKind(ParagraphElementKind.text);
								oParagraphs.add(oParagraph);
								oParagraph.setParagraphElements(oParagraphElements);
								oParagraphElements.add(oParagraphTextElement);
								oTextObject.setParagraphs(oParagraphs);
								IFontColor newFontColor ;
								newFontColor = oTextObject.getFontColor();
								newFontColor.setFont(yileFont);
								oTextObject.setFontColor(newFontColor);
								clientDoc.getReportDefController().getReportObjectController().modify(reportObject, oTextObject);
							}
						}
					}
				}

				//************************************ TEVKIFAT ****************************************************************************
				if ( FATURA.chckbxNewCheckBox_1.isSelected())

				{
					deger = GLOBAL.setting_oku("STK_FAT_BASLIK").toString();
					deger = deger.substring(1, deger.length()-1);
					parts = deger.split(",");
					baslikFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));

					ITextObject oTextObject = (ITextObject) clientDoc.getReportDefController().findObjectByName("Text12");
					// ITextObject oTextObject =  (ITextObject) clientDoc.getReportDefController().getReportObjectController().getAllReportObjects().getReportObject(51).clone(true);
					Paragraphs oParagraphs = new Paragraphs();
					Paragraph oParagraph = new Paragraph();
					ParagraphElements oParagraphElements = new ParagraphElements();
					ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
					IFontColor newFontColor ;
					// oTextObject.setLeft(500); 
					// oTextObject.setTop(100);
					oParagraphTextElement.setText("");
					oParagraphTextElement.setText(FATURA. textField_11.getText());
					oParagraphTextElement.setKind(ParagraphElementKind.text);
					oParagraphs.add(oParagraph);
					oParagraph.setParagraphElements(oParagraphElements);
					oParagraphElements.add(oParagraphTextElement);
					oTextObject.setParagraphs(oParagraphs);
					newFontColor = oTextObject.getFontColor();
					newFontColor.setFont(baslikFont);
					oTextObject.setFontColor(newFontColor);
					// clientDoc.getReportDefController().getReportObjectController().modify(clientDoc.getReportDefController().getReportObjectController().getAllReportObjects().getReportObject(51), oTextObject);
					clientDoc.getReportDefController().getReportObjectController().modify((ITextObject) clientDoc.getReportDefController().findObjectByName("Text12"), oTextObject);
					//*********
					oTextObject =null;
					oTextObject =  (ITextObject) clientDoc.getReportDefController().findObjectByName("Text14").clone(true);
					// oTextObject =  (ITextObject) clientDoc.getReportDefController().getReportObjectController().getAllReportObjects().getReportObject(53).clone(true);
					oParagraphs = new Paragraphs();
					oParagraph = new Paragraph();
					oParagraphElements = new ParagraphElements();
					oParagraphTextElement = new ParagraphTextElement();

					// oTextObject.setLeft(500); 
					// oTextObject.setTop(100);
					oParagraphTextElement.setText("");
					oParagraphTextElement.setText(FATURA. textField_12.getText());
					oParagraphTextElement.setKind(ParagraphElementKind.text);
					oParagraphs.add(oParagraph);
					oParagraph.setParagraphElements(oParagraphElements);
					oParagraphElements.add(oParagraphTextElement);
					oTextObject.setParagraphs(oParagraphs);
					newFontColor = oTextObject.getFontColor();
					newFontColor.setFont(baslikFont);
					oTextObject.setFontColor(newFontColor);
					//  clientDoc.getReportDefController().getReportObjectController().modify(clientDoc.getReportDefController().getReportObjectController().getAllReportObjects().getReportObject(53), oTextObject);
					clientDoc.getReportDefController().getReportObjectController().modify(clientDoc.getReportDefController().findObjectByName("Text14"), oTextObject);

				}
				else
				{
					clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("Text9"));
					clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("Text10"));
					clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("Text11"));
					clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("Text12"));
					clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("Text13"));
					clientDoc.getReportDefController().getReportObjectController().remove(clientDoc.getReportDefController().findObjectByName("Text14"));
				}
				//********************************************************************************************************************************    	                	 
				/// IFieldObject fieldObject = (IFieldObject) reportObject;    
				// IFontColor fontColor = fieldObject.getFontColor();
				// fontColor = newFontColor;
				// fieldObject.setFontColor(fontColor);




			}
			reportViewer.setReportSource(clientDoc.getReportSource());
			reportViewer.init();
			reportViewer.start();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getCause() + "==" + ex,  "Yazici Islem", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void doldur()
	{
		try {

			for (int i = 1; i <= 44;i++)
			{
				Degisken irs1 = new Degisken();
				irs1.irs_sut = 0 ;
				irs1.irs_sat = 0;
				irs1.fat_sut =0 ;
				irs1.fat_sat = 0;
				students.add(irs1);
			}
			ResultSet rs =null ;
			//***** Fatura SUTUN
			rs =null ;

			rs = fa_Access.parametre_oku("FAT_EVRAK_FORMAT","SUTUN");

			rs.next();
			ResultSetMetaData rsmd = rs.getMetaData();
			rsmd = rs.getMetaData();
			for (int i = 0 ; i <= rsmd.getColumnCount()  - 3;i++)
			{
				students.get(i).fat_sut= rs.getDouble(i+2);
			}
			//***Fatura SATIR
			rs =null ;

			rs = fa_Access.parametre_oku("FAT_EVRAK_FORMAT","SATIR");

			rs.next();
			rsmd = rs.getMetaData();
			for (int i =0 ; i <= rsmd.getColumnCount()  - 3;i++)
			{
				students.get(i).fat_sat= rs.getDouble(i+2);
			}

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Evrak Formatlama", JOptionPane.PLAIN_MESSAGE);
		}
	}
}

//*****************
//com.crystaldecisions.sdk.occa.report.definition.ReportObjects qwe = clientDoc.getReportDefController().getReportObjectController().getReportObjectsByKind(ReportObjectKind.field);
 //for(int i=0; i< qwe.size();i++)
//	{
//	 IFieldObject qaz = (IFieldObject)qwe.get(i);
//	 if (qaz.getName().toString().contentEquals("HESAPHESAP"))
//	 {
//		 System.out.println(qaz.getLeft() + "=" + qaz.getTop()  );
//	 }
//	}
//***************

