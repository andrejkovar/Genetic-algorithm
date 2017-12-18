package nasp.lab.modules;

import nasp.lab.GeneticAlgorithm;

import java.util.*;

public class Cities {

    private static Set<City> cities = new HashSet<>();

    public static List<Route> routes = new ArrayList<>();

    public static int getNumberOfCities(){
        return cities.size();
    }

    public static void initRoutes(){

        cities.add(new City("Paris", 48.853416, 2.342938));
        cities.add(new City("Tirana", 41.33, 19.82));
        cities.add(new City("Vienna", 48.21, 16.37));
        cities.add(new City("Minsk", 53.90,	27.57));
        cities.add(new City("Brussels",	50.85,	4.35));
        cities.add(new City("Sarajevo",	43.85,	18.36));
        cities.add(new City("Sofia",	42.70,	23.32));
        cities.add(new City("Zagreb",	45.81,	15.98));
        cities.add(new City("Prague",	50.09,	14.42));
        cities.add(new City("Copenhagen",	55.68,	12.57));
        cities.add(new City("Tallinn",	59.44,	24.75));
        cities.add(new City("Helsinki",	60.17,	24.94));
        cities.add(new City("Berlin",	52.52,	13.41));
        cities.add(new City("Gibraltar",	36.14,	-5.35));
        cities.add(new City("Athens",	37.98,	23.72));
        cities.add(new City("Budapest",	47.50,	19.04));
        cities.add(new City("Reykjavik", 	64.14,	-21.90));
        cities.add(new City("Dublin",	53.33,	-6.25));
        cities.add(new City("Rome",	41.89,	12.48));
        cities.add(new City("Pristina",	42.67,	21.17));
        cities.add(new City("Riga",	56.95,	24.11));
        cities.add(new City("Luxembourg",	49.61,	6.13));
        cities.add(new City("Skopje",	42.00,	21.43));

        for(int i = 0; i < GeneticAlgorithm.SIZE_OF_GENERATION; i++) {

            List<City> cities = new ArrayList<>(Cities.cities);
            Collections.shuffle(cities);
            Route route = new Route(cities);
            routes.add(route);
        }
    }
}
