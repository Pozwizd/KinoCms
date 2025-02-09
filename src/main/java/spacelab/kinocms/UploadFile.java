package spacelab.kinocms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис для загрузки, удаления и проверки файлов.
 */
@Service
public class UploadFile {

    /**
     * Путь к папке для загрузки файлов. Задаётся в конфигурации приложения.
     */
    @Value("${upload.folder.path}")
    private String projectPath;

    /**
     * Загружает файл в заданную папку, удаляя при этом старый файл, если он указан.
     *
     * @param file    файл, который необходимо загрузить.
     * @param OldPath путь к старому файлу, который нужно удалить (может быть {@code null}).
     * @return относительный путь к загруженному файлу.
     */
    public String uploadFile(MultipartFile file, String OldPath) {
        createFolder(projectPath);
        deleteFile(OldPath);
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        try {
            file.transferTo(new File(Paths.get(projectPath).toAbsolutePath() + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/" + Paths.get(projectPath).subpath(Paths.get(projectPath).getNameCount() - 1,
                Paths.get(projectPath).getNameCount()) + "/" + fileName;
    }

    /**
     * Удаляет файл по указанному пути.
     *
     * @param deletingFilePath путь к файлу, который нужно удалить.
     *                         Может быть {@code null}, в таком случае метод ничего не делает.
     */
    public void deleteFile(String deletingFilePath) {
        if (deletingFilePath != null) {
            String nameFile = Arrays.stream(deletingFilePath.split("/"))
                    .skip(2)
                    .collect(Collectors.joining("/"));

            File oldFile = Paths.get(projectPath + "/" + nameFile).toAbsolutePath().toFile();
            oldFile.delete();
        }
    }

    /**
     * Создаёт папку по указанному пути, если она ещё не существует.
     *
     * @param path путь к папке, которую нужно создать.
     */
    public void createFolder(String path) {
        Path currentPath = Paths.get(path).toAbsolutePath();

        File folder = new File(currentPath.toString());

        if (!folder.exists()) {
            boolean created = folder.mkdir();
            if (created) {
                System.out.println("Папка успешно создана: " + folder.getAbsolutePath());
            } else {
                System.out.println("Не удалось создать папку: " + folder.getAbsolutePath());
            }
        } else {
            System.out.println("Папка уже существует: " + folder.getAbsolutePath());
        }
    }

    /**
     * Проверяет, является ли файл допустимым изображением по типу и размеру.
     *
     * @param file файл для проверки.
     * @return {@code true}, если файл допустимый; {@code false} в противном случае.
     */
    public boolean isAllowedImageTypeAndSize(MultipartFile file) {
        if (file.getContentType() == null) {
            return false;
        }
        if (file.isEmpty()) {
            return false;
        }
        if (file.getSize() > 10 * 1024 * 1024) { // Ограничение: 10 MB
            return false;
        }

        return StringUtils.startsWithIgnoreCase(file.getContentType(), "image/");
    }
}
