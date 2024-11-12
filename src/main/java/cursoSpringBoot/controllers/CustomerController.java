package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    private List<Customer> customersList = new ArrayList<>(Arrays.asList(
            new Customer(1,"John Doe","doejohn","doe123"),
            new Customer(2,"Dio Brando","brandodio","dio123"),
            new Customer(3,"Charlotte Linlin","linlincharlotte","linlin123"),
            new Customer(4,"Hela Odinsdottir","odinsdottirhela","hela123")
    ));

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return this.customersList;
    }

    @GetMapping("/customer/{username}")
    public Customer getCustomer(@PathVariable("username") String username){
        for(Customer customer: customersList){
            if(customer.getUsername().equals(username)){
                return customer;
            }
        }
        return null;
    }

    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer customer){
        this.customersList.add(customer);
        return customer;
    }

    @PutMapping("/customer")
    public Customer modifyCustomer(@RequestBody Customer customer){
        for(Customer c: customersList){
            if(c.getID() == (customer.getID())){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable("id") int id){
        for(Customer c: customersList){
            if(c.getID() == id){
                customersList.remove(c);
                return "Customer succesfully deleted";
            }
        }
        return null;
    }

    @PatchMapping("/customer")
    public Customer patchCustomer(int id, Customer customer){
        for(Customer c: customersList){
            if(c.getID() == customer.getID()){
                if (customer.getUsername() != null){
                    c.setUsername(customer.getUsername());
                }

                if (customer.getName() != null){
                    c.setName(customer.getName());
                }

                if (customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }
            }
        }
        return null;
    }
}
