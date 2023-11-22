package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }

    /**
     * 웹과 관련이 전혀 없는 정보는 컨트롤러까지만 사용해야 한다. 서비스 계층부터는 웹 기술에 종속되지 않는 게 좋다.
     */
}
