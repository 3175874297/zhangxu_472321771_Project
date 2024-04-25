package com.controller;


import com.auxiliary.MinDfaMapper;
import com.auxiliary.NfaMapper;
import com.req.DFAReq;
import com.req.LexicalAnalysisReq;
import com.req.NfaDfaReq;
import com.resp.LexicalAnalysisResp;
import com.resp.MinDfaResp;
import com.resp.NfaDfaResp;
import com.service.MinDfaService;
import com.service.NfaDfaService;
import com.utils.DfaEnterUtils;
import com.utils.NfaEnterUtils;
import com.service.LexicalAnalysisService;
import com.service.NfaDfaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lexical")
public class AboutLexicalController {

    @Autowired
    private LexicalAnalysisService lexicalAnalysisService;

    @Autowired
    private NfaDfaService nfaDfaService;

    @Autowired
    private NfaEnterUtils nfaEnterUtils;

    @Autowired
    private MinDfaService minDfaService;

    @Autowired
    private DfaEnterUtils dfaEnterUtils;


    @PostMapping("NfaDfa")
    public NfaDfaResp NfaTransformDfa(@RequestBody NfaDfaReq nfaDfaReq)throws Exception{
        NfaMapper nfaMapper = new NfaMapper();
        NfaDfaResp dfa = new NfaDfaResp();
        try {
            nfaEnterUtils.enterK(nfaMapper,nfaDfaReq.getK());
            nfaEnterUtils.enterLetters(nfaMapper,nfaDfaReq.getLetters());
            nfaEnterUtils.enterF(nfaMapper,nfaDfaReq.getF());
            nfaEnterUtils.enterS(nfaMapper,nfaDfaReq.getS());
            nfaEnterUtils.enterZ(nfaMapper,nfaDfaReq.getZ());
        }catch (Exception e){
            e.printStackTrace();
        }
        dfa = nfaDfaService.definite(nfaMapper);
//        NfaDfaResp nfaDfaResp= new NfaDfaResp();
        String[] change1 = new String[dfa.getIndex()];
        String[] change=new String[100];
        String[] Queue=new String[100];
//        新状态生成赋值
        change = dfa.getChange();
        for (int i = 0; i <change.length; i++) {
            if (change[i]!=null){
                change1[i]=change[i];
            }

        }
        dfa.setChange(change1);
//        队列赋值
        Queue = dfa.getQueue();
        String[] Queue1 = new String[dfa.getIndex()];
        for (int i = 0; i < Queue.length; i++) {
            if (Queue[i]!=""){
                Queue1[i-1]=Queue[i];
                Queue1[i-1]=Queue1[i-1].trim();
            }
        }
        dfa.setQueue(Queue1);
        return dfa;
    }

    @PostMapping("minDfa")
    public MinDfaResp MinDfa(@RequestBody DFAReq dfaReq){
        MinDfaMapper minDfaMapper = new MinDfaMapper();
        minDfaMapper.setK(dfaEnterUtils.enterK(dfaReq.getK()));
        minDfaMapper.setLetters(dfaEnterUtils.enterLetters(dfaReq.getLetters()));
        minDfaMapper.setF(dfaEnterUtils.enterF(dfaReq.getF()));
        minDfaMapper.setS(dfaEnterUtils.enterS(dfaReq.getS()));
        minDfaMapper.setZ(dfaEnterUtils.enterZ(dfaReq.getZ()));
        MinDfaResp minDfaResp = new MinDfaResp();
        minDfaResp = minDfaService.minDFA(minDfaMapper);
        return minDfaResp;
    }

    @PostMapping("lexicalAnalysis")
    public LexicalAnalysisResp LexicalAnalysis(@RequestBody LexicalAnalysisReq lexicalAnalysisReq){
        lexicalAnalysisService.Filter(lexicalAnalysisReq.getProgram());
        lexicalAnalysisReq.setProgram(null);
        LexicalAnalysisResp analysisResp = lexicalAnalysisService.invoke();
        return analysisResp;
    }
}
