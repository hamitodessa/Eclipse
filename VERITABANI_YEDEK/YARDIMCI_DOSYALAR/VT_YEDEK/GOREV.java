package VT_YEDEK;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GOREV {

	public static  JPanel getShowRoomPanel(int num,String isim) {
	        JPanel p = new JPanel(new GridBagLayout());
	        p.setBorder(new TitledBorder("GridBagLayout"));

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	        gbc.anchor = GridBagConstraints.WEST;

	        p.add(new JLabel("Name:Honda Showroom" + num + isim), gbc);

	        gbc.gridx = 1;
	        p.add(new JLabel("City:Mandsaur"), gbc);

	        gbc.gridx = 2;
	        p.add(new JLabel("Address:25 Chakrawati Colony Railway Station Road"), gbc);

	        gbc.gridy = 1;
	        gbc.gridx = 0;
	        p.add(new JLabel("Vehicle Name:Activa"), gbc);

	        gbc.gridx = 1;
	        p.add(new JLabel("Vehicle Version:2017"), gbc);

	        gbc.gridx = 2;
	        p.add(new JLabel("Vehicle Companies:Honda"), gbc);

	        gbc.gridy = 2;
	        gbc.gridx = 0;
	        JButton btnNewButton = new JButton("go");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					   JOptionPane.showMessageDialog(null,  "Name:Honda Showroom" + num,  "Eksik Kur Okuma", JOptionPane.ERROR_MESSAGE);
					
				}
			});
	        p.add(btnNewButton, gbc);

	        return p;
	    }
}
