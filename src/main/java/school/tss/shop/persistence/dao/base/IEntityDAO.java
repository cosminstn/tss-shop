package school.tss.shop.persistence.dao.base;

import org.springframework.jdbc.core.RowMapper;
import school.tss.shop.exceptions.InvalidIdException;

public interface IEntityDAO<T> {

	/**
	 * Registers an entry and returns the created entry including the id.
	 * @return Returns created entry with id.
	 */
	T create(T newEntry);

	/**
	 * Updates an existing entry and returns the updated entry.
	 * @param id The entry's id.
	 * @return Returns the updated entry
	 * @throws InvalidIdException - Will throw this if the id is invalid.
	 */
	T update(long id, T updateEntry) throws InvalidIdException;

	/**
	 * Deletes an entry.
	 * @param id The entry's id.
	 * @throws InvalidIdException - Will throw this if the id is invalid.
	 */
	void delete(long id) throws InvalidIdException;

	/**
	 * Finds an entry using it's id.
	 * @param id The entry's id
	 * @return InvalidIdException - Will throw this if the id is invalid.
	 */
	T get(long id);
}
