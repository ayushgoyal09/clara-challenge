package com.clara.lending.client;

import com.clara.lending.exception.ClaraBusinessRuntimeException;
import com.clara.lending.exception.ClaraValidationError;
import com.clara.lending.model.GiphyMetada;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class GiphyClient {

    private static final Logger logger = LoggerFactory.getLogger(GiphyClient.class);


    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${giphy.host.name:http://api.giphy.com}")
    private String rootUrl;
    @Value(value = "${giphy.search.path:/v1/gifs/search}")
    private String searchPath;
    @Value(value = "${giphy.api.key:dc6zaTOxFJmzC}")
    private String apiKey;

    /*Centralized Giphy API configuration is done in application.properties
    * This can be moved to database for dynamic configuration*/
    public GiphyMetada search(String query, int limit) {
        Preconditions.checkArgument(StringUtils.isNotBlank(query), "Please pass valid query");
        Preconditions.checkArgument(limit > 0, "Please pass valid limit statement");
        Map<String, Object> params = new HashMap<>();
        params.put("query", query);
        params.put("apiKey", apiKey);
        params.put("limit", limit);
        String url = rootUrl + searchPath + "?q={query}&api_key={apiKey}&limit={limit}";
        logger.info("URL is : {}", url);
        GiphyMetada giphyMetada = null;
        try {
            ResponseEntity<GiphyMetada> entity = restTemplate.getForEntity(url, GiphyMetada.class, params);
            //If status returned by Giphy API is other than 200 propagating the error
            if (entity.getStatusCode() == HttpStatus.OK) {
                logger.info("Successfully retrieved results for Giphy search");
                giphyMetada = entity.getBody();
            } else {
            //Validation error is thrown if any error occurred while invoking API
                String message = "Error occured while invoking Giphy API:" + url;
                logger.error(message);
                ClaraValidationError error = new ClaraValidationError(entity.getStatusCodeValue() + "",
                        message, "Recoverable");
                throw new ClaraBusinessRuntimeException(error);
            }
        } catch (RestClientException e) {
            logger.error("Error occurred while searching giphy API for query :{}", query, e);
            throw e;
        }
        return giphyMetada;
    }

}
