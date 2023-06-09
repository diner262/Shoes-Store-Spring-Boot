package tdtu.edu.vn.shoes_store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import tdtu.edu.vn.shoes_store.model.Role;
import tdtu.edu.vn.shoes_store.model.User;
import tdtu.edu.vn.shoes_store.repository.RoleRepository;

import java.util.Collections;

@SpringBootApplication
public class ShoesStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShoesStoreApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

	}
}
