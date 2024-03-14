package com.fisagroup;

import com.fisagroup.conference.ConferenceScheduler;
import com.fisagroup.merchants.GalacticConverter;
import com.fisagroup.trains.TrainProblem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class FisaGroupApplication {
    public static void main(String[] args) {
        GalacticConverter galacticConverter = new GalacticConverter();
        galacticConverter.run();

        ConferenceScheduler conferenceScheduler = new ConferenceScheduler();
        conferenceScheduler.run();

        TrainProblem trainProblem = new TrainProblem();
        trainProblem.run();
    }
}
