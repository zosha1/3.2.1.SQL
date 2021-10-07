package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbHelper {

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3308/app", "app", "pass"
        );
    }

    @SneakyThrows
    public static void cleanDB() {
        var conn = getConnection();
        QueryRunner qr=new QueryRunner();
        qr.execute(conn,"delete from auth_codes");
        qr.execute(conn,"delete from card_transactions");
        qr.execute(conn,"delete from cards");
        qr.execute(conn,"delete from users");
    }

    @SneakyThrows
    public static String getCode () {
        var runner = new QueryRunner();
        var conn = getConnection();
        var codeSQL = "SELECT code\n" +
                "from auth_codes ac \n" +
                "left join users u on u.id = ac.user_id \n" +
                "where u.login = 'vasya'\n" +
                "ORDER by ac.created desc\n" +
                "limit 1";
        String codeVer = runner.query(conn, codeSQL, new ScalarHandler<>());
        return codeVer;
    }
}
