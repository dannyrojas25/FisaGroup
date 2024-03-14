package com.fisagroup.conference;

public class Talk implements Comparable<Talk>{
    private String title;
    private int duration;

    public Talk(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Talk talk) {
        return Integer.compare(talk.duration, this.duration);
    }
}
