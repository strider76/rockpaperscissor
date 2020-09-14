package com.cicklum.paperrockscissor.aplication.pojo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cicklum.paperrockscissor.aplication.poji.AplicationUserService;
import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.model.User;

@Service
public class AplicationUserServiceImpl implements AplicationUserService {

    private final List<User> listUsers;

    public AplicationUserServiceImpl(List<User> users) {this.listUsers = users;}

    @Override
    public User createUser(String userName, String password) throws UserDuplicatedException {

	if (findByUserName(userName).isPresent()) {
	    throw new UserDuplicatedException("User allready exist: " + userName);
	}
	User userCreated = new User(userName, password);
	listUsers.add(userCreated);

	return userCreated;

    }

    @Override
    public Optional<User> findByUserName(String userName) {
	return listUsers.stream()
		.filter(e->userName.equals(e.getUserName()))
		.findAny();
    }


}
