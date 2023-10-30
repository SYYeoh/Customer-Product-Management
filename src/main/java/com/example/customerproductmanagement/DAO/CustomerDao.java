package com.example.customerproductmanagement.DAO;

import com.example.customerproductmanagement.DTO.CustomerDetail;
import com.example.customerproductmanagement.DTO.CustomerRequest;
import com.example.customerproductmanagement.DTO.CustomerResponse;
import com.example.customerproductmanagement.DTO.OrderDetail;
import com.example.customerproductmanagement.Entity.CustomersEntity;
import com.example.customerproductmanagement.Entity.OrderItemEntity;
import com.example.customerproductmanagement.Entity.OrdersEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
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

    public CustomerResponse countTotalCustomers() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<CustomersEntity> countRoot = countQuery.from(CustomersEntity.class);
        countQuery.select(builder.count(countRoot));

        long totalRecord = entityManager.createQuery(countQuery).getSingleResult();

        CustomerResponse response = new CustomerResponse();
        response.setTotalRecord(totalRecord);
        return response;
    }

    public List<CustomersEntity> getOrdersByCustomerId(CustomerRequest request) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomersEntity> query = builder.createQuery(CustomersEntity.class);
        Root<CustomersEntity> customerRoot = query.from(CustomersEntity.class);

        // Create a fetch join to retrieve associated entities
        Join<CustomersEntity, OrdersEntity> ordersJoin = customerRoot.join("ordersEntityCollection", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        CustomerDetail detail = request.getCustomerDetail();
        if (detail.getCustId() != null) {
            predicates.add(builder.like(customerRoot.get("custId").as(String.class), "%" + detail.getCustId() + "%"));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        // Ensure distinct results
        query.distinct(true);

        // Select the root entity (CustomersEntity) to be included in the result
        query.select(customerRoot);

        TypedQuery<CustomersEntity> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }


    private static void filterCustomerList(CustomerDetail detail, CriteriaBuilder builder, Root<CustomersEntity> root, List<Predicate> predicates) {
        if (detail.getCustId() != null) {
            predicates.add(builder.like(root.get("custId").as(String.class), "%" + detail.getCustId() + "%"));
        }

        if (detail.getFirstName() != null) {
            predicates.add(builder.like(builder.lower(root.get("firstName")), "%" + detail.getFirstName().toLowerCase() + "%"));
        }

        if (detail.getLastName() != null) {
            predicates.add(builder.like(builder.lower(root.get("lastName")), "%" + detail.getLastName().toLowerCase() + "%"));
        }

        if (detail.getEmailOffice() != null) {
            predicates.add(builder.like(builder.lower(root.get("emailOffice")), "%" + detail.getEmailOffice().toLowerCase() + "%"));
        }

        if (detail.getEmailPersonal() != null) {
            predicates.add(builder.like(builder.lower(root.get("emailPersonal")), "%" + detail.getEmailPersonal().toLowerCase() + "%"));
        }

        if (detail.getAddressLine() != null) {
            predicates.add(builder.like(builder.lower(root.get("addressLine")), "%" + detail.getAddressLine().toLowerCase() + "%"));
        }

        if (detail.getCity() != null) {
            predicates.add(builder.like(builder.lower(root.get("city")), "%" + detail.getCity().toLowerCase() + "%"));
        }

        if (detail.getPostal() != null) {
            predicates.add(builder.like(builder.lower(root.get("postal")), "%" + detail.getPostal().toLowerCase() + "%"));
        }

        if (detail.getStatus() != null) {
            predicates.add(builder.like(builder.lower(root.get("status")), "%" + detail.getStatus().toLowerCase() + "%"));
        } else {
            predicates.add(builder.equal(root.get("status"), "ACTIVE"));
        }
    }
}
