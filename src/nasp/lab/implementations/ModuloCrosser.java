package nasp.lab.implementations;
import nasp.lab.interfaces.Crosser;
import nasp.lab.modules.Cities;
import nasp.lab.modules.City;
import nasp.lab.modules.Route;

import java.util.ArrayList;
import java.util.List;

public class ModuloCrosser implements Crosser<Route> {

    @Override
    public Route cross(List<Route> routes) {

        //every second city from first route
        List<City> cities = new ArrayList<>();
        for(int i = 0; i < Cities.getNumberOfCities(); i++){
            if((i % 2) == 0){
                cities.add(routes.get(0).getCities().get(i));
            }
        }

        //other cities from second route
        for(City tempCity : routes.get(1).getCities()){
            if(!cities.contains(tempCity)){
                cities.add(tempCity);
            }
        }

        return new Route(cities);
    }
}
