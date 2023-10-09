import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        Connection c = null;
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.dir") + "/src/main/resources/Database.sqlite");

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/Database.sqlite");
        } catch ( Exception e ) {
            System.out.printf("Stack trace: ");
            e.printStackTrace(System.out);
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        try {
            final PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS test(t INTEGER)");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
