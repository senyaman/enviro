package com.enviro.assessment.grad001.giftmasenya.controller;

import com.enviro.assessment.grad001.giftmasenya.service.FileParserService;
import com.enviro.assessment.grad001.giftmasenya.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    private final FileParserService fileParserService;

    @GetMapping(value = "/{name}/{surname}")
    public FileSystemResource getHttpImageLink(@PathVariable String name, @PathVariable String surname) throws IOException {
        fileParserService.parseCSV(AppConstants.FILE);
        return new FileSystemResource(fileParserService.convertCSVDataToImage(imageData().get(0)));
    }

    private List<String> imageData() throws IOException {

        List<String> imageData = new ArrayList<>();

        Reader in = new FileReader(AppConstants.FILE);
        Iterable<CSVRecord> records =  CSVFormat.DEFAULT
                .builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build()
                .parse(in);

        for(CSVRecord record : records) {
            imageData = List.of(record.get("imageData"));

        }

        return imageData;
    }
}
