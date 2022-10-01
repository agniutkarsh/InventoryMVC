
package com.inventory.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.inventory.model.TShirt;

@Repository
public class TShirtDao {

	public List<TShirt> getAllTShirts() {

		final SessionFactory factory = HibernateUtil.getSessionFactory();

		String hql = "From TShirt";
		Session session = factory.openSession();
		Query query = session.createQuery(hql);
		List<TShirt> matchedTShirts = query.list();
		return matchedTShirts;

	}

}
