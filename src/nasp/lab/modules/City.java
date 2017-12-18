package nasp.lab.modules;

public class City {

    private final String name;
    private final double latitude;
    private final double longitude;

    public City(String name, double longitude, double latitude){

        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(City city){
        this.name = city.name;
        this.latitude = city.latitude;
        this.longitude = city.longitude;
    }

    public String getName(){
        return name;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }


    /**
     * Method which calculates distance between two cities. It uses the Haversine's formula for calculations.
     *
     * @param city1 first city
     * @param city2 second city
     * @return distance between two cities in kilometers.
     */
    public static double calculateDistance(City city1, City city2){

        double earthRadius = 6371;

        double deltaLongitude = Math.toRadians(city2.getLongitude() - city1.getLongitude());
        double deltaLatitude = Math.toRadians(city2.getLatitude() - city1.getLatitude());

        double a = Math.pow(Math.sin(deltaLatitude / 2), 2)
                + Math.cos(Math.toRadians(city1.getLatitude()))
                * Math.cos(Math.toRadians(city2.getLatitude()))
                * Math.pow(Math.sin(deltaLongitude / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (Double.compare(city.latitude, latitude) != 0) return false;
        if (Double.compare(city.longitude, longitude) != 0) return false;
        return name != null ? name.equals(city.name) : city.name == null;
    }

    @Override
    public String toString(){
        return name;
    }
}
