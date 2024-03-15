package OBS_2025;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.GLOBAL;
import raven.toast.Notifications;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

@SuppressWarnings({"serial","unchecked"})
public class ETIKET_AYAR extends JInternalFrame {
	public static final SelectOnFocusGainedHandler SHARED_INSTANCE = new SelectOnFocusGainedHandler();
	private static JSpinner spnSolB;
	private static JSpinner spnUstB ;
	private static JSpinner spnSagB ;
	private static JSpinner spnAltB ;
	private static JSpinner spnEtYUK;
	private static JSpinner spnEtGEN;
	private static JSpinner spnEtBOSLUK;
	private static JComboBox<String> eTIKETYAZIMBox ;


	public ETIKET_AYAR() {

		setClosable(true);
		setTitle("ETIKET AYAR");
		setBounds(100, 100, 588, 190);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(ETIKET_AYAR.class.getResource("/ICONLAR/icons8-pincode-keyboard-30.png")), 16, 16));//
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sol Bosluk");
		lblNewLabel.setBounds(24, 35, 70, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ust Bosluk");
		lblNewLabel_1.setBounds(24, 65, 70, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Etiket Yuksekligi");
		lblNewLabel_2.setBounds(24, 95, 136, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Sag Bostluk");
		lblNewLabel_3.setBounds(293, 35, 100, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_1_1 = new JLabel("Alt Bosluk");
		lblNewLabel_1_1.setBounds(293, 65, 100, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("Etiket Genisligi");
		lblNewLabel_2_1.setBounds(293, 95, 112, 14);
		panel.add(lblNewLabel_2_1);
		spnSolB = new JSpinner();
		spnSolB.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spnSolB.setBounds(200, 30, 60, 20);
		panel.add(spnSolB);
		installFocusListener(spnSolB);
		spnUstB = new JSpinner();
		spnUstB.setBounds(200, 60, 60, 20);
		panel.add(spnUstB);
		installFocusListener(spnUstB);
		spnEtYUK = new JSpinner();
		spnEtYUK.setBounds(200, 90, 60, 20);
		panel.add(spnEtYUK);
		installFocusListener(spnEtYUK);
		spnSagB = new JSpinner();
		spnSagB.setBounds(403, 30, 60, 20);
		panel.add(spnSagB);
		installFocusListener(spnSagB);
		spnAltB = new JSpinner();
		spnAltB.setBounds(403, 60, 60, 20);
		panel.add(spnAltB);
		installFocusListener(spnAltB);
		spnEtGEN = new JSpinner();
		spnEtGEN.setBounds(403, 90, 60, 20);
		panel.add(spnEtGEN);
		installFocusListener(spnEtGEN);
		JLabel lblNewLabel_2_2 = new JLabel("Etiket Arasi Bosluk(Dikey)");
		lblNewLabel_2_2.setBounds(24, 125, 176, 14);
		panel.add(lblNewLabel_2_2);

		spnEtBOSLUK = new JSpinner();
		spnEtBOSLUK.setBounds(200, 120, 60, 20);
		panel.add(spnEtBOSLUK);
		installFocusListener(spnEtBOSLUK);

		JLabel lblNewLabel_2_1_1 = new JLabel("Etiket Yazim");
		lblNewLabel_2_1_1.setBounds(293, 125, 112, 14);
		panel.add(lblNewLabel_2_1_1);

		eTIKETYAZIMBox = new JComboBox<String>();
		eTIKETYAZIMBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Yatay", "Dikey"}));
		eTIKETYAZIMBox.setBounds(403, 121, 88, 22);
		panel.add(eTIKETYAZIMBox);

		doldur();
	}
	public static void kaydet()
	{
		try 
		{
			GLOBAL.setting_yaz("SOL_BOSLUK",String.valueOf( spnSolB.getValue()));
			GLOBAL.setting_yaz("SAG_BOSLUK",String.valueOf( spnSagB.getValue()));
			GLOBAL.setting_yaz("UST_BOSLUK", String.valueOf( spnUstB.getValue()));
			GLOBAL.setting_yaz("ALT_BOSLUK", String.valueOf( spnAltB.getValue()));
			GLOBAL.setting_yaz("ETIKET_YUK", String.valueOf( spnEtYUK.getValue()));
			GLOBAL.setting_yaz("ETIKET_GEN",  String.valueOf( spnEtGEN.getValue()));
			GLOBAL.setting_yaz("ETIKET_ARA_BOSLUK",  String.valueOf( spnEtBOSLUK.getValue()));
			GLOBAL.setting_yaz("ETIKET_YAZIM", eTIKETYAZIMBox.getSelectedItem().toString());
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
	private void doldur()
	{
		try 
		{
			spnSolB.setValue(Integer.parseInt(GLOBAL.setting_oku("SOL_BOSLUK")));
			spnSagB.setValue(Integer.parseInt(GLOBAL.setting_oku("SAG_BOSLUK")));
			spnUstB.setValue(Integer.parseInt(GLOBAL.setting_oku("UST_BOSLUK")));
			spnAltB.setValue(Integer.parseInt(GLOBAL.setting_oku("ALT_BOSLUK")));
			spnEtYUK.setValue(Integer.parseInt(GLOBAL.setting_oku("ETIKET_YUK")));
			spnEtGEN.setValue(Integer.parseInt(GLOBAL.setting_oku("ETIKET_GEN")));
			spnEtBOSLUK.setValue(Integer.parseInt(GLOBAL.setting_oku("ETIKET_ARA_BOSLUK")));
			eTIKETYAZIMBox.setSelectedItem(GLOBAL.setting_oku("ETIKET_YAZIM"));
		} 
		catch (Exception ex) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
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
	public static <T extends Component> List<T> findAllChildren(JComponent component, Class<T> clazz) {
		List<T> lstChildren = new ArrayList<T>(5);
		for (Component comp : component.getComponents()) {
			if (clazz.isInstance(comp))
				lstChildren.add((T) comp);
			else if (comp instanceof JComponent)
				lstChildren.addAll(findAllChildren((JComponent) comp, clazz));
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
