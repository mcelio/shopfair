package com.shopfair.service.product;

import com.shopfair.dataaccessobject.StoreRepository;
import com.shopfair.dataaccessobject.ProductRepository;
import com.shopfair.domainobject.ProductDO;
import com.shopfair.domainvalue.ProductType;
import com.shopfair.exception.ConstraintsViolationException;
import com.shopfair.exception.EntityNotFoundException;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some
 * product specific things.
 * <p/>
 */
@Service
public class DefaultProductService implements ProductService {

  private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultProductService.class);

  private final ProductRepository productRepository;

  private final StoreRepository storeRepository;


  public DefaultProductService(final ProductRepository productRepository,
      final StoreRepository storeRepository) {
    this.productRepository = productRepository;
    this.storeRepository = storeRepository;
  }


  /**
   * Selects a product by id.
   *
   * @return found driver
   * @throws EntityNotFoundException if no driver with the given id was found.
   */
  @Override
  public ProductDO find(Long id) throws EntityNotFoundException {
    return findProductChecked(id);
  }


  /**
   * Creates a new product.
   *
   * @throws ConstraintsViolationException if a driver already exists with the given id, ...
   * .
   */
  @Override
  public ProductDO create(ProductDO productDO) throws ConstraintsViolationException {
    ProductDO product;
    try {
      product = productRepository.save(productDO);
    } catch (DataIntegrityViolationException e) {
      LOG.warn("Some constraints are thrown due to product creation", e);
      throw new ConstraintsViolationException(e.getMessage());
    }
    return product;
  }


  /**
   * Deletes an existing driver by id.
   *
   * @throws EntityNotFoundException if no product with the given id was found.
   */
  @Override
  @Transactional
  public void delete(Long id) throws EntityNotFoundException {
    ProductDO productDO = findProductChecked(id);
    productDO.setDeleted(true);
  }

  /**
   * Find all drivers by online state.
   */
  @Override
  public List<ProductDO> find(ProductType productType) {
    return productRepository.findByProductType(productType);
  }


  private ProductDO findProductChecked(Long id) throws EntityNotFoundException {
    ProductDO productDO = productRepository.findOne(id);
    if (productDO == null) {
      throw new EntityNotFoundException("Could not find entity with id: " + id);
    }
    return productDO;
  }

  @Override
  public Page<ProductDO> search(Specification<ProductDO> specification, Pageable pageable) {
    return productRepository.findAll(specification, pageable);
  }
}
