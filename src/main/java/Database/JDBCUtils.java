package Database;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    public static DruidDataSource dataSource;
    static {
        try {
            //读取配置文件内容
            Properties properties=new Properties();
            InputStream inputStream=JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            //加载配置文件
            properties.load(inputStream);
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    null失败，有值成功!
    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn=dataSource.getConnection();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

//关闭连接
    public static void Close(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
