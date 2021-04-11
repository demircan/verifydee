package com.govern.studentdetech.dao.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "STUDENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudentEntry {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LASTNAME")
    private String lastname;
    
    @Column(name = "TCKNO")
    private String tckNo;

    @Column(name = "VERIFIED")
    private Boolean verified;
    
    @Column(name = "EXPIRE_DATE", nullable = false)
    private LocalDate expireDate;

    
}