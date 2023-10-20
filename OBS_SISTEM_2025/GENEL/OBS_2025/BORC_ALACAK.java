package OBS_2025;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import OBS_C_2025.JTextFieldLimit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({"serial","static-access"})
public class BORC_ALACAK extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtcari;
	private JLabel lblNewLabel_1 ;
	public JLabel lblNewLabel;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	public static void main(String[] args) {
		try {
			BORC_ALACAK dialog = new BORC_ALACAK();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public BORC_ALACAK() {
		setModal(true);
		setResizable(false);
		setTitle("HESAP");
		setBounds(100, 100, 406, 118);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblNewLabel = new JLabel("Hesap");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 29, 91, 14);
		contentPanel.add(lblNewLabel);

		txtcari = new JTextField();
		txtcari.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
				{
					if(CARI_ISIM_OKU.isim(txtcari.getText())[2].toString().equals("F")) return ;
					oac.hsp_hsp_kodu = txtcari.getText();
					dispose();
				}
			}
		});
		txtcari.setDocument(new JTextFieldLimit(12));
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
		txtcari.setBounds(100, 25, 124, 20);
		contentPanel.add(txtcari);
		txtcari.setColumns(10);

		lblNewLabel_1 = new JLabel(".....");
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(100, 50, 290, 14);
		contentPanel.add(lblNewLabel_1);
	}

}
