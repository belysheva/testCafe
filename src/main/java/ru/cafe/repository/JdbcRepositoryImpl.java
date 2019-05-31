package ru.cafe.repository;

import ru.cafe.model.Cafe;
import ru.cafe.model.Traffic;
import java.util.ArrayList;
import java.util.List;

//TODO replace with inmemmory DB realisation.
public class JdbcRepositoryImpl implements Repository {

    List<Traffic> traffics = new ArrayList<>();
    List<Cafe> cafes = new ArrayList<>();

    public void saveTraffic(Traffic traffic) {
        traffics.add(traffic);
    }
    //returns full list instead of filtered for eachCafe, will be solved with SQL request
    public List<Traffic> getLatestForCafe(int cafeId)  {
        return traffics;
    }
    public void saveCafe(Cafe cafe){
        cafes.add(cafe);
    }
    public List<Cafe> getAllCafes() {
        return cafes;
    }
}
