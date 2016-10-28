package mx.com.kubo.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class SHA1 {
	
	private MessageDigest md;
    private byte[] buffer, digest;
    private String hash = "";
    
    public String getHash(String message) throws NoSuchAlgorithmException 
    {
        buffer = message.getBytes();        
        md     = MessageDigest.getInstance("SHA1");
        
        md.update(buffer);
        
        digest = md.digest();

        for(byte aux : digest) 
        {
            int b = aux & 0xff;
            
            if (Integer.toHexString(b).length() == 1) 
            {
            	hash += "0";
            }
            	
            hash += Integer.toHexString(b);
        }
        
        return hash;
     }

    public static String hmacSha1(String value, String key) {
        
    	try {
    		
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();           
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            // Convert raw bytes to Hex
            byte[] hexBytes = new Hex().encode(rawHmac);

            //  Covert array of Hex bytes to a String
            return new String(hexBytes, "UTF-8");
            
        } catch (Exception e) {
            
        	throw new RuntimeException(e);
        	
        }
    }

    
    
}
