package org.rajat.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.rajat.model.BrandEntity;
import org.rajat.model.ProductEntity;

public class ProductDAO {
	SessionFactory factory = new Configuration().configure("Hibernate.cfg.xml").addAnnotatedClass(ProductEntity.class)
			.addAnnotatedClass(BrandEntity.class)
			.buildSessionFactory();

	Session session = null;

	public List<ProductEntity> getProductWithBrandId(int brandId) {
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			String HQL= "from products where brandId = "+brandId+"";
			List<ProductEntity> list= session.createQuery(HQL).getResultList();
			return list;
		} finally {
			session.close();
		}
	}

	public List<ProductEntity> getProductWithBrandIdAndCategory(int brandId, String category) {
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			String HQL = "from products where brandId= '" + brandId + "' and category= '" + category + "' ";
			List<ProductEntity> list= session.createQuery(HQL).getResultList();
			return list;
		} finally {
			session.close();
		}
	}
}
