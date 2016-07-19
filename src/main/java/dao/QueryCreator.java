package dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import intership.jdbc.anotation.*;

public class QueryCreator {

	public static <T> String getNameOfTable(T clas) throws NoSuchFieldException, SecurityException {
		Class aClass = (Class) clas;
		Annotation annotation = aClass.getAnnotation(Entity.class);

		if (annotation instanceof Entity) {
			Entity myAnnotation = (Entity) annotation;
			return myAnnotation.shema() + "." + myAnnotation.name();
		}

		return null;
	}

	public static <T> String getTableColumns(T clas, Boolean isForInsert)
			throws NoSuchFieldException, SecurityException {
		Class aClass = (Class) clas;
		String columns = "";
		if (isForInsert)
			columns = "(";
		Field[] field = aClass.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			Annotation[] annotations = field[i].getDeclaredAnnotations();

			for (Annotation annotation1 : annotations) {
				if (annotation1 instanceof Column) {
					Column myAnnotation = (Column) annotation1;
					if (i == 0)
						columns += " " + myAnnotation.name();
					else if (i == field.length - 1)
						columns += ", " + myAnnotation.name() + " ";
					else {
						columns += ", " + myAnnotation.name();
					}

				}
			}
		}
		if (isForInsert) {
			for (int i = 0; i < field.length; i++) {
				if (i == 0)
					columns += " ) VALUES ( ?";
				else {
					columns += ", ?";
				}

			}
			columns += ")";
		}
		return columns;
	}

	public static <T> String getUpdquery(T t) {
		Class tClass = t.getClass();
		String columns = "SET";

		Field[] field = tClass.getDeclaredFields(); 
		for (int i = 0; i < field.length; i++) {
			Annotation[] annotations = field[i].getDeclaredAnnotations();

			for (Annotation annotation1 : annotations) {
				if (annotation1 instanceof Column) {
					Column myAnnotation = (Column) annotation1;
					if (i == 0)
						columns += " " + myAnnotation.name() + " = ?";
					else if (i == field.length - 1)
						columns += ", " + myAnnotation.name() + " =? ";
					else {
						columns += ", " + myAnnotation.name();
					}

				}
			}

		}
		return null;
	}

	public static <T> String getCreateQuery(T t) {

		try {
			return "Insert into " + getNameOfTable(t.getClass()) + QueryCreator.getTableColumns(t.getClass(), true);
		} catch (NoSuchFieldException e) {

			e.printStackTrace();
			return null;
		} catch (SecurityException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static <T> String getDeleteQuery(T t) {
		try {
			return "DELETE FROM " + getNameOfTable(t.getClass()) + " WHERE id= ?;";
		} catch (NoSuchFieldException e) {

			e.printStackTrace();
			return null;
		} catch (SecurityException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static <T> String getUpdateQuery(T t) {
		Class tClass = t.getClass();
		String columns = "";
		try {
			columns = " UPDATE " + getNameOfTable(t.getClass()) + " SET";
		} catch (NoSuchFieldException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}

		Field[] field = tClass.getDeclaredFields(); 
		for (int i = 1; i < field.length; i++) {
			Annotation[] annotations = field[i].getDeclaredAnnotations();

			for (Annotation annotation1 : annotations) {
				if (annotation1 instanceof Column) {
					Column myAnnotation = (Column) annotation1;
					if (i == 1)
						columns += " " + myAnnotation.name() + " = ?";
					else if (i == field.length - 1)
						columns += ", " + myAnnotation.name() + " =? ";
					else {
						columns += ", " + myAnnotation.name() + " =?";
					}

				}
			}

		}
		columns += " where " + field[0].getName() + " =?;";
		return columns;
	}

}
