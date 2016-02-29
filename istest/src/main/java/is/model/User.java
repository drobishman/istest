package is.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	private String login;

	private String password;

	private String firstname;

	private String lastname;

	private double phone;

	private String email;

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="agent_id", nullable=true)
	private User agent;

	@OneToMany(mappedBy="agent")
	private Set<User> customers = new HashSet<User>();

	@OneToOne( cascade = {CascadeType.PERSIST})
	@JoinTable(name="user_roles",
	joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
	inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
			)
	private Role role;

	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinTable(name="bookmark", 
	joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
	inverseJoinColumns = {@JoinColumn(name="property_id", referencedColumnName="id"), }
			)
	private Set<Property> properties = new HashSet<Property>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public double getPhone() {
		return phone;
	}

	public void setPhone(double phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<User> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<User> customers) {
		this.customers = customers;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}


	public User toJava(String user_JSON){

		Gson gson = new Gson();

		User user = new User();

		user = gson.fromJson(user_JSON, User.class);

		return user;
	}

	public JSONObject toJSON(){

		JSONArray ja = new JSONArray();

		for(Property temp: this.getProperties()){
			ja.put(temp.toJSON());
		}

		JSONObject jo = new JSONObject();
		jo.put("id", this.getId());
		jo.put("login", this.getLogin());
		jo.put("password", this.getPassword());
		jo.put("role", this.getRole().getRole());
		jo.put("firstname", this.getFirstname());
		jo.put("lastname", this.getLastname());
		jo.put("phone", this.getPhone());
		jo.put("email", this.getEmail());
		if(this.getAgent()!=null){
			jo.put("agent_firstname", this.getAgent().getFirstname());
			jo.put("agent_lastname", this.getAgent().getLastname());
			jo.put("agent_phone", this.getAgent().getPhone());
		}
		jo.put("properties", ja);
		return jo;

	}

}
