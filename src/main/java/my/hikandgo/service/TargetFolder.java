package my.hikandgo.service;

import my.hikandgo.constants.DateFormats;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TargetFolder {

    private ArrayList<Path> targetFilesList;
    private ArrayList<Path> secondFilesList;

    private Path targetPath;
    private Path secondPath;
    public TargetFolder(Path path) throws IOException{
        this.targetPath = path;
        createFolder(path);
        this.targetFilesList = createFileList(path);
        this.secondFilesList = createSecondFileList(this.targetFilesList);
    }

    private void createFolder(Path path) throws IOException {
        String folderName = path.toString().concat("/").concat(DateFormats.currentDateGenerator((DateFormats.DIRECTORY_NAME_FORMAT)).concat("_part1"));
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

    private ArrayList<Path> createSecondFileList(ArrayList<Path> targetList) {
        ArrayList<Path> pathList = new ArrayList<>();
        String date = DateFormats.currentDateGenerator(DateFormats.FILENAME_FORMAT);
        for (Path el: targetList) {
            if (el.getFileName().toString().contains("DI")) {
                String elName = el.getFileName().toString();
                StringBuilder str = new StringBuilder(elName);
                str = str.replace(39, 47, date);
                pathList.add(secondPath.resolve(Path.of(str.toString())));
            } else if (el.getFileName().toString().contains("INC")) {
                String elName = el.getFileName().toString();
                StringBuilder str = new StringBuilder(elName);
                str = str.replace(43, 51, date);
                pathList.add(secondPath.resolve(Path.of(str.toString())));
            } else {
                pathList.add(secondPath.resolve(Path.of(el.getFileName().toString())));
            }
        }
        return pathList;
    }

    private void createSecondFiles() throws IOException {

        for (Path path: this.secondFilesList) {
            Files.createFile(path);
        }
    }

    public ArrayList<Path> getTargetFilesList() {
        return targetFilesList;
    }

    public ArrayList<Path> getSecondFilesList() {
        return secondFilesList;
    }
}
