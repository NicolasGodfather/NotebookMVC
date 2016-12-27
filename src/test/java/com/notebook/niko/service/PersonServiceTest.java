package com.notebook.niko.service;

import com.notebook.niko.dao.DaoException;
import com.notebook.niko.dao.IPersonDao;
import com.notebook.niko.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Realization tests for layer of Service
 *
 * @author Nicolas Asinovich.
 */
@Component
@ContextConfiguration ("/mvc-dispatcher-servlet.xml")
@RunWith (MockitoJUnitRunner.class)
@Transactional (propagation = Propagation.REQUIRED)
public class PersonServiceTest
{
    private static final int PERSON_ID = 0;
    private Person niko = new Person(0, "niko", "nicolas.asinovich@gmail.com");
    private Person sasha = new Person(1, "sasha", "sasha.asinovich@gmail.com");
    private Person newPerson = new Person(2, "new", "new.asinovich@gmail.com");
    private static final int NONEXISTENT_USER_ID = 3;
    private List<Person> persons = Arrays.asList(new Person[]{niko, sasha});

    @Mock
    private IPersonDao iPersonDao;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testGetByIdSuccessfully() throws DaoException
    {
        when(iPersonDao.getPerson(niko.getId())).thenReturn(niko);
        Person expectedPerson = niko;
        Person actualPerson = iPersonDao.getPerson(expectedPerson.getId());
        Assert.assertNotNull(actualPerson);
        Assert.assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void testGetByIdNotExistedUser() throws ServiceException, DaoException
    {
        int id = NONEXISTENT_USER_ID;
        Person actualPerson = personService.getPerson(id);
        when(iPersonDao.getPerson(id)).thenReturn(actualPerson);
        Assert.assertNull(actualPerson);
    }

    @Test
    public void testSaveSuccessfully() throws DaoException, ServiceException
    {
        List<Person> persons = new ArrayList<>();
        doAnswer(invocation -> {
            Person newPerson = (Person) invocation.getArguments()[0];
            persons.add(newPerson);
            Assert.assertNotNull(newPerson);
            Assert.assertEquals(newPerson, this.newPerson);
            return null;
        }).when(iPersonDao).createPerson(newPerson);
        personService.createPerson(newPerson);
        Assert.assertTrue(persons.contains(newPerson));
    }

    @Test
    public void testDeleteByIdSuccessfully() throws DaoException, ServiceException
    {
        List<Person> persons = new ArrayList<>();
        persons.add(niko);
        doAnswer(invocation -> {
            int personId = (int) invocation.getArguments()[0];
            persons.remove(0);
            Assert.assertEquals(personId, niko.getId());
            return null;
        }).when(iPersonDao).deletePerson(niko.getId());
        personService.deletePerson(niko.getId());
        Assert.assertTrue(persons.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteByIdFail() throws DaoException, ServiceException
    {
        doThrow(new RuntimeException()).when(iPersonDao).deletePerson(NONEXISTENT_USER_ID);
        personService.deletePerson(NONEXISTENT_USER_ID);
    }

    @Test
    public void testGetAllSuccessfully() throws DaoException, ServiceException
    {
        when(iPersonDao.getAllPersons()).thenReturn(persons);
        List<Person> personList = personService.getAllPersons();
        Assert.assertEquals(persons, personList);
    }
    @Test
    public void testGetAllEmpty() throws DaoException, ServiceException
    {
        List<Person> personList = new ArrayList<>();
        when(iPersonDao.getAllPersons()).thenReturn(personList);
        List<Person> personTest = personService.getAllPersons();
        Assert.assertTrue("List isn't empty", personTest.isEmpty());
    }

}
