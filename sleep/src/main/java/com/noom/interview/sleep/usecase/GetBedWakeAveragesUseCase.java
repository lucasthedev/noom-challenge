package com.noom.interview.sleep.usecase;

import com.noom.interview.sleep.repository.SleepBedWakeAveragesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GetBedWakeAveragesUseCase {
    private final SleepBedWakeAveragesRepository repository;

    public GetBedWakeAveragesUseCase(SleepBedWakeAveragesRepository repository) {
        this.repository = repository;
    }

    public Optional<BedWakeAverages> execute() {
        List<LocalDateTime> bedTimes = repository.fetchBedTimes();
        List<LocalDateTime> wakeTimes = repository.fetchWakeTimes();

        if (bedTimes.isEmpty() || wakeTimes.isEmpty()) {
            return Optional.empty();
        }

        double avgBedMinutes = bedTimes.stream()
                .filter(Objects::nonNull)
                .mapToInt(dt -> dt.getHour() * 60 + dt.getMinute())
                .average().orElse(0);

        double avgWakeMinutes = wakeTimes.stream()
                .filter(Objects::nonNull)
                .mapToInt(dt -> dt.getHour() * 60 + dt.getMinute())
                .average().orElse(0);

        LocalTime avgBedTime = LocalTime.of((int) (avgBedMinutes / 60), (int) (avgBedMinutes % 60));
        LocalTime avgWakeTime = LocalTime.of((int) (avgWakeMinutes / 60) % 24, (int) (avgWakeMinutes % 60));

        return Optional.of(new BedWakeAverages(avgBedTime, avgWakeTime));
    }

    public static class BedWakeAverages {
        private final LocalTime avgBedTime;
        private final LocalTime avgWakeTime;

        public BedWakeAverages(LocalTime avgBedTime, LocalTime avgWakeTime) {
            this.avgBedTime = avgBedTime;
            this.avgWakeTime = avgWakeTime;
        }

        public LocalTime getAvgBedTime() {
            return avgBedTime;
        }

        public LocalTime getAvgWakeTime() {
            return avgWakeTime;
        }
    }
}
