package school.tss.shop.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import school.tss.shop.exceptions.InvalidIdException;
import school.tss.shop.persistence.dao.base.EntityDAO;
import school.tss.shop.persistence.entity.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("SqlResolve")
@Component
public class UserDAO extends EntityDAO<User> {

	public UserDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		super("USER", jdbcTemplate, dataSource);
	}

	@Override
	public User create(User newEntry) {

		Map<String, Object> params = new HashMap<>();
		params.put("USERNAME", newEntry.getUsername());
		params.put("PASSWORD", newEntry.getPassword());
		params.put("ROLE", newEntry.getRole().getId());

		Number id = jdbcInsert().executeAndReturnKey(params);

		User user = new User();
		user.setId(id.longValue());
		user.setUsername(newEntry.getUsername());
		user.setPassword(newEntry.getPassword());
		user.setRole(newEntry.getRole());

		return user;
	}

	@Override
	public User update(long id, User updateEntry) throws InvalidIdException
	{
		return null;
	}

	public int update(User user) {
		/*return jdbcTemplate.update(
				"update user" + "set username = ?, password = ?, role = ?" + "where id = ?",
				user.getUsername(), user.getPassword(), user.getRole(), user.getId());*/
		String updateSql = "UPDATE user SET username = ?, password = ?, role = ? WHERE id = ?";
		return jdbcTemplate.update(updateSql,user.getUsername(),user.getPassword(),user.getRole());

	}

	public User getByUsername(String username) {
		return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ?", new Object[]{username}, getRowMapper());
	}

	public List<User> findAll(){
		return jdbcTemplate.query("select * from user", getRowMapper());
	}

	public int deleteById(long id){
		return jdbcTemplate.update("delete from user where id=?", id);
	}

	@SuppressWarnings("ConstantConditions")
	public boolean isUsernameAvailable(String username) throws Exception {
		if (username == null) {
			throw new Exception("Invalid username");
		}
		try {
			int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE USERNAME = ?",
					new Object[]{username}, Integer.class);
			return count == 0;
		} catch (Exception ex) {
			return true;
		}
	}

	@Override
	protected RowMapper<User> getRowMapper() {
		return (rs, rowNum) -> {
			User user = new User();
			user.setId(rs.getLong("ID"));
			user.setUsername(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setRole(User.Role.getRoleById(rs.getString("ROLE")));
			return user;
		};
	}
}
