package at.htlklu.springboot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Basic;
import java.util.Objects;

@Entity
@Table(name="customer", schema="springbootat0203")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //zur Behebung eines Bugs zu Json benötigt...
public class Customer {

    //region attributes
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)   //would lead to Exception at Execution (!!),
                                                            // because ID has in DB no Flag for AUTO-GENERATED VALUE!!!
    @Id
    @Column(name="id", nullable=false)
    @JsonIgnore //...wird somit im JSON String nicht hinzugefügt
                //(Vorsicht: meist wird aber die ID benötigt, hier nur als BSP!)
    private Integer id;

    @Basic
    @Column(name="first_name", nullable = true, length = 255)
    private String firstName;

    @Basic
    @Column(name="last_name", nullable = true, length = 255)
    private String lastName;
    //endregion

    //region ctors
    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //endregion

    //region getter and setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //endregion

    //region addDetails
    //if Customer gets MASTER Table (One-To-Many) >> addDetail Method is required!
    //endregion

    //region toString, equals, hashCode
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
    //endregion

    //region methods
    //endregion



}