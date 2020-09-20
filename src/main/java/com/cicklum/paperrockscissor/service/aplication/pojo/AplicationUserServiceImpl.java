package com.cicklum.paperrockscissor.service.aplication.pojo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cicklum.paperrockscissor.service.aplication.poji.AplicationUserService;
import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.model.User;

/**
 * User AplicationService
 *
 * @author manuel.millan
 */
@Service
public class AplicationUserServiceImpl implements AplicationUserService {

    private final List<User> listUsers;

    public AplicationUserServiceImpl(List<User> users) {this.listUsers = users;}

    /**
     * Function to create user in listUsers's bean
     * @param userName username to create, must be unique
     * @param password password's userName
     * @return User created
     * @throws UserDuplicatedException throwed when userName already exist in listUsers's
     */
    @Override
    public User createUser(String userName, String password) throws UserDuplicatedException {

	if (findByUserName(userName).isPresent()) {
	    throw new UserDuplicatedException("User allready exist: " + userName);
	}
	User userCreated = new User(userName, password);
	listUsers.add(userCreated);

	return userCreated;

    }

    /**
     * function to fins a userName in listUsers's bean
     * @param userName to find
     * @return Optional<User>
     */
    @Override
    public Optional<User> findByUserName(String userName) {
	return listUsers.stream()
		.filter(e->userName.equals(e.getUserName()))
		.findAny();
    }


}
