package com.devmountain.training.test.sample.service;

import com.devmountain.training.test.sample.entity.Major;
import com.devmountain.training.test.sample.repository.MajorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@SpringBootTest
public class SpringBootJpaTestExample {

    @Autowired
    private MajorRepository majorRepository;

    private String tempMajorName;

    @BeforeEach
    public void setup() {
        tempMajorName = "Major-" + getRandomInt(1, 1000);
    }

    @AfterEach
    public void teardown() {
    }

    @Test
    public void retrieveAllMajorsTest() {
        List<Major> majorList = majorRepository.findAll();
        assertEquals(7, majorList.size());
        displayMajorList(majorList);
    }

    @Test
    public void getMajorByIdTest() {
        /*
         * Pick up a random MajorModel from DB
         */
        Major randomMajor = getRandomMajor();
        if(randomMajor == null) {
            log.error("there is no major being found in database, please double check DB connection!");
        } else {
            Long majorId = randomMajor.getId();
            Optional<Major> retrievedOptionalMajor = majorRepository.findById(majorId);
            assertMajors(randomMajor, retrievedOptionalMajor.get());
        }
    }

    @Test
    public void getMajorByNameTest() {
        /*
         * Pick up a random MajorModel from DB
         */
        Major randomMajor = getRandomMajor();
        if(randomMajor == null) {
            log.error("there is no major being found in database, please double check DB connection!");
        } else {
            String majorName = randomMajor.getName();
            Major retrievedMajor = majorRepository.findByName(majorName);
            assertMajors(randomMajor, retrievedMajor);
        }
    }

    @Test
    public void saveMajorTest() {
        Major major = createMajorByName(tempMajorName);
        Major savedMajor = majorRepository.save(major);
        assertEquals(major.getName(), savedMajor.getName());
        assertEquals(major.getDescription(), savedMajor.getDescription());
        displayMajor(savedMajor);
        /*
         * Now clean up the saved Major from DB Major table
         */
        majorRepository.delete(savedMajor);
    }

    @Test
    public void updateMajorTest() {
        Major originalMajorModel = getRandomMajor();
        String currentMajorDesc = originalMajorModel.getDescription();
        String modifiedMajorDesc = currentMajorDesc + "---newUpdate";
        originalMajorModel.setDescription(modifiedMajorDesc);
        /*
         * Now start doing update operation
         */
        Major updatedMajor = majorRepository.save(originalMajorModel);
        assertMajors(originalMajorModel, updatedMajor);

        /*
         * now reset MajorModel description back to the original value
         */
        originalMajorModel.setDescription(currentMajorDesc);
        updatedMajor = majorRepository.save(originalMajorModel);
        assertMajors(originalMajorModel, updatedMajor);
    }

    @Test
    public void deleteMajorTest() {
        Major major = createMajorByName(tempMajorName);
        Major savedMajor = majorRepository.save(major);
        /*
         * Now delete the saved Major from DB Major table
         */
        majorRepository.delete(savedMajor);
    }


    private Major getRandomMajor() {
        List<Major> majorList = majorRepository.findAll();
        Major randomMajor = null;
        if(majorList != null && majorList.size() > 0) {
            int randomIndex = getRandomInt(0, majorList.size());
            randomMajor = majorList.get(randomIndex);
        }
        return randomMajor;
    }

    private void displayMajor(int index, Major major) {
        System.out.println("=== No. " + index + " Major:" );
        System.out.println("\t Id=" + major.getId());
        System.out.println("\t Name=" + major.getName());
        System.out.println("\t Description=" + major.getDescription());
    }

    private void displayMajor(Major major) {
        System.out.println("\t Id=" + major.getId());
        System.out.println("\t Name=" + major.getName());
        System.out.println("\t Description=" + major.getDescription());
    }

    /**
     * This helper method return a random int value in a range between
     * min (inclusive) and max (exclusive)
     * @param min
     * @param max
     * @return
     */
    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private void assertMajors(Major randomMajor, Major retrievedMajor) {
        assertEquals(randomMajor.getId(), retrievedMajor.getId());
        assertEquals(randomMajor.getName(), retrievedMajor.getName());
        assertEquals(randomMajor.getDescription(), retrievedMajor.getDescription());
    }

    private Major createMajorByName(String name) {
        Major major = new Major();
        major.setName(name);
        major.setDescription(name + "--description");
        return major;
    }

    private void displayMajorList(List<Major> majorList) {
        System.out.println("=== Total number of Majors found: " + majorList.size());
        int index = 1;
        for(Major major : majorList) {
            displayMajor(index, major);
            index++;
        }
    }


}
