package nasp.lab.implementations;

import nasp.lab.GeneticAlgorithm;
import nasp.lab.interfaces.Mutator;
import nasp.lab.modules.City;
import nasp.lab.modules.Route;

import java.util.Random;

public class DefaultMutator implements Mutator<Route> {

    @Override
    public Route mutate(Route route) {

        Random r = new Random();

        if (r.nextDouble() < GeneticAlgorithm.MUTATION_PROBABILITY) {

            int mutationIndex = r.nextInt(route.getCities().size());
            City mutatedCity = new City(route.getCities().get(mutationIndex));
            route.getCities().remove(mutationIndex);
            route.addCity(mutatedCity);
        }

        return route;
    }
}
