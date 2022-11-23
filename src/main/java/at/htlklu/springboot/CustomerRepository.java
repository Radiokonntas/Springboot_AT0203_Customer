package at.htlklu.springboot;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    //!!
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    //!!

    Customer findCustomerById(Integer id);  // Spring Data JPA will derive a query based on this method's signature
                                            // (=Methodparameter), which will select the Customer object for
                                            // the specified ID.

    List<Customer> findByLastName(String lastname);
}