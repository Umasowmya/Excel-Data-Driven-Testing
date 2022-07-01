import java.io.IOException;
import java.util.ArrayList;

public class TestSample {
    public static void main(String[] args) throws IOException {
        DataDrivenTest data  = new DataDrivenTest();
        ArrayList<String> a = data.getData("AddProfile");
        System.out.println(a);
    }
}
