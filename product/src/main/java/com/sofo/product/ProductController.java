package com.sofo.product;

import com.sofo.domain.Product;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by sofo on 2017/09/24.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to save Product : {}", product);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    /**
     * PUT /products -> Updates an existing product.
     */
    @RequestMapping(value = "/updateQuantity/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateQuantity(@PathVariable ObjectId productId) throws URISyntaxException {
        log.debug("REST request to update Product Quantity : {}", productId);
        Product foundProduct = productRepository.findById(productId);
        if (foundProduct.getQuantityOnHand() <= 0) {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
        }
        foundProduct.setQuantityOnHand(foundProduct.getQuantityOnHand() - 1);
        productRepository.save(foundProduct);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    /**
     * PUT /products -> Updates an existing product.
     */
    @RequestMapping(value = "/products", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to update Product : {}", product);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    /**
     * GET /products/:id -> get the "id" product.
     */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> get(@PathVariable ObjectId id) {
        log.debug("REST request to get Product : {}", id);
        return Optional.ofNullable(productRepository.findOne(id))
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /products/:id -> get the "id" product.
     */
    @RequestMapping(value = "/fetchproducts/{businessId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllByBusiness(@PathVariable ObjectId businessId) {
        log.debug("REST request to get all Products For Business: {}", businessId);
        List<Product> products = productRepository.findAllByBusinessId(businessId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
