package com.accounting.repositery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accounting.entity.transaction.LocalTransaction;

@Repository
@Qualifier("transactionRepository")
public interface TransactionRepository extends MongoRepository<LocalTransaction,String> {

//    LocalTransaction findByTransactionId(String id);

}
