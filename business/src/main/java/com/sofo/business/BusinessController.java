package com.sofo.business;

import com.sofo.domain.Business;
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

/**
 * Created by sofo on 2017/09/24.
 */
@RestController
@CrossOrigin
@RequestMapping("/business")
public class BusinessController {

    private final Logger log = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    private BusinessRepository businessRepository;

    /**
     * POST /businesss -> Create a new business.
     */
    @RequestMapping(value = "/business", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody Business businessVO) throws URISyntaxException {
        log.info("REST request to save Business : {}", businessVO);
        businessRepository.save(businessVO);
        return ResponseEntity.ok().build();
    }

    /**
     * PUT /businesss -> Updates an existing business.
     */
    @RequestMapping(value = "/business", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody Business businessVO) throws URISyntaxException {
        log.info("REST request to update Business : {}", businessVO);
        businessRepository.save(businessVO);
        return ResponseEntity.ok().build();
    }

    /**
     * GET /businesss -> get all the businesss.
     */
    @RequestMapping(value = "/businesses/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Business>> getAllForUser(@PathVariable("userId") ObjectId id) throws URISyntaxException {
        log.info("REST request to get all Businesses for user : {}", id);
        List<Business> businesses = businessRepository.findAllByUserId(id);
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    /**
     * GET /businesss/:id -> get the "id" business.
     */
    @RequestMapping(value = "/business/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Business> get(@PathVariable ObjectId id) {
        log.debug("REST request to get Business : {}", id);
        return Optional.ofNullable(businessRepository.findOne(id))
                .map(business -> new ResponseEntity<>(business, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
