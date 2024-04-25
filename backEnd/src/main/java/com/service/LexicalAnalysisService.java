package com.service;

import com.resp.LexicalAnalysisResp;

public interface LexicalAnalysisService {

    public void Filter(String sc);
    public void words();
    public LexicalAnalysisResp invoke();
}
