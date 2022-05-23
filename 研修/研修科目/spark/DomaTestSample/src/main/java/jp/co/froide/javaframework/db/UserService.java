package jp.co.froide.javaframework.db;

import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDaoImpl(DbConfig.singleton());
    private TransactionManager tm = DbConfig.singleton().getTransactionManager();

    private UserService() {
        // nop
    }

    private static final UserService instance = new UserService();

    public static UserService getInstance() {
        return instance;
    }

    public List<User> selectAll() {
        List<User> users = tm.required(() -> userDao.selectAll());
        return users;
    }

    public void insert(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        tm.required(() -> userDao.insert(user));
    }

    public void delete(int id) {
        tm.required(() -> {
            User user = userDao.selectById(id);
            if (user != null) {
                userDao.delete(user);
            }
        });
    }
}
