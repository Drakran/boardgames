package boardgame;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


import com.google.api.Page;
import com.google.api.client.util.Lists;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.Bucket;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;

public class Main {
	public static void main(String [] args) throws IOException {
//		 	Path gcp_key_file = Paths.get(System.getenv("GCP_KEY_FILE"));
//	        Files.deleteIfExists(gcp_key_file);
//	        Files.createFile(gcp_key_file);
//	        String env = System.getenv("GCP_CRED");
//	        Files.write(gcp_key_file, env.getBytes(), StandardOpenOption.WRITE);
//	       // Files.writeString(gp_keyc_file, System.getenv("GCP_CRED"), StandardOpenOption.WRITE);
		  // If you don't specify credentials when constructing the client, the client library will
		  // look for credentials via the environment variable GOOGLE_APPLICATION_CREDENTIALS.
		  Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();
	        
		}
}
