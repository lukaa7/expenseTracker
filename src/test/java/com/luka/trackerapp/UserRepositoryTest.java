package com.luka.trackerapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.luka.trackerapp.model.Role;
import com.luka.trackerapp.model.User;
import com.luka.trackerapp.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("nam@gmail.com");
		user.setPassword("name");
		user.setFirstName("First");
		user.setLastName("Last");
		user.setUsername("name123");
		
		User savedUser = repository.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail().equals(user.getEmail()));
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "nam@gmail.com";
				
		User user = repository.findByEmail(email);		
				
		assertThat(user).isNotNull();	
				
	}
	
	@Test
	public void testCreateRoles() {
		Role roleVisitor = new Role("Visitor");
		
		entityManager.persist(roleVisitor);
		
	}
	
	@Test
	public void testAssignRoleToExistingUser() {
		User user = repository.findById(1).get();
		Role roleEditor = entityManager.find(Role.class, 2);
		user.addRole(roleEditor);
	}
	
	@Test
	public void testGetUser() {
		User user = repository.findById(1).get();
		entityManager.detach(user);
		
		System.out.println(user.getEmail());
		System.out.println(user.getRoles());
	}
	
	@Test
	public void testRemoveUser() {
		repository.deleteById(4);
	}
}
