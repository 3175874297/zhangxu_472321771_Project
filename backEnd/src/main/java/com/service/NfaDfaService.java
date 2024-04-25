package com.service;

import com.auxiliary.NfaMapper;
import com.resp.NfaDfaResp;


public interface NfaDfaService {
    public NfaDfaResp definite(NfaMapper nfa);
}
