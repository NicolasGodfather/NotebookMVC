package com.notebook.niko.dao;

/**
 * Exception of dao layer
 * @author Nicolas Asinovich.
 */
public class DaoException extends Exception
{
    /**
     * @param message - text describing the conditions under which was a mistake
     * @param e       - exception
     */
    public DaoException (String message, Exception e)
    {
        super(message, e);
    }
}
