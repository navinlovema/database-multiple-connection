import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void runIt(){
        List<String> data = new ArrayList<>();
        Thread pub = new Thread(new Publisher(data));
        pub.setName("Publisher");
        Thread sub = new Thread(new Subscriber(data));
        Thread sub2 = new Thread(new Subscriber(data));
        sub.setName("Subscriber1");
        sub2.setName("Subscriber2");
    }
    public static void main(String[] args) {

        runIt();
//        pub2.run();
    }
}
