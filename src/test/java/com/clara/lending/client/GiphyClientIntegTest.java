package com.clara.lending.client;

import com.clara.lending.model.GiphyMetada;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GiphyClientIntegTest {

    @Autowired
    private GiphyClient giphyClient;
    private String query;
    private int limit;

    @Before
    public void init(){
        query = "funny cat";
        limit =5;
    }

    //Integration Test
    @Test
    public void testSearch(){
        GiphyMetada giphyMetada = giphyClient.search(query, limit);
        assertNotNull("Error occurred occurred while searching", giphyMetada);
        assertEquals("Expecting valid 200 response", 200, giphyMetada.getMeta().getStatus());
        assertNotNull("Expecting valid results ", giphyMetada.getData());
        assertTrue("Expecting more than one result", giphyMetada.getData().size() > 1);
    }
}
