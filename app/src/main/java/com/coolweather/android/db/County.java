package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by l on 2017/3/25.
 */

public class County extends DataSupport {
    private int id;
    private String countyName;
    private int weetherId;
    private int cityId;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeetherId() {
        return weetherId;
    }

    public void setWeetherId(int weetherId) {
        this.weetherId = weetherId;
    }
}
