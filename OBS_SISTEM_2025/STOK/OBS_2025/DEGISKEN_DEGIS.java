package OBS_2025;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import OBS_C_2025.STOK_ACCESS;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DEGISKEN_DEGIS extends JInternalFrame {
	
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);

	private static JPanel panel;
	private static JComboBox<String> cmbAna ;
	private static JComboBox<String> cmbAlt ;
	private static JComboBox<String> cmbyAna;
	private static JComboBox<String> cmbyAlt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DEGISKEN_DEGIS frame = new DEGISKEN_DEGIS();
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
	public DEGISKEN_DEGIS() {
		setTitle("DEGISKEN YENILEME");
		setClosable(true);
		setBounds(0,0, 430, 249);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Aranacak", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(35, 20, 340, 84);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		cmbAna = new JComboBox<String>();
		cmbAna.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbAna.setForeground(new Color(0, 0, 128));
		cmbAna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(cmbAna,cmbAlt);
			}
		});
		cmbAna.setBounds(110, 22, 203, 22);
		panel_1.add(cmbAna);
		
		cmbAlt = new JComboBox<String>();
		cmbAlt.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbAlt.setForeground(new Color(0, 0, 128));
		cmbAlt.setEnabled(false);
		cmbAlt.setBounds(110, 51, 203, 22);
		panel_1.add(cmbAlt);
		
		JLabel lblNewLabel = new JLabel("Ana Grup");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 26, 73, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblAltGrup = new JLabel("Alt Grup");
		lblAltGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltGrup.setBounds(10, 55, 73, 14);
		panel_1.add(lblAltGrup);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Yazilacak", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1_1.setBounds(35, 115, 340, 84);
		panel.add(panel_1_1);
		
		cmbyAna = new JComboBox<String>();
		cmbyAna.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbyAna.setForeground(new Color(0, 0, 128));
		cmbyAna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur(cmbyAna,cmbyAlt);
			}
		});
		cmbyAna.setBounds(110, 22, 203, 22);
		panel_1_1.add(cmbyAna);
		
		cmbyAlt = new JComboBox<String>();
		cmbyAlt.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbyAlt.setForeground(new Color(0, 0, 128));
		cmbyAlt.setEnabled(false);
		cmbyAlt.setBounds(110, 51, 203, 22);
		panel_1_1.add(cmbyAlt);
		
		JLabel lblNewLabel_1 = new JLabel("Ana Grup");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 26, 73, 14);
		panel_1_1.add(lblNewLabel_1);
		
		JLabel lblAltGrup_1 = new JLabel("Alt Grup");
		lblAltGrup_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltGrup_1.setBounds(10, 55, 73, 14);
		panel_1_1.add(lblAltGrup_1);

		ana_grup_doldur();
		alt_grup_doldur(cmbAna,cmbAlt);
		alt_grup_doldur(cmbyAna,cmbyAlt);
	}
	public static void kaydet()
	{
		try {
		if (cmbAna.getSelectedItem().toString().equals("")) return;
		if (cmbAlt.getSelectedItem().toString().equals("")) return;
		//if (cmbyAna.getSelectedItem().toString().equals("")) return;
		//if (cmbyAlt.getSelectedItem().toString().equals("")) return;
	
		if (cmbAna.getSelectedItem().toString().equals(cmbyAna.getSelectedItem().toString()) &&  cmbAlt.getSelectedItem().toString().equals(cmbyAlt.getSelectedItem().toString()))
			{
			JOptionPane.showMessageDialog(null, "Aranacak ve Yazilacak Degiskenler Ayni",  "Degisken Yenileme", JOptionPane.INFORMATION_MESSAGE);   
			return;
			}
		  GuiUtil.setWaitCursor(DEGISKEN_DEGIS.panel,true);
		//*************************
        int anagrp =0,altgrp =0,yanagrp =0,yaltgrp = 0 ;
    	ResultSet rs =null ;
    	ResultSet yrs =null ;
        			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbAna.getItemAt(cmbAna.getSelectedIndex()).toString());
        			yrs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbyAna.getItemAt(cmbyAna.getSelectedIndex()).toString());
        		if (!rs.isBeforeFirst() ) {      		
    	    	}
    	    	else
    	    	{
    	    		rs.next();
	        		anagrp  = rs.getInt("AGID_Y");
    	    	}
        		if (!yrs.isBeforeFirst() ) {      		
    	    	}
    	    	else
    	    	{
    	    		yrs.next();
	        		yanagrp  = yrs.getInt("AGID_Y");
    	    	}
     	    		rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbAlt.getItemAt(cmbAlt.getSelectedIndex()).toString());
     	    		yrs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbyAlt.getItemAt(cmbyAlt.getSelectedIndex()).toString());
     	      	if (!rs.isBeforeFirst() ) {      		
    	    	}
    	    	else
    	    	{
    	    		rs.next();
	     	      	altgrp  = rs.getInt("ALID_Y");
    	    	}
     	      	if (!yrs.isBeforeFirst() ) {      		
    	    	}
    	    	else
    	    	{
    	    		yrs.next();
	     	      	yaltgrp  = yrs.getInt("ALID_Y");
    	    	}
    	    		f_Access.degisken_degistir(anagrp,altgrp,yanagrp,yaltgrp);
  	//*************************
     	       GuiUtil.setWaitCursor(DEGISKEN_DEGIS.panel,false);
    	      	JOptionPane.showMessageDialog(null, "Degisim Tamamlandi.................",  "Degisken Yenileme", JOptionPane.PLAIN_MESSAGE);   
		}
		catch (Exception ex)
		{
		    GuiUtil.setWaitCursor(DEGISKEN_DEGIS.panel,false);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Degisken Yenileme", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void ana_grup_doldur()
	{
		try {
		getContentPane().setCursor(oac.WAIT_CURSOR);
		cmbAna .removeAllItems();
		cmbyAna .removeAllItems();
		ResultSet rs=null;
			rs = f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
		if (!rs.isBeforeFirst() ) {  
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			cmbAlt.setEnabled(false);
			cmbyAlt.setEnabled(false);
			cmbAna .addItem("");
			cmbyAna .addItem("");
			cmbAna.setSelectedItem("");
			cmbyAna.setSelectedItem("");
		    return;
		} 
		cmbAna .addItem("");
		cmbyAna .addItem("");
	    while (rs.next())
	    {
	    	cmbAna .addItem(rs.getString("ANA_GRUP"));
	    	cmbyAna .addItem(rs.getString("ANA_GRUP"));
	    }
	    getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ana Grup", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void alt_grup_doldur(JComboBox<String> anabox,JComboBox<String> altbox)
	{
		try {
		getContentPane().setCursor(oac.WAIT_CURSOR);
		altbox.removeAllItems();

		altbox .addItem("");
		ResultSet rs=null;
			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", anabox.getItemAt(anabox.getSelectedIndex()).toString());
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				int in1 = rs.getInt("AGID_Y");
				rs =null;
				rs = f_Access.stk_kod_alt_grup_degisken_oku(in1);
			}
	
		if (!rs.isBeforeFirst() ) {  
			altbox.setSelectedItem("");
			altbox.setEnabled(false);
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		} 
		else
		{
	    while (rs.next())
	    {
	    	altbox .addItem(rs.getString("ALT_GRUP"));
	    }
	    altbox.setSelectedItem(0);
	    altbox.setEnabled(true);
		}
		getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);    	
		}
	}

}
