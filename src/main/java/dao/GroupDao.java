package dao;

import java.util.List;

import intership.jdbc.entities.Group;

public interface GroupDao {
   
	public void create(Group g);

	public List<Group> getAll();

	public Group getGroup(Integer id);

	public void delete(Integer id);

	public void update(Group g);
}
