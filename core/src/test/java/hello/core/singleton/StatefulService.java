package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 이 코드 때문에 찰나의 순간에 값이 겹치면 상당히 위험하다.
        return price;
    }
//    public int getPrice() {
//        return price;
//    }
}
