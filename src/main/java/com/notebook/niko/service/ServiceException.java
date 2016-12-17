package com.notebook.niko.service;
/**
 * Exception of service layer
 * @author Nicolas Asinovich.
 */
public class ServiceException extends Exception
{
    /**
     * Exception service layer
     *
     * @param message - text describing the conditions under which was a mistake
     * @param e - exception
     */
    public ServiceException (String message, Exception e)
    {
        super(message, e);
    }
}
