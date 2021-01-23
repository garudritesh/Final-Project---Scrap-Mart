package com.app.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="scrap_material")
public class ScrapMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="scrap_id")
	private int scrapId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="material_name")
	private String materialName;
	
	@Column(name="material_specification")
	private String materialSpecification;
	
	@Column(name="material_type")
	private String materialType;
	
	
	private String shape;
	
	@Column(name="heat_treatment")
	private String heatTreatement;
	
	private int length;
	
	private int width;
	
	private int thickness;
	
	private int raduis;
	
	@Column(name="material_description")
	private String materialdecription;
	
	private int quantity;
	
	private int price;
	
	private int id;
	
	@Column(name = "scrap_Image", length = 1000)
	private byte[] scrapImage;	
	
	@Column(name = "specification_Image", length = 1000)
	private byte[] specificationImage;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScrapId() {
		return scrapId;
	}

	public void setScrapId(int scrapId) {
		this.scrapId = scrapId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialSpecification() {
		return materialSpecification;
	}

	public void setMaterialSpecification(String materialSpecification) {
		this.materialSpecification = materialSpecification;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getHeatTreatement() {
		return heatTreatement;
	}

	public void setHeatTreatement(String heatTreatement) {
		this.heatTreatement = heatTreatement;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public int getRaduis() {
		return raduis;
	}

	public void setRaduis(int raduis) {
		this.raduis = raduis;
	}

	public String getMaterialdecription() {
		return materialdecription;
	}

	public void setMaterialdecription(String materialdecription) {
		this.materialdecription = materialdecription;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public byte[] getScrapImage() {
		return scrapImage;
	}

	public void setScrapImage(byte[] scrapImage) {
		this.scrapImage = scrapImage;
	}

	public byte[] getSpecificationImage() {
		return specificationImage;
	}

	public void setSpecificationImage(byte[] specificationImage) {
		this.specificationImage = specificationImage;
	}

	public ScrapMaterial() {
		super();
	}

	public ScrapMaterial(int scrapId, User user, String materialName, String materialSpecification, String materialType,
			String shape, String heatTreatement, int length, int width, int thickness, int raduis,
			String materialdecription, int quantity, int price, byte[] scrapImage, byte[] specificationImage) {
		super();
		this.scrapId = scrapId;
		this.user = user;
		this.materialName = materialName;
		this.materialSpecification = materialSpecification;
		this.materialType = materialType;
		this.shape = shape;
		this.heatTreatement = heatTreatement;
		this.length = length;
		this.width = width;
		this.thickness = thickness;
		this.raduis = raduis;
		this.materialdecription = materialdecription;
		this.quantity = quantity;
		this.price = price;
		this.scrapImage = scrapImage;
		this.specificationImage = specificationImage;
	}

	@Override
	public String toString() {
		return "ScrapMaterial [scrapId=" + scrapId + ", user=" + user + ", materialName=" + materialName
				+ ", materialSpecification=" + materialSpecification + ", materialType=" + materialType + ", shape="
				+ shape + ", heatTreatement=" + heatTreatement + ", length=" + length + ", width=" + width
				+ ", thickness=" + thickness + ", raduis=" + raduis + ", materialdecription=" + materialdecription
				+ ", quantity=" + quantity + ", price=" + price + ", id=" + id + ", scrapImage="
				+ Arrays.toString(scrapImage) + ", specificationImage=" + Arrays.toString(specificationImage) + "]";
	}





	

	

	
}
