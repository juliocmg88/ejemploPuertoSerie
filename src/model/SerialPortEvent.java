/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;

/**
 *
 * @author MUNOZ
 */
public class SerialPortEvent {
    private String name;
    private String location;
    private Date date;
    private String eventOrigen;
    private String stringDate;
    private String terminalType;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the terminalType
     */
    public String getEventOrigen() {
        return eventOrigen;
    }

    /**
     * @param terminalType the terminalType to set
     */
    public void setEventOrigen(String eventOrigen) {
        this.eventOrigen = eventOrigen;
    }

    /**
     * @return the stringDate
     */
    public String getStringDate() {
        return stringDate;
    }

    /**
     * @param stringDate the stringDate to set
     */
    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    /**
     * @return the terminalType
     */
    public String getTerminalType() {
        return terminalType;
    }

    /**
     * @param terminalType the terminalType to set
     */
    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }
}
