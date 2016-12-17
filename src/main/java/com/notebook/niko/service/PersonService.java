package com.notebook.niko.service;

import com.notebook.niko.dao.DaoException;
import com.notebook.niko.dao.IPersonDao;
import com.notebook.niko.model.Person;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation IPersonService
 * @author Nicolas Asinovich.
 */
@Service
@Transactional (propagation = Propagation.REQUIRED)
public class PersonService implements IPersonService
{
    @Autowired
    private IPersonDao iPersonDao;

    @Autowired
    public void setiPersonDao (IPersonDao iPersonDao)
    {
        this.iPersonDao = iPersonDao;
    }

    public void createPerson (Person person) throws ServiceException
    {
        try
        {
            iPersonDao.createPerson(person);
        } catch (DaoException | HibernateException e)
        {
            throw new ServiceException("Error while transaction of createPerson", e);
        }
    }

    public void deletePerson (int id) throws ServiceException
    {
        try
        {
            iPersonDao.deletePerson(id);
        } catch (DaoException | HibernateException e)
        {
            throw new ServiceException("Error while transaction of deletePerson", e);
        }
    }

    public Person getPerson (int id) throws ServiceException
    {
        try
        {
            return iPersonDao.getPerson(id);
        } catch (DaoException | HibernateException e)
        {
            throw new ServiceException("Error while transaction of getPerson", e);
        }
    }

    public List<Person> getAllPersons () throws ServiceException
    {
        try
        {
            return iPersonDao.getAllPersons();
        } catch (DaoException | HibernateException e)
        {
            throw new ServiceException("Error while transaction of getAllUsers", e);
        }
    }

    @Override
    public List<Person> findPersonByQuery (String query) throws ServiceException
    {
        try
        {
            return iPersonDao.findPersonByQuery(query);
        } catch (DaoException | HibernateException e)
        {
            throw new ServiceException("Error while transaction of findPersonByQuery", e);
        }
    }
}
