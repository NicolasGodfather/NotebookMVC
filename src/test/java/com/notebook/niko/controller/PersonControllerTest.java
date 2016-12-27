package com.notebook.niko.controller;

import com.notebook.niko.model.Person;
import com.notebook.niko.service.PersonService;
import com.notebook.niko.service.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

/**
 * Realization tests for layer of Controller
 * @author Nicolas Asinovich.
 */
@Component
@ContextConfiguration ("/mvc-dispatcher-servlet.xml")
@RunWith (MockitoJUnitRunner.class)
@Transactional (propagation = Propagation.REQUIRED)
public class PersonControllerTest
{
    private Person niko = new Person(0, "niko", "nicolas.asinovich@gmail.com");
    private Person sasha = new Person(1, "sasha", "sasha.asinovich@gmail.com");
    private Person newPerson = new Person(2, "new", "new.asinovich@gmail.com");
    private static final int NONEXISTENT_USER_ID = 3;
    private List<Person> persons = asList(new Person[]{niko, sasha});
    ModelMap modelMap = new ModelMap();

    @Mock
    private PersonService personService;

    @InjectMocks
    private NotebookController notebookController;


    @Before
    public void setup ()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSuccessfully() throws Exception
    {
        List<Person> expectedPeople = asList(new Person());
        when(personService.getAllPersons()).thenReturn(expectedPeople);

        String viewName = notebookController.showHomePage(modelMap);
        Assert.assertEquals("homePage", viewName);
    }

    @Test
    public void testCreatePerson() throws ServiceException
    {
        modelMap.put("person", newPerson);
        newPerson.setId(0);
        List<Person> actualPersons = new ArrayList<>();
        doAnswer(invocation -> {
            Person person = (Person) invocation.getArguments()[0];
            actualPersons.add(person);
            Assert.assertNotNull(person);
            Assert.assertEquals(person, newPerson);
            return null;
        }).when(personService).createPerson(newPerson);
        BindingResult result = mock(BindingResult.class);
        notebookController.createPerson(modelMap, newPerson, result);
        Assert.assertTrue(actualPersons.contains(newPerson));
    }

    @Test
    public void testDeleteByIdSuccessfully() throws ServiceException
    {
        modelMap.put("id", niko.getId());
        List<Person> actualPersons = new ArrayList<>();
        actualPersons.add(niko);
        doAnswer(invocation -> {
            int id = (int) invocation.getArguments()[0];
            actualPersons.remove(0);
            Assert.assertEquals(id, this.niko.getId());
            return null;
        }).when(personService).deletePerson(niko.getId());
        notebookController.deletePerson(niko.getId());
        Assert.assertTrue(actualPersons.isEmpty());
    }

    @Test
    public void testDeleteByIdNonexistentUser() throws ServiceException
    {
        modelMap.put("id", NONEXISTENT_USER_ID);
        List<Person> actualPersons = new ArrayList<>();
        actualPersons.add(sasha);
        doAnswer(invocation -> {
            int id = (int) invocation.getArguments()[0];
            actualPersons.remove(0);
            Assert.assertEquals(id, NONEXISTENT_USER_ID);
            return null;
        }).when(personService).deletePerson(NONEXISTENT_USER_ID);
        notebookController.deletePerson(NONEXISTENT_USER_ID);
        Assert.assertTrue(actualPersons.isEmpty());
    }

}
