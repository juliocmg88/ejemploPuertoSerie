/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jVentanaPrincipal.java
 *
 * Created on 15/10/2011, 12:13:28 PM
 */
package ejemplopuertoserie;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.SerialPortEvent;
import threads.IOThread;

/**
 *
 * @author MUNOZ
 */
public class JVentanaPrincipal extends javax.swing.JFrame {

    private boolean selectedControl = true;
    private HashMap<JButton, Boolean> buttonBool = new HashMap<JButton, Boolean>();
    private HashMap<JLabel, Boolean> labelBool = new HashMap<JLabel, Boolean>();
    private HashMap<JButton, String> buttonLabel = new HashMap<JButton, String>();
    private HashMap<JLabel, String> inputLabel = new HashMap<JLabel, String>();
    private SerialPortManagement serialPortManagement;
    private String location;
    private boolean[] firstPortPosition = new boolean[]{false, false, false, false, false, false, false, false};
    private boolean[] secondPortPosition = new boolean[]{false, false, false, false, false, false, false, false};
    private boolean[] thirdPortPosition = new boolean[]{false, false, false, false, false, false, false, false};
    private int[] intArray = new int[8];
    private int[] outputPort1;
    private int[] outputPort2;
    private int[] outputPort3;
    private JComponent[][] inputPorts;

