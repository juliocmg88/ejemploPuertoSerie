/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import ejemplopuertoserie.JVentanaPrincipal;
import ejemplopuertoserie.SerialPortManagement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio Muñoz
 */
public class IOThread extends Thread {

    private SerialPortManagement port;
    private JVentanaPrincipal vent;
    private boolean go = true;

    public IOThread(SerialPortManagement port,JVentanaPrincipal vent) {
        this.port = port;
        this.vent=vent;
    }

    @Override
    public void run() {
        /*while (go) {
            int[] receivedData = port.readData();
            if(receivedData.length==8){
                try {
                    vent.processReadData(receivedData);
                } catch (Exception ex) {
                    Logger.getLogger(IOThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }*/
        System.out.println("run");
    }

    public void safeStop(){
        go=false;
    }
}
