package com.example.customerproductmanagement.DAO;

import com.example.customerproductmanagement.DTO.CustomerDetail;
import com.example.customerproductmanagement.DTO.CustomerRequest;
import com.example.customerproductmanagement.Entity.CustomersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao{
    @PersistenceContext
    private EntityManager entityManager;

    private int getCustId() {
        return (int) entityManager.createNativeQuery("SELECT NEXTVAL('SEQ_CUSTOMER')").getSingleResult();
    }

    @Transactional
    public void persistCustomer(CustomersEntity entity) {
        int generatedId = getCustId();
        entity.setCustId(generatedId);
        entityManager.persist(entity);
    }

    public List<CustomersEntity> retrieveCustomers(CustomerRequest request) {
        CustomerDetail detail = request.getCustomerDetail();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomersEntity> query = builder.createQuery(CustomersEntity.class);
        Root<CustomersEntity> root = query.from(CustomersEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        filterCustomerList(detail, builder, root, predicates);

        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        int startIndex = request.getStartIndex();
        int maxPerPage = request.getMaxPerPage();

        TypedQuery<CustomersEntity> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(startIndex);
        typedQuery.setMaxResults(maxPerPage);

        return typedQuery.getResultList();
    }

    public long countTotalCustomers(CustomerRequest request) {
        CustomerDetail detail = request.getCustomerDetail();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<CustomersEntity> countRoot = countQuery.from(CustomersEntity.class);
        countQuery.select(builder.count(countRoot));

        List<Predicate> predicates = new ArrayList<>();
        filterCustomerList(detail, builder, countRoot, predicates);

        if (!predicates.isEmpty()) {
            countQuery.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    public long countTotalCustomers() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<CustomersEntity> countRoot = countQuery.from(CustomersEntity.class);
        countQuery.select(builder.count(countRoot));

        return entityManager.createQuery(countQuery).getSingleResult();
    }



    private static void filterCustomerList(CustomerDetail detail, CriteriaBuilder builder, Root<CustomersEntity> root, List<Predicate> predicates) {

        if (detail.getCustId() != null) {
            predicates.add(builder.equal(root.get("custId"), detail.getCustId()));
        }

        if (detail.getFirstName() != null) {
            predicates.add(builder.equal(root.get("firstName"), detail.getFirstName()));
        }

        if (detail.getLastName() != null) {
            predicates.add(builder.equal(root.get("lastName"), detail.getLastName()));
        }

        if (detail.getEmailOffice() != null) {
            predicates.add(builder.equal(root.get("emailOffice"), detail.getEmailOffice()));
        }

        if (detail.getEmailPersonal() != null) {
            predicates.add(builder.equal(root.get("emailPersonal"), detail.getEmailPersonal()));
        }

        if (detail.getAddressLine() != null) {
            predicates.add(builder.equal(root.get("addressLine"), detail.getAddressLine()));
        }

        if (detail.getCity() != null) {
            predicates.add(builder.equal(root.get("city"), detail.getCity()));
        }

        if (detail.getPostal() != null) {
            predicates.add(builder.equal(root.get("postal"), detail.getPostal()));
        }

        if (detail.getStatus() != null) {
            predicates.add(builder.equal(root.get("status"), detail.getStatus()));
        } else {
            predicates.add(builder.equal(root.get("status"), "ACTIVE"));
        }
    }
}
