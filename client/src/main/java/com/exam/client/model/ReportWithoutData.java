package com.exam.client.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "report")
@Entity
public class ReportWithoutData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq")
    @SequenceGenerator(allocationSize = 1, name = "report_seq", sequenceName = "report_seq")
    @Column(name = "id_report", nullable = false, unique = true)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "id_client", nullable = false)
    private Long idClient;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "data", columnDefinition = "CHARACTER LARGE OBJECT")
    private String data;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status", length = 15, nullable = false)
    private ReportStatus reportStatus;

}
