package intership.jdbc.entities;
import intership.jdbc.anotation.*;
@Entity(name = "student", shema = "test")
public class Student {

	@Column(name = "id", type = "Integer")
	private Integer id;
	@Column(name = "name", type = "String")
	private String name;
	@Column(name = "surname", type = "String")
	private String surname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
