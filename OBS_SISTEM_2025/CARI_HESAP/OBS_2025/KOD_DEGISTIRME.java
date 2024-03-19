package OBS_2025;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;


import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

@SuppressWarnings({"serial" , "static-access"})
public class KOD_DEGISTIRME extends JInternalFrame {
	
	private static Obs_TextFIeld textField_1;
	private static JLabel lblNewLabel ;
	private static JLabel lblNewLabel_1 ;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	private Obs_TextFIeld textField_2;

	public KOD_DEGISTIRME() {
		setTitle("KOD DEGISTIRME");
		setClosable(true);
		setBounds(5, 5, 606, 187);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(KOD_DEGISTIRME.class.getResource("/ICONLAR/icons8-exchange-30.png")), 16, 16));//
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Aranacak", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		panel_1.setBounds(34, 21, 250, 74);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("...");
		lblNewLabel.setBounds(28, 49, 212, 14);
		panel_1.add(lblNewLabel);
		
		textField_2 = new Obs_TextFIeld(12,"");
		textField_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_2.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_2.setBounds(28, 21, 150, 20);
		textField_2.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel.setText(CARI_ISIM_OKU.isim(textField_2.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
					lblNewLabel.setText(CARI_ISIM_OKU.isim(textField_2.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel.setText(CARI_ISIM_OKU.isim(textField_2.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Yazilacak", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_1_1.setBounds(313, 21, 250, 74);
		panel.add(panel_1_1);
		
		textField_1 = new Obs_TextFIeld(12,"");
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_1.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(28, 21, 150, 20);
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		panel_1_1.add(textField_1);
		
		lblNewLabel_1 = new JLabel("...");
		lblNewLabel_1.setBounds(28, 49, 212, 14);
		panel_1_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Kaydet");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hisset();
			}
		});
		btnNewButton.setIcon(new ImageIcon(KOD_DEGISTIRME.class.getResource("/ICONLAR/save.png")));
		btnNewButton.setBounds(34, 106, 529, 30);
		panel.add(btnNewButton);

	}
	private void hisset()
	{
		if (textField_2.getText().equals("") || textField_1.getText().equals("") )
		{
			textField_2.requestFocus();
			return;
		}
		int g = JOptionPane.showOptionDialog( null,  "Hesap Kodlari Degisecek...?", "Cari Dosyasindan Kod Degisme ",   JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
		if(g != 0 ) { return;	}	
		getContentPane().setCursor(oac.WAIT_CURSOR);
		try
		{
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Kod Degistirme   Eski Kod:" + textField_2.getText()+
					" Yeni Kod:"+textField_1.getText());
			lBILGI.seteVRAK("");
			c_Access.cari_kod_degis_hesap(textField_2.getText(), textField_1.getText()
					,lBILGI,BAGLAN_LOG.cariLogDizin);
			c_Access.cari_kod_degis_satirlar(textField_2.getText(), textField_1.getText());
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO,"Islem Basari ile tamamlandi...."  );
		}
		catch ( Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
}
