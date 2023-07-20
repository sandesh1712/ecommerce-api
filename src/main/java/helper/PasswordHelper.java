package helper;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

public class PasswordHelper {	
  public static String encryptPassword(String password){
	  HashCode hashedPassString = Hashing.sha256().hashString(password,Charsets.UTF_8);
	  return hashedPassString.toString();
  };
}
