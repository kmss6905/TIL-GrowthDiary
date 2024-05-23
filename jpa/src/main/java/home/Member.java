package home;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")
public class Member {

  @Id
  private Long id;
  private String name;

  public Member(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Member() {

  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
