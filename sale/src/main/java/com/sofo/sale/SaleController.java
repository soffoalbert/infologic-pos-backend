package com.sofo.sale;

import com.sofo.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

/**
 * Created by sofo on 2017/09/24.
 */
@RestController
@RequestMapping("/sale")
public class SaleController {

    private final Logger log = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SaleService saleService;

    /**
     * POST /sales -> Create a new sale.
     * @throws InventoryException
     */
    @RequestMapping(value = "/sales", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Product> > create(@Valid @RequestBody Sale sale) throws URISyntaxException, InventoryException {
        log.info("REST request to save Sale : {}", sale);
        Set<Product> products = saleService.registerSale(sale);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /*
     * PUT /sales -> Updates an existing sale.
     */
    @RequestMapping(value = "/sales", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> voidSale(@Valid @RequestBody Sale saleVO) throws URISyntaxException {
        log.info("REST request to void Sale : {}", saleVO);
        saleRepository.save(saleVO);
        return ResponseEntity.ok().build();
    }

    /*
    * GET /sales -> get all the sales.
    */
    @RequestMapping(value = "/sales", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sale>> getAll(Pageable pageable) throws URISyntaxException {
        List<Sale> sales = saleRepository.findAll();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
}
