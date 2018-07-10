package com.shopfair.service.store;

import com.shopfair.domainobject.StoreDO;
import com.shopfair.exception.ConstraintsViolationException;
import com.shopfair.exception.EntityNotFoundException;

public interface StoreService {

  StoreDO find(Long id) throws EntityNotFoundException;

  StoreDO create(StoreDO storeDO) throws ConstraintsViolationException;

  void delete(Long id) throws EntityNotFoundException;

  void updateLocation(long id, double longitude, double latitude)
      throws EntityNotFoundException;

  Iterable<StoreDO> findAll();
}
