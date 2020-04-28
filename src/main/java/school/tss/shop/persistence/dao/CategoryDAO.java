package school.tss.shop.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import school.tss.shop.persistence.dao.base.EntityDAO;
import school.tss.shop.persistence.entity.Category;
import school.tss.shop.persistence.entity.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryDAO extends EntityDAO<Category> {

	public CategoryDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		super("CATEGORY", jdbcTemplate, dataSource);
	}

	@Override
	protected RowMapper<Category> getRowMapper() {
		return (rs, rowNum) -> {
			Category category = new Category();
			category.setId(rs.getLong("ID"));
			category.setName(rs.getString("NAME"));
			return category;
		};
	}

	@Override
	public Category create(Category newEntry) {
		Map<String, Object> params = new HashMap<>();
		params.put("NAME", newEntry.getName());

		Number id = jdbcInsert().executeAndReturnKey(params);

		Category category = new Category();
		category.setId(id.longValue());
		category.setName(newEntry.getName());

		return category;
	}

	public int update(Category category) {
		return jdbcTemplate.update(
				"update category" + "set name = ?" + "where id = ?",
				category.getName(), category.getId());
	}

	public Category getByCategory(String name) {
		return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE NAME = ?", new Object[]{name}, getRowMapper());
	}

	public List<Category> findAll(String name){
		return jdbcTemplate.query("select * from category", getRowMapper());
	}

	public int deleteById(long id){
		return jdbcTemplate.update("delete from category where id=?", id);
	}

	@Override
	public Category update(long id, Category updateEntry) {
		return null;
	}

}
