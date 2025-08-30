package com.app.paysim.specification;

import com.app.paysim.entities.PaymentEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PaymentSpecification {
    /**
     * Builds a Specification for filtering PaymentEntity based on non-null fields of the provided parameters.
     *
     */
    public static Specification<PaymentEntity> filterByPaymentRecord(Long id, Long dealerId, Double amount, String method, String status) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicates = criteriaBuilder.conjunction();
            predicates = toPredicate(predicates, id, root.get("id"), criteriaBuilder);
            predicates = toPredicate(predicates, dealerId, root.get("dealerId"), criteriaBuilder);
            predicates = toPredicate(predicates, amount, root.get("amount"), criteriaBuilder);
            predicates = toPredicate(predicates, method, root.get("method"), criteriaBuilder);
            predicates = toPredicate(predicates, status, root.get("status"), criteriaBuilder);
            return predicates;
        };
    }
    // Helper method to add conditions to the predicate
    private static <T> Predicate toPredicate(Predicate existingPredicate, T value, jakarta.persistence.criteria.Path<T> path, jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder) {
        if (value != null) {
            return criteriaBuilder.and(existingPredicate, criteriaBuilder.equal(path, value));
        }
        return existingPredicate;
    }
}