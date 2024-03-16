package OBS_2025;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import OBS_C_2025.Obs_TextFIeld;
import raven.toast.Notifications;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({"serial","static-access"})
public class BORC_ALACAK extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Obs_TextFIeld txtcari;
	private JLabel lblNewLabel_1 ;
	public JLabel lblNewLabel;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	public BORC_ALACAK() {
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				oac.hsp_hsp_kodu = "";
			}
		});
		setModal(true);
		setResizable(false);
		setTitle("HESAP");
		setBounds(100, 100, 406, 110);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		lblNewLabel = new JLabel("Hesap");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 15, 83, 14);
		contentPanel.add(lblNewLabel);

		txtcari = new Obs_TextFIeld(12);
		txtcari.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
				{
					if(CARI_ISIM_OKU.isim(txtcari.getText())[2].toString().equals("F"))
					{
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, "Bu Numarada Hesap Bulunamadi....." );
						return ;
					}
					oac.hsp_hsp_kodu = txtcari.getText();
					dispose();
				}
			}
		});
		txtcari.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				lblNewLabel_1.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
			}
			public void removeUpdate(DocumentEvent e) {
				lblNewLabel_1.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
			}
			public void insertUpdate(DocumentEvent e) {
				lblNewLabel_1.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
			}
		});
		txtcari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						txtcari.setText( oac.hsp_hsp_kodu);
						lblNewLabel_1.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		txtcari.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtcari.setBounds(100, 11, 124, 20);
		contentPanel.add(txtcari);
		txtcari.setColumns(10);

		lblNewLabel_1 = new JLabel(".....");
		//lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(100, 40, 285, 14);
		contentPanel.add(lblNewLabel_1);
	}

}
