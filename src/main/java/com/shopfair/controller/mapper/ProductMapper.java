package com.shopfair.controller.mapper;

import com.shopfair.datatransferobject.ProductDTO;
import com.shopfair.datatransferobject.ProductDTO.ProductDTOBuilder;
import com.shopfair.domainobject.ProductDO;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

  public static ProductDO makeProductDO(ProductDTO productDTO) {
    return new ProductDO(productDTO.getName(), productDTO.getDescription(),
        productDTO.getProductType(), productDTO.getStore());
  }


  public static ProductDTO makeProductDTO(ProductDO productDO) {
    ProductDTOBuilder productDTOBuilder = ProductDTO.newBuilder()
        .setId(productDO.getId())
        .setName(productDO.getName())
        .setProductType(productDO.getProductType())
        .setStore(productDO.getStore());

    return productDTOBuilder.createProductDTO();
  }


  public static List<ProductDTO> makeProductDTOList(Collection<ProductDO> drivers) {
    return drivers.stream()
        .map(ProductMapper::makeProductDTO)
        .collect(Collectors.toList());
  }
}
