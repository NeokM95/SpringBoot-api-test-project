package nl.koenm.apitest.service;

import nl.koenm.apitest.exception.RecordNotFoundException;
import nl.koenm.apitest.model.User;
import nl.koenm.apitest.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findUserById(long nr) {
        if (!userRepository.existsById(nr)) { throw new RecordNotFoundException("User does not exist"); }
        return userRepository.findById(nr).orElse(null);
    }


    @Override
    public void saveUser(User user) {

        if (userRepository.existsByUsernameIgnoreCase(user.getUsername())) {
            throw new RecordNotFoundException("User does not exist");
        } else {
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUserById(long nr) {
        if (!userRepository.existsById(nr)) { throw new RecordNotFoundException("User does not exist"); }
        userRepository.deleteById(nr);
    }

    @Override
    public Object getUserByUsername(String name) {
        if (!userRepository.existsByUsernameIgnoreCase(name)) { throw new RecordNotFoundException("User does not exist"); }
        return userRepository.findUserByUsername(name);
    }
}
