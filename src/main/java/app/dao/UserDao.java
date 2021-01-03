package app.dao;
import org.springframework.security.core.userdetails.UserDetails;
import app.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUser(long id);

    void save(User user);

    void update(User user);

    void delete(long id);

    UserDetails loadUserByUsername(String userName);
}
