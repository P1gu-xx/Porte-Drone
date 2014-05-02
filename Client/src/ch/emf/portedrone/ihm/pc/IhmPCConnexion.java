package ch.emf.portedrone.ihm.pc;

import ch.emf.portedrone.ctrl.ICtrlIhm;
import ch.emf.portedrone.ihm.IIhmConnexionCtrl;
import javax.swing.JOptionPane;


/**
 * IHM de l'application pour l'écran de connexion.
 * @author PeclatJ
 */
public class IhmPCConnexion extends javax.swing.JFrame implements IIhmConnexionCtrl {

    /**
     * Constructeur de l'application.
     */
    public IhmPCConnexion() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form
     * Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLogin = new javax.swing.JTextField();
        txtMotDePasse = new javax.swing.JTextField();
        btnConnexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexion");

        txtLogin.setText("login");

        txtMotDePasse.setText("mot de passe");

        btnConnexion.setText("Connexion");
        btnConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnexionClique(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(btnConnexion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConnexion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cette methode est appelée quand le bouton de connexion est cliqué. 
     * @param evt l'évenement
     */
    private void btnConnexionClique(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnexionClique
        ctrl.connexion(txtLogin.getText(), txtMotDePasse.getText());
    }//GEN-LAST:event_btnConnexionClique

    @Override
    public void afficherMessage(String message, String titre, int type) {
        JOptionPane.showMessageDialog(this, message, message, type);
    }
    
    @Override
    public void exit() {
        System.exit(0);
    }
    
    /**
     * Permet de définir le contrôleur de l'IHM Connexion,
     * @param ctrl La référence vers le contrôleur
     */
    public void setCtrl(ICtrlIhm ctrl) {
        this.ctrl = ctrl;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnexion;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtMotDePasse;
    // End of variables declaration//GEN-END:variables

    /**
     * La référence vers le contrôleur.
     */
    private ICtrlIhm ctrl;

}
