package com.miapp.club.threshold;

class Magazines {

    public String name_magazine;
    public String magazine_id;
    public Magazines()
    {}

    public Magazines(String name_magazine,String magazine_id) {
        this.name_magazine = name_magazine;
        this.magazine_id=magazine_id;
    }
    public void setName_magazine(String name_magazine)
    {this.name_magazine=name_magazine;
    }

    public String getName_magazine() {
        return name_magazine;
    }
    public void setMagazine_id(String magazine_id)
    {this.magazine_id=magazine_id;
    }

    public String getMagazine_id() {
        return magazine_id;
    }

}

