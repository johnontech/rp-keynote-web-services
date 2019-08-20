package com.nationwide.nf.rp.controller;

import com.nationwide.nf.rp.bean.*;
import com.nationwide.nf.rp.service.DocuSignSubscriptionService;
import com.nationwide.nf.rp.service.LoanService;
import com.nationwide.nf.rp.util.LoanWebServiceValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by John Jorgensen on 4/17/2017.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    DocuSignSubscriptionService docuSignSubscriptionService;

    @Autowired
    LoanWebServiceValidator validator;

    private Logger log = Logger.getLogger(getClass().getName());

    @RequestMapping(method = RequestMethod.GET, value = "/docuSignConfiguration/{feedSeqId}", produces =  {"application/json"})
    public ResponseEntity<DocuSignConfiguration> getConfiguration(@PathVariable String feedSeqId) {
        System.out.println("Calling getConfiguration with parameters: Feed Seq Id '" + feedSeqId);
        log.debug("Calling getConfiguration with parameters: Feed Seq Id '" + feedSeqId);
        DocuSignConfiguration docuSignConfiguration = docuSignSubscriptionService.getDocuSignSubscription(feedSeqId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");

        return new ResponseEntity<DocuSignConfiguration>(docuSignConfiguration, headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/docuSignConfiguration", produces =  {"application/json"})
    public ResponseEntity<DocuSignConfiguration[]> getAllConfigurations() {
        System.out.println("Calling getAllDocuSignConfigurations");
        log.debug("Calling getAllDocuSignConfigurations");
        DocuSignConfiguration[] allDocuSignConfigurations = docuSignSubscriptionService.getAllDocuSignSubscriptions();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");

        return new ResponseEntity<DocuSignConfiguration[]>(allDocuSignConfigurations, headers, HttpStatus.OK );
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/docuSignConfiguration/{feedSeqId}", produces =  {"application/json"})
    public ResponseEntity<DocuSignConfiguration> updateDocuSignConfiguration(@RequestBody DocuSignConfiguration docuSignConfiguration) {
        log.info("Calling updateDocuSignConfiguration with parameters, docuSignConfiguration: '" + docuSignConfiguration);
        docuSignSubscriptionService.updateDocuSignConfiguration(docuSignConfiguration);
        return new ResponseEntity<DocuSignConfiguration>(docuSignConfiguration, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/docuSignConfiguration/{feedSeqId}", produces =  {"application/json"})
    public ResponseEntity<DocuSignConfiguration> createDocuSignConfiguration(@RequestBody DocuSignConfiguration docuSignConfiguration) {
        log.info("Calling createDocuSignConfiguration with parameters, docuSignConfiguration: '" + docuSignConfiguration);
        docuSignSubscriptionService.createDocuSignConfiguration(docuSignConfiguration);
        return new ResponseEntity<DocuSignConfiguration>(docuSignConfiguration, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/docuSignConfiguration/{feedSeqId}", produces =  {"application/json"})
    public ResponseEntity<DocuSignConfiguration> deleteConfiguration(@PathVariable String feedSeqId) {
        log.debug("Delete configuration with parameters: Feed Seq Id '" + feedSeqId + "'");
        log.debug("Delete configuration with parameters: Feed Seq Id '" + feedSeqId + "'");
        log.debug("Delete configuration with parameters: Feed Seq Id '" + feedSeqId + "'");
        log.debug("Delete configuration with parameters: Feed Seq Id '" + feedSeqId + "'");
        log.debug("Delete configuration with parameters: Feed Seq Id '" + feedSeqId + "'");
        log.debug("Delete configuration with parameters: Feed Seq Id '" + feedSeqId + "'");
        log.debug("Delete configuration with parameters: Feed Seq Id '" + feedSeqId + "'");
//        DocuSignConfiguration docuSignConfiguration = docuSignSubscriptionService.deleteDocuSignSubscription(feedSeqId);
        log.debug("Successfully deleted configuration with parameters: Feed Seq Id '" + feedSeqId + "'");
        return new ResponseEntity<DocuSignConfiguration>(HttpStatus.OK);
    }
}
