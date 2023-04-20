package org.rajat.service;

import java.util.List;

import org.rajat.DAO.BrandDAO;
import org.rajat.model.BrandEntity;

public class BrandService {

	BrandDAO dao = new BrandDAO();

	public List<BrandEntity> getBrands() {
		return dao.getBrands();
	}

	public BrandEntity getBrandWithId(int brandId) {
		return dao.getBrandWithId(brandId);
	}

	public void createBrand(BrandEntity entity) {
		dao.createBrand(entity);
	}

	public void updateBrand(int brandId, BrandEntity brandEntity) {
		dao.updateBrand(brandId  ,brandEntity);
	}

	public void deleteBrand(int brandId) {
		dao.deleteBrand(brandId);
	}
}
