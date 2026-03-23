package agribazar.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private final String uploadDir = "uploads/";

	public String saveFile(MultipartFile file) throws IOException {

		File directory = new File(uploadDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

		Path path = Paths.get(uploadDir + fileName);
		
		Files.write(path, file.getBytes());

		return fileName; // This goes into imageUrl field
	}
}