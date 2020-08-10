package it.lucastudio.project.madProject.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name="static_mail_data")
@AllArgsConstructor
@NoArgsConstructor
public class StaticMailData {
	
	@Id
	Integer id;
	String bodytext;
	String oggetto;
	String type_mail;

}
