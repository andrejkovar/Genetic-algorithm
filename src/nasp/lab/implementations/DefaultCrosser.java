package nasp.lab.implementations;

import nasp.lab.GeneticAlgorithm;
import nasp.lab.interfaces.Crosser;
import nasp.lab.modules.Cities;
import nasp.lab.modules.City;
import nasp.lab.modules.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultCrosser implements Crosser<Route> {
    @Override
    public Route cross(List<Route> routes) {

        Random r = new Random();

        int crossIndex = r.nextInt(Cities.getNumberOfCities());

        List<City> subCities = new ArrayList<>(routes.get(0).getCities().subList(0, crossIndex));
        for (City tempCity : routes.get(1).getCities()) {
            if (!subCities.contains(tempCity)) {
                subCities.add(tempCity);
            }
        }

        return new Route(subCities);
    }
}
