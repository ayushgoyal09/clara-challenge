package com.clara.lending.controller;

import com.clara.lending.model.GiphyResponse;
import com.clara.lending.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class GiphyController {

    @Autowired
    private GifService gifService;

    //API for searching gif. Setting the limit to 5, it's an optional parameter
    @RequestMapping(path = "/search/{query}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GiphyResponse> search(@PathVariable("query") @NotNull @Valid String query,
                                                @RequestParam(value= "limit", defaultValue = "5") Integer limit){
        return new ResponseEntity<>(gifService.searchGif(query, limit), HttpStatus.OK);
    }
}
