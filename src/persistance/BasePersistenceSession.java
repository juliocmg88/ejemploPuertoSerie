package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import model.SerialPortEvent;
import model.Usuario;

public class BasePersistenceSession {

    private static ProjectLogger log = ProjectLogger.getLogger(BasePersistenceSession.class);
    private static String baseTablesPrefix = BaseConfiguration.getInstance().getValue("base.persistence.tablePrefix");
    private DBSession dbSession;

    public BasePersistenceSession(DBSession dbSession) {
        this.dbSession = dbSession;
    }

    public List<SerialPortEvent> getEvents(String initialDate, String finalDate) {
        List<SerialPortEvent> list = new ArrayList<SerialPortEvent>();
        String sqlCondition = "";
        try {
            if (finalDate==null) {
                sqlCondition = " DATE LIKE " + wrapField(initialDate + "%");
            }else{
                sqlCondition=" DATE <= "+wrapField(finalDate)+" AND DATE >= "+wrapField(initialDate);
            }
            ResultSet rs = dbSession.executeQuery("SELECT * FROM serial_port_events.`events` e"
                    + " WHERE " + sqlCondition + " ORDER BY DATE DESC");

            if (rs.next()) {
                do {
                    SerialPortEvent cont = new SerialPortEvent();
                    cont.setName(rs.getString("EVENT_NAME"));
                    cont.setDate(rs.getDate("DATE"));
                    cont.setStringDate(rs.getString("DATE"));
                    cont.setLocation(rs.getString("LOCATION"));
                    cont.setEventOrigen(rs.getString("EVENT_ORIGEN"));
                    cont.setTerminalType(rs.getString("TERMINAL_TYPE"));
                    list.add(cont);
                } while (rs.next());
            }
            return list;
        } catch (Exception e) {
            log.fatal("Error al obtener eventos", e);
            throw new AppRuntimeException("Error al obtener eventos", e);
        }
    }

        public model.Usuario getUsers(String login, String password) throws SQLException {

//        if (baseConnection) {
//            connectLocalBD();
//        } else {
//            connectRemoteBD();
//        }
        if (dbSession != null) {
            ResultSet res = dbSession.executeQuery("SELECT * FROM serial_port_events.users WHERE LOGIN="
                    + wrapField(login) + " AND PASSWORD="
                    + wrapField(password) + ";");
            Usuario usua = new Usuario();
            if (res.next()) {
                //res.first();
                do {
                    usua.setLogin(res.getString("LOGIN"));
                    usua.setPassword(res.getString("PASSWORD"));
                    usua.setNombre(res.getString("NAME"));
                } while (res.next());
            }
            return usua;
        } else {
            return null;
        }
    }

    public boolean setEvents(SerialPortEvent cont) {
        try {
            dbSession.beginTransaction();
            String fieldsSet = "EVENT_NAME=" + wrapField(cont.getName()) + ","
                    + "LOCATION=" + wrapField(cont.getLocation()) + ","
                    + "DATE=" + formatDate(cont.getDate()) + ","
                    + "EVENT_ORIGEN=" + wrapField(cont.getEventOrigen()) + ","
                    + "TERMINAL_TYPE=" + wrapField(cont.getTerminalType());
            dbSession.executeUpdate("INSERT INTO serial_port_events.`events` SET " + fieldsSet
                    + " ON DUPLICATE KEY UPDATE " + fieldsSet + ";");
            dbSession.commitTransaction();
            return true;
        } catch (Exception e) {
            dbSession.rollbackTransaction();
            log.fatal("Error al editar/agregar Información de Evento", e);
            throw new AppRuntimeException("Error al editar/agregar Información de Evento", e);
        }
    }

    private String wrapField(String field) {
        return BasePersistenceUtil.wrapStringField(field);
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date == null) {
            return wrapField(null);
        } else {
            return wrapField(sdf.format(date));
        }
    }
}
