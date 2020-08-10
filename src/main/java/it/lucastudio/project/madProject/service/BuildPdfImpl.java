package it.lucastudio.project.madProject.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


@Service
class BuildPdfImpl implements BuildPdf {
	
	

	public BuildPdfImpl() {
		super();
		
		
	}
	
	@Override
	public byte[] createReport(String json, String path) throws JRException {
		JasperReport report = (JasperReport) JRLoader.loadObject(new File(path));
		JsonDataSource ds = new JsonDataSource(new ByteArrayInputStream(json.getBytes()));
		JasperPrint jasperPrint = JasperFillManager.fillReport(report,new HashMap(), ds);
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
	}
	

}





