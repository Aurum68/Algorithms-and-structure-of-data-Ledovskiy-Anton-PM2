public class Main {

    public static int findMinAttempts(int floors) {
        int attempts = 0;
        int totalFloorsCovered = 0;

        while (totalFloorsCovered < floors) {
            attempts++;
            totalFloorsCovered += attempts;
        }

        return attempts;
    }

    public static void main(String[] args) {
        int floors = 100;
        System.out.printf("Floors: %d, minimal steps to find necessary floor: %s%n", floors, findMinAttempts(floors));
    }
}