package at.htlklu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

    /*
        Here is what each Spring annotation means:

        The @RestController annotation marks the DemoController class as a request handler (a REST controller).
        IntelliJ IDEA designates it with The Java Bean icon in the gutter, which you can click to navigate to the
        corresponding Spring bean declaration.

        The @Autowired annotation tells Spring to inject the customerRepository bean, which is implemented from the
        repository interface. IntelliJ IDEA designates it with The autowired dependencies icon in the gutter, which
        you can click to navigate to the corresponding autowired dependency.

        The @PostMapping("/add") annotation maps the addCustomer() method to POST requests for /add.

        The @RequestParam annotations map the method parameters to the corresponding web request parameters.

        The @GetMapping("/list") annotation maps the getCustomers() method to GET requests for /list.

        The @GetMapping("/find/{id}") annotation maps the findCustomerById() method to GET requests for /find/{id}.

        The @PathVariable annotation maps the value in place of the id variable from the URL to the corresponding
        method parameter.
     */

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/add")
    public String addCustomer(@RequestParam(value="firstAttr", required=false, defaultValue = "firstname") String first,
                              @RequestParam(value="secondAttr", required=true) String sur) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(sur);
        customerRepository.save(customer);
        return "Added new customer to repo!";
    }

    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

   @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
       Optional<Customer> customerbyid = customerRepository.findById(id);
       if (customerbyid.isPresent()) {
           return customerbyid.get();
       } else
           return null;

       // return customerRepository.findCustomerById(id);
   }

    @GetMapping("/findMe/{id}")
    public Customer findCustomerByIdWithOwnRepoMethod(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id);
    }

    @GetMapping("/findMeBySurname/{lastname}")
    public ArrayList<Customer> findCustomerByNameWithOwnRepoMethod(@PathVariable String lastname) {
        return (ArrayList<Customer>) customerRepository.findByLastName(lastname);
    }
}
