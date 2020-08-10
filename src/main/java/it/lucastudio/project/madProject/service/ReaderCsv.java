package it.lucastudio.project.madProject.service;

import java.util.List;

import it.lucastudio.project.madProject.dto.DataSchool;

public interface ReaderCsv {

	List<DataSchool> createListBean(byte[] bytes) throws Exception;

}
