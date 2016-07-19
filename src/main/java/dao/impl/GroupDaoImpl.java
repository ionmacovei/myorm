package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.List;

import dao.*;
import intership.jdbc.entities.Group;

public class GroupDaoImpl implements GroupDao {
	private Group group;

	public void create(Group g) {
		try {
			String sqlForInsert = getCreateQuery();
			PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(sqlForInsert);
			
			ps.setInt(1, g.getId());
			ps.setInt(2, g.getNumber());
			ps.setString(3, g.getDepartment());
			ps.executeUpdate();
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}

	public List<Group> getAll() {
		try {
			List<Group> listGroups = new ArrayList<Group>();
			PreparedStatement ps = ConnectionDB.getConnection()
					.prepareStatement("select * from " + QueryCreator.getNameOfTable(Group.class));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Group g = new Group();
				g.setId(rs.getInt("id"));
				g.setNumber(rs.getInt("number"));
				g.setDepartment(rs.getNString("department"));
				listGroups.add(g);
			}
			return listGroups;
		} catch (Exception e) {
			System.out.println("MyException: " + e);
			e.printStackTrace();
			return null;
		}
	}

	public Group getGroup(Integer id) {
		try { 
		Group g= new Group();
         String sql= getSelctQuery()+" where id = "+ id;
		PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			
			g.setId(rs.getInt("id"));
			g.setNumber(rs.getInt("number"));
			g.setDepartment(rs.getNString("department"));
			
		}
		return g;
	} catch(Exception e)
	{
		System.out.println("MyException: " + e);
		e.printStackTrace();
		return null;
	}
}

	public String getCreateQuery() {

		try {
			return "Insert into " + QueryCreator.getNameOfTable(Group.class)
					+ QueryCreator.getTableColumns(Group.class, true);
		} catch (NoSuchFieldException e) {

			e.printStackTrace();
			return null;
		} catch (SecurityException e) {

			e.printStackTrace();
			return null;
		}
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	public void update(Group g) {
		// TODO Auto-generated method stub

	}

	public String getSelctQuery() {
		try {
			return "Select *from " + QueryCreator.getNameOfTable(Group.class);
		} catch (NoSuchFieldException e) {

			e.printStackTrace();
			return null;
		} catch (SecurityException e) {

			e.printStackTrace();
			return null;
		}
	}
	/*
	 * @Override public String getUpdateQuery() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public String getDeleteQuery() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override protected List<Group> parseResultSet(ResultSet rs) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override protected void prepareStatementForInsert(PreparedStatement
	 * statement, Group g) { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override protected <T> void prepareStatementForUpdate(PreparedStatement
	 * statement, T object) { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override protected void prepareStatementForUpdate(PreparedStatement
	 * statement, Group object) { // TODO Auto-generated method stub
	 * 
	 * }
	 */
}
