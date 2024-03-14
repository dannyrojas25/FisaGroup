package com.fisagroup.conference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConferenceScheduler {
    public static void main(String[] args) {
        List<Talk> talks = new ArrayList<>();
        talks.add(new Talk("Writing Fast Tests Against Enterprise Rails", 60));
        talks.add(new Talk("Overdoing it in Python", 45));
        talks.add(new Talk("Lua for the Masses", 30));
        talks.add(new Talk("Ruby Errors from Mismatched Gem Versions", 45));
        talks.add(new Talk("Common Ruby Errors", 45));
        talks.add(new Talk("Rails for Python Developers", 5));
        talks.add(new Talk("Communicating Over Distance", 60));
        talks.add(new Talk("Accounting-Driven Development", 45));
        talks.add(new Talk("Woah", 30));
        talks.add(new Talk("Sit Down and Write", 30));
        talks.add(new Talk("Pair Programming vs Noise", 45));
        talks.add(new Talk("Rails Magic", 60));
        talks.add(new Talk("Ruby on Rails: Why We Should Move On", 60));
        talks.add(new Talk("Clojure Ate Scala (on my project)", 45));
        talks.add(new Talk("Programming in the Boondocks of Seattle", 30));
        talks.add(new Talk("Ruby vs. Clojure for Back-End Development", 30));
        talks.add(new Talk("Ruby on Rails Legacy App Maintenance", 60));
        talks.add(new Talk("A World Without HackerNews", 30));
        talks.add(new Talk("User Interface CSS in Rails Apps", 30));

        scheduleConference(talks);
    }

    public static void scheduleConference(List<Talk> talks) {
        Collections.sort(talks);
        List<Track> tracks = new ArrayList<>();
        Track currentTrack = new Track();

        for (Talk talk : talks) {
            if (!currentTrack.addTalk(talk)) {
                tracks.add(currentTrack);
                currentTrack = new Track();
                currentTrack.addTalk(talk);
            }
        }

        tracks.add(currentTrack);
        printConferenceSchedule(tracks);
    }

    public static void printConferenceSchedule(List<Track> tracks) {
        int trackNumber = 1;
        for (Track track : tracks) {
            System.out.println("Track " + trackNumber + ":");

            int startTimeAM = 9 * 60; // 9:00 AM in minutes
            int startTimePM = 1 * 60; // 1:00 PM in minutes

            for (Talk talk : track.getTalks()) {
                int hoursAM = startTimeAM / 60;
                int minutesAM = startTimeAM % 60;
                int hoursPM = startTimePM / 60;
                int minutesPM = startTimePM % 60;

                System.out.printf("%02d:%02dAM %s %dmin\n", hoursAM, minutesAM, talk.getTitle(), talk.getDuration());
                startTimeAM += talk.getDuration();

                System.out.printf("%02d:%02dPM %s %dmin\n", hoursPM, minutesPM, talk.getTitle(), talk.getDuration());
                startTimePM += talk.getDuration();
            }

            System.out.println("12:00PM Lunch\n");
            System.out.println("05:00PM Networking Event\n");

            trackNumber++;
        }
    }

}
