package com.enviro.assessment.grad001.giftmasenya.service;

import com.enviro.assessment.grad001.giftmasenya.entity.AccountProfile;
import com.enviro.assessment.grad001.giftmasenya.repository.AccountProfileRepository;
import com.enviro.assessment.grad001.giftmasenya.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class FileParserServiceImpl implements FileParserService {

    private final AccountProfileRepository accountProfileRepository;

    @Override
    public void parseCSV(File csvFile) throws IOException {

        String name = null;
        String surname = null;
        String imageData = null;

        Reader in = new FileReader(csvFile);
        Iterable<CSVRecord> records =  CSVFormat.DEFAULT
                .builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build()
                .parse(in);

        for(CSVRecord record : records) {
            name = record.get("name");
            surname = record.get("surname");
            imageData = record.get("imageData");

            URI imageLink = createImageLink(convertCSVDataToImage(imageData));

            AccountProfile accountProfile = AccountProfile.builder()
                    .accountHolderName(name)
                    .accountHolderSurname(surname)
                    .httpImageLink(imageLink)
                    .build();

            accountProfileRepository.save(accountProfile);
        }

    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) throws IOException {

        File imageFile = null;

        Reader in = new FileReader(AppConstants.FILE);
        Iterable<CSVRecord> records =  CSVFormat.DEFAULT
                .builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build()
                .parse(in);

        if(accountProfileRepository.existsById(1L)) {
            imageFile = new File("enviro.jpeg");
        }

        if(accountProfileRepository.existsById(2L)) {
            imageFile = new File("momentum.png");
        }

        for(CSVRecord record : records) {
            if(accountProfileRepository.existsById(1L)) {
                imageFile = new File("enviro.jpeg");
                base64ImageData = record.get("imageData");
            }

            if(accountProfileRepository.existsById(2L)) {
                imageFile = new File("momentum.png");
                base64ImageData = record.get("imageData");
            }
        }

        byte[] decodedBytes = Base64.getDecoder().decode(base64ImageData);
        assert imageFile != null;
        FileUtils.writeByteArrayToFile(imageFile, decodedBytes);

        return imageFile;
    }


    @Override
    public URI createImageLink(File fileImage) {
        return fileImage.toURI();
    }
}
