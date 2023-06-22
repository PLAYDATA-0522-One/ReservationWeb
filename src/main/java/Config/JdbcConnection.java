package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    //CREATE DATABASE `test2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
    //private final String url = "jdbc:mysql://localhost:3306/airplane" + "?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";

    //private final String url = "jdbc:mysql://localhost:3306/ticket_reservation" + "?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";

    private final String url = "jdbc:mysql://localhost:3306/airplane" + "?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";

    private final String root = "root";
    //private final  String pw = "hyun2002";
    private final String pw = "1q2w3e4r!!";

    public Connection getJdbc() {

        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, root, pw);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("연동 성공");
        return conn;
    }
}
