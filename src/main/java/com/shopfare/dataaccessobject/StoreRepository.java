package com.shopfare.dataaccessobject;

import com.shopfare.domainobject.StoreDO;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for store table.
 * <p/>
 */
public interface StoreRepository extends CrudRepository<StoreDO, Long> {

}
