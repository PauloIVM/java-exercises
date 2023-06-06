package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class NewUser {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicios-jpa");
        EntityManager em = emf.createEntityManager();

        // INFO: Para escrever no banco:
        em.getTransaction().begin();
        em.persist(new User("paulo JPA 2", "email2"));
        em.getTransaction().commit();

        User user = em.find(User.class, 1L);
        System.out.println(user.getName());

        String jpql = "select u from User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setMaxResults(5);

        List<User> users = query.getResultList();

        for (User u: users) {
            System.out.println(u.getName());
        }

        // INFO: Para atualizar no banco:
        em.getTransaction().begin();
        User u2 = em.find(User.class, 1L);
        System.out.println(u2.getName());
        u2.setName("ChangedName");
        em.merge(u2);
        em.getTransaction().commit();

        System.out.println(em.find(User.class, 1L).getName());

        em.getTransaction().begin();
        u2.setName("Paulo again");
        em.merge(u2);
        em.getTransaction().commit();

        // INFO: Estado gerenciado vs não gerenciado:
        // em.detach(user);

        // INFO: Deletar um registro:
        // em.getTransaction().begin();
        // em.remove(user);
        // em.getTransaction().commit();

        System.out.println(em.find(User.class, 1L).getName());

        em.close();
        emf.close();
    }
}
