package it.lucastudio.project.madProject.service;

import net.sf.jasperreports.engine.JRException;

public interface BuildPdf {

	byte[] createReport(String json, String path) throws JRException;

}
