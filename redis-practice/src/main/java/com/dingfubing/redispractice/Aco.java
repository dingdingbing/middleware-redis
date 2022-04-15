package com.dingfubing.redispractice;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/4/12 14:45
 */
public class Aco {

    private List<LatLng> latLngList;
    private int cityCount;

    public List<LatLng> getLatLngList() {
        return latLngList;
    }

    public void setLatLngList(List<LatLng> latLngList) {
        this.latLngList = latLngList;
    }

    public int getCityCount() {
        return cityCount;
    }

    public void setCityCount(int cityCount) {
        this.cityCount = cityCount;
    }

    public void init() {
        this.cityCount = 1000;
    }

    public void bestChoice(List<LatLng> list, float[] altitude) {
        for (int i = 0; i < this.cityCount; i++) {
            this.latLngList.add(list.get(i));
        }
    }
}
class LatLng {

    private Long lat;
    private Long lng;

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }

    public LatLng() {
    }

    public LatLng(Long lat, Long lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
class TestMain {

    public static void main(String[] args) {
        Aco aco = new Aco();
        aco.init();
        List<LatLng> latLngList = new ArrayList<>();
        float[] altitude = {1.0f, 2.0f};
        aco.bestChoice(latLngList, altitude);
        // 最后的结果应该在aco里面获得
        System.out.println(aco.getLatLngList());
    }
}
