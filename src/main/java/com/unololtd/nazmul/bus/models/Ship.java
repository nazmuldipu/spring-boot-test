package com.unololtd.nazmul.bus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unololtd.nazmul.bus.models.base.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ship extends BaseEntity {
    private String shipNumber;
    private String name;
    @Column(length = 10000)
    private String description;
    private String kidsPolicy;
    private String phoneNumber;
    private String quality;//Premium, Delux, Regular
    private String floor;
    private String size;//Square feet
    private String startingPoint;
    private String droppingPoint;
    private String startTime;
    private String route;
    private boolean ac;
    private boolean containCabin;
    private boolean online;
    private boolean enabled;
    private boolean deleted;

    private Integer discount;
    private int startsFrom;
    private float rating;
    private int numberOfReviews;
    private int version;

    private Integer hotelswavePercentage;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> imagePaths;

    @Embedded
    private ShipFacilities shipFacilities;



//    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Category> categoryList;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<User> user;

    public Ship() {
    }

    public Ship(String shipNumber, String name, String phoneNumber, String floor, String startingPoint, String droppingPoint, String startTime, boolean deleted) {
        this.shipNumber = shipNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.floor = floor;
        this.startingPoint = startingPoint;
        this.droppingPoint = droppingPoint;
        this.startTime = startTime;
        this.deleted = deleted;
    }

    public String getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKidsPolicy() {
        return kidsPolicy;
    }

    public void setKidsPolicy(String kidsPolicy) {
        this.kidsPolicy = kidsPolicy;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getDroppingPoint() {
        return droppingPoint;
    }

    public void setDroppingPoint(String droppingPoint) {
        this.droppingPoint = droppingPoint;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public boolean isContainCabin() {
        return containCabin;
    }

    public void setContainCabin(boolean containCabin) {
        this.containCabin = containCabin;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public int getStartsFrom() {
        return startsFrom;
    }

    public void setStartsFrom(int startsFrom) {
        this.startsFrom = startsFrom;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Integer getHotelswavePercentage() {
        return hotelswavePercentage;
    }

    public void setHotelswavePercentage(Integer hotelswavePercentage) {
        this.hotelswavePercentage = hotelswavePercentage;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public ShipFacilities getShipFacilities() {
        return shipFacilities;
    }

    public void setShipFacilities(ShipFacilities shipFacilities) {
        this.shipFacilities = shipFacilities;
    }

//    public List<Category> getCategoryList() {
//        return categoryList;
//    }
//
//    public void setCategoryList(List<Category> categoryList) {
//        this.categoryList = categoryList;
//    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipNumber='" + shipNumber + '\'' +
                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", kidsPolicy='" + kidsPolicy + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
//                ", quality='" + quality + '\'' +
                ", floor='" + floor + '\'' +
//                ", size='" + size + '\'' +
                ", startingPoint='" + startingPoint + '\'' +
                ", droppingPoint='" + droppingPoint + '\'' +
                ", startTime='" + startTime + '\'' +
//                ", route='" + route + '\'' +
//                ", ac=" + ac +
//                ", containCabin=" + containCabin +
//                ", online=" + online +
//                ", enabled=" + enabled +
//                ", deleted=" + deleted +
//                ", discount=" + discount +
//                ", startsFrom=" + startsFrom +
//                ", rating=" + rating +
//                ", numberOfReviews=" + numberOfReviews +
//                ", hotelswavePercentage=" + hotelswavePercentage +
//                ", imagePaths=" + imagePaths +
//                ", shipFacilities=" + shipFacilities +
                '}';
    }
}