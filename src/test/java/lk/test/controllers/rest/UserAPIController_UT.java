package lk.test.controllers.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lk.test.data.entities.User;
import lk.test.services.UserService;

@WebMvcTest
class UserAPIController_UT {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private ObjectMapper obMapper;

	@BeforeEach
	void setUp() throws Exception {
		obMapper = new ObjectMapper();
	}

	@Test
	@DisplayName("GET REQ: / ->APP-STARTUP ")
	void appControllerStartUpTest() throws Exception {
		/*
		 * This will print the valid request on console
		 * -----------------------------------------------------------------------------
		 * this.mockMvc.perform(get("/user/")).andDo(print()).andExpect(status().isOk())
		 * .andExpect(content().string(containsString("APP-STARTED"))).andReturn();
		 * -----------------------------------------------------------------------------
		 */

		MvcResult result = this.mockMvc.perform(get("/user/")).andExpect(status().isOk())
				.andExpect(content().string(containsString("APP-STARTED"))).andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}

	@Test
	@DisplayName("GET REQ: /user/{id} ")
	public void getUser() throws Exception {

		int userId = 1111;
		User searchUser = new User(userId, "newFn", "newLn", "new@email.com");
		doReturn(searchUser).when(userService).getUserById(userId);

		MvcResult result = this.mockMvc.perform(get("/user/{uId}", userId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		User fetchedUser = obMapper.readValue(result.getResponse().getContentAsString(), User.class);
		assertEquals(fetchedUser, searchUser);

	}

	@Test
	@DisplayName("POST REQ: /user/save ")
	public void saveUserTest() throws Exception {

		User newUser = new User("saUser", "saname", "saveName@email.com");
		doReturn(newUser).when(userService).addUser(any());

		String jsonRequest = obMapper.writeValueAsString(newUser);
		MvcResult result = this.mockMvc
				.perform(post("/user/save").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();

//		if we want to check returned json with request
//		assertThat(resultContent).isEqualToIgnoringWhitespace(jsonRequest);

		User savedUser = obMapper.readValue(resultContent, User.class);
		assertEquals(savedUser, newUser);
	}

	@Test
	@DisplayName("GET REQ: /user/all ")
	public void getAllUsers() throws Exception {

		User user1 = new User(1111, "us1", "uss1", "uss1@email.com");
		User user2 = new User(2222, "us2", "uss2", "uss2@email.com");
		User user3 = new User(3333, "us3", "uss3", "uss3@email.com");

		List<User> userList = Stream.of(user1, user2, user3).collect(Collectors.toList());

		doReturn(userList).when(userService).findAllUsers();

		MvcResult result = this.mockMvc.perform(get("/user/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultContent = result.getResponse().getContentAsString();
		List<User> fetchedUsers = obMapper.readValue(resultContent,
				obMapper.getTypeFactory().constructCollectionType(List.class, User.class));

		assertEquals(fetchedUsers, userList);
	}

	@Test
	@DisplayName("PUT REQ: /user/update ")
	public void updateUserTest() throws Exception {

		User userUpdate = new User(1010, "Namef", "NameL", "nm@email.com");
		userUpdate.setFirstName("XXXXX");
		doReturn(userUpdate).when(userService).updateUser(userUpdate);

		String jsonRequest = obMapper.writeValueAsString(userUpdate);

		MvcResult result = this.mockMvc.perform(put("/user/update").contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest).header(HttpHeaders.IF_MATCH, 1)).andExpect(status().isOk()).andReturn();

		String resultContent = result.getResponse().getContentAsString();
		User updatedUser = obMapper.readValue(resultContent, User.class);

		assertEquals(updatedUser, userUpdate);

	}

	@Test
	@DisplayName("DELETE REQ: /user/delete ")
	public void deleteUserTest() throws Exception {
		User dUser = new User(2233, "userD", "dUser", "du@email.com");
		doNothing().when(userService).removeUser(any());

		String jsonRequest = obMapper.writeValueAsString(dUser);
		MvcResult result = this.mockMvc
				.perform(delete("/user/delete").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}

}
