package com.test.youtube.run;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/run")
public class RunController {

    private final RunRepository repo;

    public RunController(RunRepository repository) {
        this.repo = repository;
    }

    @GetMapping("/")
    String home() {
        return "Welcome to the Spring Boot World!";
    }

    @GetMapping("/getRuns")
    List<Run> getRuns() {

        return repo.findAll();
    }

    @GetMapping("/getRunById/{id}")
    Run GetRunById(@PathVariable Integer id) {
        var run = repo.findById(id);

        if (run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
        }
        return run.get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRunById(@PathVariable Integer id) {
        repo.deleteRunById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void addRun(@Valid @RequestBody Run run) {
        repo.createRun(run);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateRun(@PathVariable Integer id, @RequestBody Run run) {
        var runToUpdate = repo.findById(id);

        if (runToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
        }

        repo.updateRun(runToUpdate.get());
    }

    @GetMapping("/Location/{location}")
    List<Run> getRunsByLocation(@PathVariable String location)
    {
        return repo.findByLocation(location);
    }
}
