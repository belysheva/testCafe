package ru.cafe.service;

import com.google.gson.Gson;
import ru.cafe.model.Cafe;
import ru.cafe.model.Traffic;
import ru.cafe.repository.JdbcRepositoryImpl;
import ru.cafe.repository.Repository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//Add for NullObjects checks
public class Service {

    private static int counter;
    final static int PER_DAY = 20;
    final static int MAX_TRAFFIC = 10;
    final static int CAFES_COUNT = 5;
    private final Repository repository;
    String [] names = {"Mac","Rostics","Lunch","Bar","CoffeeTime"};

    public Service() throws SQLException {
        repository = new JdbcRepositoryImpl();
        createCafe(names);
        accuringTraffic();
    }
//TODO change return type to correspond the task
    public Map<String, Double> calcAvg() throws SQLException {

        List<Cafe> cafes = repository.getAllCafes();
        Map<String, Double> results = new ConcurrentHashMap();

       for (Cafe cafe : cafes) {
            List<Traffic> traffics = repository.getLatestForCafe(cafe.getId());
            results.put(cafe.getName(),
                   (traffics.size() != 0 ) ? totalTrafficForCafe(traffics) / traffics.size() : 0.0);
        }
        return results;
    }

    private int totalTrafficForCafe(List<Traffic> traffics) {
        int sum = 0;
        for (Traffic traffic : traffics) { sum += traffic.getAmount(); }
        return sum;
    }

    public void createTraffic() throws SQLException {

        int period = (int) counter/ PER_DAY;
        List<Cafe> cafes = null;
        cafes = repository.getAllCafes();

        for (Cafe cafe: cafes) {
            repository.saveTraffic(new Traffic(
                    counter++,
                    cafe.getId(),
                    (int)(Math.random()*MAX_TRAFFIC),
                    LocalDate.now().plus(Period.ofDays(period))));
        }
    }
    public void accuringTraffic(){
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                try {
                    createTraffic();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        long period = 3000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    public String trafficToJSON(Map<String, Double> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public void createCafe(String [] names) throws SQLException {
        for (int i = 0; i < CAFES_COUNT; i++) {
            repository.saveCafe(new Cafe(i, names[i])); }
    }
}
