package stu.learning.service.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ProductsApplicationTests {

		private final String PRODUCTS_PATH = "/products";

		@Autowired
		private MockMvc mockMvc;

		@Test
		public void whenReadAll_thenStatusIsOk() throws Exception {
			this.mockMvc.perform(get(PRODUCTS_PATH))
					.andExpect(status().isOk());
		}
//
//		@Test
//		public void whenReadOne_thenStatusIsOk() throws Exception {
//			this.mockMvc.perform(get(STUDENTS_PATH + 1))
//					.andExpect(status().isOk());
//		}
//
//		@Test
//		public void whenCreate_thenStatusIsCreated() throws Exception {
//			Student student = new Student(10, "Albert", "Einstein");
//			this.mockMvc.perform(post(STUDENTS_PATH).content(asJsonString(student))
//					.contentType(MediaType.APPLICATION_JSON_VALUE))
//					.andExpect(status().isCreated());
//		}
//
//		@Test
//		public void whenUpdate_thenStatusIsOk() throws Exception {
//			Student student = new Student(1, "Nikola", "Tesla");
//			this.mockMvc.perform(put(STUDENTS_PATH + 1)
//					.content(asJsonString(student))
//					.contentType(MediaType.APPLICATION_JSON_VALUE))
//					.andExpect(status().isOk());
//		}
//
//		@Test
//		public void whenDelete_thenStatusIsNoContent() throws Exception {
//			this.mockMvc.perform(delete(STUDENTS_PATH + 3))
//					.andExpect(status().isNoContent());
//		}

		private String asJsonString(final Object obj) {
			try {
				return new ObjectMapper().writeValueAsString(obj);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

}


//import org.springframework.beans.factory.annotation.Autowired;
//		import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//		import org.springframework.boot.test.mock.mockito.MockBean;
//		import org.springframework.test.web.servlet.MockMvc;
//
//@WebMvcTest(GreetingController.class)
//public class WebMockTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private GreetingService service;
//
//	@Test
//	public void greetingShouldReturnMessageFromService() throws Exception {
//		when(service.greet()).thenReturn("Hello, Mock");
//		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Hello, Mock")));
//	}
//}

//@WebMvcTest
//public class WebLayerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	public void shouldReturnDefaultMessage() throws Exception {
//		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Hello, World")));
//	}
//}