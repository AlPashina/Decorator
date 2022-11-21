import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

import java.util.Base64;

public class MainFrame {
    public static void main(String[] args){
        List listA = new ArrayList();
        listA.add("element 0");
        listA.add("element 1");
        listA.add("element 2");

        CipherList cl=new CipherList(listA,"my key");
        cl.add("element 3");
        cl.print();
        cl.print();
        System.out.println(cl.get(2));
        cl.remove(1);
        cl.print();
    }

}
