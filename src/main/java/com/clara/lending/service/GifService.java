package com.clara.lending.service;

import com.clara.lending.model.GiphyResponse;

public interface GifService {

    GiphyResponse searchGif(String query, int limit);

}
