package jp.co.froide.javaframework.db;

import org.seasar.doma.*;

import java.util.List;

@Dao
public interface UserDao {

    @Select
    User selectById(Integer id);

    @Select
    List<User> selectAll();

    @Insert
    int insert(User user);


    @Update
    int update(User user);



    @Delete
    int delete(User user);


    @BatchInsert
    int[] batchInsert(List<User> users);

    @BatchUpdate
    int[] batchUpdate(List<User> users);

    @BatchDelete
    int[] batchDelete(List<User> users);
}
