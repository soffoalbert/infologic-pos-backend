package com.sofo.sale;

import com.sofo.domain.Business;
import com.sofo.domain.Product;
import junit.framework.Assert;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sofo on 2017/09/24.
 */
public class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    private String productServiceUrl = "http://product:8086/product/";

    @Mock
    private RestTemplate restTemplate;


    private Sale sale;
    private Business business;
    private Product product;
    private Product product2;
    private Set<Product> products;

    private  SaleService saleService;

    @Before
    public void setup (){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterSaleHappyDay() throws InventoryException{
        saleService = new SaleService();
        ReflectionTestUtils.setField(saleService, "saleRepository", saleRepository);
        ReflectionTestUtils.setField(saleService, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(saleService, "productServiceUrl", productServiceUrl);

        Business business = new Business(new ObjectId("59c7a0f68c9d2e9422a7cce7"), "Nwambo Limited", "146 Long Street", "0214454854", "www.nwambo.com",
                "59c7a0f68c9d2e9422a7cce7");
        Product product = new Product(new ObjectId("59c7a0f68c9d2e9422a7cce7"), "socks", new BigDecimal(2), new BigDecimal(45),
                20, new ObjectId("59c7a0f68c9d2e9422a7cce7"));

        Product product2 = new Product(new ObjectId("59c7a0f68c9d2e9422a7cce8"), "socks", new BigDecimal(2), new BigDecimal(45),
                20, new ObjectId("59c7a0f68c9d2e9422a7cce7"));
        Set<Product> products = new HashSet<>();
        products.add(product);
        products.add(product2);

        Sale sale = new Sale();
        sale.setProducts(products);
        sale.setBusiness(business);

        Mockito.when(restTemplate.exchange(productServiceUrl + "/updateQuantity/59c7a0f68c9d2e9422a7cce8", HttpMethod.PUT,
                null, new ParameterizedTypeReference<Boolean>() {},
                (Object) "mstine")).thenReturn(new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK));
        Mockito.when(restTemplate.exchange(productServiceUrl + "/updateQuantity/59c7a0f68c9d2e9422a7cce7", HttpMethod.PUT,
                null, new ParameterizedTypeReference<Boolean>() {},
                (Object) "mstine")).thenReturn(new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK));
        Mockito.when(saleRepository.save(sale)).thenReturn(sale);
        Set<Product> outOfStockProducts = saleService.registerSale(sale);
        Mockito.verify(restTemplate, Mockito.times(1)).exchange(productServiceUrl + "/updateQuantity/59c7a0f68c9d2e9422a7cce8", HttpMethod.PUT,
                null, new ParameterizedTypeReference<Boolean>() {},
                (Object) "mstine");
        Mockito.verify(saleRepository, Mockito.times(1)).save(sale);
        Assert.assertEquals(outOfStockProducts.size(), 1);
    }

    @Test
    public void testRegisterSaleNotHappyDay() throws InventoryException{
        saleService = new SaleService();
        ReflectionTestUtils.setField(saleService, "saleRepository", saleRepository);
        ReflectionTestUtils.setField(saleService, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(saleService, "productServiceUrl", productServiceUrl);
        Business business = new Business(new ObjectId("59c7a0f68c9d2e9422a7cce7"), "Nwambo Limited", "146 Long Street", "0214454854", "www.nwambo.com",
                "59c7a0f68c9d2e9422a7cce7");
        Product product = new Product(new ObjectId("59c7a0f68c9d2e9422a7cce7"), "socks", new BigDecimal(2), new BigDecimal(45),
                20, new ObjectId("59c7a0f68c9d2e9422a7cce7"));

        Product product2 = new Product(new ObjectId("59c7a0f68c9d2e9422a7cce8"), "socks", new BigDecimal(2), new BigDecimal(45),
                20, new ObjectId("59c7a0f68c9d2e9422a7cce7"));
        Set<Product> products = new HashSet<>();
        products.add(product);
        products.add(product2);

        Sale sale = new Sale();
        sale.setProducts(products);
        sale.setBusiness(business);

        Mockito.when(restTemplate.exchange(productServiceUrl + "/updateQuantity/59c7a0f68c9d2e9422a7cce8", HttpMethod.PUT,
                null, new ParameterizedTypeReference<Boolean>() {},
                (Object) "mstine")).thenReturn(new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK));
        Mockito.when(restTemplate.exchange(productServiceUrl + "/updateQuantity/59c7a0f68c9d2e9422a7cce7", HttpMethod.PUT,
                null, new ParameterizedTypeReference<Boolean>() {},
                (Object) "mstine")).thenReturn(new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK));
        Mockito.when(saleRepository.save(sale)).thenReturn(sale);
        Set<Product> outOfStockProducts = saleService.registerSale(sale);
        Mockito.verify(restTemplate, Mockito.times(1)).exchange(productServiceUrl + "/updateQuantity/59c7a0f68c9d2e9422a7cce8", HttpMethod.PUT,
                null, new ParameterizedTypeReference<Boolean>() {},
                (Object) "mstine");
        Mockito.verify(saleRepository, Mockito.times(1)).save(sale);
        Assert.assertEquals(outOfStockProducts.size(), 2);
    }


}
