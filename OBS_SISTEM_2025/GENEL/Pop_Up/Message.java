package Pop_Up;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import raven.glasspanepopup.GlassPanePopup;

/**
 *
 * @author RAVEN
 */
@SuppressWarnings("serial")
public class Message extends javax.swing.JPanel {

    public Message() {
        initComponents();
        setOpaque(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setSelectionColor(new Color(48, 170, 63, 200));
        txt.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt = new javax.swing.JTextPane();
        cmdOK = new Pop_Up.Button();
 
        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(80, 80, 80));
        jLabel1.setText("Your Message Title Dialog Custom");

        txt.setEditable(false);
        txt.setForeground(new java.awt.Color(133, 133, 133));
        txt.setText("This is part of a series of short tutorials about specific elements, components, or interactions. We’ll cover the UX, the UI, and the construction inside of Sketch. Plus, there’s a freebie for you at the end!");

        cmdOK.setBackground(new java.awt.Color(48, 170, 63));
        cmdOK.setForeground(new java.awt.Color(255, 255, 255));
        cmdOK.setText("OK");

 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 261, Short.MAX_VALUE))
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                     .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }

    @SuppressWarnings("unused")
	private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {
        GlassPanePopup.closePopupLast();
    }

    public void eventOK(ActionListener event) {
        cmdOK.addActionListener(event);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Pop_Up.Button cmdOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextPane txt;
    // End of variables declaration//GEN-END:variables
}
