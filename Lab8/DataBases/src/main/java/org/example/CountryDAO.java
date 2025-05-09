package org.example;

import java.sql.*;
public class CountryDAO
{
    public void create(Connection con, String name, String code, int id) throws SQLException {
        try(PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (name, code, continent) values (?, ?, ?)")){
            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(Connection con, String name) throws SQLException {
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                "select id from countries where name ='"+name+"'")){
            return rs.next()?rs.getInt(1):null;
        }
    }
    public String findById(Connection con, int id) throws SQLException {
        try(Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "select name from countries where id ='"+id+"'")){
            return rs.next()?rs.getString(1):null;
        }
    }
}
