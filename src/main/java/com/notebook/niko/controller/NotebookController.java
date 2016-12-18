package com.notebook.niko.controller;

import com.notebook.niko.model.Person;
import com.notebook.niko.service.IPersonService;
import com.notebook.niko.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Realization main app controller
 * @author Nicolas Asinovich.
 */
@Controller
public class NotebookController
{
    private static final Logger logger = LoggerFactory.getLogger(NotebookController.class);
    private static final String HOME_PAGE = "homePage";
    private static final String CREATE_PERSON = "createPerson";
    private static final String REDIRECT_HOME = "redirect:/home";

    @Autowired
    private IPersonService iPersonService;

    @RequestMapping (value = {"/", "/home"}, method = RequestMethod.GET)
    public String showHomePage (ModelMap modelMap) throws ServiceException
    {
        modelMap.addAttribute("persons", iPersonService.getAllPersons());
        logger.info("Web-app successfully launched.");
        return HOME_PAGE;
    }

    @RequestMapping (value = "/creating", method = RequestMethod.GET)
    public String showCreatePersonPage (ModelMap modelMap)
    {
        modelMap.addAttribute("person", new Person());
        return CREATE_PERSON;
    }

    @RequestMapping (value = "person", method = RequestMethod.POST)
    public String createPerson (ModelMap modelMap, @Valid @ModelAttribute ("person") Person person,
                                BindingResult bindingResult) throws ServiceException
    {
        if (bindingResult.hasErrors())
        {
            return CREATE_PERSON;
        }
        Person newPerson = iPersonService.createPerson(new Person(person.getName(), person.getEmail()));
        modelMap.addAttribute("person", newPerson);
        logger.info("New person successfully created.");
        return REDIRECT_HOME;
    }

    @RequestMapping (value = "/deletePerson/{id}")
    public String deletePerson (@PathVariable ("id") int id) throws ServiceException
    {
        this.iPersonService.deletePerson(id);
        logger.info("Person successfully deleted.");
        return REDIRECT_HOME;
    }

}
