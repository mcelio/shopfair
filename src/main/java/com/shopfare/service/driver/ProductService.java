package com.shopfare.service.driver;

import com.shopfare.domainobject.ProductDO;
import com.shopfare.domainvalue.ProductType;
import com.shopfare.exception.ConstraintsViolationException;
import com.shopfare.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ProductService {

  ProductDO find(Long id) throws EntityNotFoundException;

  ProductDO create(ProductDO productDO) throws ConstraintsViolationException;

  void delete(Long id) throws EntityNotFoundException;

  List<ProductDO> find(ProductType productType);

  Page<ProductDO> search(Specification<ProductDO> specification, Pageable pageable);

}
