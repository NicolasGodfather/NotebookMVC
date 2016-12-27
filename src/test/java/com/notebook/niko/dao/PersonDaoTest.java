package com.notebook.niko.dao;

import com.notebook.niko.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Realization tests for layer of DAO
 *
 * @author Nicolas Asinovich.
 */
@Repository
@ContextConfiguration ("/mvc-dispatcher-servlet.xml")
@RunWith (SpringJUnit4ClassRunner.class)
@Transactional (propagation = Propagation.REQUIRED)
public class PersonDaoTest
{
    private static final int PERSON_ID = 0;
    private static final String PERSON_NAME = "Niko";
    private static final String PERSON_EMAIL = "nicolas.asinovich@gmail.com";
    private static final int NONEXISTENT_USER_ID = 2;
    private List<Person> persons;

    @Autowired
    private IPersonDao iPersonDao;

    @Before
    public void initialPersonList() throws DaoException
    {
        persons = iPersonDao.getAllPersons();
    }

    @Test
    public void getByIdExistingPerson () throws DaoException
    {
        Person person = new Person();
        person.setId(PERSON_ID);
        person.setName(PERSON_NAME);
        person.setEmail(PERSON_EMAIL);
        Person person2 = iPersonDao.getPerson(PERSON_ID);
        Assert.assertNotNull("Person with id " + PERSON_ID + " is null", person2);
        Assert.assertEquals("Person id " + person2.getId() + " != " + person.getId(), person2.getId(), person.getId());
        Assert.assertEquals("Person name " + person2.getName() + " != " + person.getName(), person2.getName(), person.getName());
        Assert.assertEquals("Person email " + person2.getEmail() + " != " + person.getEmail(), person2.getEmail(), person.getEmail());
    }

    @Test
    public void getByIdNonexistentPerson () throws DaoException
    {
        int id = NONEXISTENT_USER_ID;
        Person person = iPersonDao.getPerson(NONEXISTENT_USER_ID);
        Assert.assertNull("Person with id " + id + " is mot null", person);
    }

    @Test
    public void testCreateSuccessfully () throws DaoException
    {
        String name = "Dima";
        String email = "Dima@gmail.com";
        Person newPerson = new Person(name, email);
        Person person = iPersonDao.createPerson(newPerson);
        Assert.assertEquals(person + "!=" + newPerson, person, newPerson);
    }

    @Test
    public void testDeleteSuccessfully () throws DaoException
    {
        int id = PERSON_ID;
        iPersonDao.deletePerson(id);
        Assert.assertTrue(iPersonDao.getPerson(id) == null);
    }


    @Test(expected = Exception.class)
    public void testDeleteByIdFail() throws DaoException
    {
        iPersonDao.deletePerson(NONEXISTENT_USER_ID);
    }

    @Test
    public void testGetAllSuccessfully() throws DaoException
    {
        Assert.assertNotNull("List is null", persons);
        Person person = persons.get(PERSON_ID);
        String actual = person.getId() + person.getName();
        String expected = PERSON_ID + PERSON_NAME;
        Assert.assertEquals(expected + "!=" + actual, expected, actual);
    }

    @Test
    public void testGetAllEmpty() throws DaoException
    {
        persons.removeAll(persons);
        Assert.assertNotNull("List is null", persons);
        Assert.assertTrue("List isn't empty", persons.isEmpty());
    }

}
