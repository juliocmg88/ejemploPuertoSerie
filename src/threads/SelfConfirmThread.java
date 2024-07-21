/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package threads;

import ejemplopuertoserie.JConfirmNewEvents;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio Muñoz
 */
public class SelfConfirmThread extends Thread{
    private JConfirmNewEvents event;

    public SelfConfirmThread(JConfirmNewEvents event){
        this.event=event;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(10000);
            event.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
