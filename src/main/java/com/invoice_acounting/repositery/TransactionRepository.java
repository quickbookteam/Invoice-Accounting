package com.invoice_acounting.repositery;

import com.invoice_acounting.entity.transaction.LocalTransaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("transactionRepository")
public interface TransactionRepository extends MongoRepository<LocalTransaction,String> {

    LocalTransaction findByTransactionId(String id);

}
