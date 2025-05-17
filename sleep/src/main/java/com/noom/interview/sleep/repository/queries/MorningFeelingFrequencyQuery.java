package com.noom.interview.sleep.repository.queries;

public class MorningFeelingFrequencyQuery {
    public static final String MORNING_FEELING_FREQUENCY =
            "SELECT morning_feel, COUNT(*) as freq " +
            "FROM sleep " +
            "WHERE sleep_date >= CURRENT_DATE - INTERVAL '30 days' " +
            "AND morning_feel IS NOT NULL " +
            "GROUP BY morning_feel";
}
