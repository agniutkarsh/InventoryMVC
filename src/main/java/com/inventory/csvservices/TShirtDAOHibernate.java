package com.inventory.csvservices;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;

import com.inventory.csvservices.TShirt;

public class TShirtDAOHibernate {
	private static final EntityManagerFactory entityManagerFactory;

    // like SessionFactory, an EntityManagerFactory is set up once for an application
    // 		IMPORTANT: notice how the name here matches the name we gave the persistence-unit in persistence.xml!
    static  {
        entityManagerFactory = Persistence.createEntityManagerFactory("Tshirt-Search");
    }
    public void insertTShirtDataInDB(TShirt tShirt) {
        if (searchTShirts( tShirt.getId() ).size() == 0) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(tShirt);
            entityManager.getTransaction().commit();
            entityManager.close();
        } else {
            System.out.println("Already present! skipping..");
        }
    }

    public List<TShirt> searchTShirts(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        SearchSession searchSession = Search.session( entityManager );
        MassIndexer indexer = searchSession.massIndexer( TShirt.class ).threadsToLoadObjects( 7 );
        try {
            indexer.startAndWait();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        SearchResult<TShirt> result = searchSession.search(TShirt.class)
                .where( f -> f.match()
                        .fields("id")
                        .matching( id ) )
                .fetch(20);
        long totalHitCount = result.total().hitCount();
        List<TShirt> hits = result.hits().stream().collect(Collectors.toList());
        entityManager.getTransaction().commit();
        entityManager.close();
        return hits;
    }
	/*
	 * public static List<TShirt> searchTShirts(String colour, String gender, String
	 * size) { EntityManager entityManager =
	 * entityManagerFactory.createEntityManager();
	 * entityManager.getTransaction().begin(); SearchSession searchSession =
	 * Search.session( entityManager ); List<TShirt> hits=new ArrayList<>(); hits =
	 * searchSession.search( TShirt.class ) .where( f -> f.bool() .must(
	 * f.match().field("availability") .matching("Y")) .must(
	 * f.match().field("colour") .matching(colour)) .must( f.match().field("size")
	 * .matching(size)) .must( f.bool().should(
	 * f.match().field("genderRecommendation") .matching(gender)) .should(
	 * f.match().field("genderRecommendation") .matching("U")))
	 * ).fetchHits(20).stream().collect(Collectors.toList());
	 * 
	 * entityManager.getTransaction().commit(); entityManager.close(); return hits;
	 * }
	 */

}
