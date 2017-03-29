package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by l on 2017/3/25.
 */

public class Utility {

    /*
        解析和处理服务器返回的省级数据
     */

    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
             for(int i=0;i<allProvinces.length();i++){
                 JSONObject jsonObject = allProvinces.getJSONObject(i);
                 Province province = new Province();
                 province.setProvinceCode(jsonObject.getInt("id"));
                 province.setProvinceName(jsonObject.getString("name"));
                 province.save();
             }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response,int province){
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCity = new JSONArray(response);
                for (int i = 0; i < allCity.length(); i++) {
                    JSONObject jsonObject = allCity.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setCityName(jsonObject.getString("name"));
                    city.setProvinceId(province);
                    city.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public static boolean handleCountyResponse(String response,int city){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounty = new JSONArray(response);
                for (int i=0;i<allCounty.length();i++){
                    JSONObject jsonObject = allCounty.getJSONObject(i);
                    County county =new County();
                    county.setWeetherId(jsonObject.getString("weather_id"));
                    county.setCountyName(jsonObject.getString("name"));
                    county.setCityId(city);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;

    }

    /*
            将返回的JSON数据解析成Weather实体类
     */
    public static Weather handWeatherResponse(String response){
        try{
            JSONObject jsonObject =new JSONObject(response);
            JSONArray jsonArray =jsonObject.getJSONArray("HeWeather");
            String weatherContent =jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
