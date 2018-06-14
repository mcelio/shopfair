package com.shopfare.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopfare.domainvalue.GeoCoordinate;
import com.shopfare.domainvalue.StoreType;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreDTO {

  @JsonIgnore
  private Long id;

  @NotNull(message = "Name can not be null!")
  private String name;

  @NotNull(message = "Address can not be null!")
  private String address;

  @NotNull(message = "Store type can not be null!")
  private StoreType storeType;

  private Integer rating;

  private GeoCoordinate coordinate;

  private StoreDTO() {
  }

  private StoreDTO(Long id, String name, String address, StoreType storeType,
      Integer rating) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.storeType = storeType;
    this.rating = rating;
  }


  public static StoreDTOBuilder newBuilder() {
    return new StoreDTOBuilder();
  }

  @JsonProperty
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public StoreType getStoreType() {
    return storeType;
  }

  public void setStoreType(StoreType storeType) {
    this.storeType = storeType;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public GeoCoordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(GeoCoordinate coordinate) {
    this.coordinate = coordinate;
  }

  public static class StoreDTOBuilder {

    private Long id;
    private String name;
    private String address;
    private StoreType storeType;
    private Integer rating;
    private GeoCoordinate coordinate;

    public StoreDTOBuilder setId(Long id) {
      this.id = id;
      return this;
    }

    public StoreDTOBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public StoreDTOBuilder setAddress(String address) {
      this.address = address;
      return this;
    }

    public StoreDTOBuilder setRating(Integer rating) {
      this.rating = rating;
      return this;
    }

    public StoreDTOBuilder setCoordinate(GeoCoordinate coordinate) {
      this.coordinate = coordinate;
      return this;
    }

    public StoreDTOBuilder setStoreType(StoreType storeType) {
      this.storeType = storeType;
      return this;
    }

    public StoreDTO createStoreDTO() {
      return new StoreDTO(id, name, address, storeType,
          rating);
    }

  }
}
