package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import OBS_2025.YIL_SONU.CheckBoxHeader;
import OBS_2025.YIL_SONU.MyItemListener;
import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_SAATLI;
import net.proteanit.sql.DbUtils;

@SuppressWarnings({"serial","static-access"})
public class ETIKET extends JInternalFrame {
private JTable table;
static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
static ADRES_ACCESS a_Access = new ADRES_ACCESS(oac._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
static ResultSet rs = null ;
private static final Vector<?> Boolean = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ETIKET frame = new ETIKET();
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
	public ETIKET() {
		setMaximizable(true);
		setTitle("ETIKET");
	
		setIconifiable(true);
		setResizable(true);
	
		setClosable(true);
		setBounds(0, 0, 1200, 600);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);



		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				case 0:
					return true;
				default:
					return false;
				}
			}
		};
		table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e) 
			{
				TableModel model = (TableModel)e.getSource();
				if (model.getRowCount() > 0) {
					int row;
					row = table.getSelectedRow();     //e.getFirstRow();
					int column = e.getColumn();
				
					//secilen_satir();
				}
			}

		});
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		hisset();
	}
	public void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			rs = a_Access.adr_etiket();
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
			//	lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addColumn("Sec",Boolean);
				table.moveColumn(table.getColumnCount()-1, 0);

				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				JCheckBox checkBox = new JCheckBox();
				checkBox.setHorizontalAlignment(JCheckBox.CENTER);
				DefaultCellEditor dce = new DefaultCellEditor( checkBox );
				table.getColumnModel().getColumn(0).setCellEditor(dce);
				tc.setCellRenderer(new CheckBoxRenderer());
				tc.setMinWidth(50);
				tc.setMaxWidth(50);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				

				Dimension dd = table.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				//
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
				//
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);

				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);

				table.setSelectionBackground(Color.PINK);
				table.setSelectionForeground(Color.BLUE);

				//***
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				//lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

				String deger;
				String[] parts;
				Font bigFont;
				deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);

			}
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	///********
	class MyItemListener implements ItemListener
	{
		@SuppressWarnings("removal")
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			Object source = e.getSource();
			if (source instanceof AbstractButton == false) return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			for(int x = 0, y = table.getRowCount(); x < y; x++)
			{
				table.setValueAt(new Boolean(checked),x,0);
			}
		}
	}
	class CheckBoxHeader extends JCheckBox   implements TableCellRenderer, MouseListener 
	{
		protected CheckBoxHeader rendererComponent;
		protected int column;
		protected boolean mousePressed = false;
		public CheckBoxHeader(ItemListener itemListener) {
			rendererComponent = this;
			rendererComponent.addItemListener(itemListener);
		}
		public Component getTableCellRendererComponent(
				JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (table != null) {
				JTableHeader header = table.getTableHeader();
				if (header != null) {
					rendererComponent.setForeground(header.getForeground());
					rendererComponent.setBackground(header.getBackground());
					rendererComponent.setFont(header.getFont());
					header.addMouseListener(rendererComponent);
				}
			}
			setColumn(column);

			setHorizontalAlignment(JLabel.CENTER);

			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			//setSelected(true);
			return rendererComponent;
		}
		protected void setColumn(int column) {
			this.column = column;
		}
		public int getColumn() {
			return column;
		}
		protected void handleClickEvent(MouseEvent e) {
			if (mousePressed) {
				mousePressed=false;
				JTableHeader header = (JTableHeader)(e.getSource());
				JTable tableView = header.getTable();
				TableColumnModel columnModel = tableView.getColumnModel();
				int viewColumn = columnModel.getColumnIndexAtX(e.getX());
				int column = tableView.convertColumnIndexToModel(viewColumn);

				if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
					doClick();
				}
			}
		}
		public void mouseClicked(MouseEvent e) {
			handleClickEvent(e);
			((JTableHeader)e.getSource()).repaint();
		}
		public void mousePressed(MouseEvent e) {
			mousePressed = true;
		}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) 
		{
		}
	}
}

