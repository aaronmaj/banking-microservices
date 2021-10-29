package com.banque.services.core.persistence.jpa;


import com.banque.services.core.model.Client;
import com.banque.services.core.persistence.ClientDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class JpaClientDao  implements ClientDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("banking-pu");

    @Override
    public List<Client> findAll() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Client> people = em.createQuery("select c from Client c", Client.class)
                .getResultList();
        em.getTransaction().commit();
        em.close();
        return people;
    }
    public Client findByCNI(String cni) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Client client = em.createQuery("select c from Client c where c.cni =?1", Client.class)
                .setParameter(1,cni)
                .getSingleResult();

        em.getTransaction().commit();
        em.close();
        return client;
    }
    @Override
    public Optional<Client> findbyId(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        em.getTransaction().commit();
        em.close();
        return Optional.of(client);
    }

    @Override
    public Integer save(Client client) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
        em.close();
        return client.getId();
    }

    @Override
    public void delete(Client client) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(client);
        em.getTransaction().commit();
        em.close();

    }

}