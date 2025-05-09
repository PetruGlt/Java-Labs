package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;

public class Importer {
    public void importer(Connection connection, String path){
     try(BufferedReader br = new BufferedReader(new FileReader(path))){
         String line=br.readLine();
         var citiyDao=new CityDAO();
         var countryDao=new CountryDAO();
         var continentDao=new ContinentDAO();
         while((line=br.readLine())!=null){
            String[] tokens=line.split(",", -1);
            if(tokens.length<6)continue;
            String countryName=tokens[0].trim();
            String capitalName=tokens[1].trim();
            double latitude=Double.parseDouble(tokens[2]);
            double longitude=Double.parseDouble(tokens[3]);
            String countryCode=tokens[4].trim();
            String continentName=tokens[5].trim();

            var continentId=continentDao.findByName(connection, continentName);
            if(continentId==null){
                continentDao.create(connection,continentName);
                connection.commit();
                continentId=continentDao.findByName(connection, continentName);
            }
            var countryId=countryDao.findByName(connection, countryName);
            if(countryId==null){
                countryDao.create(connection,countryName, countryCode, continentId );
                connection.commit();
                countryId=countryDao.findByName(connection, countryName);
            }
            var cityId=citiyDao.findByName(connection, capitalName);
            if(cityId==null){
                if(!capitalName.equals("N/A")) {
                    citiyDao.create(connection, countryName, capitalName,true, latitude, longitude );
                    connection.commit();
                }
            }
         }
     }
     catch(Exception e){
         System.out.println("Importer error: "+e.getMessage());
     }
    }
}
