/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;


import ejemplopuertoserie.JVentanaPrincipal;
import ejemplopuertoserie.SerialPortManagement;
import ejemplopuertoserie.UserLogin;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import persistance.BasePersistenceSession;

/**
 *
 * @author programador
 */
public class LoginThread extends Thread {

    private UserLogin userLogin;
    public int valor;
    private String info;
    private String login;
    private String password;
    private BasePersistenceSession persistance;
    private SerialPortManagement serialPortManagement;

    public LoginThread(String login, String password, UserLogin userLogin,
            SerialPortManagement serialPortManagement) {
        this.login = login;
        this.password = password;
        this.userLogin = userLogin;
        this.serialPortManagement=serialPortManagement;
        this.persistance=serialPortManagement.getPersistance();
    }
    
    public LoginThread(String login, String password, UserLogin userLogin) {
        this.login = login;
        this.password = password;
        this.userLogin = userLogin;
        this.persistance=serialPortManagement.getPersistance();
    }

    @Override
    public void run() {
        Runnable execute = new Runnable() {

            @Override
            public void run() {
                userLogin.barProgress(valor);
                userLogin.barInformation(info);
            }
        };
        try {
//            if (TrackerUtilities.getProperty("trackersystem.server").equals("YES")) {
//                if (!password.equals("12345")) {
                    startUserLogin(execute);
//                } else {
//                    JChangePassword changePassword = new JChangePassword(userLogin,localService,remoteService);
//                    changePassword.setVisible(true);
//                }
//            } else {
//               startUserLogin(execute);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void startUserLogin(Runnable execute) throws Exception {
            userLogin.setCurrentUser(persistance.getUsers(login, password));
        
        valor = 30;
        info = "Connecting to data base";
        SwingUtilities.invokeAndWait(execute);
        Thread.sleep(1000);
        if (userLogin.getCurrentUser().getLogin() != null) {
            valor = 99;
            info = "Connecting to data base";
            SwingUtilities.invokeAndWait(execute);
            JVentanaPrincipal vent = new JVentanaPrincipal(serialPortManagement);
            vent.setVisible(true);
            userLogin.dispose();
        } else {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Usuario no registrado, verifique Login y Password e intente de nuevo");
            valor = 0;
            info = "Try Again";
            SwingUtilities.invokeAndWait(execute);
            userLogin.getjTextField1().setText("");
            userLogin.getjPasswordField1().setText("");
            userLogin.getjTextField1().grabFocus();
        }
    }
}