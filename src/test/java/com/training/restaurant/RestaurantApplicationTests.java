package com.training.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.restaurant.item.Item;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest
class RestaurantApplicationTests extends AbstractIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void save_one_item() throws Exception {
		final var item = new Item();
		item.setCategory("food");
		item.setName("meal");
		item.setBrand("house");
		item.setPrice(5F);

		mockMvc.perform(post("/menu")
				.content(new ObjectMapper().writeValueAsString(item))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.category").value("food"))
				.andExpect(jsonPath("$.name").value("meal"))
				.andExpect(jsonPath("$.brand").value("house"))
				.andExpect(jsonPath("$.price").value(5.0));
	}

}
