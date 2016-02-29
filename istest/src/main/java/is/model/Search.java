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
@Table(name="search")
public class Search {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer user_id;

	private String province;
	
	private String city;
	
	private String zone;
	
	private Integer rooms;
	
	private Integer bath;
	
	private Integer price_min;
	
	private Integer price_max;
	
	private Integer sqm_min;
	
	private Integer sqm_max;
	
	private String type;
	
	private String subtype;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="result", 
		joinColumns = {@JoinColumn(name="search_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="property_id", referencedColumnName="id")}
	)
	private Set<Property> result;
	
	public Set<Property> getResults() {
		return result;
	}

	public void setResults(Set<Property> result) {
		this.result = result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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

	public Integer getPrice_min() {
		return price_min;
	}

	public void setPrice_min(Integer price_min) {
		this.price_min = price_min;
	}

	public Integer getPrice_max() {
		return price_max;
	}

	public void setPrice_max(Integer price_max) {
		this.price_max = price_max;
	}

	public Integer getSqm_min() {
		return sqm_min;
	}

	public void setSqm_min(Integer sqm_min) {
		this.sqm_min = sqm_min;
	}

	public Integer getSqm_max() {
		return sqm_max;
	}

	public void setSqm_max(Integer sqm_max) {
		this.sqm_max = sqm_max;
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
	
public Search toJava(String search_JSON){
		
		Gson gson = new Gson();

		Search search = new Search();
		
		search = gson.fromJson(search_JSON, Search.class);
		
		return search;
	}
	
	public String toJSON(){
	
		JSONObject jo = new JSONObject();
		jo.put("id", this.getId());
		jo.put("user_id", this.getUser_id());
		jo.put("province", this.getProvince());
		jo.put("city", this.getCity());
		jo.put("zone", this.getZone());
		jo.put("rooms", this.getRooms());
		jo.put("bath", this.getBath());
		jo.put("price_min", this.getPrice_min());
		jo.put("price_max", this.getPrice_max());
		jo.put("sqm_min", this.getSqm_min());
		jo.put("sqm_max", this.getSqm_max());
		jo.put("type", this.getType());
		jo.put("subtype", this.getSubtype());
		
		return jo.toString();
	 
	}
	
}
