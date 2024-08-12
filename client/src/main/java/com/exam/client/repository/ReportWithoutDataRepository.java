package com.exam.client.repository;

import com.exam.client.model.ReportWithoutData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportWithoutDataRepository extends JpaRepository<ReportWithoutData, Long> {

}
