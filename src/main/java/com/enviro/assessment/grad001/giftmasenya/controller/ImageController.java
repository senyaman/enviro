package com.enviro.assessment.grad001.giftmasenya.controller;

import com.enviro.assessment.grad001.giftmasenya.service.FileParserService;
import com.enviro.assessment.grad001.giftmasenya.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    private final FileParserService fileParserService;

    @GetMapping(value = "/{name}/{surname}")
    public FileSystemResource getHttpImageLink(@PathVariable String name, @PathVariable String surname) throws IOException {
        fileParserService.parseCSV(AppConstants.FILE);

        return null;
    }
}
