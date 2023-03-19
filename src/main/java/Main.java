import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Driver driver = DriverManager.getDriver("jdbc:postgresql://localhost:5432/postgres");
        System.out.println("JDBC driver version: " + driver.getMajorVersion() + "." + driver.getMinorVersion());
    }
}
