package obs.backup.other;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import OBS_C_2025.CheckListItem;
import OBS_C_2025.CheckListRenderer;
import OBS_C_2025.JTextFieldLimit;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

@SuppressWarnings({"rawtypes","unchecked","serial"})
public class EmirAnaGiris extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JPanel container ;
	
	private static JTextField txtEmir;
	public JTextArea textAciklama;
	private JButton btnServer ;
	private JButton btnSurucuSec ;
	private JButton btnDosyaSec ;
	
	public static JCheckBox chckbxDurum;
	public JCheckBox chckbxServerDosya ;
	
	private static JLabel lblNewLabel_6 ;
	private static JLabel lblNewLabel_5;



	public static JList list;
	public static DefaultListModel<CheckListItem> model ;
	/**
	 * Create the panel.
	 */
	
	
	public EmirAnaGiris() {
		setLayout(new BorderLayout(0, 0));
		JPanel panel_4 = new JPanel();
		add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_2 = new JSplitPane();
		panel_4.add(splitPane_2, BorderLayout.CENTER);
		
		
		JPanel panel_10 = new JPanel();
		splitPane_2.setRightComponent(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Durum");
		lblNewLabel.setBounds(26, 68, 86, 14);
		panel_10.add(lblNewLabel);
		
		chckbxDurum = new JCheckBox("");
		chckbxDurum.setBounds(150, 68, 99, 23);
		panel_10.add(chckbxDurum);
		
		JLabel lblNewLabel_1 = new JLabel("Emir Ismi");
		lblNewLabel_1.setBounds(26, 100, 99, 14);
		panel_10.add(lblNewLabel_1);
		
		txtEmir = new JTextField();
		txtEmir.setBounds(150, 100, 219, 20);
		panel_10.add(txtEmir);
		txtEmir.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Aciklama");
		lblNewLabel_2.setBounds(26, 150, 99, 14);
		panel_10.add(lblNewLabel_2);
		
		textAciklama = new JTextArea();
		textAciklama.setBounds(150, 150, 219, 77);
		textAciklama.setDocument(new JTextFieldLimit(50));
		//textAciklama.setBorder(txtEmir.getBorder());
		//Border borderr = BorderFactory.createLineBorder(new Color(235,235,235));
		//textAciklama.setBorder(BorderFactory.createCompoundBorder(borderr, null));
		panel_10.add(textAciklama);
		
		
		chckbxServerDosya = new JCheckBox("Sql Server / Diger Dosya Yedekleme");
		chckbxServerDosya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxServerDosya.isSelected())
				{
					btnServer.setVisible(true);
					btnDosyaSec.setVisible(false);
					btnSurucuSec.setVisible(false);
				}
				else {
					btnServer.setVisible(false);
					btnDosyaSec.setVisible(true);
					btnSurucuSec.setVisible(true);
				}
			}
		});
		chckbxServerDosya.setToolTipText("");
		chckbxServerDosya.setBounds(150, 302, 250, 23);
		panel_10.add(chckbxServerDosya);
		
		JLabel lblNewLabel_4 = new JLabel("Dosya Sayisi");
		lblNewLabel_4.setBounds(26, 373, 100, 14);
		panel_10.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setBounds(150, 373, 48, 14);
		panel_10.add(lblNewLabel_5);
		
		JButton btnNewButton_4 = new JButton("Kayit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.genelKayit();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(317, 579, 89, 23);
		panel_10.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cikis");
		btnNewButton_5.setBounds(218, 579, 89, 23);
		panel_10.add(btnNewButton_5);
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(300,0));
		splitPane_2.setLeftComponent(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_9.add(splitPane_3, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(0,75));
		splitPane_3.setLeftComponent(panel_11);
		panel_11.setLayout(null);
		
		lblNewLabel_6 = new JLabel(".......");
		lblNewLabel_6.setBounds(10, 11, 121, 14);
		panel_11.add(lblNewLabel_6);
		
		btnDosyaSec = new JButton("Dosya Sec");
		btnDosyaSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Dosya Seciniz");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Dosya Sec");
				chooser.setApproveButtonToolTipText("Dosya Sec");
				//FileNameExtensionFilter docFilter = new FileNameExtensionFilter(".docx", "Microsoft Word Documents");
				//chooser.addChoosableFileFilter(docFilter);
				
				//chooser.addChoosableFileFilter(new FileNameExtensionFilter("Dosyalar", "jpg", "png", "gif", "bmp","pdf","txt","xls","xlsx","doc","docx","db","xml","mdb","accdb"));
				chooser.setApproveButtonMnemonic('s');
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					model.addElement( new CheckListItem(chooser.getSelectedFile().getName(),chooser.getSelectedFile().getParent()));
					list.repaint();
				}
				else {
				}
			}
		});
	
		btnDosyaSec.setBounds(2, 41, 146, 30);
		panel_11.add(btnDosyaSec);
		
		btnSurucuSec = new JButton("Surucu Sec");
		btnSurucuSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Surucu Seciniz");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Surucu Sec");
				chooser.setApproveButtonToolTipText("Surucu Sec");
				chooser.setApproveButtonMnemonic('s');
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					model.addElement( new CheckListItem(chooser.getSelectedFile().toString(),"Surucu"));
					list.repaint();
				}
				else {
					// System.out.println("No Selection ");
				}
			}
		});
		btnSurucuSec.setBounds(147, 41, 148, 30);
		panel_11.add(btnSurucuSec);
		
		btnServer = new JButton("Server Baglanti");
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			OBS_BACKUP.tabbedPane_1.setSelectedIndex(4);
			}
		});
		btnServer.setVisible(false);
		btnServer.setBounds(2, 41, 290, 30);
		panel_11.add(btnServer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane_1);

		model = new DefaultListModel<>();
		
		//model.addElement( new CheckListItem("apple","C:\\"));
		list = new JList(model);
				
				
		list.setCellRenderer(new CheckListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				JList list = (JList) event.getSource();
				int index = list.locationToIndex(event.getPoint());// Get index of item

				CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
				item.setSelected(!item.isSelected()); // Toggle selected state
				list.repaint(list.getCellBounds(index, index));// Repaint cell
				if (list.getModel().getSize() != 0) 
				{
					int dosyaSayi = 0 ;
					for (int i =0; i< list.getModel().getSize(); i++)
					{
						item = (CheckListItem) list.getModel().getElementAt(i);
						if(item.isSelected)
						{
							dosyaSayi +=1 ;
						}
					} 
					lblNewLabel_5.setText(Integer.toString(dosyaSayi));
				}
			}
		});
		list.setDropTarget(new DropTarget() {
			
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>) evt .getTransferable().getTransferData(  DataFlavor.javaFileListFlavor);
					if(droppedFiles.size() > 1){
					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.WARNING,  "Tek Seferde 1 Dosya Ekleyebilirsiniz.....!!");
					}
					else{
						File droppedFile = (File) droppedFiles.get(0);
						model.addElement( new CheckListItem(droppedFile.getName(),droppedFile.getParent() ));
						list.repaint();
					}
				} catch (Exception ex) {
				OBS_BACKUP.	mesaj_goster(15000,Notifications.Type.ERROR, ex.getMessage() );
				}
			}
		});	
		scrollPane_1.setViewportView(list);

		
		
	}

}
