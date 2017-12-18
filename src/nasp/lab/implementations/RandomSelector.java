package nasp.lab.implementations;

import nasp.lab.interfaces.Selector;
import nasp.lab.modules.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSelector implements Selector<Route> {
    @Override
    public List<Route> select(List<Route> routes, Route bestRoute) {

        Random r = new Random();

        List<Route> selectedRoutes = new ArrayList<>();

        int firstParentIndex = r.nextInt(routes.size());
        int secondParentIndex = r.nextInt(routes.size());

        selectedRoutes.add(routes.get(firstParentIndex));
        selectedRoutes.add(routes.get(secondParentIndex));

        return selectedRoutes;
    }
}
