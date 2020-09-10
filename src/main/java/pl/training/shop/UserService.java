package pl.training.shop;

import java.util.List;

public interface UserService {

    List<User> getAll();

    void add(User user);

}
