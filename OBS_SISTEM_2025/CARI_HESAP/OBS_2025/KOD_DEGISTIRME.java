package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class KOD_DEGISTIRME extends JInternalFrame {
	
	private static JTextField textField_1;
	private static JLabel lblNewLabel ;
	private static JLabel lblNewLabel_1 ;
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KOD_DEGISTIRME frame = new KOD_DEGISTIRME();
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
	public KOD_DEGISTIRME() {
		setTitle("KOD DEGISTIRME");
		setClosable(true);
		setBounds(5, 5, 606, 187);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Aranacak", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));

		panel_1.setBounds(34, 21, 250, 74);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		lblNewLabel = new JLabel("...");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(28, 49, 212, 14);
		panel_1.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.show();
						textField_2.setText( GLOBAL.hsp_hsp_kodu);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_2.setBounds(28, 21, 150, 20);
		textField_2.setDocument(new JTextFieldLimit(12));
		textField_2.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
				lblNewLabel.setText(CARI_ISIM_OKU.isim(textField_2.getText())[0]);
				getContentPane().setCursor(DEFAULT_CURSOR);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
					lblNewLabel.setText(CARI_ISIM_OKU.isim(textField_2.getText())[0]);
				getContentPane().setCursor(DEFAULT_CURSOR);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
		
					lblNewLabel.setText(CARI_ISIM_OKU.isim(textField_2.getText())[0]);
				
				getContentPane().setCursor(DEFAULT_CURSOR);
				
			}
			
		});
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Yazilacak", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1_1.setBounds(313, 21, 250, 74);
		panel.add(panel_1_1);
		
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.show();
						textField_1.setText( GLOBAL.hsp_hsp_kodu);
						//getContentPane().setCursor(WAIT_CURSOR);
						//lblNewLabel_1.setText(isimoku(textField_1.getText()));
						//getContentPane().setCursor(DEFAULT_CURSOR);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				}
			});
		
		
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(28, 21, 150, 20);
		textField_1.setDocument(new JTextFieldLimit(12));
		textField_1.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
			
					lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
					getContentPane().setCursor(DEFAULT_CURSOR);

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);

					lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
					getContentPane().setCursor(DEFAULT_CURSOR);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
					lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
					getContentPane().setCursor(DEFAULT_CURSOR);
			}
		
		});
		panel_1_1.add(textField_1);
		
		lblNewLabel_1 = new JLabel("...");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setBounds(28, 49, 212, 14);
		panel_1_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Kaydet");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hisset();
			}
		});
		btnNewButton.setIcon(new ImageIcon(KOD_DEGISTIRME.class.getResource("/ICONLAR/icons8-ok-16.png")));
		btnNewButton.setBounds(34, 106, 529, 31);
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
    	        getContentPane().setCursor(WAIT_CURSOR);
	        try
	        {
	        	if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
	 		    {
	            	 oac.cARI_HESAP_MSSQL.cari_kod_degis_hesap(textField_2.getText(), textField_1.getText());
	            	 oac.cARI_HESAP_MSSQL.cari_kod_degis_satirlar(textField_2.getText(), textField_1.getText());		
	 		    }
	 		    else
	 		    {
	 		    	oac.cARI_HESAP_MYSQL.cari_kod_degis_hesap(textField_2.getText(), textField_1.getText());
	 		    	oac.cARI_HESAP_MYSQL.cari_kod_degis_satirlar(textField_2.getText(), textField_1.getText());
 		    	}
		            getContentPane().setCursor(DEFAULT_CURSOR);
		            JOptionPane.showMessageDialog(null, "Islem Basari ile tamamlandi...." );
		        }
		        catch ( Exception ex)
		        {
		        	getContentPane().setCursor(DEFAULT_CURSOR);
		            JOptionPane.showMessageDialog(null, ex.getMessage() ); 
		        }
	}
	
}
