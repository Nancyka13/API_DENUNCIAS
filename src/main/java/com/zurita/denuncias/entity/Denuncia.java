package com.zurita.denuncias.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder

@AllArgsConstructor
@NoArgsConstructor

@EntityListeners(AuditingEntityListener.class)
@Table(name="denuncias")
public class Denuncia {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name = "dni", nullable = false, length = 8)
    private String dni;
	
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    @Column(name = "titulo", nullable = false, length = 30)
    private String titulo;
    
    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;
    
    @Column(name = "descripcion", nullable = true, length = 255)
    private String descripcion;
    

    @Column(name = "created_at", nullable = false, updatable = false )
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
    
    @Column(name = "updated_at", nullable = false, updatable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    
    
    

	
}




