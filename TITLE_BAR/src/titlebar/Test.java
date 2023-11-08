package titlebar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		

		setContentPane(contentPane);
		
		setUndecorated(true);
		SimpleTitleBar pnlBar = new SimpleTitleBar();
		
		pnlBar.init(this);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnlBar, BorderLayout.NORTH);
		getContentPane().setBackground(new Color(25,50,25));
		setTitle("gfdgdfgdfgfdg");
	}

}
