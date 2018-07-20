package com.example.p010_location.bean;

public class LocationBean {

    private String latitude; // 纬度
    private String longitude; // 经度
    private String pointInterestName;   // 兴趣点
    private String areaInterestName;  // 兴趣面
    private String province;  // 省
    private String city;  // 市
    private String district;  //区
    private String street;  //街道
    private String address;  //地址
    private String cityCode; // 邮编
    private String adCode;//地区编码

    @Override
    public String toString() {
        return "LocationBean{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", pointInterestName='" + pointInterestName + '\'' +
                ", areaInterestName='" + areaInterestName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", address='" + address + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", adCode='" + adCode + '\'' +
                '}';
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPointInterestName() {
        return pointInterestName;
    }

    public void setPointInterestName(String pointInterestName) {
        this.pointInterestName = pointInterestName;
    }

    public String getAreaInterestName() {
        return areaInterestName;
    }

    public void setAreaInterestName(String areaInterestName) {
        this.areaInterestName = areaInterestName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
