package nasp.lab.implementations;

import nasp.lab.GeneticAlgorithm;
import nasp.lab.interfaces.Selector;
import nasp.lab.modules.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BestSelector implements Selector<Route> {

    @Override
    public List<Route> select(List<Route> routes, Route bestRoute) {

        Random r = new Random();

        Route randomRoute = new Route(routes.get(r.nextInt(GeneticAlgorithm.SIZE_OF_GENERATION)));

        List<Route> selectedRoutes = new ArrayList<>();
        selectedRoutes.add(bestRoute);
        selectedRoutes.add(randomRoute);

        return selectedRoutes;
    }
}
