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

	private static JdbcTemplate jb;

	public CategoryDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		super("CATEGORY", jdbcTemplate, dataSource);
		jb = jdbcTemplate;
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


	public static void deleteTable() {

		String updateSql = "DELETE FROM category";

		String sqlAdd1 = "INSERT INTO CATEGORY(NAME) VALUES ( 'Computers' )";
		String sqlAdd2 = "INSERT INTO CATEGORY(NAME) VALUES ( 'Smartphones' )";

		jb.update(updateSql);
		jb.update(sqlAdd1);
		jb.update(sqlAdd2);


	}


	public int update(Category category) {

		String updateSql = "UPDATE category SET name = ? WHERE id = ?";
		return jdbcTemplate.update(updateSql, category.getName(), category.getId());

	}

	public Category getByName(String name) {

		return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE NAME = ?", new Object[]{name}, getRowMapper());
	}

	public List<Category> findAll(){
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
