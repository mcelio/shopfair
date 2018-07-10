package com.shopfair.domainobject;

import com.shopfair.domainvalue.ProductType;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "product",
    uniqueConstraints = @UniqueConstraint(name = "id", columnNames = {"id"})
)
public class ProductDO {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime dateCreated = ZonedDateTime.now();

  @Column(nullable = false)
  @NotNull(message = "Name can not be null!")
  private String name;

  @Column(nullable = false)
  @NotNull(message = "Description can not be null!")
  private String description;

  @OneToOne
  @JoinColumn(name = "store_id")
  private StoreDO store;

  @Column(nullable = false)
  private Boolean deleted = false;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductType productType;


  private ProductDO() {
  }


  public ZonedDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(ZonedDateTime dateCreated) {
    this.dateCreated = dateCreated;
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

  public StoreDO getStore() {
    return store;
  }

  public void setStore(StoreDO store) {
    this.store = store;
  }

  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  public ProductDO(String name, String description, ProductType productType, StoreDO store) {
    this.name = name;
    this.description = description;
    this.deleted = false;
    this.productType = productType;
    this.store = store;

  }

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
}
