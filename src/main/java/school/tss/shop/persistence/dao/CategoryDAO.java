package school.tss.shop.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import school.tss.shop.persistence.dao.base.EntityDAO;
import school.tss.shop.persistence.entity.Category;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

	@Override
	public Category update(long id, Category updateEntry) {
		return null;
	}

}
