package com.fisagroup.conference;

import com.fisagroup.conference.Talk;

import java.util.ArrayList;
import java.util.List;

public class Track {
    private List<Talk> talks;
    private int remainingTime;

    public Track() {
        this.talks = new ArrayList<>();
        this.remainingTime = 180; // 3 hours in minutes
    }

    public boolean addTalk(Talk talk) {
        if (talk.getDuration() <= remainingTime) {
            talks.add(talk);
            remainingTime -= talk.getDuration();
            return true;
        }
        return false;
    }

    public List<Talk> getTalks() {
        return talks;
    }
}
