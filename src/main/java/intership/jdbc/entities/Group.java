package intership.jdbc.entities;

import intership.jdbc.anotation.*;

@Entity(name = "group", shema = "test")
public class Group {
  
	@Column(name = "id", type = "Integer")
    private Integer id = null;
	@Column(name = "number", type = "Integer")
    private int number;
	@Column(name = "department", type = "String")
    private String department;

    public Integer getId() {
        return id;
    }

    public void setId(int id) { this.id = id;  }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
