package com.app.dvm.repository;


import com.app.dvm.entities.DealerEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Lazy
@Repository
public interface DealerRepository extends CrudRepository<DealerEntity, Long> {
    Collection<DealerEntity> findAll();
}
