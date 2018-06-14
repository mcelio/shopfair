package com.shopfare.service.car;

import com.shopfare.domainobject.StoreDO;
import com.shopfare.exception.ConstraintsViolationException;
import com.shopfare.exception.EntityNotFoundException;

public interface StoreService {

  StoreDO find(Long id) throws EntityNotFoundException;

  StoreDO create(StoreDO storeDO) throws ConstraintsViolationException;

  void delete(Long id) throws EntityNotFoundException;

  void updateLocation(long id, double longitude, double latitude)
      throws EntityNotFoundException;

  Iterable<StoreDO> findAll();
}
