import java.math.BigInteger;
import java.security.MessageDigest;

public class PwHash {
    public PwHash(){

    }
    public String hash_pw(String pw){
        String pwhash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pw.getBytes("utf8"));
            pwhash = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception exc){
            exc.printStackTrace();
        }
        return pwhash;
    }
}
