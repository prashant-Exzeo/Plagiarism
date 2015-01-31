package com.knightRider.Plagiarism;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class SmbPlagiarism implements Plagiarism {

	public static final Logger LOGGER = LoggerFactory.getLogger(SmbPlagiarism.class);
	
	public File copyFile(final String networkUrl, final String fileName,
			final String userName, final String password) {
		boolean istaskDone = false;
		try {
			String fileContent = "Hey its demo file";

			String user = userName + ":" + password;
			LOGGER.info("User: {}", user);
			
			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(
					user);
			String path = networkUrl + fileName;
			LOGGER.info("Path : {}", path);
			
			SmbFile networkFile = new SmbFile(path, auth);
			SmbFileOutputStream smbFileOutputStream = new SmbFileOutputStream(
					networkFile);
			smbFileOutputStream.write(fileContent.getBytes());

			istaskDone = true;
			LOGGER.info("Successful : {}", istaskDone);
		} catch (Exception e) {
			istaskDone = false;
			LOGGER.error("Something went wrong :( ",e);
		}
		return null;
	}

}
