package com.app.paysim.repository;

import com.app.paysim.entities.PaymentEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity,Long> {

}
