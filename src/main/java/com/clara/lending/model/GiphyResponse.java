package com.clara.lending.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyResponse {

    private List<MetaData> data = new ArrayList<>();

    public List<MetaData> getData() {
        return data;
    }

    public void setData(List<MetaData> data) {
        this.data = data;
    }
}
