package com.miapp.club.threshold;
class Events {
    String event_name;
    String event_id;
    String photo_id;

    public Events(){

    }
    Events(String event_name, String photo_id, String event_id) {
        this.event_name = event_name;
        this.event_id=event_id;
        this.photo_id = photo_id;
    }

    public void setEvent_name(String event_name)
    {this.event_name=event_name;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_id(String event_id){
        this.event_id=event_id;
    }

    public String getEvent_id() {
        return event_id;
    }
    public void setphoto_id(String photo_id)
    {this.photo_id=photo_id;
    }

    public String getphoto_id() {
        return photo_id;
    }

}