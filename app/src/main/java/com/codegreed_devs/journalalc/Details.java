package com.codegreed_devs.journalalc;

public class Details {
    private String Heading;
    private String Thought;

    public Details(){

    }

    public Details(String heading, String thought) {
        this.Heading = heading;
        this.Thought = thought;
    }

    public String getHeading() {
        return Heading;
    }

    public String getThought() {
        return Thought;
    }
}
