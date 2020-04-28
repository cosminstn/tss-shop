package school.tss.shop.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import school.tss.shop.persistence.dao.base.EntityDAO;
import school.tss.shop.persistence.entity.Item;
import school.tss.shop.persistence.entity.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ItemDAO extends EntityDAO<Item> {

	public ItemDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		super("ITEM", jdbcTemplate, dataSource);
	}

	@Override
	public Item create(Item newEntry) {
		Map<String, Object> params = new HashMap<>();
		params.put("CATEGORY_ID", newEntry.getCategoryId());
		params.put("NAME", newEntry.getName());
		params.put("PRICE", newEntry.getPrice());
		params.put("CURRENT_DISCOUNT_PRC", newEntry.getCurrentDiscount());
		Number id = jdbcInsert().executeAndReturnKey(params);

		Item item = new Item();
		item.setId(id.longValue());
		item.setName(newEntry.getName());
		item.setCategoryId(newEntry.getCategoryId());
		item.setPrice(newEntry.getPrice());

		return item;
	}

	@Override
	public Item update(long id, Item updateEntry) {
		return null;
	}

	public int update(Item item) {

		String updateSql = "UPDATE item SET name = ?, price = ? WHERE id = ?";
		return jdbcTemplate.update(updateSql, item.getName(), item.getPrice(), item.getId());
	}

	public Item getByName(String name) {
		return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE_NAME + " WHERE NAME = ?", new Object[]{name}, getRowMapper());
	}

	public List<Item> findAll(){
		return jdbcTemplate.query("select * from item", getRowMapper());
	}

	public int deleteById(long id){
		return jdbcTemplate.update("delete from item where id=?", id);
	}

	@Override
	protected RowMapper<Item> getRowMapper() {
		return (rs, rowNum) -> {
			Item item = new Item();
			item.setId(rs.getLong("ID"));
			item.setCategoryId(rs.getLong("CATEGORY_ID"));
			item.setName(rs.getString("NAME"));
			item.setPrice(rs.getDouble("PRICE"));
			return item;
		};
	}
}