package com.miapp.club.threshold;

class Messages{


    public String message;
    public String name;
    public String time;
    public String date;
    public Messages(){

    }
    public Messages( String message, String name, String time,String date) {
        this.message = message;
        this.name = name;
        this.time = time;
        this.date=date;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {

        this.time = time;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {

        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}