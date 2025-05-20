package shopping.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.onlineshopping.dto.CustomerDTO;
import shopping.onlineshopping.mapper.CustomerMapper;
import shopping.onlineshopping.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDTO getCustomerById(Long id){
        return customerMapper.toCustomerDTO(customerRepository.findById(id).get());
    }
    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        return customerMapper.toCustomerDTO(customerRepository.save(customerMapper.toCustomer(customerDTO)));
    }
    public void deleteCustomerById(Long id){
        customerRepository.deleteById(id);
    }
    public CustomerDTO updateCustomer(CustomerDTO customerDTO){
        return customerMapper.toCustomerDTO(customerRepository.save(customerMapper.toCustomer(customerDTO)));
    }
    public List<CustomerDTO> getAllCustomers(){
        return customerRepository.findAll().stream().map(customerMapper::toCustomerDTO).toList();
    }
    public CustomerDTO getCustomerByEmail(String email){
        return customerMapper.toCustomerDTO(customerRepository.findByEmail(email));
    }
    public CustomerDTO getCustomerByContactName(String contactName){
        return customerMapper.toCustomerDTO(customerRepository.findByContactName(contactName));
    }
    public List<CustomerDTO> getAllCustomersByCountry(String country){
        return customerRepository.findByCountry(country).stream().map(customerMapper::toCustomerDTO).toList();
    }
    public List<CustomerDTO> getAllCustomersByRegion(String region){
        return customerRepository.findByRegion(region).stream().map(customerMapper::toCustomerDTO).toList();
    }
    public List<CustomerDTO> getAllCustomersByCity(String city){
        return customerRepository.findByCity(city).stream().map(customerMapper::toCustomerDTO).toList();
    }
}
