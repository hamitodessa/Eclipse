package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import OBS_C_2025.BAGLAN;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;

public class FIRMA_ISMI_DEGIS extends JInternalFrame {

	private static String modul ;
	private static JTextField textField;
	private static JPanel panel_1;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS() ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FIRMA_ISMI_DEGIS frame = new FIRMA_ISMI_DEGIS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FIRMA_ISMI_DEGIS() {
		setClosable(true);
		setTitle("FIRMA ISMI DEGISTIRME");
		setBounds(100, 100, 663, 118);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Baslik", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(52, 11, 529, 64);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 128));
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBounds(53, 25, 423, 20);
		panel_1.add(textField);
		textField.setColumns(10);

	}
	public static void modul(String mod)
	{
		
		modul = mod ;
		if (modul.equals("cari"))
		{
			panel_1.setBorder(new TitledBorder(null, "Cari Hesap", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		isim_oku(mod);
		}
		else if (modul.equals("kambiyo"))
		{
			panel_1.setBorder(new TitledBorder(null, "Kambiyo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		isim_oku(mod);
		}
		else if (modul.equals("stok"))
		{
			panel_1.setBorder(new TitledBorder(null, "Stok", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		isim_oku(mod);
		}
		else if (modul.equals("adres"))
		{
			panel_1.setBorder(new TitledBorder(null, "Adres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		isim_oku(mod);
		}
	}
	private static void  isim_oku(String mod)
	{
		try
		{
		if (modul.equals("cari"))
		{
				textField.setText(oac._ICar.cari_firma_adi() );
		}
		else if (modul.equals("kambiyo"))
		{
				textField.setText(oac._IKambiyo.kam_firma_adi());
		}
		else if (modul.equals("stok"))
		{
           		textField.setText(oac._Istok.fat_firma_adi());
		}
		else if (modul.equals("adres"))
		{
           		textField.setText(oac._IAdres.adr_firma_adi());
		}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage()); 
		}
	}
	public static void kaydet()
	{
		try
		{
		if (modul.equals("cari"))
		{
			
           	oac._ICar.cari_firma_adi_kayit(textField.getText());
           	BAGLAN.cariDizin.fIRMA_ADI = textField.getText();
		    OBS_MAIN.lblNewLabel_1.setText ( "Cari:" + BAGLAN.cariDizin.kOD + "/ " + BAGLAN.cariDizin.fIRMA_ADI + "/ " + (BAGLAN.cariDizin.yER == "S" ?  BAGLAN.cariDizin.sERVER : "Lokal"));

		}
		else if (modul.equals("kambiyo"))
		{
			oac._IKambiyo.kam_firma_adi_kayit(textField.getText());
           	
        	BAGLAN.kamDizin.fIRMA_ADI = textField.getText();
		    OBS_MAIN.lblNewLabel_23.setText ( "Kambiyo:" + BAGLAN.kamDizin.kOD + "/ " + BAGLAN.kamDizin.fIRMA_ADI + "/ " + (BAGLAN.kamDizin.yER == "S" ?  BAGLAN.kamDizin.sERVER : "Lokal"));
		}
		else if (modul.equals("stok"))
		{
           	oac._Istok.stk_firma_adi_kayit(textField.getText());
           	BAGLAN.fatDizin.fIRMA_ADI = textField.getText();
		    OBS_MAIN.lblNewLabel_23.setText ( "Stok:" + BAGLAN.fatDizin.kOD + "/ " + BAGLAN.fatDizin.fIRMA_ADI + "/ " + (BAGLAN.fatDizin.yER == "S" ?  BAGLAN.fatDizin.sERVER : "Lokal"));
		}
		else if (modul.equals("adres"))
		{
           	oac._IAdres.adr_firma_adi_kayit(textField.getText());
           	BAGLAN.adrDizin.fIRMA_ADI = textField.getText();
		    OBS_MAIN.lblNewLabel_22.setText ( "Adres:" + 	BAGLAN.adrDizin.kOD + "/ " + 	BAGLAN.adrDizin.fIRMA_ADI + "/ " + (	BAGLAN.adrDizin.yER == "S" ?  	BAGLAN.adrDizin.sERVER : "Lokal"));
		}
		kapat();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage()); 
		}
	}
	private static void kapat()
	{
		for(int i=0;i<OBS_MAIN.desktopPane.getAllFrames().length;i++)
        {   
        JInternalFrame frame=(JInternalFrame) OBS_MAIN.desktopPane.getComponent(i);
        String tit = frame.getTitle();
        if (tit.equals("FIRMA ISMI DEGISTIRME") )
        	{
        	OBS_MAIN.desktopPane.remove(i);
    		OBS_MAIN.desktopPane.repaint();
        	}
        }
	
	}
}
