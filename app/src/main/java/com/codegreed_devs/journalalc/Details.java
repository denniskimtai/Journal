package com.codegreed_devs.journalalc;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Details implements Serializable {
    //get id from firebase
    @Exclude private String id;

    private String Heading;
    private String Thought;

    public Details(){

    }

    public Details(String heading, String thought) {
        this.Heading = heading;
        this.Thought = thought;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return Heading;
    }

    public String getThought() {
        return Thought;
    }
}
