package com.service;

import com.dtos.PaymentDTO;
import com.repository.TransactionRepository;
import com.repository.entity.Payment;
import com.repository.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public void createTransaction(PaymentDTO paymentDTO) {
        var transaction = Transaction.builder()
                .payment(modelMapper.map(paymentDTO, Payment.class)).build();

       transactionRepository.save(transaction);
    }
}
