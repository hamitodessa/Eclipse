package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.TARIH_CEVIR;

public class ORNEK_SCROLL_PANE extends JInternalFrame {

	static JSplitPane splitPane ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ORNEK_SCROLL_PANE frame = new ORNEK_SCROLL_PANE();
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
	public ORNEK_SCROLL_PANE() {
		
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1063, 600);
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(new Color(0, 191, 255)));
		leftPanel.setMinimumSize(new Dimension(0, 50));
		leftPanel.setMaximumSize(new Dimension(0, 50));
		JScrollPane centerPanel = new JScrollPane();
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 191, 255)));
		rightPanel.setMinimumSize(new Dimension(0, 20));
		rightPanel.setMaximumSize(new Dimension(0, 20));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, leftPanel, centerPanel);
		leftPanel.setLayout(null);
		JSplitPane sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp, rightPanel);
		rightPanel.setLayout(null);
		sp2.setDividerSize(1);
		sp2.setResizeWeight(1.0);
		getContentPane().add(sp2, BorderLayout.CENTER);
		
	
	}

}
