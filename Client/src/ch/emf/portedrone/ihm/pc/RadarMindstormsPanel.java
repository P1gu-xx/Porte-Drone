package ch.emf.portedrone.ihm.pc;

import ch.emf.portedrone.beans.mindstorms.Echo;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;


/**
 * JPanel pour afficher dans l'IHM le radar du Mindstorms.
 * @author PeclatJ
 */
public class RadarMindstormsPanel extends JPanel{
    
    /**
     * Constructeur du panel.
     */
    public RadarMindstormsPanel() {
        listeEcho = new ArrayList<>();
        angleRadar = (int) (Math.PI/7);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.GREEN);
        g.fillOval(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.BLACK);
        for (int i = 0; i < nombreDeSubdivision; i++) {
            g.drawArc(
                    i*(this.getWidth()/nombreDeSubdivision) - (i*(this.getWidth()/nombreDeSubdivision))/2,
                    i*(this.getHeight()/nombreDeSubdivision) - (i*(this.getHeight()/nombreDeSubdivision))/2,
                    this.getWidth()-(i*(this.getWidth()/nombreDeSubdivision)),
                    this.getHeight()-(i*(this.getHeight()/nombreDeSubdivision)),
                    0, 360);
        }
        
        g.drawLine(
                this.getWidth()/2,
                0,
                this.getWidth()/2,
                this.getHeight()/2 - (this.getHeight()-((nombreDeSubdivision-1)*(this.getHeight()/nombreDeSubdivision)))/2
                );
        g.drawLine(
                0,
                this.getHeight()/2,
                this.getWidth()/2 - (this.getWidth()-((nombreDeSubdivision-1)*(this.getWidth()/nombreDeSubdivision)))/2,
                this.getHeight()/2
                );
        g.drawLine(
                this.getWidth(),
                this.getHeight()/2,
                this.getWidth()/2 + (this.getWidth()-((nombreDeSubdivision-1)*(this.getWidth()/nombreDeSubdivision)))/2,
                this.getHeight()/2
                );
        g.drawLine(
                this.getWidth()/2,
                this.getHeight(),
                this.getWidth()/2,
                this.getHeight()/2 + (this.getHeight()-((nombreDeSubdivision-1)*(this.getHeight()/nombreDeSubdivision)))/2
                );
        
        g.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2 + (int)(Math.cos(angleRadar)*this.getWidth()/2), this.getHeight()/2 + (int)(Math.sin(angleRadar)*this.getHeight()/2));
        
        for (int i = 0; i < listeEcho.size(); i++) {
            listeEcho.get(i).update(g, this.getWidth(), this.getHeight());
        }
    }
    
    /**
     * Défini la liste des échos à afficher sur l'écran.
     * @param listeEcho La liste des échos.
     */
    public void setEchos(ArrayList<Echo> listeEcho) {
        this.listeEcho = new ArrayList<>();
        for (Echo echo : listeEcho) {
            this.listeEcho.add(new IhmEcho(echo.distance, echo.angle));
        }
    }
    
    /**
     * Défini l'angle du radar actuel et modifie sa position sur l'écran.
     * @param angleRadar L'angle du radar en radian.
     */
    public void setAngle(double angleRadar) {
        this.angleRadar = angleRadar;
    }
    
    /**
     * La liste des échos.
     */
    private ArrayList<IhmEcho> listeEcho;
    
    /**
     * L'angle du radar en radian.
     */
    private double angleRadar;
    
    /**
     * Constante utile à l'affichage, elle définie le nombre de petit cercle sur le radar.
     */
    private static final int nombreDeSubdivision = 4;
}
