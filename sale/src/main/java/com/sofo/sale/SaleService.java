package com.sofo.sale;
import com.sofo.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sofo on 2017/09/24.
 */
@Service
public class SaleService {

    private final Logger log = LoggerFactory.getLogger(SaleController.class);
    @Value("${product.service.url}")
    private String productServiceUrl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SaleRepository saleRepository;


    public Set<Product> registerSale(Sale sale) throws  InventoryException{
        // update quantity on hand
        // save sale
        Set<Product> productSold = new HashSet<>();
        Set<Product> outOfStockProducts = new HashSet<>();
        for (Product product : sale.getProducts()) {
            ResponseEntity<Boolean> exchange =
                    this.restTemplate.exchange(productServiceUrl + "/updateQuantity/" + product.getId(), HttpMethod.PUT,
                            null, new ParameterizedTypeReference<Boolean>() {},
                            (Object) "mstine");

            boolean productQuantityUpdated = exchange.getBody();
            if (!productQuantityUpdated) {
                outOfStockProducts.add(product);
                log.error("this product: {} for business: {} is out of stock.", product.getName(), product.getBusinessId());
            } else {
                productSold.add(product);
            }
        }
        sale.setProducts(productSold);
        saleRepository.save(sale);
        return outOfStockProducts;
    }
}
