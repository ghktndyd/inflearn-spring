package hello.core;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {

        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("안녕롬복아");

        System.out.println("helloLombok = " + helloLombok.getName());

        System.out.println("helloLombok = " + helloLombok);

    }
}


