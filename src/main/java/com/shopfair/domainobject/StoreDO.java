package com.shopfair.domainobject;

import com.shopfair.domainvalue.GeoCoordinate;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "store", uniqueConstraints = @UniqueConstraint(name = "id", columnNames = {
    "id"}))
public class StoreDO {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  @NotNull(message = "Name can not be null!")
  private String name;

  @Column(nullable = false)
  @NotNull(message = "Address can not be null!")
  private String address;

  @Column(nullable = false)
  @NotNull(message = "Rating can not be null!")
  @Min(0)
  @Max(5)
  private Integer rating;

  @Embedded
  private GeoCoordinate coordinate;

  @Column
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

  @Column(nullable = false)
  private Boolean deleted = false;

  private StoreDO() {
  }

  public StoreDO(String name, String address, Integer rating) {
    this.name = name;
    this.address = address;
    this.name = name;
    this.rating = rating;
    this.coordinate = null;
    this.dateCoordinateUpdated = null;
    this.deleted = false;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public GeoCoordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(GeoCoordinate coordinate) {
    this.coordinate = coordinate;
    this.dateCoordinateUpdated = ZonedDateTime.now();
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

  public ZonedDateTime getDateCoordinateUpdated() {
    return dateCoordinateUpdated;
  }

  public void setDateCoordinateUpdated(ZonedDateTime dateCoordinateUpdated) {
    this.dateCoordinateUpdated = dateCoordinateUpdated;
  }
}
