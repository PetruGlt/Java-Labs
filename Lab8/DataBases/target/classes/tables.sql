
create DATABASE World;

create table countries(
                          id INT AUTO_INCREMENT primary key  ,
                          name TEXT,
                          code TEXT,
                          continent INT
);

create table continents(
                           id INT AUTO_INCREMENT primary key ,
                           name TEXT
);

create table cities(
    id INT AUTO_INCREMENT primary key ,
    country TEXT,
    name TEXT,
    capital bool,
    latitude double,
    longitude double
);
