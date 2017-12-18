package nasp.lab;

import nasp.lab.graphs.RoutesGraph;
import nasp.lab.implementations.*;
import nasp.lab.interfaces.*;
import nasp.lab.modules.Cities;
import nasp.lab.modules.Route;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {

    public static final int NUM_OF_GENERATION = 10000;
    public static final int SIZE_OF_GENERATION = 100;
    public static final double MUTATION_PROBABILITY = 0.2;

    protected final int numberOfCities;

    private List<Route> routes;
    private List<Route> bestGenerationRoutes;

    private Route tempBestRoute;
    private Route tempWorstRoute;

    private Initializer initializer;
    private Selector selector;
    private Crosser crosser;
    private Mutator mutator;

    public GeneticAlgorithm(Initializer initializer, Selector selector, Crosser crosser, Mutator mutator) {

        bestGenerationRoutes = new ArrayList<>();
        numberOfCities = Cities.getNumberOfCities();

        this.initializer = initializer;
        this.selector = selector;
        this.crosser = crosser;
        this.mutator = mutator;
    }

    @SuppressWarnings("unchecked")
    public void startAlgorithm() {

        List<Route> selectedRoutes;
        Route newRoute;

        routes = initializer.initialize();
        evaluate(null);
        for (int i = 0; i < NUM_OF_GENERATION; i++) {

            selectedRoutes = selector.select(routes, getTempBestRoute());
            newRoute = (Route) crosser.cross(selectedRoutes);
            newRoute = (Route) mutator.mutate(newRoute);

            evaluate(newRoute);

            bestGenerationRoutes.add(getTempBestRoute());
        }

        System.out.println("BEST ROUTE: " + tempBestRoute.getDistance() + "km -> " + tempBestRoute);
    }

    public Route getTempBestRoute(){
        return new Route(tempBestRoute);
    }

    public Route getTempWorstRoute(){
        return new Route(tempWorstRoute);
    }

    private void evaluate(Route route){

        if(tempBestRoute == null){

            int indexOfBestRoute = 0;
            for (Route tempBestRoute : routes) {
                if (tempBestRoute.getDistance() < routes.get(indexOfBestRoute).getDistance()) {
                    indexOfBestRoute = routes.indexOf(tempBestRoute);
                }
            }

            tempBestRoute = new Route(routes.get(indexOfBestRoute));
        }

        if(tempWorstRoute == null){

            int indexOfWorstRoute = 0;
            for (Route tempWorstRoute : routes) {
                if (tempWorstRoute.getDistance() > routes.get(indexOfWorstRoute).getDistance()) {
                    indexOfWorstRoute = routes.indexOf(tempWorstRoute);
                }
            }

            tempWorstRoute = new Route(routes.get(indexOfWorstRoute));
        }

        if(route == null) return;

        if (route.getDistance() < tempWorstRoute.getDistance()){
            routes.add(route);
            routes.remove(tempWorstRoute);
            tempWorstRoute = null;
        }

        if(route.getDistance() < tempBestRoute.getDistance()){
            tempBestRoute = route;
        }
    }

    public List<Route> getBestGenerationRoutes() {
        return bestGenerationRoutes;
    }

    public static void main(String[] args) {

        Cities.initRoutes();

        DefaultInitializer initializer = new DefaultInitializer();
        RandomSelector selector = new RandomSelector();
        DefaultCrosser crosser = new DefaultCrosser();
        DefaultMutator mutator = new DefaultMutator();

        GeneticAlgorithm algorithm = new GeneticAlgorithm(initializer, selector, crosser, mutator);
        algorithm.startAlgorithm();

        GeneticAlgorithm selectModifAlgorithm = new GeneticAlgorithm(initializer, new BestSelector(), crosser, mutator);
        selectModifAlgorithm.startAlgorithm();

        GeneticAlgorithm crossModifAlgorithm = new GeneticAlgorithm(initializer, selector, new ModuloCrosser(), mutator);
        crossModifAlgorithm.startAlgorithm();

        SwingUtilities.invokeLater(() -> showGui(
                algorithm.getBestGenerationRoutes(),
                selectModifAlgorithm.getBestGenerationRoutes(),
                crossModifAlgorithm.getBestGenerationRoutes(),
                "Genetic algorithm - NASP_LAB"));
    }

    private static void showGui(List<Route> originalRoutes, List<Route> selectModifiedRoutes, List<Route> crossModifiedRoutes, String title) {

        RoutesGraph mainPanel = new RoutesGraph(originalRoutes, crossModifiedRoutes, selectModifiedRoutes);
        mainPanel.setPreferredSize(new Dimension(1024, 768));
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}