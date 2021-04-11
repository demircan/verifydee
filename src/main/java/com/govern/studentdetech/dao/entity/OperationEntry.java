package com.govern.studentdetech.dao.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "TXN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OperationEntry {

	@Id
    @Column(name = "OPERATION_ID", nullable = false)
    private String txnId;
	
	@Column(name = "TCKNO")
    private String tckNo;
	
	@Column(name = "BARKOD_NO")
    private String barkodNo;
	
	@Column(name = "VERIFIED")
    private Boolean verified;
	
	@Column(name = "OPERATION_DATE", nullable = false)
    private LocalDateTime operationDate;
	
}
