package com.test.youtube.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Run(
                int id,
                @NotEmpty String title,
                LocalDateTime startedOn,
                LocalDateTime completedOn,
                @Positive int miles,
                Location location

) {
}
