package org.rajat.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.rajat.model.BrandEntity;

public class BrandDAO {

	SessionFactory factory = new Configuration().configure("Hibernate.cfg.xml").addAnnotatedClass(BrandEntity.class)
			.buildSessionFactory();

	Session session = null;

	public List<BrandEntity> getBrands() {
		List<BrandEntity> list = new ArrayList<BrandEntity>();
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			list = session.createQuery("from brands").getResultList();
		} finally {
			session.close();
		}

		return list;
	}

	public BrandEntity getBrandWithId(int brandId) {
		BrandEntity brand = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			brand = session.get(BrandEntity.class, brandId);
		} finally {
			session.close();
		}
		return brand;

	}

	public void createBrand(BrandEntity entity) {
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

	public void updateBrand(int brandId, BrandEntity brandEntity) {
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			BrandEntity oldBrand = session.get(BrandEntity.class , brandId);
			oldBrand.setBrandName(brandEntity.getBrandName());
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

	public void deleteBrand(int brandId) {
			try {
				Session session=factory.getCurrentSession();
				session.beginTransaction();
				BrandEntity entity= session.get(BrandEntity.class, brandId);
				session.remove(entity);
				session.getTransaction().commit();
			} finally {
				if(session!=null)
				{
					session.close();
				}
			}
	}

}
