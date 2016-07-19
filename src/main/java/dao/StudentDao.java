package dao;

import java.util.List;

import intership.jdbc.entities.Student;

public interface StudentDao {

	public void create();

	public List<Student> getAll();

	public Student getStudent(Integer id);

	public void delete(Integer id);

	public void update(Student s);

}
