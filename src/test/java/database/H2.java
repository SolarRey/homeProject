package database;

import org.testng.annotations.Test;

import java.sql.*;


public class H2 {

    @Test
    public void H2Connect() throws SQLException {
        String url = "jdbc:h2:mem:";

        Connection con = DriverManager.getConnection(url);
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery("SELECT 1+1");

            if (rs.next()) {

                System.out.println(rs.getInt(1));
            }


    }
}
