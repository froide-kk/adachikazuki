package jp.co.froide.javaframework.db;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

import javax.sql.DataSource;

public class DbConfig implements Config {
    private static final DbConfig CONFIG = new DbConfig();

    private final Dialect dialect;

    private final LocalTransactionDataSource dataSource;

    private final TransactionManager transactionManager;

    private DbConfig() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialect = new MysqlDialect();
        dataSource = new LocalTransactionDataSource("jdbc:mysql://localhost:13306/USER", "root", "secret");
        transactionManager = new LocalTransactionManager(dataSource.getLocalTransaction(getJdbcLogger()));
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public static DbConfig singleton() {
        return CONFIG;
    }
}
