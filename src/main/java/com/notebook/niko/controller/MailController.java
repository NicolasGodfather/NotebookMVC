package com.notebook.niko.controller;

import com.notebook.niko.model.Person;
import com.notebook.niko.service.IPersonService;
import com.notebook.niko.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Realization Mail Controller
 * @author Nicolas Asinovich.
 */
@Controller
@RequestMapping ("/email")
public class MailController
{
    private static final Logger logger = LoggerFactory.getLogger(MailController.class);
    private static final String RESULT = "mailSendingResult";
    private static final String GREETING = "Hi guys! I hope you like my solution =)";
    private static final String LINK = "/n Here you will see my source code https://github.com/NicolasGodfather/NotebookMVC";
    private static final String SUBJECT = "Java Developer";

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private IPersonService iPersonService;

    public void setJavaMailSender (JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping (value = "/sendingData/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String sendEmail (@PathVariable ("id") int id)
    {
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            @Override
            public void prepare (MimeMessage mimeMessage) throws Exception
            {
                try
                {
                    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                    messageHelper.setTo(generateEmailById(id));
                    messageHelper.setSubject(SUBJECT);
                    messageHelper.setText(GREETING + LINK);
                    FileSystemResource file = new FileSystemResource(new File("E:\\JAVA\\cruds\\NotebookMVC\\src\\main\\resources\\private\\CV.doc"));
                    messageHelper.addAttachment(file.getFilename(), file);
                    logger.info("Files successfully attached.");
                } catch (MessagingException e)
                {
                    throw new MailParseException(e);
                }
            }
        };
        this.javaMailSender.send(preparator);
        logger.info("Email successfully sent.");

        return RESULT;
    }

    private String generateEmailById (int id) throws ServiceException
    {
        Person person = iPersonService.getPerson(id);
        return person.getEmail();
    }
}
