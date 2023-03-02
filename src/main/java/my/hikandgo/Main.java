package my.hikandgo;

import my.hikandgo.constants.Tegs407;
import my.hikandgo.service.Builder407;
import my.hikandgo.service.TargetFolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String key = reader.readLine();

        TargetFolder folder = new TargetFolder(Path.of(key));
        Builder407.buildActualDate(folder.getTargetFilesList(), folder.getSecondFilesList());

    }
}