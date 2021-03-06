/**
 * 
 */
package com.bhuwan.jaxrsdemo.messanger.resources.custombeans;

import javax.ws.rs.MatrixParam;
import javax.ws.rs.QueryParam;

/**
 * @author bhuwan
 *
 */
public class MessageFilterBean {

    private @MatrixParam("year") int year;
    private @QueryParam("start") int start;
    private @QueryParam("size") int size;

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year
     *            the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start
     *            the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

}
