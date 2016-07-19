package dao;

import java.util.List;

public interface GenericDao {

	public <T> void create(T t);

	public <T> List<T> getAll(Class<T> clas);

	public <T>void delete(T t);

	public <T> void update(T t);

	public <T> T getObject(Class<T> clas,Integer id);

}
