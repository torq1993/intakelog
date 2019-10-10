package com.pepsi.onenetwork.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.pepsi.onenetwork.configuration.PodUploadConfiguration;

@Component
public class PodUploadUtil {

	@Autowired
	PodUploadConfiguration podUploadConfiguration;

	private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/**
	 * Global instance of the scopes required by this quickstart. If modifying
	 * these scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
	// private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
	// private static final String SERVICE_ACC_JSON = podUploadConfiguration.get

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @param HTTP_TRANSPORT
	 *            The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException
	 *             If the credentials.json file cannot be found.
	 */

	private static JsonBatchCallback<Permission> callback = new JsonBatchCallback<Permission>() {
		@Override
		public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) throws IOException {
			// Handle error
			System.err.println(e.getMessage());
		}

		@Override
		public void onSuccess(Permission permission, HttpHeaders responseHeaders) throws IOException {
			System.out.println("Permission ID: " + permission.getId());
		}
	};

	private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		String SERVICE_ACC_JSON = podUploadConfiguration.getServiceAccCred();
		ClassPathResource resource = new ClassPathResource(SERVICE_ACC_JSON);
		GoogleCredential credential = GoogleCredential.fromStream(resource.getInputStream())
				.createScoped(Collections.singleton(DriveScopes.DRIVE_FILE));
		return credential;
	}

	public String uploadFile(byte[] pdfByteStream, String dtno, String shipno)
			throws IOException, GeneralSecurityException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		File fileMetadata = new File();
		fileMetadata.setName(dtno + "_" + shipno + ".pdf");
		java.io.File tempFile = java.io.File.createTempFile("tmp", ".pdf", null);
		FileOutputStream fos = new FileOutputStream(tempFile);
		fos.write(pdfByteStream);
		fos.flush();
		fos.close();
		FileContent mediaContent = new FileContent("application/pdf", tempFile);
		File file = service.files().create(fileMetadata, mediaContent).setFields("id").execute();
		BatchRequest batch = service.batch();
		Permission userPermission = new Permission().setType("anyone").setRole("reader");
		service.permissions().create(file.getId(), userPermission).setFields("id").queue(batch, callback);

		batch.execute();
		return podUploadConfiguration.getPodPreviewUrl() + file.getId();
	}
}