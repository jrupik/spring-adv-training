package pl.training.shop;

import java.util.ArrayList;
import java.util.List;

public class ArrayListUserService implements UserService {

    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

}
