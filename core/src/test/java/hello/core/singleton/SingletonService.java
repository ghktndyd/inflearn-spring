package hello.core.singleton;

public class SingletonService {

    // 1. 스태틱 영역에 객체를 딱 1개만 미리 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    // 2. 퍼블릭 메서드를 통해서만 객체를 사용할 수 있다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 만들어서 외부에서 객체를 생성하지 못하도록 한다.
    private SingletonService() {}

    public void login() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
