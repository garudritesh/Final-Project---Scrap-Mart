package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.ScrapMaterial;
import com.app.entity.User;
import com.app.repo.UserRepository;
import com.app.repo.ScrapRepository;


@Service
public class ScrapServiceImple {

	@Autowired
	private ScrapRepository scrapRepository;
	
	public ScrapMaterial saveScrapMaterial(ScrapMaterial scrap) {
		return scrapRepository.save(scrap);
	}
	
	public ScrapMaterial updateScrapMaterial(ScrapMaterial scrap) {
		return scrapRepository.save(scrap);
	}
	
	public void deleteScrapMaterial(int id) {
		 scrapRepository.deleteById(id);
	}
	
	public List<ScrapMaterial> FindMaterial(int lht,int wth,int tth, String spe,String MT) {
		return scrapRepository.findScrapMaterial(lht,wth,tth,spe,MT);
	}
	
	public List<ScrapMaterial> FindMaterialByMaterialType(String MT) {
		return scrapRepository.findScrapMaterialByType(MT);
	}
	
	public ScrapMaterial fetchByScrapId(int id) {
		return scrapRepository.findByScrapId(id);
	}
	
	public List<ScrapMaterial> FindAllScrap(User user) {
		return scrapRepository.findByUser(user);
	}
	
	public void updateScrapImage(int materialName, byte[] scrapImage) {
		scrapRepository.updateScrapImage(scrapImage, materialName);
	}
	
	public void updateSpecificationImage(byte[] scrapImage, int materialName) {
		scrapRepository.updateSpecificationImage(scrapImage, materialName);
	}
	
	public Optional<ScrapMaterial> getByScrapId(int id) {
		return scrapRepository.findUsingScrapId(id);
	}
}
