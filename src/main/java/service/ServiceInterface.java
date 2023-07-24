package service;

import java.util.List;

public interface ServiceInterface<T> {
	public T findById(int id);

	public List<T> findAll();

	public T create(T t);

	public T update(T t);

	public void delete(int id);
}
