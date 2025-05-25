package GeometryApp;

public class Geometry {
    public static void main(String[] args) {
        GeoStars r1 = new GeoStars(10, 5, '*');
        GeoStars r2 = new GeoStars(6, 8, '*');

        System.out.println("Stars 1:");
        r1.draw();

        System.out.println("\nStars 2:");
        r2.draw();
    }
}
