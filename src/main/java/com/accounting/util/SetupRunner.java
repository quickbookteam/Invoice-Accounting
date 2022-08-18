package com.accounting.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.accounting.entity.User;
import com.accounting.repositery.UserRepository;
import com.accounting.scheduler.ConnectionSchedular;

@Component
public class SetupRunner implements CommandLineRunner {

	private ConnectionSchedular connectionSchedular;
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public SetupRunner(ConnectionSchedular connectionSchedular ,UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.connectionSchedular = connectionSchedular;
		this.userRepository=userRepository;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;

	}

	public void run(String... args) throws Exception {
		connectionSchedular.connectionStablished();
		User user=User.builder()
				.userName("admin")
				.password(this.bCryptPasswordEncoder.encode("admin"))
				.build();
		this.userRepository.save(user);

	}
	}
