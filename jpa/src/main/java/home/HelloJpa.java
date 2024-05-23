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
      Member member = new Member(111L, "A");
      Member member1 = new Member(211L, "B");

      em.persist(member);
      em.persist(member1); // 아직 쿼리 나가지 않고, 우선은 영속성 컨텍스트에 저장된 상태이다.
      System.out.println("===================");
      Member findMember = em.find(Member.class, 111L); // 1차캐시에서 조회한다.
      System.out.println(findMember);
      tx.commit(); // 실제 commit 시점에 쿼리가 나간다.
    } catch (Exception e) {
      System.out.println("error occur");
      e.printStackTrace();
      tx.rollback();
    }finally {
      em.close();
    }
    emf.close();
  }
}