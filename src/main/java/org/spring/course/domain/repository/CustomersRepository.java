package org.spring.course.domain.repository;

import org.spring.course.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomersRepository {

    private static final String INSERT = "insert into CUSTOMERS (NAME) values (?) ";
    private static final String SELECT_ALL = "SELECT * FROM CUSTOMERS ";
    private static final String UPDATE = "update CUSTOMERS set NAME = ? where ID = ? ";
    private static final String DELETE = "delete from CUSTOMERS where ID = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer save(Customer Customer){
        jdbcTemplate.update( INSERT, Customer.getName());
        return Customer;
    }

    public Customer update(Customer Customer){
        jdbcTemplate.update(UPDATE, Customer.getName(), Customer.getId());
        return Customer;
    }

    public void delete(Customer Customer){
        delete(Customer.getId());
    }

    public void delete(Integer id){
        jdbcTemplate.update(DELETE, id);
    }

    public List<Customer> searchByName(String name){
        return jdbcTemplate.query(
                SELECT_ALL.concat(" where NAME like ? "),
                new Object[]{"%" + name + "%"},
                getCustomerMapper());
    }

    public List<Customer> getAll(){
        return jdbcTemplate.query(SELECT_ALL, getCustomerMapper());
    }

    private RowMapper<Customer> getCustomerMapper() {
        return new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                return new Customer(id, name);
            }
        };
    }

}