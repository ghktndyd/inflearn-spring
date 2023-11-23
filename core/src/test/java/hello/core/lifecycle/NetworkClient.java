package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    @PostConstruct
    public void init() {
        /* 이 메세지가 나온다는 건 스프링 빈이 모두 생성되었다는 것이다. */
        System.out.println("NetworkClient.init");
        /*의존 관계 주입이 끝나면*/

    }

    @PreDestroy
    public void close() {
        /* 이 메세지가 나온다는 건 종료할 준비가 되었다는 뜻이다. 모든 빈을 소멸한다.*/
        System.out.println("NetworkClient.close");

        disconnect();
    }
}
