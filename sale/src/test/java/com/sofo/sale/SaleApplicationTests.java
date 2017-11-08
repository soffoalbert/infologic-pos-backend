package com.sofo.sale;

import com.sofo.domain.Business;
import com.sofo.domain.Product;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleApplicationTests {

	@Inject
	private SaleRepository saleRepository;

	@Inject
	private RestTemplate restTemplate;

	@Inject
	private SaleService saleService;

	private MockMvc restSaleMockMvc;

	private Sale sale;
	private Business business;
	private Product product;
	private Product product2;
	private Set<Product> products;


	@PostConstruct
	public void setup() {
		MockitoAnnotations.initMocks(this);
		SaleController saleController = new SaleController();
		ReflectionTestUtils.setField(saleController, "saleRepository", saleRepository);
		ReflectionTestUtils.setField(saleController, "restTemplate", restTemplate);
		ReflectionTestUtils.setField(saleController, "saleService", saleService);
		this.restSaleMockMvc = MockMvcBuilders.standaloneSetup(saleController).build();
	}

	@Before
	public void init() {
		business = new Business(new ObjectId("59c7a0f68c9d2e9422a7cce7"), "Nwambo Limited", "146 Long Street", "0214454854", "www.nwambo.com",
				"59c7a0f68c9d2e9422a7cce7");
		product = new Product(new ObjectId("59c788969abe6c8fb9827b18"), "socks", new BigDecimal(2), new BigDecimal(45),
				20, new ObjectId("59c7a0f68c9d2e9422a7cce7"));

		product2 = new Product(new ObjectId("59c788969abe6c8fb9827b15"), "socks", new BigDecimal(2), new BigDecimal(45),
				20, new ObjectId("59c7a0f68c9d2e9422a7cce7"));
		Set<Product> products = new HashSet<>();
		products.add(product);
		products.add(product2);

		sale = new Sale();
		sale.setId(new ObjectId("59c7a0f68c9d2e9422a7cce1"));
		sale.setTimeStamp(ZonedDateTime.now());
		sale.setProducts(products);
		sale.setBusiness(business);
	}

	@Test
	public void registerSale() throws Exception {
		// Create the Business
		restSaleMockMvc.perform(MockMvcRequestBuilders.post("/sales")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(TestUtil.convertObjectToJsonBytes(sale)))
				.andExpect(status().isOk());
	}

	@Test
	public void contextLoads() {
	}

}
