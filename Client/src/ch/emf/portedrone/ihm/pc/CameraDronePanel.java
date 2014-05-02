package ch.emf.portedrone.ihm.pc;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * JPanel pour afficher dans l'IHM la camera du drone.
 * @author PeclatJ
 */
public class CameraDronePanel extends javax.swing.JPanel {

    /**
     * Constructeur du panel.
     */
    public CameraDronePanel() {
        initComponents();
        img = null;
    }

    // End of variables declaration                   
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (img != null) {
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);

        }
    }

    /**
     * Définir l'image de la camera du drone à afficher.
     * @param img Lîmage de la camera du drone.
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    /**
     * L'image de la camera du drone.
     */
    private BufferedImage img;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
