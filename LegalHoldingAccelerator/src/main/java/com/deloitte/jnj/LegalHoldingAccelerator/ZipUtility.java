package com.deloitte.jnj.LegalHoldingAccelerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtility {
	public String zip(Path file, Path zipFileName) {
		try {
			// create ZipOutputStream to write to the zip file
			FileOutputStream fos = new FileOutputStream(zipFileName.toFile());
			ZipOutputStream zos = new ZipOutputStream(fos);
			// add a new Zip Entry to the ZipOutputStream
			ZipEntry ze = new ZipEntry(file.toFile().getName());
			zos.putNextEntry(ze);
			// read the file and write to ZipOutputStream
			FileInputStream fis = new FileInputStream(file.toFile());
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			// Close the zip entry to write to zip file
			zos.closeEntry();
			// Close resources
			zos.close();
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zipFileName.toString();
	}

	public String unzip(Path zipFilePath, Path destDir) {
		File dir = destDir.toFile();
		// create output directory if it doesn't exist
		if (!dir.exists())
			dir.mkdirs();
		FileInputStream fis;
		// buffer for read and write data to file
		byte[] buffer = new byte[1024];
		String unzippedPath = null;
		try {
			fis = new FileInputStream(zipFilePath.toFile());
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			if (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				unzippedPath = newFile.getAbsolutePath();
				// System.out.println("Unzipping to " + unzippedPath);
				// create directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				// close this ZipEntry
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			// close last ZipEntry
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return unzippedPath;
	}

	public static void main(String[] args) {
		/*
		 * Path source = Paths.get(
		 * "C:\\Users\\bhapanda\\Documents\\jnj_source\\MT_PATENT_CONFLICT_FULL_.zip"
		 * ); Path target =
		 * Paths.get("C:\\Users\\bhapanda\\Documents\\jnj_intermediate"); String
		 * unzip = new ZipUtility().unzip(source, target);
		 * System.out.println(unzip);
		 */

		Path source = Paths.get("C:\\Users\\bhapanda\\Documents\\jnj_intermediate\\MT_PATENT_CONFLICT_FULL_.xlsx");
		Path target = Paths.get("C:\\Users\\bhapanda\\Desktop\\out.zip");
		String zip = new ZipUtility().zip(source, target);
		System.out.println(zip);
	}
}
