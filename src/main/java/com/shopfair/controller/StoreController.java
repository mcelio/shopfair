package com.shopfair.controller;

import com.shopfair.controller.mapper.StoreMapper;
import com.shopfair.datatransferobject.StoreDTO;
import com.shopfair.domainobject.StoreDO;
import com.shopfair.exception.ConstraintsViolationException;
import com.shopfair.exception.EntityNotFoundException;
import com.shopfair.service.store.StoreService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a store will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/stores")
public class StoreController {

  private final StoreService storeService;


  @Autowired
  public StoreController(final StoreService storeService) {
    this.storeService = storeService;
  }


  @GetMapping("/{storeId}")
  public StoreDTO getStore(@Valid @PathVariable long storeId) throws EntityNotFoundException {
    return StoreMapper.makeStoreDTO(storeService.find(storeId));
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public StoreDTO createStore(@Valid @RequestBody StoreDTO storeDTO) throws ConstraintsViolationException {
    StoreDO storeDO = StoreMapper.makeStoreDO(storeDTO);
    return StoreMapper.makeStoreDTO(storeService.create(storeDO));
  }

  @PutMapping("/{storeId}")
  public void updateLocation(
      @Valid @PathVariable long storeId, @RequestParam double longitude,
      @RequestParam double latitude)
      throws ConstraintsViolationException, EntityNotFoundException {
    storeService.updateLocation(storeId, longitude, latitude);
  }


  @DeleteMapping("/{storeId}")
  public void deleteStore(@Valid @PathVariable Long storeId) throws EntityNotFoundException {
    storeService.delete(storeId);
  }


  @GetMapping
  public List<StoreDTO> findStores()
      throws ConstraintsViolationException, EntityNotFoundException {
    return StoreMapper.makeStoreDTOList(StreamSupport.stream(storeService.findAll().spliterator(), false)
        .collect(Collectors.toList()));
  }
}
