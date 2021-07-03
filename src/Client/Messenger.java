package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Messenger {
    DataOutputStream out;
    DataInputStream in;
    PrintWriter sendM;
    Scanner get;

    public Messenger(DataOutputStream out, DataInputStream in) {
        this.out = out;
        this.in = in;
        sendM = new PrintWriter(out);
        get = new Scanner(in);
    }

    public String send(String s)
    {
        for (String s1 : s.split("\\s"))
        {
            sendM.write(s1);
        }
        return get.nextLine();
    }
}
