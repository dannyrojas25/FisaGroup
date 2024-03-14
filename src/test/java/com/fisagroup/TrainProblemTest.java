package com.fisagroup;

import com.fisagroup.trains.TrainProblem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TrainProblemTest {
    private TrainProblem trainProblem;

    @Mock
    private Map<String, TrainProblem.Town> towns;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trainProblem = new TrainProblem();
        trainProblem.towns = towns;
    }

    @Test
    public void testCalculateDistance() {
        TrainProblem.Town townA = new TrainProblem.Town("A");
        TrainProblem.Town townB = new TrainProblem.Town("B");
        townA.addRoute(townB, 5);
        when(towns.get("A")).thenReturn(townA);
        when(towns.get("B")).thenReturn(townB);

        String distance = trainProblem.calculateDistance(Arrays.asList("A", "B"));

        assertEquals("NO SUCH ROUTE", distance);
    }

}
