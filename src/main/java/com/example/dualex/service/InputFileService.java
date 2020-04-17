package com.example.dualex.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputFileService {

    public List<String> fileToArrayList(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        Files.lines(Path.of(path)).forEach(lines::add);
        return lines;
    }


}
