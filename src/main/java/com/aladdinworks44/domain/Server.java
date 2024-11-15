package com.aladdinworks44.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="servers")
@Getter @Setter @NoArgsConstructor
public class Server {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="server_id")
	private Integer serverId;
    
  	@Column(name="model")
	private String model;
    
  	@Column(name="cpu_load")
	private double cpuLoad;
    
	




}
