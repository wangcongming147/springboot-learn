package com.learn.springboot.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.learn.springboot.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	/**
	 * JdbcTemplate主要提供以下五类方法：
	 * execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；
	 * update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句
	 * query方法及queryForXXX方法：用于执行查询相关语句；
	 * call方法：用于执行存储过程、函数相关语句。
	 * //1.查询一行数据并返回int型结果  
	 * jdbcTemplate.queryForInt("select count(*) from test"); 
	 * //2. 查询一行数据并将该行数据转换为Map返回  
	 * jdbcTemplate.queryForMap("select * from test where name='name5'"); 
	 * //3.查询一行任何类型的数据，最后一个参数指定返回结果类型  
	 * jdbcTemplate.queryForObject("select count(*) from test", Integer.class);
	 * //4.查询一批数据，默认将每行数据转换为Map  
	 * jdbcTemplate.queryForList("select * from test"); 
	 * //5.只查询一列数据列表，列类型是String类型，列名字是name
	 * jdbcTemplate.queryForList("select name from test where name=?", new Object[]{"name5"}, String.class);
	 * //6.查询一批数据，返回为SqlRowSet，类似于ResultSet，但不再绑定到连接上  
	 * SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from test"); 
	 * 
	 * 
	 */
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Product entity){
		String sql = "INSERT INTO ln_product(id,NAME,price,description,weight) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql,  new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setObject(1, entity.getId());
				ps.setObject(2,	entity.getName());
				ps.setObject(3, entity.getPrice());
				ps.setObject(4, entity.getDescription());
				ps.setObject(5, entity.getWeight());
			}  
		});
	}
	
	@Override
	public void delete(String id){
		String sql = "delete from ln_product where id=?";
		jdbcTemplate.execute(sql, new PreparedStatementCallback<String>() {
			@Override
			public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setObject(1, id);
				return null;
			}
		});
	}
	
	@Override
	public Product query(String id){
		List<Product> results = jdbcTemplate.query(new PreparedStatementCreator() {  
			@Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {  
				String sql = "SELECT id,name,price,description,weight FROM ln_product WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setObject(1, id);
                return ps;  
            }
        }, new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int paramInt)
					throws SQLException {
				Product entity = new Product();
				entity.setId(rs.getString("id"));
				entity.setName(rs.getString("name"));
				entity.setPrice(rs.getString("price"));
				entity.setWeight(rs.getString("weight"));
				entity.setDescription(rs.getString("description"));
				return entity;
			}  
        });
		
		if(CollectionUtils.isEmpty(results)){
			return null;
		}
		
		return results.get(0);
	}
}
