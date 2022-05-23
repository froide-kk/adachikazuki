package jp.co.froide.javaframework.db;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.seasar.doma.jdbc.criteria.expression.Expressions;

import java.util.List;
import java.util.Objects;

public class UserRepository {
    private final Entityql entityql;
    private final NativeSql nativeSql;

    public UserRepository(Config config) {
        Objects.requireNonNull(config);
        entityql = new Entityql(config);
        nativeSql = new NativeSql(config);
    }

    public User selectById(int id) {
        User_ u = new User_();
        return entityql.from(u).where(c -> c.eq(u.id, id)).fetchOne();
    }

    public User selectByName(String name) {
        User_ u = new User_();
        return entityql.from(u).where(c -> c.eq(u.name, name)).fetchOne();
    }

    public List<User> select(Integer offset, Integer limit) {
        User_ u = new User_();
        return nativeSql.from(u).orderBy(c -> c.asc(u.id)).offset(offset).limit(limit).fetch();
    }

    public List<User> selectAll() {
        return select(null, null);
    }

    public long selectCount() {
        User_ u = new User_();
        return nativeSql.from(u).select(Expressions.count()).fetchOptional().orElse(0L);
    }

    public void insert(User user) {
        User_ u = new User_();
        entityql.insert(u, user).execute();
    }

    public void insertByNativeSql(User user) {
        User_ u = new User_();
        nativeSql
                .insert(u)
                .values(
                        c -> {
                            c.value(u.name, user.getName());
                            c.value(u.password, user.getPassword());
                        })
                .execute();
    }

    public void update(User user) {
        User_ u = new User_();
        entityql.update(u, user).execute();
    }

    public void updateByNativeSql(User user) {
        User_ u = new User_();
        nativeSql
                .update(u)
                .set(
                        c -> {
                            c.value(u.name, user.getName());
                            c.value(u.password, user.getPassword());
                        }
                )
                .where(c -> c.eq(u.id, user.getId()))
                .execute();
    }

    public void delete(User user) {
        User_ u = new User_();
        entityql.delete(u, user).execute();
    }

    public void deleteByNativeSql(User user) {
        User_ u = new User_();
        nativeSql.delete(u).where(c -> c.eq(u.id, user.getId())).execute();
    }

    public void batchInsert(List<User> users) {
        User_ u = new User_();
        entityql.insert(u, users).execute();
    }

    public void batchUpdate(List<User> users) {
        User_ u = new User_();
        entityql.update(u, users).execute();
    }

    public void batchDelete(List<User> users) {
        User_ u = new User_();
        entityql.delete(u, users).execute();
    }
}
