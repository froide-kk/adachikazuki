package jp.co.froide.javaframework.db;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

import javax.sql.DataSource;

public class DbTestConfig implements Config {
    private static final DbTestConfig CONFIG = new DbTestConfig();

    private final Dialect dialect;

    private final LocalTransactionDataSource dataSource;

    private final LocalTransactionManager transactionManager;

    private DbTestConfig() {
        dialect = new H2Dialect();
        dataSource = new LocalTransactionDataSource(
                "jdbc:h2:mem:javaframework;INIT=RUNSCRIPT FROM 'classpath:db/create.script';NON_KEYWORDS=USER",
                "sa",
                null
        );
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

    public LocalTransactionManager getLocalTransactionManager() {
        return transactionManager;
    }

    public static DbTestConfig singleton() {
        return CONFIG;
    }
}
