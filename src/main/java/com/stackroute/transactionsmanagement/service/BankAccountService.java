package com.stackroute.transactionsmanagement.service;

import com.stackroute.transactionsmanagement.model.BankAccount;
import com.stackroute.transactionsmanagement.model.IsolationLevel;
import com.stackroute.transactionsmanagement.repository.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional
    public void transferMoney(Long fromAccountId, Long toAccountId, double amount, IsolationLevel isolationLevel) {
        //write business logic for transferring money from one account to another
    BankAccount b1 = bankAccountRepository.findById(fromAccountId).orElse(null);
    BankAccount b2 = bankAccountRepository.findById(toAccountId).orElse(null);
    if(b1!=null && b2!=null && (b1.getBalance()-amount)>=0) {
    	b1.setBalance(b1.getBalance()-amount);
    	b2.setBalance(b2.getBalance()+amount);
    	bankAccountRepository.save(b1);
    	bankAccountRepository.save(b2);
    	
    }
    else {
    	throw new RuntimeException();
    }
    }
}

