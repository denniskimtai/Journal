package com.codegreed_devs.journalalc;

public class DetailsView {

    int id;
    String Heading;
    String Thought;

    public DetailsView(int id, String heading, String thought) {
        this.id = id;
        Heading = heading;
        Thought = thought;
    }

    public int getId() {
        return id;
    }

    public String getHeading() {
        return Heading;
    }

    public String getThought() {
        return Thought;
    }
}
