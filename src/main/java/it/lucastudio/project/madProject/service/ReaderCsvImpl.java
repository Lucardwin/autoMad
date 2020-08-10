package it.lucastudio.project.madProject.service;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import it.lucastudio.project.madProject.dto.DataSchool;

@Service
public class ReaderCsvImpl implements ReaderCsv {
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DataSchool> createListBean(byte[] bytes) throws Exception {

		Reader reader = new InputStreamReader(new ByteArrayInputStream(bytes));
		
		CsvToBean readerCsv= new CsvToBeanBuilder(reader)
				.withType(DataSchool.class)
				.withSeparator('|')	       
				.build();

		return readerCsv.parse();
	}

}
