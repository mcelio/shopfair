package com.shopfair.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shopfair.domainobject.StoreDO;
import com.shopfair.domainvalue.ProductType;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

  @JsonIgnore
  private Long id;

  @NotNull(message = "Name can not be null!")
  private String name;

  @NotNull(message = "Description can not be null!")
  private String description;

  @NotNull(message = "Product type can not be null!")
  private ProductType productType;

  private StoreDO store;

  private ProductDTO() {
  }

  private ProductDTO(Long id, String name, String description, StoreDO store) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.store = store;
  }

  public static ProductDTOBuilder newBuilder() {
    return new ProductDTOBuilder();
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public StoreDO getStore() {
    return store;
  }

  public static class ProductDTOBuilder {

    private Long id;
    private String name;
    private String description;
    private StoreDO store;
    private ProductType productType;

    public ProductDTOBuilder setId(Long id) {
      this.id = id;
      return this;
    }

    public ProductDTOBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public ProductDTOBuilder setStore(StoreDO store) {
      this.store = store;
      return this;
    }

    public ProductDTOBuilder setDescription(String description) {
      this.description = description;
      return this;
    }

    public ProductDTOBuilder setProductType(ProductType productType) {
      this.productType = productType;
      return this;
    }

    public ProductDTO createProductDTO() {
      return new ProductDTO(id, name, description, store);
    }
  }
}
