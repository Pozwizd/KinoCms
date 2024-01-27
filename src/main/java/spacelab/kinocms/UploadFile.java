package spacelab.kinocms;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFile {

    private static final String UPLOAD_FOLDER = Paths.get("images").toFile().getAbsolutePath() + "/";

    public String uploadFile(MultipartFile file, String OldPath) {

        if (OldPath != null){
            String filePath = Paths.get("").toFile().getAbsolutePath() + OldPath;
            File oldFile = new File(filePath);
            oldFile.delete();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            file.transferTo(new File(UPLOAD_FOLDER + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/images/" + fileName;
    }


    public Boolean deleteFile(String deletingFilePath) {
        if (deletingFilePath != null){
            String filePath = Paths.get("").toFile().getAbsolutePath() + deletingFilePath;
            File oldFile = new File(filePath);
            oldFile.delete();
            return true;
        }
        return false;
    }
}
