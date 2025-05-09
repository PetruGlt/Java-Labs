package org.example;

import java.sql.*;
public class CityDAO
{
    public void create(Connection con, String country, String name, boolean capital, double latitude, double longitude) throws SQLException {
        try(PreparedStatement pstmt = con.prepareStatement(
                "insert into cities (country, name, capital, latitude, longitude) values (?, ?, ?, ?, ?)")){
            pstmt.setString(1, country);
            pstmt.setString(2, name);
            pstmt.setInt(3, capital ? 1 : 0);
            pstmt.setDouble(4, latitude);
            pstmt.setDouble(5, longitude);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(Connection con, String name) throws SQLException {
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select id from cities where name ='"+name+"'")){
            return rs.next()?rs.getInt(1):null;
        }
    }
    public String findById(Connection con, int id) throws SQLException {
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select name from cities where id ='"+id+"'")){
            return rs.next()?rs.getString(1):null;
        }
    }

    public double getLatitude(Connection con, int id) throws SQLException {
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select latitude from cities where id ='"+id+"'")){
            return rs.next()?rs.getDouble(1):0;
        }
    }

    public double getLongitude(Connection con, int id) throws SQLException {
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select longitude from cities where id ='"+id+"'")){
            return rs.next()?rs.getDouble(1):0;
        }
    }
}
