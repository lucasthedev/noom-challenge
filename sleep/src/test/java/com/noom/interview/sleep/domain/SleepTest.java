package com.noom.interview.sleep.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SleepTest {
    @Test
    public void registerSleep() {
        Sleep sleep = new Sleep();

        String id = sleep.getId();

        Assertions.assertNotNull(id);
    }

    @Test
    public void updateSleep(){
        Sleep sleep = new Sleep();

        sleep.updateSleep(sleep);

        Assertions.assertNotNull(sleep.getTotalTimeInBed());
        Assertions.assertNotNull(sleep.getFeeling());
    }
}
