package com.devmountain.training.test.sample.repository;

import com.devmountain.training.test.sample.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    Major findByName(String majorName);
}
