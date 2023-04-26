package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

@SuppressWarnings("serial")
public class ETIKET_AYAR extends JInternalFrame {
	 public static final SelectOnFocusGainedHandler SHARED_INSTANCE = new SelectOnFocusGainedHandler();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ETIKET_AYAR frame = new ETIKET_AYAR();
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
	public ETIKET_AYAR() {
		
		setClosable(true);
		setTitle("ETIKET AYAR");
		setBounds(100, 100, 588, 190);
		
		
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sol Bosluk");
		lblNewLabel.setBounds(24, 35, 70, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ust Bosluk");
		lblNewLabel_1.setBounds(24, 65, 70, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Etiket Yuksekligi");
		lblNewLabel_2.setBounds(24, 95, 86, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sag Bostluk");
		lblNewLabel_3.setBounds(293, 35, 70, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Alt Bosluk");
		lblNewLabel_1_1.setBounds(293, 65, 70, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Etiket Genisligi");
		lblNewLabel_2_1.setBounds(293, 95, 97, 14);
		panel.add(lblNewLabel_2_1);
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinner.setBounds(170, 30, 60, 20);
		panel.add(spinner);
		installFocusListener(spinner);
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(170, 60, 60, 20);
		panel.add(spinner_1);
		installFocusListener(spinner_1);
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(170, 90, 60, 20);
		panel.add(spinner_2);
		installFocusListener(spinner_2);
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(403, 30, 60, 20);
		panel.add(spinner_3);
		installFocusListener(spinner_3);
		JSpinner spinner_1_1 = new JSpinner();
		spinner_1_1.setBounds(403, 60, 60, 20);
		panel.add(spinner_1_1);
		installFocusListener(spinner_1_1);
		JSpinner spinner_2_1 = new JSpinner();
		spinner_2_1.setBounds(403, 90, 60, 20);
		panel.add(spinner_2_1);
		installFocusListener(spinner_2_1);
		JLabel lblNewLabel_2_2 = new JLabel("Etiket Arasi Bosluk(Dikey)");
		lblNewLabel_2_2.setBounds(24, 125, 125, 14);
		panel.add(lblNewLabel_2_2);
		
		JSpinner spinner_2_2 = new JSpinner();
		spinner_2_2.setBounds(170, 120, 60, 20);
		panel.add(spinner_2_2);
		installFocusListener(spinner_2_2);

	}
	 public void installFocusListener(JSpinner spinner) {
	        JComponent spinnerEditor = spinner.getEditor();
	        if (spinnerEditor != null) {
	            List<JTextComponent> lstChildren = findAllChildren(spinner, JTextComponent.class);
	            if (lstChildren != null && lstChildren.size() > 0) {
	                JTextComponent editor = lstChildren.get(0);
	                editor.addFocusListener(SHARED_INSTANCE);
	            }
	        }
	    }
	 @SuppressWarnings("unchecked")
	public static <T extends Component> List<T> findAllChildren(JComponent component, Class<T> clazz) {
	        List<T> lstChildren = new ArrayList<T>(5);
	        for (Component comp : component.getComponents()) {
	            if (clazz.isInstance(comp)) {
	                lstChildren.add((T) comp);
	            } else if (comp instanceof JComponent) {
	                lstChildren.addAll(findAllChildren((JComponent) comp, clazz));
	            }
	        }
	        return Collections.unmodifiableList(lstChildren);
	    }
    public static class SelectOnFocusGainedHandler extends FocusAdapter {
	        @Override
	        public void focusGained(FocusEvent e) {
	            Component comp = e.getComponent();
	            if (comp instanceof JTextComponent) {
	                final JTextComponent textComponent = (JTextComponent) comp;
	                new Thread(new Runnable() {
	                    @Override
	                    public void run() {
	                        try {
	                            Thread.sleep(25);
	                        } catch (InterruptedException ex) {
	                        }
	                        SwingUtilities.invokeLater(new Runnable() {
	                            @Override
	                            public void run() {
	                                textComponent.selectAll();
	                            }
	                        });
	                    }
	                }).start();
	            }            
	        }        
	    }
}
