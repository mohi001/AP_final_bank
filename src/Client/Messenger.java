package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Messenger {
    DataOutputStream out;
    DataInputStream in;

    public Messenger(DataOutputStream out, DataInputStream in) {
        this.out = out;
        this.in = in;
    }

    public String send(String s) throws IOException
    {
        System.out.println("send:");
        for (String s1 : s.split("\\s"))
        {
            out.writeUTF(s1);
            System.out.println(s1);
        }
        System.out.println("}");
        return in.readUTF();
    }

    public String sendNS(String... s) throws IOException
    {
        System.out.println("send:");
        for (String s1 : s)
        {
            out.writeUTF(s1);
            System.out.println(s1);
        }
        System.out.println("}");
        return in.readUTF();
    }
}
