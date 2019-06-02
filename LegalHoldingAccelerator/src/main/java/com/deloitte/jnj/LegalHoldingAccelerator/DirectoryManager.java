package com.deloitte.jnj.LegalHoldingAccelerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryManager {

	private static final String userName = System.getProperty("user.name");

	private static final Path basePath = Paths.get("D:\\Users\\" + userName + "\\LegalHold_TestData");

	public Path getJnjSourcePath() {
		return basePath.resolve(Constants.sourceDirectory);
	}

	public Path getJnjIntermediatePath() {
		return basePath.resolve(Constants.intermediateDirectory);
	}

	public Path getJnjDestinationPath() {
		return basePath.resolve(Constants.destinationDirectory);
	}

	public Path getJnjWorkingPath() {
		return basePath.resolve(Constants.workingDirectory);
	}

	public void process() {
		Path sourcePath = basePath.resolve(Constants.sourceDirectory);
		Path intermediatePath = basePath.resolve(Constants.intermediateDirectory);
		Path destinationPath = basePath.resolve(Constants.destinationDirectory);
		try {
			if (Files.notExists(sourcePath)) {
				Files.createDirectories(sourcePath);
				System.out.println("Created directory: " + sourcePath);
			}
			if (Files.notExists(intermediatePath)) {
				Files.createDirectories(intermediatePath);
				System.out.println("Created directory: " + intermediatePath);
			}
			if (Files.notExists(destinationPath)) {
				Files.createDirectories(destinationPath);
				System.out.println("Created directory: " + destinationPath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
