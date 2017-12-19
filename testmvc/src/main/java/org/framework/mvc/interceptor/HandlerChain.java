package org.framework.mvc.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */
public class HandlerChain {
    private List<Interceptor> interceptorList = new ArrayList<>();
    private Handler handler = new Handler();
    private  int interceptorIndex = -1;
    public  HandlerChain(){
        interceptorList.add(new Interceptor1());
        interceptorList.add(new Interceptor2());
        interceptorList.add(new Interceptor3());
    }

    public  void execute(){
       boolean result =  executeBefore();
       if(result==false) return;

        handler.handle();
        executeAfter();
    }

    private  boolean executeBefore(){
        for(int i= 0;i<interceptorList.size();i++){

            if(interceptorList.get(i).before()==false) {
                interceptorIndex = i;
                triggerExecuteAfter();
                return  false;
            }
        }
        return true;
    }

    private  void triggerExecuteAfter(){
        for(int i= interceptorIndex -1;i>=0;i--){
            interceptorList.get(i).after();
        }
    }
    private  void executeAfter(){
        for(int i= interceptorList.size()-1;i>=0;i--){
            interceptorList.get(i).after();
        }
    }
}
