package org.spring.course.domain.repository;

import org.spring.course.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Customer save(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Transactional
    public Customer update(Customer customer) {
        entityManager.merge(customer);
        return customer;
    }

    /*
    In the bellow function we need to check if the entityManager contains
    the last version (updated version) of the customer before deleting it
     */
    @Transactional
    public void delete(Customer customer) {
        if(!entityManager.contains(customer)){
            customer = entityManager.merge(customer);
        }
        entityManager.remove(customer);
    }

    @Transactional
    public void delete(Integer id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
    }
    /*
    In HQL we use the class name, not the table name.
     */
    @Transactional(readOnly = true)
    public List<Customer> searchByName(String name) {
        String jpql = " SELECT C FROM Customer C WHERE C.name LIKE :name ";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Customer> getAll() {
        return entityManager.createQuery("FROM Customer ", Customer.class).getResultList();
    }
}