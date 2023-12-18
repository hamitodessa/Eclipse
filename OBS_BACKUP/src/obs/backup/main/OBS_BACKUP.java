package obs.backup.main;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import obs.backup.gorev.gOREV_TAKIP;
import obs.backup.other.Title_Bar;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;

public class OBS_BACKUP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel container ;
	private JPanel panel_2 ;
	private JTabbedPane tabbedPane ;
	int x ,y ;
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
		setUndecorated(true);
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("obs.backup.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatMacDarkLaf.setup();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX(); 
				y = e.getY(); 
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen(); 
				setLocation(xx-x,yy-y);
			}
		});
		
		setBounds(100, 100, 900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(new Title_Bar(this), BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100,0));
		splitPane.setLeftComponent(panel);
		
		JButton btnGorevler = new JButton("Gorevler");
		btnGorevler.setPreferredSize(new Dimension(0,25));
		btnGorevler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
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
		panel.add(btnGorevler);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Component[] components = container.getComponents();

				for (Component component : components) {
					System.out.println(component.getName());
					if (component.getName().toString().equals("hamit")) {


						JPanel qweJPanel = (JPanel) component ; 

						Component[] componentt = qweJPanel.getComponents();
						for (Component compo : componentt) {

							System.out.println(compo.getName());
						}

					}


					// }
				}
			}
		});
		
		JButton btnYeni_Gorev = new JButton("Yeni Gorev");
		panel.add(btnYeni_Gorev);
		btnNewButton_1.setPreferredSize(new Dimension(0,25));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("alt pan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
			}
		});
		panel.add(btnNewButton);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Gorevler", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_1, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 128));
		panel_2.setPreferredSize(new Dimension(0,75));
		splitPane_1.setRightComponent(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		
		splitPane_1.setLeftComponent(scrollPane);
		
		container = new JPanel(); 
		scrollPane.setViewportView(container);
	
		container.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Yeni Gorev", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_3.add(tabbedPane_1, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_5, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_6, null);
		
		


	
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
