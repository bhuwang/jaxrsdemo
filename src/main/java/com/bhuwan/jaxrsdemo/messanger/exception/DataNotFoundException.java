/**
 * 
 */
package com.bhuwan.jaxrsdemo.messanger.exception;

/**
 * @author bhuwan
 *
 */
public class DataNotFoundException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 764103685903622148L;
    
    public DataNotFoundException(String message) {
        super(message);
    }

}
