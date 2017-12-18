package nasp.lab.modules;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<City> cities;
    private double distance;

    public Route(List<City> cities){

        this.cities = new ArrayList<>(cities);
        calculateDistance();
    }

    public Route(Route route){
        this.cities = route.getCities();
        this.distance = route.getDistance();
    }

    public List<City> getCities() {
        return cities;
    }

    public double getDistance(){
        return distance;
    }

    public void addCity(City city){
        cities.add(city);
        calculateDistance();
    }

    public void calculateDistance(){

        double distance = 0.0;
        for(int i = 0; i < cities.size(); i++){
            distance += City.calculateDistance(cities.get(i), cities.get((i+1) % cities.size()));
        }

        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (Double.compare(route.distance, distance) != 0) return false;
        return cities.equals(route.cities);
    }

    @Override
    public String toString() {
        return cities.toString();
    }
}
