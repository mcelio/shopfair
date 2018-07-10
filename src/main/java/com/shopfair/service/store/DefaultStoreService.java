package com.shopfair.service.store;

import com.shopfair.dataaccessobject.StoreRepository;
import com.shopfair.domainobject.StoreDO;
import com.shopfair.domainvalue.GeoCoordinate;
import com.shopfair.exception.ConstraintsViolationException;
import com.shopfair.exception.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for store
 * specific things.
 * <p/>
 */
@Service
public class DefaultStoreService implements StoreService {

  private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultStoreService.class);

  private final StoreRepository storeRepository;


  public DefaultStoreService(final StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }


  /**
   * Selects a store by id.
   *
   * @return found store
   * @throws EntityNotFoundException if no store with the given id was found.
   */
  @Override
  public StoreDO find(Long id) throws EntityNotFoundException {
    return findStoreChecked(id);
  }


  /**
   * Creates a new store.
   *
   * @throws ConstraintsViolationException if a store already exists with the given id,
   */
  @Override
  public StoreDO create(StoreDO storeDO) throws ConstraintsViolationException {
    StoreDO car;
    try {
      car = storeRepository.save(storeDO);
    } catch (DataIntegrityViolationException e) {
      LOG.warn("Some constraints are thrown due to store creation", e);
      throw new ConstraintsViolationException(e.getMessage());
    }
    return car;
  }


  /**
   * Deletes an existing store by id.
   *
   * @throws EntityNotFoundException if no driver with the given id was found.
   */
  @Override
  @Transactional
  public void delete(Long id) throws EntityNotFoundException {
    StoreDO storeDO = findStoreChecked(id);
    storeDO.setDeleted(true);
  }


  /**
   * Find all stores.
   */
  @Override
  public Iterable<StoreDO> findAll() {
    return storeRepository.findAll();
  }


  private StoreDO findStoreChecked(Long id) throws EntityNotFoundException {
    StoreDO storeDO = storeRepository.findOne(id);
    if (storeDO == null) {
      throw new EntityNotFoundException("Could not find entity with id: " + id);
    }
    return storeDO;
  }

  /**
   * Update the location for a product.
   */
  @Override
  @Transactional
  public void updateLocation(long id, double longitude, double latitude)
      throws EntityNotFoundException {
    StoreDO storeDO = findStoreChecked(id);
    storeDO.setCoordinate(new GeoCoordinate(latitude, longitude));
  }
}
