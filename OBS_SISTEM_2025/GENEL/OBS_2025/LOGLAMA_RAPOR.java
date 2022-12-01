package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import OBS_C_2025.GLOBAL;

public class LOGLAMA_RAPOR extends JInternalFrame {
	 static JSplitPane splitPane ;
	 static JTable table;
	 private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGLAMA_RAPOR frame = new LOGLAMA_RAPOR();
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
	public LOGLAMA_RAPOR() {
		setTitle("LOG RAPORLAMA");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1250, 600);
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 50));
		panel.setMaximumSize(new Dimension(0, 50));
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(oac.gridcolor);
		
		
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane.setViewportView(table);
	}

}
