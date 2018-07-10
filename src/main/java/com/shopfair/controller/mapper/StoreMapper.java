package com.shopfair.controller.mapper;

import com.shopfair.datatransferobject.StoreDTO;
import com.shopfair.datatransferobject.StoreDTO.StoreDTOBuilder;
import com.shopfair.domainobject.StoreDO;
import com.shopfair.domainvalue.GeoCoordinate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StoreMapper {

  public static StoreDO makeStoreDO(StoreDTO storeDTO) {
    return new StoreDO(storeDTO.getName(), storeDTO.getAddress(), storeDTO.getRating());
  }


  public static StoreDTO makeStoreDTO(StoreDO storeDO) {
    StoreDTOBuilder storeDTOBuilder = StoreDTO.newBuilder()
        .setId(storeDO.getId())
        .setName(storeDO.getName())
        .setRating(storeDO.getRating())
        .setAddress(storeDO.getAddress());

    GeoCoordinate coordinate = storeDO.getCoordinate();
    if (coordinate != null) {
      storeDTOBuilder.setCoordinate(coordinate);
    }

    return storeDTOBuilder.createStoreDTO();
  }

  public static List<StoreDTO> makeStoreDTOList(Collection<StoreDO> cars) {
    return cars.stream()
        .map(StoreMapper::makeStoreDTO)
        .collect(Collectors.toList());
  }
}
