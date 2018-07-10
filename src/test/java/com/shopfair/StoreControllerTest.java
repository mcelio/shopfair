package com.shopfair;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.shopfair.dataaccessobject.StoreRepository;
import com.shopfair.service.store.StoreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreControllerTest {

  private static final String LOGIN_JSON = "{\"username\": \"shopfair\",\"password\" : \"shopfair\"}";
  private MockMvc mockMvc;
  @Autowired
  private StoreService carService;
  @Autowired
  private StoreRepository storeRepository;
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

  }

  @Test
  @WithMockUser(username = "shopfair", password = "shopfair", roles = "USER")
  public void addStore() throws Exception {

    String storeDtoToJson = "{\n"
        + "  \"address\": \"string\",\n"
        + "  \"coordinate\": {\n"
        + "    \"latitude\": 0,\n"
        + "    \"longitude\": 0\n"
        + "  },\n"
        + "  \"id\": 0,\n"
        + "  \"name\": \"string\",\n"
        + "  \"rating\": 0,\n"
        + "  \"storeType\": \"SWEET\"\n"
        + "}";
    this.mockMvc.perform(post("/v1/stores")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(storeDtoToJson)).andDo(print()).andExpect(status().isCreated());

  }


  @Test
  public void authentication() throws Exception {
    this.mockMvc.perform(post("/login").content(LOGIN_JSON)).andDo(print())
        .andExpect(header().stringValues("authorization"));
  }

}
