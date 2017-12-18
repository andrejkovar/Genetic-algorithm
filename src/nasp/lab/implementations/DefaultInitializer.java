package nasp.lab.implementations;

import nasp.lab.interfaces.Initializer;
import nasp.lab.modules.Cities;
import nasp.lab.modules.Route;

import java.util.ArrayList;
import java.util.List;

public class DefaultInitializer implements Initializer<Route> {

    @Override
    public List<Route> initialize() {

        return new ArrayList<>(Cities.routes);
    }
}
