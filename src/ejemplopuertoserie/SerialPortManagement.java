/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplopuertoserie;

/*import giovynet.nativelink.SerialPort;
import giovynet.serial.Baud;
import giovynet.serial.Com;
import giovynet.serial.Parameters;*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import persistance.BasePersistenceSession;
import persistance.DBSession;
import persistance.DBSessionFactory;

/**
 *
 * @author MUNOZ
 */
public class SerialPortManagement {

//    static SerialPort puerto = new SerialPort();
//    static List<String> listaPuertos;
//    private static Com com;
//
//    /**
//     * @return the com
//     */
//    public static Com getCom() {
//        return com;
//    }
//
//    /**
//     * @param aCom the com to set
//     */
//    public static void setCom(Com aCom) {
//        com = aCom;
//    }
    private DBSessionFactory instance = DBSessionFactory.getInstance();
    private BasePersistenceSession persistance;
//    private Com com;

    public SerialPortManagement() {
        DBSession dbSession = instance.openBaseDBSession();
        persistance = new BasePersistenceSession(dbSession);
        //com = portSettings();
    }
/*
    public List<String> getFreePorts() {
        try {
            return puerto.getFreeSerialPort();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Com portSettings() {
        List<String> list = getFreePorts();
        for (Iterator<String> it = list.iterator(); it.hasNext();) {
            String string = it.next();
            System.out.println(string);
        }
        try {
            Parameters parameters = new Parameters();
            if (getFreePorts() != null && getFreePorts().size() > 0) {
                //Para laptops y pc es 1
                String freePort = getFreePorts().get(0);
                //Open the port
                parameters.setPort(freePort);
                parameters.setBaudRate(Baud._9600);
                parameters.setByteSize("8");
                parameters.setReadInterval(500);
                System.out.println("Open port: " + freePort);
            }
            return new Com(parameters);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendData(int[] intArray) {
        try {
            //Com com = portSettings();
            //Send data
            System.out.println("<Send Data>");
            for (int i = 0; i < intArray.length; i++) {
                Thread.sleep(400);
                com.writeDataInt(com.getPort(), intArray[i]);
                System.out.println(Integer.toString(intArray[i], 2));
            }
            //Close the port
            System.out.println("<End Send Data>");
            //com.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] readData() {
        try {
            //Com com = portSettings();
            //Receive data
            List<String> list = new ArrayList<String>();
            List<Integer> listNum = new ArrayList<Integer>();

            char checkpoint = 0;
            Character cha = com.receiveSingleChar();
            if (cha != null) {
                checkpoint = cha;
            }
            if (checkpoint == 'E') {
                for (int i = 0; i < 9; i++) {
                    Thread.sleep(500);
//                list.add(com.receiveSingleString());
                    listNum.add(com.readDataInt(com.getPort()));

                    //data.append(com.receiveSingleString());
                    //received[i]=com.readDataInt(port);
                }
                //com.close();
            } else if(checkpoint== 'A'){
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(500);
                    listNum.add(com.readDataInt(com.getPort()));

                    //data.append(com.receiveSingleString());
                    //received[i]=com.readDataInt(port);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    listNum.add(0);
                }
            }
            //System.out.println(data);

            listNum.remove(listNum.size() - 1);
            if (listNum.size() == 8) {
                System.out.println("<Receive Data>");
                for (Iterator<Integer> it = listNum.iterator(); it.hasNext();) {
                    //String integer = it.next();
                    Integer integer = it.next();
                    System.out.println(integer + " Size: " + listNum.size());


                }
                System.out.println("<End Receive Data>");
            }
            return listToArray(listNum);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
*/
    /**
     * @return the persistance
     */
    public BasePersistenceSession getPersistance() {
        return persistance;
    }

    private int[] listToArray(List<Integer> list) {
        int[] integer = new int[list.size()];
        int i = 0;
        for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
            Integer integer1 = it.next();
            integer[i] = integer1;
            i++;
        }
        return integer;
    }

    private Integer asciiToNumber(String ascii) {
        byte[] number = ascii.getBytes();
        Integer integer = (int) number[0];
        return integer;
    }
}
