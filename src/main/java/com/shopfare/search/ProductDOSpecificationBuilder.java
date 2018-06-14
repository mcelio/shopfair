package com.shopfare.search;

import com.shopfare.domainobject.ProductDO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class ProductDOSpecificationBuilder {

  private final List<SearchCriteria> params;


  public ProductDOSpecificationBuilder() {
    params = new ArrayList<SearchCriteria>();
  }


  public ProductDOSpecificationBuilder with(String key, String operation, Object value) {
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }


  public Specification<ProductDO> build() {
    if (params.size() == 0) {
      return null;
    }

    List<Specification<ProductDO>> specs = new ArrayList<Specification<ProductDO>>();
    for (SearchCriteria param : params) {
      specs.add(new ProductDOSpecification(param));
    }

    Specification<ProductDO> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specifications.where(result).and(specs.get(i));
    }
    return result;
  }
}
