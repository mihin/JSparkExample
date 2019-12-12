import java.io.Serializable;
import java.util.Random;

public class HighLoad implements Task, Serializable {

    @Override
    public String execute(int iterations) {
        String poorString = "";
        Random r = new Random();
        for (int i = 0; i <  iterations*1000; i++) {
            poorString += Character.forDigit(r.nextInt(36), 36);
        }
        return poorString;
    }
}
