package obs.backup.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import OBS_C_2025.ScrollPaneWin11;
import obs.backup.gorev.gOREV_TAKIP;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;

public class OBS_BACKUP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel container ;
	/**
	 * Hamit.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBS_BACKUP frame = new OBS_BACKUP();
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
	public OBS_BACKUP() {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("obs.backup.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatMacDarkLaf.setup();
		
		setBounds(100, 100, 900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100,0));
		splitPane.setLeftComponent(panel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setPreferredSize(new Dimension(0,25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				container.removeAll();
					gOREV_TAKIP x = new gOREV_TAKIP("hamit");
					//x.setBounds(0, 0, 400, 100);
					
					container.add(x);
					gOREV_TAKIP x2 = new gOREV_TAKIP("aden");
					//x2.setBounds(0, 110, 400, 100);
					container.add(x2);
//				for (int i = 0; i < 15; i++) {
//					gOREV_TAKIP x = new gOREV_TAKIP(String.valueOf(i + 1));
//			          x.setLocation(0, 45 *i);
//			         container.add(x);
//			      }
					pack();
				}
			});
		panel.setLayout(new GridLayout(20, 1, 0, 0));
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<JTextField> list = new ArrayLists<JTextField>();
				Component[] components = panel.getComponents();

				for (Component component : components) {
				    if (component.getClass().equals(JTextField.class)) {
				        list.add((JTextField)component);
				    }
				}
			}
		});
		btnNewButton_1.setPreferredSize(new Dimension(0,25));
		panel.add(btnNewButton_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 128));
		panel_2.setPreferredSize(new Dimension(0,75));
		splitPane_1.setRightComponent(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		
		splitPane_1.setLeftComponent(scrollPane);
		
		container = new JPanel(); 
		scrollPane.setViewportView(container);
	
		container.setLayout(new GridLayout(10, 1, 0, 0));
		
		


	
	}
	public static void okuma(String isim)
	{
		System.out.println(isim);
	}
	@Override
	   public Dimension getPreferredSize() {
	      Dimension superSz = super.getPreferredSize();
	      if (isPreferredSizeSet()) {
	         return superSz;
	      }
	      return new Dimension(900, 700);
	   }

}
