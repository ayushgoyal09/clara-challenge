package com.clara.lending.service.impl;

import com.clara.lending.client.GiphyClient;
import com.clara.lending.model.Data;
import com.clara.lending.model.GiphyMetada;
import com.clara.lending.model.GiphyResponse;
import com.clara.lending.model.MetaData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GifServiceImplTest {

    @InjectMocks
    private GifServiceImpl gifService;
    @Mock
    private GiphyClient giphyClient;
    private String query;
    private int limit;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        query = "cat";
        limit = 5;
    }

    @Test
    public void testSearchGif(){
        when(giphyClient.search(anyString(), anyInt())).thenReturn(getGiphyMetada());
        GiphyResponse response = gifService.searchGif(query, limit);
        assertNotNull("Giphy Response can not be null", response);
        List<MetaData> data = response.getData();
        assertNotNull("Giphy Response List cannot be null", data);
        Assert.assertTrue("Expected size is 5", data.size()==5);
    }

    @Test
    public void testSearchGif_Empty(){
        when(giphyClient.search(anyString(), anyInt())).thenReturn(new GiphyMetada());
        GiphyResponse response = gifService.searchGif(query, limit);
        assertNotNull("Giphy Response can not be null", response);
        List<MetaData> data = response.getData();
        assertNotNull("Giphy Response List cannot be null", data);
        Assert.assertTrue("Expected size is 5", data.size()==0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidQuery(){
        gifService.searchGif(null, 5);
        fail("Illegal Argument Exception is excepted");
    }

    private GiphyMetada getGiphyMetada(){
        GiphyMetada giphyMetada = new GiphyMetada();
        List<Data> dataList = new ArrayList<>();
        dataList.add(getData("www.lending.com", "12321"));
        dataList.add(getData("www.claralending.com", "546454"));
        dataList.add(getData("www.testexample.com", "435341"));
        dataList.add(getData("www.giphy.com", "324234"));
        dataList.add(getData("www.example.org", "5452323"));
        giphyMetada.setData(dataList);
        return giphyMetada;
    }

    private Data getData(String url, String id) {
        Data data1 = new Data();
        data1.setUrl(url);
        data1.setId(id);
        return data1;
    }

    private GiphyMetada getGiphyMetada_Empty(){
        GiphyMetada giphyMetada = new GiphyMetada();
        return giphyMetada;
    }
}
