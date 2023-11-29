package obs.obs_fihrist.other;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.ScrollPaneWin11;
import obs.obs_fihrist.OBS_FIHRIST;
import raven.toast.Notifications;

@SuppressWarnings("serial")
public class FormFihrist  extends javax.swing.JPanel {

	private JLabel lblSatir ;
	private JLabel lblbilgi;
	private JTable table;
	private JTextField textField;
	private static JTextField txtcd;
	private static JTextField txtAdi;
	private static JTextField txtT1;
	private static JTextField txtT2;
	private static JTextField txtT3;
	private static JTextField txtT4;
	private static JTextField txtFax;
	private static JTextField txtMail;
	private static JTextField txtNot;
	private static JTextField txtNot2;
	 public FormFihrist() {
		 
		setLayout(new BorderLayout(0, 0));
		add(new Title_Bar(), BorderLayout.NORTH);
		 
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		 
		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		scrollPane_1.setMinimumSize(new Dimension(0, 170));
		scrollPane_1.setMaximumSize(new Dimension(0, 170));
		splitPane.setLeftComponent(scrollPane_1);
		
		JPanel panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 33, 855, 5);
		panel_2.add(separator);

		JLabel lblNewLabel = new JLabel("Adi");
		lblNewLabel.setBounds(10, 11, 48, 14);
		panel_2.add(lblNewLabel);

		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			   // arama();
			  }
			  public void removeUpdate(DocumentEvent e) {
			   // arama();
			  }
			  public void insertUpdate(DocumentEvent e) {
			   // arama();
			  }
			});
		textField.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorMoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorAdded(AncestorEvent pEvent) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						textField.requestFocusInWindow();
					}
				});
			}
		});

		textField.setBounds(68, 7, 372, 20);
		panel_2.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Adi");
		lblNewLabel_1.setBounds(10, 46, 48, 14);
		panel_2.add(lblNewLabel_1);

		txtAdi = new JTextField();
		txtAdi.setDocument(new JTextFieldLimit(50));
		txtAdi.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAdi.setBounds(68, 43, 372, 20);
		panel_2.add(txtAdi);
		txtAdi.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tel_1");
		lblNewLabel_2.setBounds(10, 75, 48, 14);
		panel_2.add(lblNewLabel_2);

		txtT1 = new JTextField();
		txtT1.setDocument(new JTextFieldLimit(25));
		txtT1.setBounds(68, 72, 148, 20);
		panel_2.add(txtT1);
		txtT1.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Tel_2");
		lblNewLabel_2_1.setBounds(234, 72, 48, 14);
		panel_2.add(lblNewLabel_2_1);

		txtT2 = new JTextField();
		txtT2.setDocument(new JTextFieldLimit(25));
		txtT2.setColumns(10);
		txtT2.setBounds(292, 69, 148, 20);
		panel_2.add(txtT2);

		JLabel lblNewLabel_2_2 = new JLabel("Tel_3");
		lblNewLabel_2_2.setBounds(450, 72, 48, 14);
		panel_2.add(lblNewLabel_2_2);

		txtT3 = new JTextField();
		txtT3.setDocument(new JTextFieldLimit(25));
		txtT3.setColumns(10);
		txtT3.setBounds(502, 69, 148, 20);
		panel_2.add(txtT3);

		JLabel lblNewLabel_2_3 = new JLabel("Tel_4");
		lblNewLabel_2_3.setBounds(660, 72, 48, 14);
		panel_2.add(lblNewLabel_2_3);

		txtT4 = new JTextField();
		txtT4.setDocument(new JTextFieldLimit(25));
		txtT4.setColumns(10);
		txtT4.setBounds(718, 69, 148, 20);
		panel_2.add(txtT4);

		JLabel lblNewLabel_2_4 = new JLabel("Fax");
		lblNewLabel_2_4.setBounds(10, 101, 48, 14);
		panel_2.add(lblNewLabel_2_4);

		txtFax = new JTextField();
		txtFax.setDocument(new JTextFieldLimit(25));
		txtFax.setColumns(10);
		txtFax.setBounds(68, 98, 148, 20);
		panel_2.add(txtFax);

		JLabel lblNewLabel_2_5 = new JLabel("Mail");
		lblNewLabel_2_5.setBounds(234, 100, 48, 14);
		panel_2.add(lblNewLabel_2_5);

		txtMail = new JTextField();
		txtMail.setDocument(new JTextFieldLimit(50));
		txtMail.setColumns(10);
		txtMail.setBounds(292, 97, 360, 20);
		panel_2.add(txtMail);

		JLabel lblNewLabel_2_6 = new JLabel("Not");
		lblNewLabel_2_6.setBounds(10, 129, 48, 14);
		panel_2.add(lblNewLabel_2_6);

		txtNot = new JTextField();
		txtNot.setDocument(new JTextFieldLimit(50));
		txtNot.setColumns(10);
		txtNot.setBounds(68, 126, 360, 20);
		panel_2.add(txtNot);
		
		JLabel lblNewLabel_2_6_1 = new JLabel("Not_2");
		lblNewLabel_2_6_1.setBounds(450, 129, 48, 14);
		panel_2.add(lblNewLabel_2_6_1);
		
		txtNot2 = new JTextField();
		txtNot2.setDocument(new JTextFieldLimit(50));
		txtNot2.setColumns(10);
		txtNot2.setBounds(508, 126, 360, 20);
		panel_2.add(txtNot2);
		
		txtcd = new JTextField();
		txtcd.setVisible(false);
		txtcd.setBounds(45, 148, 15, 15);
		panel_2.add(txtcd);
		txtcd.setColumns(10);
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		JSplitPane splitPanealt = new JSplitPane();
		splitPanealt.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPanealt.setDividerSize(0);
		splitPanealt.setResizeWeight(1.0);
		splitPane.setRightComponent(splitPanealt);
		
		JPanel panel_4 = new JPanel();
		panel_4.setMinimumSize(new Dimension(0, 30));
		panel_4.setMaximumSize(new Dimension(0, 30));

		splitPanealt.setRightComponent(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_2_7 = new JLabel("Satir Sayisi :");
		lblNewLabel_2_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_7.setBounds(10, 7, 85, 14);
		panel_4.add(lblNewLabel_2_7);
		
		lblSatir = new JLabel("0");
		lblSatir.setForeground(new Color(0, 0, 128));
		lblSatir.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSatir.setBounds(100, 7, 51, 14);
		panel_4.add(lblSatir);
		
		lblbilgi = new JLabel("...");
		lblbilgi.setBounds(174, 7, 300, 14);
		lblbilgi.setForeground(new Color(0, 0, 128));
		lblbilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4.add(lblbilgi);
		
		/////////// fihrist tablo
		
		
		ScrollPaneWin11 scrollPane_2 = new ScrollPaneWin11();
		splitPanealt.setLeftComponent(scrollPane_2);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (table.getRowCount() == 0) return ;
					if (table.getSelectedRow()  < 0) return;
					try {
						//fih_kutu_temizle();
						//fih_doldur_kutu(table,table.getSelectedRow());
					} catch (Exception e1) {
						OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
					}
				}
			}
		});		
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(table);

	 }
}
