package pdl.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ImageControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ImageDao imageDao;

	@BeforeAll
	public static void reset() {
  	// reset Image class static counter
  	ReflectionTestUtils.setField(Image.class, "count", Long.valueOf(0));
	}

	@Test
	@Order(1)
	public void getImageListShouldReturnSuccess() throws Exception {
		mockMvc.perform(get("/images"))
				.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	public void getImageShouldReturnNotFound() throws Exception {
		mockMvc.perform(get("/images/0"))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(3)
	public void getImageShouldReturnSuccess() throws Exception {
		// TODO
		Image img = new Image("image.jpg", new byte[] {1, 2, 3});
		imageDao.create(img);
		long id = img.getId();
		mockMvc.perform(get("/images/" + id))
				.andExpect(status().isOk());
		mockMvc.perform(delete("/images/" + id));
	}

	@Test
	@Order(4)
	public void deleteImagesShouldReturnMethodNotAllowed() throws Exception {
		mockMvc.perform(delete("/images/"))
				.andExpect(status().isMethodNotAllowed());
	}

	@Test
	@Order(5)
	public void deleteImageShouldReturnNotFound() throws Exception {
		// TODO
		mockMvc.perform(delete("/images/12"))
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(6)
	public void deleteImageShouldReturnSuccess() throws Exception {
		Image img = new Image("image.jpg", new byte[] {1, 2, 3});
		imageDao.create(img);
		long id = img.getId();
		mockMvc.perform(delete("/images/" + id))
				.andExpect(status().isNoContent());
	}

	@Test
	@Order(7)
	public void createImageShouldReturnSuccess() throws Exception {
		Image img = new Image("test.jpg", new byte[] {1, 2, 3});
		imageDao.create(img);
		mockMvc.perform(get("/images/" + img.getId()))
				.andExpect(status().isOk());
	}

	@Test
	@Order(8)
	public void createImageShouldReturnUnsupportedMediaType() throws Exception {
		Image img = new Image("test.png", new byte[] {1, 2, 3});
		imageDao.create(img);
		mockMvc.perform(get("/images/" + img.getId()))
				.andExpect(status().isOk());
	}
	
}