    /** Creates new form jVentanaPrincipal */
    public JVentanaPrincipal(SerialPortManagement serialPortManagement) {
        initComponents();
        setLocationRelativeTo(null);
        this.serialPortManagement = serialPortManagement;
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        List<JButton> list = buttonList();
//        selectedControl = serialPortManagement.readData();
        for (Iterator<JButton> it = list.iterator(); it.hasNext();) {
            JButton jButton = it.next();
            buttonBool.put(jButton, selectedControl);
            changeColorToggleButton(selectedControl, jButton);
        }
        Component[] components = jImagenPanel1.getComponents();
        for (int i = 0; i < components.length; i++) {
            Component component = components[i];
            if (component instanceof JLabel) {
                labelBool.put((JLabel) component, false);
            }
        }

        fillButtonLabelMap();
        fillInputLabelsMap();
        configureInputPorts();
//        configureInputPorts();
//        processReadData(serialPortManagement.readData());
        final JVentanaPrincipal frame = this;
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                JConfirmExit jConfirmExit = new JConfirmExit(frame, true);
                jConfirmExit.setVisible(true);
            }
        });
        IOThread ioThread = new IOThread(serialPortManagement, this);
        ioThread.start();
    }

    private String configureLabelLocation(JLabel label) {
        if (label == jLabel9 || label == jLabel18) {
            return "AT 01";
        }
        if (label == jLabel1 || label == jLabel10 || label == jLabel19) {
            return "SOCO-CAGUA 1";
        }
        if (label == jLabel16 || label == jLabel21 || label == jLabel5) {
            return "SOCO 02";
        }
        if (label == jLabel8 || label == jLabel17) {
            return "BP1 - BP2";
        }
        if (label == jLabel11 || label == jLabel20) {
            return "TR 01";
        }
        if (label == jLabel12 || label == jLabel14) {
            return "TR 02";
        }
        if (label == jLabel13 || label == jLabel22) {
            return "AT 02";
        }
        if (label == jLabel15 || label == jLabel31 || label == jLabel44) {
            return "CAGUA 01";
        }
        if (label == jLabel23 || label == jLabel43) {
            return "AT 03";
        }
        if (label == jLabel25 || label == jLabel32 || label == jLabel36) {
            return "S-IGNACIO 02";
        }
        if (label == jLabel6 || label == jLabel24 || label == jLabel42) {
            return "S-IGNACIO 01";
        }
        if (label == jLabel3 || label == jLabel4 || label == jLabel33) {
            return "RESERVA 01";
        }
        if (label == jLabel2 || label == jLabel37 || label == jLabel41) {
            return "RESERVA 02";
        }
        if (label == jLabel26 || label == jLabel34 || label == jLabel38) {
            return "P-NEGRO 01";
        }
        if (label == jLabel27 || label == jLabel39) {
            return "AT 04";
        }
        if (label == jLabel28 || label == jLabel30 || label == jLabel35) {
            return "VILLA II -TAIGUAY";
        }
        if (label == jLabel7 || label == jLabel29 || label == jLabel40) {
            return "CORINSA 01";
        }
        return "";
    }

    private void fillInputLabelsMap() {
        inputLabel.put(jLabel1, "H103");
        inputLabel.put(jLabel2, "H703");
        inputLabel.put(jLabel3, "H604");
        inputLabel.put(jLabel4, "606");
        inputLabel.put(jLabel5, "H203");
        inputLabel.put(jLabel6, "H503");
        inputLabel.put(jLabel7, "H1003");
        inputLabel.put(jLabel8, "H134");
        inputLabel.put(jLabel9, "H184");
        inputLabel.put(jLabel10, "H104");
        inputLabel.put(jLabel11, "H114");
        inputLabel.put(jLabel12, "H214");
        inputLabel.put(jLabel13, "H284");
        inputLabel.put(jLabel14, "H216");
        inputLabel.put(jLabel15, "H306");
        inputLabel.put(jLabel16, "H204");
        inputLabel.put(jLabel17, "H136");
        inputLabel.put(jLabel18, "H186");
        inputLabel.put(jLabel19, "H106");
        inputLabel.put(jLabel20, "H116");
        inputLabel.put(jLabel21, "H206");
        inputLabel.put(jLabel22, "H286");
        inputLabel.put(jLabel23, "H386");
        inputLabel.put(jLabel24, "H506");
        inputLabel.put(jLabel25, "H404");
        inputLabel.put(jLabel26, "H804");
        inputLabel.put(jLabel27, "H484");
        inputLabel.put(jLabel28, "H904");
        inputLabel.put(jLabel29, "H1004");
        inputLabel.put(jLabel30, "H906");
        inputLabel.put(jLabel31, "H303");
        inputLabel.put(jLabel32, "H403");
        inputLabel.put(jLabel33, "H603");
        inputLabel.put(jLabel34, "H803");
        inputLabel.put(jLabel35, "H903");
        inputLabel.put(jLabel36, "H406");
        inputLabel.put(jLabel37, "H706");
        inputLabel.put(jLabel38, "H806");
        inputLabel.put(jLabel39, "H486");
        inputLabel.put(jLabel40, "H1006");
        inputLabel.put(jLabel41, "H704");
        inputLabel.put(jLabel42, "H504");
        inputLabel.put(jLabel43, "H384");
        inputLabel.put(jLabel44, "H304");
    }

    private void fillButtonLabelMap() {
        buttonLabel.put(jButton1, "H180");
        buttonLabel.put(jButton2, "H105");
        buttonLabel.put(jButton3, "H205");
        buttonLabel.put(jButton4, "H280");
        buttonLabel.put(jButton5, "H380");
        buttonLabel.put(jButton6, "H505");
        buttonLabel.put(jButton7, "H705");
        buttonLabel.put(jButton8, "H480");
        buttonLabel.put(jButton9, "H130");
        buttonLabel.put(jButton11, "H1005");
        buttonLabel.put(jButton12, "H110");
        buttonLabel.put(jButton13, "H210");
        buttonLabel.put(jButton14, "H305");
        buttonLabel.put(jButton15, "H405");
        buttonLabel.put(jButton16, "H605");
        buttonLabel.put(jButton17, "H805");
        buttonLabel.put(jButton18, "H905");
    }

    private List<JButton> buttonList() {
        List<JButton> list = new ArrayList<JButton>();
        list.add(jButton1);
        list.add(jButton2);
        list.add(jButton3);
        list.add(jButton4);
        list.add(jButton5);
        list.add(jButton6);
        list.add(jButton7);
        list.add(jButton8);
        list.add(jButton9);
        list.add(jButton11);
        list.add(jButton12);
        list.add(jButton13);
        list.add(jButton14);
        list.add(jButton15);
        list.add(jButton16);
        list.add(jButton17);
        list.add(jButton18);
        return list;
    }

    private void configureInputPorts() {
        inputPorts = new JComponent[8][8];
        inputPorts[0][0] = jLabel9;
        inputPorts[0][1] = jLabel18;
        inputPorts[0][2] = jLabel1;
        inputPorts[0][3] = jLabel10;
        inputPorts[0][4] = jLabel19;
        inputPorts[0][5] = jLabel16;
        inputPorts[0][6] = jLabel21;
        inputPorts[0][7] = jLabel5;
        inputPorts[1][0] = jLabel13;
        inputPorts[1][1] = jLabel22;
        inputPorts[1][2] = jLabel43;
        inputPorts[1][3] = jLabel23;
        inputPorts[1][4] = jLabel42;
        inputPorts[1][5] = jLabel24;
        inputPorts[1][6] = jLabel6;
        inputPorts[1][7] = jLabel2;
        inputPorts[2][0] = jLabel41;
        inputPorts[2][1] = jLabel37;
        inputPorts[2][2] = jLabel27;
        inputPorts[2][3] = jLabel39;
        inputPorts[2][4] = jLabel29;
        inputPorts[2][5] = jLabel40;
        inputPorts[2][6] = jLabel7;
        inputPorts[2][7] = jLabel35;
        inputPorts[3][0] = jLabel11;
        inputPorts[3][1] = jLabel20;
        inputPorts[3][2] = jLabel12;
        inputPorts[3][3] = jLabel14;
        inputPorts[3][4] = jLabel44;
        inputPorts[3][5] = jLabel15;
        inputPorts[3][6] = jLabel31;
        inputPorts[3][7] = jLabel32;
        inputPorts[4][0] = jLabel25;
        inputPorts[4][1] = jLabel36;
        inputPorts[4][2] = jLabel3;
        inputPorts[4][3] = jLabel4;
        inputPorts[4][4] = jLabel33;
        inputPorts[4][5] = jLabel26;
        inputPorts[4][6] = jLabel38;
        inputPorts[4][7] = jLabel34;
        inputPorts[5][0] = jLabel28;
        inputPorts[5][1] = jLabel30;
        inputPorts[5][2] = jLabel8;
        inputPorts[5][3] = jLabel17;
        inputPorts[5][4] = jButton1;
        inputPorts[5][5] = jButton2;
        inputPorts[5][6] = jButton3;
        inputPorts[5][7] = jButton4;
        inputPorts[6][0] = jButton5;
        inputPorts[6][1] = jButton6;
        inputPorts[6][2] = jButton7;
        inputPorts[6][3] = jButton8;
        inputPorts[6][4] = jButton11;
        inputPorts[6][5] = jButton12;
        inputPorts[6][6] = jButton13;
        inputPorts[6][7] = jButton14;
        inputPorts[7][0] = jButton15;
        inputPorts[7][1] = jButton16;
        inputPorts[7][2] = jButton17;
        inputPorts[7][3] = jButton18;
        inputPorts[7][4] = jButton9;
        inputPorts[7][5] = new JComponent() {
        };
        inputPorts[7][6] = new JComponent() {
        };
        inputPorts[7][7] = new JComponent() {
        };
    }

    public void processReadData(int[] data) throws Exception {
        for (int i = 0; i < data.length; i++) {
            char[] charArray = Integer.toString(data[i], 2).toCharArray();
            char[] charArrays = processCharArray(charArray);
            for (int k = 0; k < charArrays.length; k++) {
                if (charArrays[k] == '1') {
                    boolean newState = true;
                    if (inputPorts[i][k] instanceof JLabel) {
                        JLabel label = (JLabel) inputPorts[i][k];
                        boolean currentState = labelBool.get(label);
                        if (newState != currentState) {
                            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/romboazul.jpg")));
                            SerialPortEvent serialPortEvent = new SerialPortEvent();
                            serialPortEvent.setName("Activo");
                            labelBool.put(label, true);
                            serialPortEvent.setDate(new Date());
                            serialPortEvent.setLocation(configureLabelLocation(label) + " " + inputLabel.get(label));
                            serialPortEvent.setEventOrigen("Circuito Externo");
                            serialPortEvent.setTerminalType("Entrada");
                            boolean completed = serialPortManagement.getPersistance().setEvents(serialPortEvent);
                            if (completed) {
//                                JOptionPane.showMessageDialog(this, "El evento \"" + serialPortEvent.getTerminalType() + " : " + serialPortEvent.getLocation()
//                                        + " = " + serialPortEvent.getName()
//                                        + "\" se ha añadido a la base de datos",
//                                        "Informacion Actualizada", JOptionPane.INFORMATION_MESSAGE);
                                JConfirmNewEvents events = new JConfirmNewEvents(serialPortEvent);
                                events.setVisible(true);
                                Thread.sleep(1000);
                            }
                        }
                    } else if (inputPorts[i][k] instanceof JButton) {
                        if (buttonBool.get((JButton) inputPorts[i][k])) {
                            processButtonInformation(((JButton) inputPorts[i][k]), false);
                        }
                    }
                } else {
                    boolean newState = false;
                    if (inputPorts[i][k] instanceof JLabel) {
                        JLabel label = (JLabel) inputPorts[i][k];
                        boolean currentState = labelBool.get(label);
                        if (newState != currentState) {
                            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg")));
                            SerialPortEvent serialPortEvent = new SerialPortEvent();
                            serialPortEvent.setName("Inactivo");
                            labelBool.put(label, false);
                            serialPortEvent.setDate(new Date());
                            serialPortEvent.setLocation(configureLabelLocation(label) + " " + inputLabel.get(label));
                            serialPortEvent.setEventOrigen("Circuito Externo");
                            serialPortEvent.setTerminalType("Entrada");
                            boolean completed = serialPortManagement.getPersistance().setEvents(serialPortEvent);
                            if (completed) {
//                                JOptionPane.showMessageDialog(this, "El evento \"" + serialPortEvent.getTerminalType() + " : " + serialPortEvent.getLocation()
//                                        + " = " + serialPortEvent.getName()
//                                        + "\" se ha añadido a la base de datos",
//                                        "Informacion Actualizada", JOptionPane.INFORMATION_MESSAGE);
                                JConfirmNewEvents events = new JConfirmNewEvents(serialPortEvent);
                                events.setVisible(true);
                                Thread.sleep(1000);
                            }
                        }
                    } else if (inputPorts[i][k] instanceof JButton) {
                        if (!buttonBool.get((JButton) inputPorts[i][k])) {
                            processButtonInformation(((JButton) inputPorts[i][k]), false);
                        }
                    }
                }
            }
        }
    }

    private char[] processCharArray(char[] array) {
        int f = 7;
        char[] charArrays = new char[]{'0', '0', '0', '0', '0', '0', '0', '0'};
        for (int i = array.length - 1; i > -1; i--) {
            charArrays[f] = array[i];
            f--;
        }
        return charArrays;
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
    new JVentanaPrincipal().setVisible(true);
    }
    });
    }*/
    private void processButtonInformation(JButton jButton, boolean origen) {
        try {
            boolean initialState = buttonBool.get(jButton);
            int port = configureByteToSend(jButton);
            Boolean portValue = portPosition(jButton, port);
            outputPort1 = processBooleanArray(firstPortPosition);
            outputPort2 = processBooleanArray(secondPortPosition);
            outputPort3 = processBooleanArray(thirdPortPosition);

            intArray[0] = 'S';
            intArray[1] = Integer.parseInt(byteToString(outputPort1), 2);
            intArray[2] = Integer.parseInt(byteToString(outputPort2), 2);
            intArray[3] = Integer.parseInt(byteToString(outputPort3), 2);
            intArray[4] = '\r';
            intArray[5] = '\n';
            intArray[6] = 0;
            intArray[7] = 0;
//            serialPortManagement.sendData(intArray);
            changeColorToggleButton(!initialState, jButton);
            buttonBool.put(jButton, !initialState);
            SerialPortEvent serialPortEvent = new SerialPortEvent();
            if (portValue) {
                serialPortEvent.setName("Activo");
            } else {
                serialPortEvent.setName("Inactivo");
            }
            serialPortEvent.setDate(new Date());
            serialPortEvent.setLocation(location + " " + buttonLabel.get(jButton));
            if (origen) {
                serialPortEvent.setEventOrigen("Aplicacion");
            } else {
                serialPortEvent.setEventOrigen("Circuito Externo");
            }
            serialPortEvent.setTerminalType("Salida");
            boolean completed = serialPortManagement.getPersistance().setEvents(serialPortEvent);
            if (completed) {
//                JOptionPane.showMessageDialog(this, "El evento \"" + serialPortEvent.getTerminalType() + " : "
//                        + serialPortEvent.getLocation()
//                        + " = " + serialPortEvent.getName()
//                        + "\" se ha añadido a la base de datos",
//                        "Informacion Actualizada", JOptionPane.INFORMATION_MESSAGE);
                JConfirmNewEvents events = new JConfirmNewEvents(serialPortEvent);
                events.setVisible(true);
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void changeColorToggleButton(boolean selected, JButton jButton) {
        if (selected) {
            jButton.setBackground(Color.white);
            jButton.setToolTipText("Activar");
            jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel1.gif")));
        } else {
            jButton.setBackground(Color.blue);
            jButton.setToolTipText("Desactivar");
            jButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif")));
        }
    }

    private int configureByteToSend(JButton jButton) {
        if (jButton1.equals(jButton)
                || jButton2.equals(jButton)
                || jButton3.equals(jButton)
                || jButton4.equals(jButton)
                || jButton5.equals(jButton)
                || jButton6.equals(jButton)
                || jButton7.equals(jButton)
                || jButton8.equals(jButton)) {
            return 0;
        } else if (jButton11.equals(jButton)
                || jButton12.equals(jButton)
                || jButton13.equals(jButton)
                || jButton14.equals(jButton)
                || jButton15.equals(jButton)
                || jButton16.equals(jButton)
                || jButton17.equals(jButton)
                || jButton18.equals(jButton)) {
            return 1;

        } else if (jButton9.equals(jButton)) {
            return 2;
        } else {
            return 3;
        }
    }

    private Boolean portPosition(JButton jButton, int port) {
        if (port == 0) {
            if (jButton.equals(jButton8)) {
                firstPortPosition[0] = !firstPortPosition[0];
                location = "AT 04";
                return firstPortPosition[0];
            } else if (jButton.equals(jButton7)) {
                firstPortPosition[1] = !firstPortPosition[1];
                location = "RESERVA 02";
                return firstPortPosition[1];
            } else if (jButton.equals(jButton6)) {
                firstPortPosition[2] = !firstPortPosition[2];
                location = "S-IGNACIO 01";
                return firstPortPosition[2];
            } else if (jButton.equals(jButton5)) {
                firstPortPosition[3] = !firstPortPosition[3];
                location = "AT 03";
                return firstPortPosition[3];
            } else if (jButton.equals(jButton4)) {
                firstPortPosition[4] = !firstPortPosition[4];
                location = "AT 02";
                return firstPortPosition[4];
            } else if (jButton.equals(jButton3)) {
                firstPortPosition[5] = !firstPortPosition[5];
                location = "SOCO 02";
                return firstPortPosition[5];
            } else if (jButton.equals(jButton2)) {
                firstPortPosition[6] = !firstPortPosition[6];
                location = "SOCO-CAGUA 1";
                return firstPortPosition[6];
            } else if (jButton.equals(jButton1)) {
                firstPortPosition[7] = !firstPortPosition[7];
                location = "AT 01";
                return firstPortPosition[7];
            }
        } else if (port == 1) {
            if (jButton.equals(jButton18)) {
                secondPortPosition[0] = !secondPortPosition[0];
                location = "VILLA II -TAIGUAY";
                return secondPortPosition[0];
            } else if (jButton.equals(jButton17)) {
                secondPortPosition[1] = !secondPortPosition[1];
                location = "P-NEGRO 01";
                return secondPortPosition[1];
            } else if (jButton.equals(jButton16)) {
                secondPortPosition[2] = !secondPortPosition[2];
                location = "RESERVA 01";
                return secondPortPosition[2];
            } else if (jButton.equals(jButton15)) {
                secondPortPosition[3] = !secondPortPosition[3];
                location = "S-IGNACIO 02";
                return secondPortPosition[3];
            } else if (jButton.equals(jButton14)) {
                secondPortPosition[4] = !secondPortPosition[4];
                location = "CAGUA 01";
                return secondPortPosition[4];
            } else if (jButton.equals(jButton13)) {
                secondPortPosition[5] = !secondPortPosition[5];
                location = "TR 02";
                return secondPortPosition[5];
            } else if (jButton.equals(jButton12)) {
                secondPortPosition[6] = !secondPortPosition[6];
                location = "TR 01";
                return secondPortPosition[6];
            } else if (jButton.equals(jButton11)) {
                secondPortPosition[7] = !secondPortPosition[7];
                location = "CORINSA 01";
                return secondPortPosition[7];
            }
        } else if (port == 2) {
            if (jButton.equals(jButton9)) {
                thirdPortPosition[7] = !thirdPortPosition[7];
                location = "BP1 - BP2";
                return thirdPortPosition[7];
            }
        }
        return null;
    }

    private int[] processBooleanArray(boolean[] booleanArray) {
        int[] intArray = new int[booleanArray.length];
        for (int i = 0; i < intArray.length; i++) {
            if (booleanArray[i]) {
                intArray[i] = 1;
            } else {
                intArray[i] = 0;
            }

        }
        return intArray;
    }

    private String byteToString(int[] portStatus) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < portStatus.length; i++) {
            int j = portStatus[i];
            buff.append(j);
        }
        return buff.toString();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jImagenPanel1 = new jimagenpanel.JImagenPanel();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Subestacion Aragua");
        setResizable(false);

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton18.setToolTipText("Desactivar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton17.setToolTipText("Desactivar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton16.setToolTipText("Desactivar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton15.setToolTipText("Desactivar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton14.setToolTipText("Desactivar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton13.setToolTipText("Desactivar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton12.setToolTipText("Desactivar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton11.setToolTipText("Desactivar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton9.setToolTipText("Desactivar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton8.setToolTipText("Desactivar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton7.setToolTipText("Desactivar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton6.setToolTipText("Desactivar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton5.setToolTipText("Desactivar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton4.setToolTipText("Desactivar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton3.setToolTipText("Desactivar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton2.setToolTipText("Desactivar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/nivel2.gif"))); // NOI18N
        jButton1.setToolTipText("Desactivar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ejemplopuertoserie/contactoabierto.jpg"))); // NOI18N

        javax.swing.GroupLayout jImagenPanel1Layout = new javax.swing.GroupLayout(jImagenPanel1);
        jImagenPanel1.setLayout(jImagenPanel1Layout);
        jImagenPanel1Layout.setHorizontalGroup(
            jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                        .addContainerGap(135, Short.MAX_VALUE)
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jImagenPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jImagenPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(69, 69, 69)))
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jImagenPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jImagenPanel1Layout.createSequentialGroup()
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel16)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addComponent(jLabel21))
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jImagenPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jImagenPanel1Layout.createSequentialGroup()
                                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jImagenPanel1Layout.createSequentialGroup()
                                                .addGap(74, 74, 74)
                                                .addComponent(jLabel23))
                                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(jLabel44)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel43)))
                                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                                .addComponent(jLabel36)
                                                .addGap(27, 27, 27))
                                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel25)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel42)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)))
                                        .addGap(41, 41, 41)))))
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel41)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel27))
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel37)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel38)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel39))
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel28)
                            .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3))
                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel31)
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel17))
                                .addGap(29, 29, 29)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel9))
                                .addGap(49, 49, 49)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel10))
                                .addGap(28, 28, 28)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel11))
                                .addGap(69, 69, 69)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addGap(71, 71, 71)
                                .addComponent(jLabel15)))
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(196, 196, 196))
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(62, 62, 62)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel33)))
                                .addGap(53, 53, 53)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel35)))
                                .addGap(36, 36, 36)))))
                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29)
                        .addGap(65, 65, 65))
                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel40)
                        .addContainerGap())))
        );
        jImagenPanel1Layout.setVerticalGroup(
            jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jImagenPanel1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9))
                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton8)
                                            .addComponent(jButton7)))
                                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton11)))
                                .addGap(32, 32, 32)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29)
                                    .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel42)
                                        .addComponent(jLabel43)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel3))))
                            .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addComponent(jLabel44))
                            .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel41)
                                .addComponent(jLabel26)))))
                .addGap(18, 18, 18)
                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton16))
                    .addGroup(jImagenPanel1Layout.createSequentialGroup()
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel36)
                                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21)))
                            .addComponent(jLabel14)
                            .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel23)
                                .addComponent(jLabel22))
                            .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel39)
                                .addComponent(jLabel30)))
                        .addGap(18, 18, 18)
                        .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton18)
                            .addComponent(jButton15)
                            .addComponent(jButton14)
                            .addComponent(jButton13)
                            .addComponent(jButton12)
                            .addComponent(jButton17))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jImagenPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35))
                .addGap(231, 231, 231))
        );

        jMenu1.setText("Archivo");

        jMenuItem4.setText("Refrescar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Herramientas");

        jMenuItem2.setText("Mostrar Eventos dia actual");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Mostrar Eventos dias pasados");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jImagenPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jImagenPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        final JVentanaPrincipal frame = this;
                JConfirmExit jConfirmExit = new JConfirmExit(frame, true);
                jConfirmExit.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JTablaEventos eventsTable = new JTablaEventos(serialPortManagement, sdf.format(new Date()), null);
        eventsTable.setLocation(getX() + 400, getY() + 250);
        eventsTable.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JDateDialogFrame dialog = new JDateDialogFrame(this, true, serialPortManagement);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
        /*    int[] receivedData = serialPortManagement.readData();
            if (receivedData.length == 8) {
                processReadData(receivedData);
            }
         */
        System.out.println("Evento 4");
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        processButtonInformation(jButton1, true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        processButtonInformation(jButton2, true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        processButtonInformation(jButton3, true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        processButtonInformation(jButton4, true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        processButtonInformation(jButton5, true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        processButtonInformation(jButton6, true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        processButtonInformation(jButton7, true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        processButtonInformation(jButton8, true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        processButtonInformation(jButton11, true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        processButtonInformation(jButton9, true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        processButtonInformation(jButton12, true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        processButtonInformation(jButton13, true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        processButtonInformation(jButton14, true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        processButtonInformation(jButton15, true);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        processButtonInformation(jButton16, true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        processButtonInformation(jButton17, true);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        processButtonInformation(jButton18, true);
    }//GEN-LAST:event_jButton18ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private jimagenpanel.JImagenPanel jImagenPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    // End of variables declaration//GEN-END:variables
}
