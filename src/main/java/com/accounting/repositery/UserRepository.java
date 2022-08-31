package com.accounting.repositery;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.accounting.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{

    User findByUserName(String username);
}
