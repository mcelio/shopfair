package com.shopfair.search;

import com.shopfair.domainobject.StoreDO;
import com.shopfair.domainobject.ProductDO;
import com.shopfair.domainvalue.StoreType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductDOSpecification implements Specification<ProductDO> {

  private static final String NAME = "name";
  private static final String RATING = "rating";
  private static final String PRODUCT_TYPE = "productType";
  private SearchCriteria criteria;


  public ProductDOSpecification(final SearchCriteria criteria) {
    super();
    this.criteria = criteria;
  }


  @Override
  public Predicate toPredicate(Root<ProductDO> root, CriteriaQuery<?> query,
      CriteriaBuilder builder) {
    String operation = criteria.getOperation();
    String field = criteria.getKey();
    Join<ProductDO, StoreDO> storeProduct = root.join("store");
    if (NAME.equals(field)) {
      return builder.like(storeProduct.get(criteria.getKey()), "%" + criteria.getValue() + "%");
    } else if (RATING.equals(field)) {
      if (operation.equalsIgnoreCase(">")) {
        return builder.greaterThan(
            storeProduct.get(criteria.getKey()), criteria.getValue().toString());
      } else {
        return builder.lessThan(
            storeProduct.get(criteria.getKey()), criteria.getValue().toString());
      }
    } else if (PRODUCT_TYPE.equals(field)) {
      if (StoreType.SAVORY.toString().equals(criteria.getValue())) {
        return builder.equal(
            storeProduct.get(criteria.getKey()), StoreType.SAVORY);
      } else if (StoreType.SWEET.toString().equals(criteria.getValue())) {
        return builder.equal(
            storeProduct.get(criteria.getKey()), StoreType.SWEET);
      }

    }
    return null;
  }
}
