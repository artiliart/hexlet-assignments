package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@EqualsAndHashCode(of = {"title" , "price"})
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private Integer price;
}
// END
