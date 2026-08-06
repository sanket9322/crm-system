 package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.payload.RegisterRequest;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
 @Autowired
    private   UserRepository repo;
   @Autowired
    private   PasswordEncoder encoder;


   @Override
   public User register(RegisterRequest request) {

       User user = new User();
       user.setName(request.getName());
       user.setEmail(request.getEmail());
       user.setContact(request.getContact());
       user.setCity(request.getCity());
       user.setPassword(encoder.encode(request.getPassword()));

       Role role = Role.valueOf("ROLE_" + request.getRole().toUpperCase());
       user.setRole(role);
       return repo.save(user);
   }


   @Override
   public User create(User user) {
	// TODO Auto-generated method stub
	return repo.save(user);
   }


   @Override
   public List<User> getAll() {
	// TODO Auto-generated method stub
	return repo.findAll();
   }


   @Override
   public User getById(Long id) {
	// TODO Auto-generated method stub
	return repo.getById(id);
   }


   public User update(Long id, User user) {
	// TODO Auto-generated method stub
	   
	   User u = repo.getById(id);
	   u.setName(user.getName());
	   u.setEmail(user.getEmail());
	   u.setCity(user.getCity());
	   u.setContact(user.getContact());
	   u.setPassword(user.getPassword());
	   u.setRole(user.getRole());
	return repo.save(u);
   }


   @Override
   public void delete(Long id) {
	// TODO Auto-generated method stub
	 repo.deleteById(id);
   }


}

	


