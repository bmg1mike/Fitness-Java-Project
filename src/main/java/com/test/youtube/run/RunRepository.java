package com.test.youtube.run;

import java.util.List;
import java.util.Optional;

public interface RunRepository {
    List<Run> findAll();

    Optional<Run> findById(Integer id);

    void createRun(Run run);

    void updateRun(Run run);

    void deleteRunById(Integer id);

    int count();

    void saveAll(List<Run> runs);

    List<Run> findByLocation(String location);
}
