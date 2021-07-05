package Server;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
    private Properties properties;
    private Session session ;
    private MimeMessage mimeMessage ;
    private Transport transport ;
    private String message ;
    private int code ;

    public SendMail() {
        String host = "smtp.gmail.com";
        properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        session = Session.getDefaultInstance(properties, null);
        code = (int)(Math.random() * 900 + 100) ;
        message = "Hi!\nYour sign up code is " + code ;
    }

    public int send(String address) {
        try {
            mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom("Bank.AP.FUM.finalProject@gmail.com");
            mimeMessage.setSubject("Sign up code");
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
            mimeMessage.setContent(message, "text/html;charset=UTF-8");

            transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "bank.ap.fum.finalproject", "asdQWE123!@#");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return code ;
    }
}
