package my.hikandgo.service;

import my.hikandgo.constants.DateFormats;
import my.hikandgo.constants.Tegs407;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Builder407 {

    public static Date currentDate = new Date();

    public static void buildActualDate(ArrayList<Path> targetPathList, ArrayList<Path> secondPathList) throws IOException {
        int i = 0;
        List<Path> xmlList = targetPathList.stream().filter(x -> x.toString().endsWith("XML")).collect(Collectors.toList());
        List<Path> xmlSecondList = secondPathList.stream().filter(x -> x.toString().endsWith("XML")).collect(Collectors.toList());
        System.out.println(xmlList);
        System.out.println(xmlSecondList);
        for (Path targetPath: xmlList) {
            try (
                    Stream<String> input = Files.lines(targetPath);
                    BufferedWriter output = new BufferedWriter(new FileWriter(xmlSecondList.get(i).toString()))
                    ) {
                String[] newList = input.toArray(String[]::new);
                i ++;
                for (String str:newList) {
                    if (str.contains(Tegs407.DATE_MSG.getTegOpen())) {
                        String newString = getNewStringLane(
                                str,
                                DateFormats.currentDateGenerator(DateFormats.MAIN_DATE_FORMAT),
                                Tegs407.DATE_MSG.getTegOpen(),
                                Tegs407.DATE_MSG.getTegClose()
                        );
                        output.write(newString + "\n");
                    } else {
                        output.write(str + "\n");
                    }
                }
            }
        }


    }

    public static String getNewStringLane(String str, String newString, String tegOpen, String tegClose) {
        int numberSpace = str.length() - str.replaceAll(" ", "").length();
        String lastString = " ".repeat(numberSpace)
                .concat(tegOpen)
                .concat(newString)
                .concat(tegClose);

        return lastString;
    }


}
