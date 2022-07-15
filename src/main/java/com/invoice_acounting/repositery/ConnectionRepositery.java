package com.invoice_acounting.repositery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice_acounting.entity.Connection;

@Repository
public  interface ConnectionRepositery extends JpaRepository<Connection, Long> {

}
