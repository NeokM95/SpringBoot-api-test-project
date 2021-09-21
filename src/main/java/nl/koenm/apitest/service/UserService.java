package nl.koenm.apitest.service;


import nl.koenm.apitest.model.User;

public interface UserService {

    Iterable<User> findAllUsers();
    User findUserById(long nr);
    void saveUser(User user);
    void deleteUserById(long nr);
    Object getUserByUsername(String name);
}
