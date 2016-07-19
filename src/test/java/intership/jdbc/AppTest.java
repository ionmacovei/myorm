package intership.jdbc;

import dao.GenericDaoImpl;
import dao.impl.GroupDaoImpl;
import dao.impl.StudentDaoImpl;
import intership.jdbc.entities.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	private GenericDaoImpl dao;

	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}

	@org.junit.Test
	public void testCreate() {
		dao = new GenericDaoImpl();
		Group g = new Group();
		g.setId(111);
		g.setNumber(131);
		g.setDepartment("Fii123");

		Student s = new Student();
		s.setId(40);
		s.setName("Ion");
		s.setSurname("Macovei");

	}

	@org.junit.Test
	public void testGetAllGroups() {
		dao = new GenericDaoImpl();
		List<Group> lgrup = new ArrayList<Group>();
		lgrup = dao.getAll(Group.class);
		for (Group group : lgrup)
			System.out.println(group.getId() + " " + group.getNumber() + " " + group.getDepartment());

	}

	@org.junit.Test
	public void testGetAllStudents() {
		dao = new GenericDaoImpl();
		List<Student> lstudent = new ArrayList<Student>();
		lstudent = dao.getAll(Student.class);
		for (Student s : lstudent) {
			//System.out.println(s.getId() + " " + s.getName() + " " + s.getSurname());

		}

	}

	@org.junit.Test
	public void testGetStudent() {
		dao = new GenericDaoImpl();
		Student s = new Student();
		s = dao.getObject(Student.class, 40);

		System.out.println(s.getId() + " " + s.getName() + " " + s.getSurname());

	}

}
