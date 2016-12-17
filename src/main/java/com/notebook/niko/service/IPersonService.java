package com.notebook.niko.service;

import com.notebook.niko.model.Person;

import java.util.List;

/**
 * Realization CRUD operations service layer
 * @author Nicolas Asinovich.
 */
public interface IPersonService
{
    void createPerson (Person person) throws ServiceException;
    void deletePerson (int id) throws ServiceException;
    Person getPerson(int id) throws ServiceException;
    List<Person> getAllPersons() throws ServiceException;
    List<Person> findPersonByQuery(String query) throws ServiceException;

}
