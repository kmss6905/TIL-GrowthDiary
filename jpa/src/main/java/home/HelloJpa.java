package home;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
 * 엔티티 매니저는 쓰레드간에 공유 X(사용하고 버려야 한다)
 * JPA 의 모든 데이터변경은 트랜잭션 안에서 실행
 */
public class HelloJpa {
  public static void main(String[] args) {
    // 딱 하나
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    // 트랙잭션 단위, 일관적인 단위를 할 떄마다 반드시 엔티티 매니저를 만들어서 쿼리를 날려야한다.
    // 모든 데이터를 변경하는 작업은 트랜잭션 안에서 이루어져야 한다.
    EntityManager em = emf.createEntityManager();

    // 트랜잭션을 얻는다.
    EntityTransaction tx = em.getTransaction();

    // 트랜잭션 시작
    tx.begin();
    try {

      Member member = new Member();
      member.setId(2L);
      member.setName("A");

      em.persist(member);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    }finally {
      em.close();
    }
    emf.close();
  }
}