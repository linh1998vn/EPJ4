package com.example.fai_edunext.services;

import com.example.fai_edunext.dto.request.TestRequest;
import com.example.fai_edunext.dto.response.MessageResponse;
import com.example.fai_edunext.entity.Test;
import com.example.fai_edunext.repository.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class TestService {
    @Autowired
    ITestRepository iTestRepository;

    public ResponseEntity<?> getAllTest() {
        var tests = iTestRepository.findAllTest(false);
        if (Objects.isNull(tests)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Test not record"));
        }
        return ResponseEntity.ok().body(tests);
    }

    public ResponseEntity<?> getTestById(Long id) {
        var checkTest = iTestRepository.findById(id).get();
        if (Objects.isNull(checkTest)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Test not exists"));
        }
        return ResponseEntity.ok().body(checkTest);
    }

    public ResponseEntity<?> createTest(TestRequest testRequest) {
        Test test = new Test();
        test.setTestName(testRequest.getTestName());
        if (iTestRepository.existsByTestName(test.getTestName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Test already exists"));
        }
        test.setType(testRequest.getType());
        iTestRepository.save(test);
        return ResponseEntity.ok().body(new MessageResponse("Create Test successfully"));
    }

    public ResponseEntity<?> updateTest(Long id, TestRequest testRequest) {
        var checkTestById = iTestRepository.findById(id).get();
        if (Objects.isNull(checkTestById)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Test not exists"));
        }
        if(testRequest.getTestName() == null){
            checkTestById.setTestName(checkTestById.getTestName());
        } else {
            if (iTestRepository.existsByTestName(checkTestById.getTestName())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Test already exists"));
            }
            checkTestById.setTestName(testRequest.getTestName());
        }

        checkTestById.setType(testRequest.getType());
        checkTestById.setDelete(testRequest.isDelete());
        iTestRepository.save(checkTestById);
        return ResponseEntity.ok().body(new MessageResponse("update Test successfully"));
    }

    public ResponseEntity<?> deleteTestById(Long id) {
        var checkTest = iTestRepository.findById(id).get();
        if (Objects.isNull(checkTest)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Test not exists"));
        }
        checkTest.setDelete(true);
        iTestRepository.save(checkTest);
        return ResponseEntity.ok().body(new MessageResponse("delete Test successfully"));
    }

    public ResponseEntity<?> searchTest(String query) {
        var search = iTestRepository.searchTest(query);
        return ResponseEntity.ok().body(search);
    }

    // file
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    public Boolean createTestAll(String testName, String type, String dueDate, MultipartFile file) {
        try {
            Test test = new Test();
            test.setTestName(testName);
            test.setType(type);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDate date = LocalDate.parse(dueDate, dtf);
            test.setDueDate(date);
            test = iTestRepository.save(test);
            // set file
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("file");
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path file1 = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve("test_" + test.getId() + file.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(file1)) {
                os.write(file.getBytes());
            }
            test.setFile("test_" + test.getId() + file.getOriginalFilename());
            iTestRepository.save(test);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

