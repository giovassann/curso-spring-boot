package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customersList = new ArrayList<>(Arrays.asList(
            new Customer(1,"John Doe","doejohn","doe123"),
            new Customer(2,"Dio Brando","brandodio","dio123"),
            new Customer(3,"Charlotte Linlin","linlincharlotte","linlin123"),
            new Customer(4,"Hela Odinsdottir","odinsdottirhela","hela123")
    ));

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(this.customersList);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getCustomer(@PathVariable("username") String username){
        for(Customer customer: customersList){
            if(customer.getUsername().equals(username)){
                return ResponseEntity.ok(customer);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Customer with username '%s' not found.",username));
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
        this.customersList.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Customer with username '%s' was created successfully.", customer.getUsername()));
    }

    @PutMapping
    public ResponseEntity<?> modifyCustomer(@RequestBody Customer customer){
        for(Customer c: customersList){
            if(c.getID() == (customer.getID())){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.ok().body(String.format("Customer with username '%s' was modified successfully.", customer.getUsername()));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Customer with id %1d not found.",customer.getID()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") int id){
        for(Customer c: customersList){
            if(c.getID() == id){
                customersList.remove(c);
                return ResponseEntity.ok().body(String.format("Customer with id %1d successfully deleted.", id));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Customer with id %1d not found.",id));
    }

    @PatchMapping
    public ResponseEntity<?> patchCustomer(@RequestBody Customer customer){
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
                return ResponseEntity.ok().body(String.format("Customer with id '%1d' was modified successfully.", customer.getID()));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Customer with id %1d not found.",customer.getID()));
    }
}
