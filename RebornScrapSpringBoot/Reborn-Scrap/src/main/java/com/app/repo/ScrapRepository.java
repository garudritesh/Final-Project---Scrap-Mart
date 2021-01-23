package com.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.ScrapMaterial;
import com.app.entity.User;

@Repository
public interface ScrapRepository extends JpaRepository<ScrapMaterial, Integer>{

	
	@Query(value= "SELECT * FROM scrap_material  WHERE length = ? and width= ? and thickness = ? and shape = ? and material_type = ? ",nativeQuery = true)
	List<ScrapMaterial> findScrapMaterial(int lht,int wth,int tth, String spe,String MT);
	
	@Query(value= "SELECT * FROM scrap_material  WHERE  material_type = ? ",nativeQuery = true)
	List<ScrapMaterial> findScrapMaterialByType(String MT);
	
	
	public ScrapMaterial findByScrapId(int id);
	
	public List<ScrapMaterial> findByUser(User user);
	
	
	@Transactional
	@Modifying
	@Query(value= "UPDATE scrap_material SET scrap_image = ? where scrap_id = ?",nativeQuery = true)
	public int updateScrapImage(byte[] scrapImage, int materialName);
	
	@Transactional
	@Modifying
	@Query(value= "UPDATE scrap_material SET specification_image = ? where scrap_id = ?",nativeQuery = true)
	public int updateSpecificationImage(byte[] scrapImage, int materialName);
	
	
	@Query(value= "SELECT * FROM scrap_material  WHERE  scrap_id = ? ",nativeQuery = true)
	public Optional<ScrapMaterial> findUsingScrapId(int id);

}
