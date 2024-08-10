package com.test.youtube.run;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class JDBCRunRepository implements RunRepository {

    private final Logger log = LoggerFactory.getLogger(JDBCRunRepository.class.getName());
    private JdbcClient jdbcClient;

    public JDBCRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void createRun(Run run) {
        var updated = jdbcClient.sql(
                "INSERT INTO run (id,title, started_on, completed_on, miles, location) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(),
                        run.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to insert run");
    }

    public void deleteRunById(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM run WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete run");
    }

    public void updateRun(Run run) {
        var updated = jdbcClient.sql(
                "UPDATE run SET title = ?, started_on = ?, completed_on = :?, miles = ?, location = ? WHERE id = :id")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location(), run.id()))
                .update();

        Assert.state(updated == 1, "Failed to update run");
    }

    public void saveAll(List<Run> runs) {
        runs.forEach(this::createRun);
    }

    public int count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public List<Run> findByLocation(String location)
    {
        return jdbcClient.sql("SELECT * FROM run WHERE location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }
}
