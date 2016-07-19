package dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import intership.jdbc.entities.Group;
import intership.jdbc.anotation.*;;

public class GenericDaoImpl implements GenericDao {

	public <T> void create(T t) {

		String sql = QueryCreator.getCreateQuery(t);
		try {
			PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(sql);

			if (t.getClass().isAnnotationPresent(Entity.class)) {
				Class tClass = (Class) t.getClass();

				Field[] field = tClass.getDeclaredFields(); // obtain field
															// object
				for (int i = 0; i < field.length; i++) {
					field[i].setAccessible(true);
					Annotation[] annotations = field[i].getDeclaredAnnotations();

					for (Annotation annotation1 : annotations) {

						Column myAnnotation = (Column) annotation1;
						if (myAnnotation.type().equals("String")) {

							ps.setString(i + 1, field[i].get(t).toString());

						} else if (myAnnotation.type().equals("Integer")) {

							ps.setInt(i + 1, (Integer) field[i].get(t));
						}

					}

				}

			}
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> clas) {
		Class c = (Class) clas;
		List<T> list = new ArrayList<T>();
	
		try {
			String sql = "Select * from " + QueryCreator.getNameOfTable(c);
	
			PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			Field[] fields = clas.getDeclaredFields();
			while (rs.next()) {
				T t = (T) c.newInstance();
				for (int j = 0; j < fields.length; j++) {
					fields[j].setAccessible(true);
					Annotation[] annotations = fields[j].getDeclaredAnnotations();
					
					for (Annotation annotation1 : annotations) {

						Column myAnnotation = (Column) annotation1;
						if (myAnnotation.type().equals("String")) {

							fields[j].set(t, rs.getString(fields[j].getName()));

						} else if (myAnnotation.type().equals("Integer")) {

							fields[j].set(t, rs.getInt(fields[j].getName()));
						}

					}
					
					
				}

				list.add(t);

			}

		} catch (Exception e1) {

			e1.printStackTrace();
		}

		return list;
	}

	public <T> T getObject(Class<T> clas,Integer id) {
		Class c = (Class) clas;
		T t=null;
		try {
			 t = (T) c.newInstance();
			String sql = "Select  *from " + QueryCreator.getNameOfTable(c)+" where id = "+id;

			PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			Field[] fields = clas.getDeclaredFields();
			while (rs.next()) {
				
				for (int j = 0; j < fields.length; j++) {
					fields[j].setAccessible(true);
					Annotation[] annotations = fields[j].getDeclaredAnnotations();
					
					for (Annotation annotation1 : annotations) {

						Column myAnnotation = (Column) annotation1;
						if (myAnnotation.type().equals("String")) {

							fields[j].set(t, rs.getString(fields[j].getName()));

						} else if (myAnnotation.type().equals("Integer")) {

							fields[j].set(t, rs.getInt(fields[j].getName()));
						}

					}
					
					
				}

		

			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return t;
	}

	public <T> void delete(T t) {

		String sql = QueryCreator.getDeleteQuery(t);
		try {
			PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(sql);

			if (t.getClass().isAnnotationPresent(Entity.class)) {
				Class tClass = (Class) t.getClass();
				Field field = tClass.getDeclaredField("id");
				field.setAccessible(true);
				ps.setInt(1, (Integer) field.get(t));
			}
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public <T> void update(T t) {
		String sql = QueryCreator.getUpdateQuery(t);
		try {
			PreparedStatement ps = ConnectionDB.getConnection().prepareStatement(sql);

			if (t.getClass().isAnnotationPresent(Entity.class)) {
				Class tClass = (Class) t.getClass();

				Field[] field = tClass.getDeclaredFields(); 
				field[0].setAccessible(true); 
				int i;
				for (i = 1; i < field.length; i++) {
					field[i].setAccessible(true);
					Annotation[] annotations = field[i].getDeclaredAnnotations();

					for (Annotation annotation1 : annotations) {

						Column myAnnotation = (Column) annotation1;
						if (myAnnotation.type().equals("String")) {

							ps.setString(i, field[i].get(t).toString());

						} else if (myAnnotation.type().equals("Integer")) {

							ps.setInt(i, (Integer) field[i].get(t));
						}

					}

				}
				ps.setInt(i, (Integer) field[0].get(t));

			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
