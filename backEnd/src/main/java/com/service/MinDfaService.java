package com.service;

import com.auxiliary.MinDfaMapper;
import com.resp.MinDfaResp;

public interface MinDfaService {
    public MinDfaResp minDFA(MinDfaMapper dfa);
}
