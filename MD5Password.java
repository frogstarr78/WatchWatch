import java.security.*;

/*
 * Derived from code found at: 
 *  http://www.spiration.co.uk/post/1199
 *
 * Thank you Christo
 *
 */

public class MD5Password {

	public static String encode(String password) {
		byte[] defaultBytes = password.getBytes();
		 StringBuffer hexBuffer = new StringBuffer();
		try{
			 MessageDigest algorithm = MessageDigest.getInstance("MD5");
			 algorithm.reset();
			 algorithm.update(defaultBytes);
			 byte messageDigest[] = algorithm.digest();
							
			for (int i=0;i<messageDigest.length;i++) {
				String hexChar = Integer.toHexString(0xFF & messageDigest[i]);
				if(hexChar.length() == 1) 
					hexBuffer.append('0');
				hexBuffer.append(hexChar);
			}
			 password=hexBuffer+"";
		}catch(NoSuchAlgorithmException nsae){
							
		}
		return hexBuffer+"";
	}
}
