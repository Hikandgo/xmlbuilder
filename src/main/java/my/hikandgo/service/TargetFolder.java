package my.hikandgo.service;

import my.hikandgo.constants.DateFormats;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TargetFolder {

    private final Date currentDate = new Date();
    private final ArrayList<Path> targetFilesList;
    private ArrayList<Path> secondFilesList;

    private final Path targetPath;
    private Path secondPath;
    public TargetFolder(Path path) throws IOException{
        this.targetPath = path;
        createFolder(path);
        this.targetFilesList = createFileList(path);
    }

    public void createFolder(Path path) throws IOException {
        String folderName = path.toString().concat("/").concat(dateFormater(DateFormats.DIRECTORY_NAME_FORMAT)).concat("_part1");
        Path newPath = Path.of(folderName);
        if (!Files.exists(newPath)) {
            Files.createDirectory(newPath);
            this.secondPath = newPath;
        } else {
            List<Path> folderList = createFolderList(path)
                    .stream()
                    .filter(x -> x.toString().contains("_part"))
                    .collect(Collectors.toList());

            int lastId = folderList.size() + 1;

            Path lastPath = Path.of(folderName.replace("_part1", "_part".concat(String.valueOf(lastId))));
            Files.createDirectory(lastPath);
            this.secondPath = lastPath;
        }

    }

    private String dateFormater(DateFormats date) {
        SimpleDateFormat dateForm = new SimpleDateFormat(date.getFormat());
        String newDate = dateForm.format(currentDate);
        return newDate;
    }

    private ArrayList<Path> createFileList(Path path) throws IOException {
        ArrayList<Path> pathList = new ArrayList<>();

        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path el : files) {
                if (Files.isRegularFile(el)) pathList.add(el);
            }
        }
        return pathList;
    }

    private ArrayList<Path> createFolderList(Path path) throws IOException {
        ArrayList<Path> pathList = new ArrayList<>();

        try (DirectoryStream<Path> folders = Files.newDirectoryStream(path)) {
            for (Path el: folders) {
                if (Files.isDirectory(el)) pathList.add(el);
            }
        }
        return pathList;
    }


}
