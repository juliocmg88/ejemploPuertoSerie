/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ImagenFondoPanel.java
 *
 * Created on 25/10/2011, 09:14:07 PM
 */
package jimagenpanel;

import ejemplopuertoserie.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author MUNOZ
 */
public class JImagenPanel extends javax.swing.JPanel {

    private Image imgFondo;

    /** Creates new form ImagenFondoPanel */
    public JImagenPanel() {
        preInit();
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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

    private void preInit() {
        imgFondo = new ImageIcon(getClass().getResource("/ejemplopuertoserie/Esquema_Final_Proyecto_Subestacion_Aragua.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(imgFondo, 0, 0, getWidth(),getHeight(),this);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
