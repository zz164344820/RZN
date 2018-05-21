package chihane.jdaddressselector.model;


import java.io.Serializable;

public class City implements Serializable{
    public int id;
    public int province_id;
    public String name;


    public City() {
    }

    public City(int id, int province_id, String name) {
        this.id = id;
        this.province_id = province_id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}