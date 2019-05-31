package ru.cafe.repository;

import ru.cafe.model.Cafe;
import ru.cafe.model.Traffic;
import java.sql.SQLException;
import java.util.List;

public interface Repository {

    void saveTraffic(Traffic traffic) throws SQLException;
    List<Traffic> getLatestForCafe(int cafeId) throws SQLException;
    List<Cafe> getAllCafes() throws SQLException;
    void saveCafe(Cafe cafe) throws SQLException;
}
