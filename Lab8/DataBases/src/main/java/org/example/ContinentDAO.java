package org.example;

import java.sql.*;

public class ContinentDAO {
    public void create(Connection con, String name) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into continents (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(Connection con, String name) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from continents where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public String findById(Connection con, int id) throws SQLException {
        try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "select name from continents where id='" + id+"'")
        ){
            return rs.next() ? rs.getString(1) : null;
        }
    }
}