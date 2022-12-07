package com.mike.controller;

import com.mike.model.Customer;
import com.mike.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{id}")
    public void deleteCustomerById(@PathVariable Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Integer id, @RequestBody Customer updateCustomer){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(!customerOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found!");
        }
        var customer = customerOptional.get();
        customer.setAge(updateCustomer.getAge());
        customer.setEmail(updateCustomer.getEmail());
        customer.setName(updateCustomer.getName());

        return ResponseEntity.status(HttpStatus.OK).body(customerRepository.save(customer));
    }
}
