/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplopuertoserie;

import giovynet.nativelink.SerialPort;
import giovynet.serial.Com;
import java.util.Iterator;
import java.util.List;
import threads.IOThread;

/**
 *
 * @author programador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                SerialPortManagement serialPortManagement = new SerialPortManagement();
                UserLogin userLogin = UserLogin.getInstance(serialPortManagement);
                userLogin.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
                userLogin.pack();
                userLogin.setVisible(true);                
            }
        });
    }
}
