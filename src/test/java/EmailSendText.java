import utils.EmailSend;

public class EmailSendText {
    public static void main(String[] args) throws Exception {
        EmailSend emailSend = EmailSend.instance;
        emailSend.sendEmail("3127023395@qq.com");
    }
}
