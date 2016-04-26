package com.example.tantao.broadcastbestpractice.intent;

import java.io.Serializable;

/**
 * Created by tantao on 2016/4/22.
 */
public class Peson implements Serializable{

    /**
     * id : 1
     * name : Liy
     */

    private String id;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
