package com.fisagroup.trains;

import java.util.*;

public class TrainProblem {
    public static class Town {
        String name;
        Map<Town, Integer> routes;

        public Town(String name) {
            this.name = name;
            this.routes = new HashMap<>();
        }

        public void addRoute(Town destination, int distance) {
            routes.put(destination, distance);
        }
    }

    private static class Route {
        List<Town> towns;
        int distance;

        public Route(List<Town> towns, int distance) {
            this.towns = towns;
            this.distance = distance;
        }
    }

    public static Map<String, Town> towns = new HashMap<>();

    public void run() {
        initializeGraph();

        System.out.println("Output #1: " + calculateDistance(Arrays.asList("A", "B", "C")));
        System.out.println("Output #2: " + calculateDistance(Arrays.asList("A", "D")));
        System.out.println("Output #3: " + calculateDistance(Arrays.asList("A", "D", "C")));
        System.out.println("Output #4: " + calculateDistance(Arrays.asList("A", "E", "B", "C", "D")));
        System.out.println("Output #5: " + calculateDistance(Arrays.asList("A", "E", "D")));
        System.out.println("Output #6: " + countTripsWithMaxStops(getTown("C"), getTown("C"), 3));
        System.out.println("Output #7: " + countTripsWithExactStops(getTown("A"), getTown("C"), 4, 0));
        System.out.println("Output #8: " + findShortestRoute(getTown("A"), getTown("C")));
        System.out.println("Output #9: " + findShortestRoute(getTown("B"), getTown("B")));
        System.out.println("Output #10: " + countRoutesWithDistanceLessThan(getTown("C"), getTown("C"), 30, 0));
    }

    private static void initializeGraph() {
        addRoute("A", "B", 5);
        addRoute("B", "C", 4);
        addRoute("C", "D", 8);
        addRoute("D", "C", 8);
        addRoute("D", "E", 6);
        addRoute("A", "D", 5);
        addRoute("C", "E", 2);
        addRoute("E", "B", 3);
        addRoute("A", "E", 7);
    }

    private static void addRoute(String sourceName, String destinationName, int distance) {
        Town source = getTown(sourceName);
        Town destination = getTown(destinationName);
        source.addRoute(destination, distance);
    }

    private static Town getTown(String name) {
        return towns.computeIfAbsent(name, Town::new);
    }

    public static String calculateDistance(List<String> townNames) {
        List<Town> towns = new ArrayList<>();
        for (String name : townNames) {
            Town town = getTown(name);
            if (town == null) {
                return  "NO SUCH ROUTE"; // Town not found
            }
            towns.add(town);
        }

        int distance = 0;
        for (int i = 0; i < towns.size() - 1; i++) {
            Town current = towns.get(i);
            Town next = towns.get(i + 1);
            Integer routeDistance = current.routes.get(next);
            if (routeDistance == null) {
                return "NO SUCH ROUTE"; // Route not found
            }
            distance += routeDistance;
        }
        return String.valueOf(distance);
    }

    private static int countTripsWithMaxStops(Town start, Town end, int maxStops) {
        int count = 0;
        Queue<Town> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Town current = queue.poll();
            if (current == end) {
                count++;
            }

            if (current.routes.keySet().size() > 0) {
                for (Town neighbor : current.routes.keySet()) {
                    queue.add(neighbor);
                }
            }
        }

        return count;
    }

    private static int countTripsWithExactStops(Town start, Town end, int exactStops, int stops) {
        if (stops == exactStops && start == end) {
            return 1;
        }

        int count = 0;
        for (Town neighbor : start.routes.keySet()) {
            count += countTripsWithExactStops(neighbor, end, exactStops, stops + 1);
        }
        return count;
    }

    private static int findShortestRoute(Town start, Town end) {
        Set<Town> visited = new HashSet<>();
        PriorityQueue<Route> queue = new PriorityQueue<>(Comparator.comparingInt(r -> r.distance));
        queue.add(new Route(Collections.singletonList(start), 0));

        while (!queue.isEmpty()) {
            Route currentRoute = queue.poll();
            Town currentTown = currentRoute.towns.get(currentRoute.towns.size() - 1);

            if (currentTown == end) {
                return currentRoute.distance;
            }

            if (visited.contains(currentTown)) {
                continue;
            }

            visited.add(currentTown);

            for (Town neighbor : currentTown.routes.keySet()) {
                List<Town> newTowns = new ArrayList<>(currentRoute.towns);
                newTowns.add(neighbor);
                int newDistance = currentRoute.distance + currentTown.routes.get(neighbor);
                queue.add(new Route(newTowns, newDistance));
            }
        }

        return -1; // No route found
    }

    private static int countRoutesWithDistanceLessThan(Town start, Town end, int maxDistance, int distance) {
        int count = 0;
        for (Town neighbor : start.routes.keySet()) {
            int newDistance = distance + start.routes.get(neighbor);
            if (newDistance < maxDistance) {
                count += countRoutesWithDistanceLessThan(neighbor, end, maxDistance, newDistance);
            }
        }
        if (start == end && distance < maxDistance) {
            count++; // Found a valid route
        }
        return count;
    }
}
