package jp.co.froide.javaframework.db;


import org.seasar.doma.*;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    int insert(Book book);

    @Update
    int updata(Book book);

    @Delete
    int delete(Book book);

    @Select
    Book selectById(Integer id);

    @Select
    List<Book> selectAll();

    @Select
    List<Book> selectByName(String name);

}
