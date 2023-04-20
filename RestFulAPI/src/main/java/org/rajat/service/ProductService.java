package org.rajat.service;

import java.util.List;

import org.rajat.DAO.ProductDAO;
import org.rajat.model.ProductEntity;

public class ProductService {
	
	ProductDAO pDao =new ProductDAO();

	public List<ProductEntity> getProductWithBrandId(int brandId) {
		
		return pDao.getProductWithBrandId(brandId);
	}

	public List<ProductEntity> getProductWithBrandIdAndCategory(int brandId, String category) {
		return pDao.getProductWithBrandIdAndCategory(brandId , category);
	}

}
