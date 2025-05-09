    package org.example;

    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Scanner;

    public class Main {
        public static void main(String args[]) {
            try {
                var connection=Database.getConnection();




//                var continents = new ContinentDAO();
////                continents.create(connection, "Europe");
////                continents.create(connection, "Africa");
//
//                var countries = new CountryDAO();
//                int europeId = continents.findByName(connection, "Europe");
//                int africaId = continents.findByName(connection, "Africa");
//
////                countries.create(connection, "Romania","RO", europeId);
////                countries.create(connection, "Ukraine","UA", europeId);
////                countries.create(connection, "Egypt", "EG", africaId);
//                String sql = "select name from countries where continent ='"+europeId+"'";
//                try (var stmt = Database.getConnection().createStatement()) {
//                    ResultSet rs = stmt.executeQuery(sql);
//                    while (rs.next()) {
//                        System.out.println(rs.getString(1));
//                    }
//                }
//                connection.commit();




//                var Importer = new Importer();
//                Importer.importer(connection, "/Users/alexrimniceanu/Facultate/Anul_2/Semestrul_2/Java/Lab8/DataBases/src/main/resources/concap.csv");
                var cityDao=new CityDAO();
                Scanner scanner = new Scanner(System.in);
                while(true) {
                    System.out.print("Enter the first city: ");
                    String cityName1=scanner.nextLine().trim();
                    if (cityName1.equals("")||cityName1.equals("exit")) {
                        break;
                    }
                    System.out.print("Enter the second city: ");
                    String cityName2=scanner.nextLine().trim();
                    if (cityName2.equals("")||cityName2.equals("exit")) {
                        break;
                    }
                    var cityId1 = cityDao.findByName(connection, cityName1);
                    var cityId2 = cityDao.findByName(connection, cityName2);
                    if (cityId1 == null || cityId2 == null) {
                        System.out.println("Couldnt Compute");
                        if (cityId1 == null) {
                            System.out.println("Couldnt find "+ cityName1);
                        }
                        if (cityId2 == null) {
                            System.out.println("Couldnt find "+ cityName2);
                        }
                    } else {
                        var cityDistance = Distance.distance(cityDao.getLatitude(connection, cityId1), cityDao.getLongitude(connection, cityId1), cityDao.getLatitude(connection, cityId2), cityDao.getLongitude(connection, cityId2));
                        System.out.println("Distance between the 2 cities: " + cityDistance + " Kilometers");
                    }
                }
            } catch (SQLException e) {
                System.err.println(e);
                try{
                Database.getConnection().rollback();}
                catch (SQLException ex){
                    System.err.println("Error getting the connection to rollback "+ex.getMessage());
                }
            }
        }
    }