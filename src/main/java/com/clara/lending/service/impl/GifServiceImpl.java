package com.clara.lending.service.impl;

import com.clara.lending.client.GiphyClient;
import com.clara.lending.model.Data;
import com.clara.lending.model.GiphyMetada;
import com.clara.lending.model.GiphyResponse;
import com.clara.lending.model.MetaData;
import com.clara.lending.service.GifService;
import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GifServiceImpl implements GifService {

    @Autowired
    private GiphyClient giphyClient;

    @Override
    public GiphyResponse searchGif(String query, int limit) {
        Preconditions.checkArgument(StringUtils.isNotBlank(query), "Please pass valid query");
        Preconditions.checkArgument(limit > 0, "Please pass valid limit statement");
        GiphyMetada giphyMetada = giphyClient.search(query, limit);
        GiphyResponse response = new GiphyResponse();
        if(giphyMetada != null){
            List<Data> dataList = giphyMetada.getData();
            /*The number of results returned are always 5. If results returned by client
            are less than 5 will return an empty list
            */
            if(CollectionUtils.isNotEmpty(dataList) && dataList.size() >= 5){
                List<MetaData> metaDataList = response.getData();
                dataList.stream().forEach(data -> {
                    MetaData metaData = new MetaData();
                    metaData.setGif_id(data.getId());
                    metaData.setUrl(data.getUrl());
                    metaDataList.add(metaData);
                });
            }
        }
        return response;
    }
}
