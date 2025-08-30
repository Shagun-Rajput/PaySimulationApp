package com.app.paysim.repository;

import com.app.paysim.entities.PaymentEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Lazy
@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity,Long> {

    List<PaymentEntity> findAll(Specification<PaymentEntity> filterByPaymentRecord);
}
