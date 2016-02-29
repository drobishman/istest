package is.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import is.model.Property;
import is.model.Role;
import is.model.Search;
import is.model.User;
import is.service.PropertyService;
import is.service.SearchService;
import is.service.UserService;

@Controller
public class LinkNavigation {

	@Autowired
	UserService userService;
	@Autowired
	SearchService searchService;
	@Autowired
	PropertyService propertyService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("login-form");
	}

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexPage() {
		return new ModelAndView("home");
	}

	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ModelAndView userPage() {
		return new ModelAndView("user");
	}

	@RequestMapping(value="/agent", method=RequestMethod.GET)
	public ModelAndView agentPage() {
		return new ModelAndView("agent");
	}

	@RequestMapping(value="/administrator", method=RequestMethod.GET)
	public ModelAndView administratorPage() {
		return new ModelAndView("administrator");
	}

	@RequestMapping(value = "/android/login", method = RequestMethod.POST)	
	public  @ResponseBody String login( @RequestBody String user_JSON  ) {		

		User userJSON = new User();
		JSONObject status = new JSONObject();

		userJSON = userJSON.toJava(user_JSON);
		if(userJSON!=null){
			User user = userService.getUser(userJSON.getLogin(),userJSON.getPassword());	
			if(user!=null) {
				user = userService.getBookmark(user);
				return user.toJSON().toString().replace("\\","");
			}
			else
				status.put("status", "not found");
		}
		else{
			status.put("status", "user fail");
		}
		return status.toString();
	}

	@RequestMapping(value = "/android/register", method = RequestMethod.POST)	
	public  @ResponseBody String register( @RequestBody String user_JSON  ) {

		User userJSON = new User();
		JSONObject status=new JSONObject();

		userJSON = userJSON.toJava(user_JSON);
		if(userJSON!=null){
			Role role = new Role();
			role.setId(3);
			role.setRole("user");
			userJSON.setRole(role);
			if(userService.getUser(userJSON.getLogin())==null){
				userService.setUser(userJSON);
				status.put("status", "success");
			}else
				status.put("status", "duplicated");

		}
		else{
			status.put("status", "user fail");
		}
		return status.toString();
	}

	@RequestMapping(value = "/android/remove", method = RequestMethod.POST)	
	public  @ResponseBody String remove( @RequestBody String user_JSON  ) {

		User userJSON = new User();
		JSONObject status = new JSONObject();

		userJSON = userJSON.toJava(user_JSON);
		if(userJSON!=null){
			User user = userService.getUser(userJSON.getId(),userJSON.getPassword());
			user.setAgent(null);
			userService.updateUser(user);
			userService.removeUser(user);
			status.put("status","success");
		}
		else{
			status.put("status","error");
		}
		return status.toString();
	}

	@RequestMapping(value = "/android/update", method = RequestMethod.POST)	
	public  @ResponseBody String update( @RequestBody String user_JSON  ) {

		JSONObject status = new JSONObject();

		JSONObject jsonObject = new JSONObject(user_JSON);

		if(jsonObject.has("id") && jsonObject.has("password")){
			User user = userService.getUser(jsonObject.getInt("id"),jsonObject.getString("password"));
			user.setLogin(jsonObject.getString("email"));
			if(jsonObject.has("new_password")){
				user.setPassword(jsonObject.getString("new_password"));
			}else{
				user.setPassword(jsonObject.getString("password"));
			}
			user.setFirstname(jsonObject.getString("firstname"));
			user.setLastname(jsonObject.getString("lastname"));
			user.setPhone(jsonObject.getDouble("phone"));
			user.setEmail(jsonObject.getString("email"));
			userService.updateUser(user);
			status.put("status","updated");
		}
		else{
			status.put("status","not updated");
		}
		return status.toString();
	}

	@RequestMapping(value = "/android/doSearch", method = RequestMethod.POST)	
	public  @ResponseBody String doSearch( @RequestBody String search_JSON  ) {

		Search searchJSON = new Search();
		JSONArray ja = new JSONArray();

		searchJSON = searchJSON.toJava(search_JSON);
		if(searchJSON!=null){
			Search search = searchService.doSearch(searchJSON);
			for (Property temp : search.getResults()){
				ja.put(temp.toJSON());
			}    
		}
		return ja.toString().replace("\\","");
	}

	@RequestMapping(value = "/android/addBookmark", method = RequestMethod.POST)	
	public  @ResponseBody String addBookmark( @RequestBody String JSON  ) {

		JSONObject status = new JSONObject();

		JSONObject jo = new JSONObject(JSON);

		Property property = new Property();

		property = propertyService.getProperty(jo.getInt("id"));


		if(property!=null){

			userService.addBookmark(jo.getInt("user_id"), property);
			status.put("status", "success");
		}
		else{
			status.put("status", "property not found");
		}
		return status.toString();
	}

	@RequestMapping(value = "/android/removeBookmark", method = RequestMethod.POST)	
	public  @ResponseBody String removeBookmark( @RequestBody String JSON  ) {

		JSONObject status = new JSONObject();

		JSONObject jo = new JSONObject(JSON);

		Property property = new Property();

		property = propertyService.getProperty(jo.getInt("id"));


		if(property!=null){

			userService.removeBookmark(jo.getInt("user_id"), property);
			status.put("status", "removed");
		}
		else{
			status.put("status", "not removed");
		}
		return status.toString();
	}
}