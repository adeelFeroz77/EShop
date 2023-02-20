package com.EShop.EShop.service;

import com.EShop.EShop.domain.Customer;
import com.EShop.EShop.dto.CustomerDto;
import com.EShop.EShop.exception.RecordAlreadyExistException;
import com.EShop.EShop.exception.RecordNotFoundException;
import com.EShop.EShop.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper){
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public CustomerDto getById(Long id){
        return toDto(findById(id));
    }

    public List<CustomerDto> getAllCustomers(){
        return customerRepository.findAllByIsActive(Boolean.TRUE)
                .stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto saveCustomer(CustomerDto customerDto){
        Customer c = toDo(customerDto);
        Optional<Customer> customer = customerRepository.findByEmail(c.getEmail());
        Optional<Customer> customerN = customerRepository.findByCellNumber(c.getCellNumber());
        if(customer.isEmpty() && customerN.isEmpty()){
            return toDto(customerRepository.save(c));
        }
        if(customer.isPresent() && customerN.isPresent() && customer.get().getId()==customerN.get().getId() && !customer.get().getIsActive()){
            customer.get().setIsActive(Boolean.TRUE);
            return toDto(customerRepository.save(customer.get()));
        }
        throw new RecordAlreadyExistException("Record Already Exist");
    }

    public CustomerDto updateByFields(Long id, Map<String,Object> fields){
        Customer c = findById(id);
        fields.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Customer.class,key);
            field.setAccessible(Boolean.TRUE);
            ReflectionUtils.setField(field,c,value);
        });
        return toDto(customerRepository.save(c));
    }

    public String deleteById(Long id){
        Customer c = findById(id);
        c.setIsActive(Boolean.FALSE);
        customerRepository.save(c);
        return String.format("Customer deleted on Id:%d",id);
    }

    private Customer toDo(CustomerDto customerDto){
        return modelMapper.map(customerDto,Customer.class);
    }

    private CustomerDto toDto(Customer customer){
        return modelMapper.map(customer,CustomerDto.class);
    }

    private Customer findById(Long id){
        return customerRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException(String.format("Record not found on Id:%d",id))
        );
    }
}
