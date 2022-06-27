import java.util.List;

public class Publisher implements Runnable {

    List<String> data;
    Publisher(List<String> data){
        this.data = data;
    }


    @Override
    public void run() {
        for (int i = 0; i <= 9; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Publishing"+ i);
            data.add(i+"");
        }
        data.add("EOF");
    }
}
