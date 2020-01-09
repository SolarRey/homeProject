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

    @Test
    public void H2Add() throws SQLException {
        String url = "jdbc:h2:mem:";

        Connection con = DriverManager.getConnection(url);
        Statement stm = con.createStatement();
        stm.executeUpdate("CREATE TABLE CUSTOMER (id number, name varchar(20), age number, address varchar(20), \n" +
                "salary number);  \n" +
                "\n" +
                "INSERT into CUSTOMER values (1, 'Ramesh', 32, 'Ahmedabad', 2000); \n" +
                "INSERT into CUSTOMER values (2, 'Khilan', 25, 'Delhi', 1500); \n" +
                "INSERT into CUSTOMER values (3, 'kaushik', 23, 'Kota', 2000); \n" +
                "INSERT into CUSTOMER values (4, 'Chaitali', 25, 'Mumbai', 6500); \n" +
                "INSERT into CUSTOMER values (5, 'Hardik', 27, 'Bhopal', 8500); \n" +
                "INSERT into CUSTOMER values (6, 'Komal', 22, 'MP', 4500); \n" +
                "INSERT into CUSTOMER values (7, 'Muffy', 24, 'Indore', 10000);");

        ResultSet rs = stm.executeQuery("SELECT * from CUSTOMER");
        while (rs.next()) {
            System.out.println(rs.getInt(1));
        }
    }
}
