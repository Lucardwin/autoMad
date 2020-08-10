package it.lucastudio.project.madProject.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name="data_school")
@Table(name="data_school")
@AllArgsConstructor
@NoArgsConstructor
public class DataSchool {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@CsvBindByName
	String regione;
	@CsvBindByName
	String provincia;
	@CsvBindByName
	String denominazioneistitutoriferimento;
	@CsvBindByName
	String codicescuola;
	@CsvBindByName
	String denominazionescuola;
	@CsvBindByName
	String indirizzoscuola;
	@CsvBindByName
	String capscuola;
	@CsvBindByName
	String descrizionecomune;
	@CsvBindByName
	String descrizionetipologiagradoistruzionescuola;
	@CsvBindByName
	String indirizzoemailscuola;
	@CsvBindByName
	String indirizzopecscuola;
	@CsvBindByName
	String sitowebscuola;
	
	String sitomad;
	


}
