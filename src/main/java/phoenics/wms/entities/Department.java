package phoenics.wms.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the departments database table.
 * 
 */
@Entity
@Table(name="departments")
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	private int sort;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="department")
	private List<User> users;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="parent_id")
	private Department department;

	//bi-directional many-to-one association to Department
	@OneToMany(mappedBy="department")
	private List<Department> departments;

	public Department() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setDepartment(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setDepartment(null);

		return user;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setDepartment(this);

		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setDepartment(null);

		return department;
	}

}