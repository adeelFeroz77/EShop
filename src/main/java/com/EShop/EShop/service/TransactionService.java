package com.EShop.EShop.service;

import com.EShop.EShop.domain.Transaction;
import com.EShop.EShop.dto.TransactionDto;
import com.EShop.EShop.exception.RecordNotFoundException;
import com.EShop.EShop.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    TransactionService(TransactionRepository transactionRepository,ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.transactionRepository = transactionRepository;
    }

    public TransactionDto getTransactionById(Long id){
        return toDto(findById(id));
    }

    public TransactionDto saveTransaction(TransactionDto transactionDto){
        Transaction t = toDo(transactionDto);
        t.setAmount(transactionRepository.getTotalAmount(t.getCart().getId()));
        return toDto(transactionRepository.save(t));
    }

    public List<TransactionDto> getAllTransaction(){
        return transactionRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private Transaction findById(Long id){
        return transactionRepository.findById(id).orElseThrow(
                ()->new RecordNotFoundException(String.format("Transaction not found on Id: %d",id))
        );
    }

    private Transaction toDo(TransactionDto transactionDto){
        return modelMapper.map(transactionDto, Transaction.class);
    }

    private TransactionDto toDto(Transaction transaction){
        return modelMapper.map(transaction,TransactionDto.class);
    }
}
