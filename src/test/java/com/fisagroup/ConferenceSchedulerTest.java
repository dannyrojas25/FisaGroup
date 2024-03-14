package com.fisagroup;


import com.fisagroup.conference.ConferenceScheduler;
import com.fisagroup.conference.Talk;
import com.fisagroup.conference.Track;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ConferenceSchedulerTest {
    @Test
    public void testScheduleConference() {
        List<Talk> talks = new ArrayList<>();
        talks.add(new Talk("Test Talk 1", 30));
        talks.add(new Talk("Test Talk 2", 45));

        List<Track> result = ConferenceScheduler.scheduleConference(talks);

        assertEquals(1, result.size());
    }
}
