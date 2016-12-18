package com.notebook.niko.dao;

import com.notebook.niko.model.Person;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation IPersonDao
 * @author Nicolas Asinovich.
 */
@Repository
public class PersonDao implements IPersonDao
{
    private static final Logger logger = LoggerFactory.getLogger(PersonDao.class);
    private SessionFactory sessionFactory;

    @Autowired
    public PersonDao (SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    public Session getSession ()
    {
        return this.sessionFactory.getCurrentSession();
    }

    public Person createPerson (Person person) throws DaoException
    {
        try
        {
            getSession().save(person);
            logger.info("Person successfully created. Person details: " + person);
            return person;
        } catch (HibernateException e)
        {
            throw new DaoException(String.format("Creating %s", person), e);
        }
    }

    public void deletePerson (int id) throws DaoException
    {
        Person person = (Person) getSession().load(Person.class, id);
        try
        {
            if (person != null) {
                getSession().delete(person);
            }
            logger.info("Person successfully deleted. Person details: " + person);
        } catch (HibernateException e)
        {
            throw new DaoException(String.format("Deleting %s", person), e);
        }
    }

    public Person getPerson (int id) throws DaoException
    {
        try
        {
            logger.info("Get person with id = " + id);
            return (Person) getSession().get(Person.class, id);
        } catch (HibernateException e)
        {
            throw new DaoException(String.format("Getting person with id = %s", id), e);
        }
    }

    @SuppressWarnings ("unchecked")
    public List<Person> getAllPersons () throws DaoException
    {
        try
        {
            String hql = "from Person";
            Query query = getSession().createQuery(hql);
            return query.list();
        } catch (HibernateException e)
        {
            throw new DaoException("Hibernate query error!", e);
        }
    }

}
