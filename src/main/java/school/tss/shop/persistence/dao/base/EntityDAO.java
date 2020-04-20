package school.tss.shop.persistence.dao.base;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import school.tss.shop.exceptions.InvalidIdException;
import school.tss.shop.persistence.entity.Item;

import javax.sql.DataSource;

@SuppressWarnings("SqlResolve")
public abstract class EntityDAO<T> implements IEntityDAO<T> {

	protected final String TABLE_NAME;
	protected final JdbcTemplate jdbcTemplate;
	protected final DataSource dataSource;

	public EntityDAO(String TABLE_NAME, JdbcTemplate jdbcTemplate, DataSource dataSource) {
		this.TABLE_NAME = TABLE_NAME;
		this.jdbcTemplate = jdbcTemplate;
		this.dataSource = dataSource;
		this.jdbcTemplate.setDataSource(dataSource);
	}

	protected SimpleJdbcInsert jdbcInsert() {
		return new SimpleJdbcInsert(dataSource)
				.withTableName(TABLE_NAME)
				.usingGeneratedKeyColumns("ID");
	}

	@Override
	public T get(long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE ID = ?", new Object[]{id}, getRowMapper());
	}

	@Override
	public void delete(long id) throws InvalidIdException {
		int result = jdbcTemplate.update("DELETE FROM " + TABLE_NAME + " WHERE ID = ?", id);
		if (result <= 0) {
			throw new InvalidIdException();
		}
	}

	protected abstract RowMapper<T> getRowMapper();
}
