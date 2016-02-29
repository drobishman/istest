package is.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.json.JSONObject;

import com.google.gson.Gson;

@Entity
@Table(name="property")
public class Property {
	
	@Id
	@GeneratedValue
	private Integer id;

	private String province;
	
	private String city;
	
	private String zone;
	
	private Integer rooms;
	
	private Integer bath;
	
	private String type;
	
	private String subtype;
	
	private Integer price;
	
	private Integer sqm;
	
	private String description;
	
	private String foto_link;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="bookmark", 
		joinColumns = {@JoinColumn(name="property_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")}
	)
	private Set<User> users;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="result", 
		joinColumns = {@JoinColumn(name="property_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="search_id", referencedColumnName="id")}
	)
	private Set<Search> result;
	

	public Set<Search> getResult() {
		return result;
	}

	public void setResult(Set<Search> result) {
		this.result = result;
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public Integer getBath() {
		return bath;
	}

	public void setBath(Integer bath) {
		this.bath = bath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getSqm() {
		return sqm;
	}

	public void setSqm(Integer sqm) {
		this.sqm = sqm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFoto_link() {
		return foto_link;
	}

	public void setFoto_link(String foto_link) {
		this.foto_link = foto_link;
	}
	
public Property toJava(String property_JSON){
		
		Gson gson = new Gson();

		Property property = new Property();
		
		property = gson.fromJson(property_JSON, Property.class);
		
		return property;
	}

public JSONObject toJSON(){
	
	JSONObject jo = new JSONObject();
	jo.put("id", this.getId());
	jo.put("province", this.getProvince());
	jo.put("city", this.getCity());
	jo.put("zone",this.getZone());
	jo.put("rooms", this.getRooms());
	jo.put("bath", this.getBath());
	jo.put("type", this.getType());
	jo.put("subtype", this.getSubtype());
	jo.put("price", this.getPrice());
	jo.put("description", this.getDescription());
	jo.put("foto_link", this.getFoto_link());
	jo.put("sqm", this.getSqm());
	return jo;
 
}

}
