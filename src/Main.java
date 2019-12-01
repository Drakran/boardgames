import java.io.FileInputStream;
import java.io.IOException;

import com.google.api.Page;
import com.google.api.client.util.Lists;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.Bucket;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;

public class Main {
	public static void main(String [] args) throws IOException {
		Storage storage = (Storage) StorageOptions.getDefaultInstance().getService();
		}
}
