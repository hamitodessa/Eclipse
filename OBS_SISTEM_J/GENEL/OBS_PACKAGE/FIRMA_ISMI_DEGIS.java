package OBS_PACKAGE;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;

public class FIRMA_ISMI_DEGIS extends JInternalFrame {

	private static String modul ;
	private static JTextField textField;
	private static JPanel panel_1;
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS() ;
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
			if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
		    {
				textField.setText(oac.cARI_HESAP_MSSQL.cari_firma_adi());
		    }
           	else
           	{
           		textField.setText(oac.cARI_HESAP_MYSQL.cari_firma_adi());
           	}
		}
		else if (modul.equals("kambiyo"))
		{
			if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
		    {
				textField.setText(oac.kAMBIYO_MSSQL.kam_firma_adi());
		    }
           	else
           	{
           		textField.setText(oac.kAMBIYO_MYSQL.kam_firma_adi());
           	}
		}
		else if (modul.equals("stok"))
		{
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    {
				textField.setText(oac.sTOK_MSSQL.fat_firma_adi());
		    }
           	else
           	{
           		textField.setText(oac.sTOK_MYSQL.fat_firma_adi());
           	}
		}
		else if (modul.equals("adres"))
		{
			if (CONNECTION.adrdizinbilgi.han_sql.equals("MS SQL"))
		    {
				textField.setText(oac.aDRES_MSSQL.adr_firma_adi());
		    }
           	else
           	{
           		textField.setText(oac.aDRES_MYSQL.adr_firma_adi());
           	}
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
			if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
		    {
				oac.cARI_HESAP_MSSQL.cari_firma_adi_kayit(textField.getText());
		    }
           	else
           	{
           		oac.cARI_HESAP_MYSQL.cari_firma_adi_kayit(textField.getText());
           	}
			CONNECTION.caridizinbilgi.firma_adi = textField.getText();
		    OBS_MAIN.lblNewLabel_1.setText ( "Cari:" + CONNECTION.caridizinbilgi.kod + "/ " + CONNECTION.caridizinbilgi.firma_adi + "/ " + (CONNECTION.caridizinbilgi.yer == "S" ?  CONNECTION.caridizinbilgi.server : "Lokal"));

		}
		else if (modul.equals("kambiyo"))
		{
			if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
		    {
				oac.kAMBIYO_MSSQL.kam_firma_adi_kayit(textField.getText());
		    }
           	else
           	{
           		oac.kAMBIYO_MYSQL.kam_firma_adi_kayit(textField.getText());
           	}
			CONNECTION.kamdizinbilgi.firma_adi = textField.getText();
		    OBS_MAIN.lblNewLabel_23.setText ( "Kambiyo:" + CONNECTION.kamdizinbilgi.kod + "/ " + CONNECTION.kamdizinbilgi.firma_adi + "/ " + (CONNECTION.kamdizinbilgi.yer == "S" ?  CONNECTION.kamdizinbilgi.server : "Lokal"));
		}
		else if (modul.equals("stok"))
		{
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    {
				oac.sTOK_MSSQL.stk_firma_adi_kayit(textField.getText());
		    }
           	else
           	{
           		oac.sTOK_MYSQL.stk_firma_adi_kayit(textField.getText());
           	}
			CONNECTION.fatdizinbilgi.firma_adi = textField.getText();
		    OBS_MAIN.lblNewLabel_23.setText ( "Stok:" + CONNECTION.fatdizinbilgi.kod + "/ " + CONNECTION.fatdizinbilgi.firma_adi + "/ " + (CONNECTION.fatdizinbilgi.yer == "S" ?  CONNECTION.fatdizinbilgi.server : "Lokal"));
		}
		else if (modul.equals("adres"))
		{
			if (CONNECTION.adrdizinbilgi.han_sql.equals("MS SQL"))
		    {
				oac.aDRES_MSSQL.adr_firma_adi_kayit(textField.getText());
		    }
           	else
           	{
           		oac.aDRES_MYSQL.adr_firma_adi_kayit(textField.getText());
           	}
			CONNECTION.adrdizinbilgi.firma_adi = textField.getText();
		    OBS_MAIN.lblNewLabel_22.setText ( "Adres:" + CONNECTION.adrdizinbilgi.kod + "/ " + CONNECTION.adrdizinbilgi.firma_adi + "/ " + (CONNECTION.adrdizinbilgi.yer == "S" ?  CONNECTION.adrdizinbilgi.server : "Lokal"));
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
