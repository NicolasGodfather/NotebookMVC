package com.notebook.niko.dao;

import com.notebook.niko.model.Person;

import java.util.List;

/**
 * Main CRUD operations with DAO layer
 * @author Nicolas Asinovich.
 */
public interface IPersonDao
{
    void createPerson (Person person) throws DaoException;
    void deletePerson (int id) throws DaoException;
    Person getPerson(int id) throws DaoException;
    List<Person> getAllPersons() throws DaoException;
    List<Person> findPersonByQuery(String query) throws DaoException;

}
