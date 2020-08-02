package com.pb.weixin.service;

import java.util.List;

import com.pb.weixin.vo.Cyric;

public interface CyricService {

	
	
	public List<Cyric> getAllCyrics(Cyric cyric);
	
	public List<Cyric> getAllCyricsByCyric(Cyric cyric);
	
	public void updateCyric(Cyric cyric);
	
	public void deleteCyric(Cyric cyric);
	
	public void addCyric(Cyric cyric);
}
