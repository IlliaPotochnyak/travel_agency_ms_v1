package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

//    static {
//        config.setDriverClassName("com.mysql.jdbc.Driver");
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/travel_agency_db1");
//        config.setUsername( "root" );
//        config.setPassword( "root" );
//        config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
//        ds = new HikariDataSource( config );
//    }
//    static {
//        config.setDriverClassName(DRIVER_CLASS_NAME);
//        config.setJdbcUrl(DB_URL);
//        config.setUsername(DB_USER_NAME);
//        config.setPassword(DB_PASSWORD);
//        config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
//        ds = new HikariDataSource( config );
//    }
    static {
        Properties properties = new Properties();
        String connectionFile = "MySQLProperties.properties";
        try (InputStream resource = DataSource.class.getClassLoader().getResourceAsStream(connectionFile)){
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setDriverClassName(properties.getProperty("driverClassName"));
        config.setJdbcUrl(properties.getProperty("url"));
        config.setUsername(properties.getProperty("username"));
        config.setPassword(properties.getProperty("password"));
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }


    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}