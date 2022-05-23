package jp.co.froide.javaframework.db;

import org.junit.jupiter.api.Test;
import org.seasar.doma.Delete;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.seasar.doma.internal.util.AssertionUtil.assertNull;

public class UserDaoTest {
    @Test
    public void testSelectById() {
        UserDao userDao = new UserDaoImpl(DbTestConfig.singleton());
        TransactionManager tm = DbTestConfig.singleton().getTransactionManager();
        tm.required(() -> {
            User selectUser = userDao.selectById(101);
            assertNotNull(selectUser);
        });
    }

    @Test
    public void testSelectAll() {
        //取得できる配列の長さが５
        UserDao userDao = new UserDaoImpl(DbTestConfig.singleton());
        TransactionManager tm = DbTestConfig.singleton().getTransactionManager();
        tm.required(() -> {
            List<User> selectArray = userDao.selectAll();
            assertEquals(5, selectArray.size());
        });

    }

    @Test
    public void testInsert() {
        //追加するデータは適当でOK
        //追加した後にselectAllで取得できる長さが６
        //追加されたデータの値がちゃんと合っていること
        UserDao userDao = new UserDaoImpl(DbTestConfig.singleton());
        TransactionManager tm = DbTestConfig.singleton().getTransactionManager();

//            insert into `User` values(106, 'adachi/', 'password');
//            id=106
//            name=adachi
//            password=password

        //追加するデータの定義
        User Adachi = new User();
        Adachi.id = 106;
        Adachi.name = "adachi";
        Adachi.password = "passwaord";
        //定義したデータをUser型でインサートする
        tm.required(() -> {
            userDao.insert(Adachi);
            //追加したデータを含めselectAllで取得する
            List<User> insertArray = userDao.selectAll();
            //追加したデータ合わせて配列の長さが６になっているかの確認
            assertEquals(6, insertArray.size());
            //追加したデータの中身を確認して、合っているかの比較。
            String name = "adachi";
            assertEquals(name, Adachi.name);
        });


    }


    @Test
    public void testUpdater() {
        UserDao userDao = new UserDaoImpl(DbTestConfig.singleton());
        TransactionManager tm = DbTestConfig.singleton().getTransactionManager();
        //idが101のデータをsueokaにかえる
        //変更後にidのデータのnameがsueokanになっていること

        //データ(101)の参照
        tm.required(() -> {
            User updata = userDao.selectById(101);
            updata.name = "sueoka";
            assertEquals("sueoka", updata.name);
        });

    }

    @Test
    public void testDelete() {
        UserDao userDao = new UserDaoImpl(DbTestConfig.singleton());
        TransactionManager tm = DbTestConfig.singleton().getTransactionManager();
        tm.required(() -> {
            //idが101を参照
            tm.required(() -> {
                User delete101 = userDao.selectById(101);
                userDao.delete(delete101);
                //削除後に101のデータが取得できないこと
                User deleted101 = userDao.selectById(101);
                assertNull(deleted101);
        });
    });
}
}
