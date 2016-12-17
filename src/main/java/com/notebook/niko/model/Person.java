package com.notebook.niko.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Realization POJO class
 * @author Nicolas Asinovich.
 */
@Entity
@Table (name = "person_list", catalog = "notebook")
public class Person implements Serializable
{
    private static final long serialVersionUID = 454567856767L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Pattern (regexp = "^[а-яА-Яa-zA-Z0-9_]{3,30}$", message = "{name_error}")
    private String name;
    @Pattern(regexp = "^.{6,30}$", message = "{email_error}")
    private String email;

    public Person ()
    {
    }

    public Person (String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString ()
    {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return email != null ? email.equals(person.email) : person.email == null;

    }

    @Override
    public int hashCode ()
    {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
