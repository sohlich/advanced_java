package cz.fai.blog.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * Represents model for contact database table.
 *
 * @author Frantisek Spacek
 */
@Entity
@Table(name = "AUTHOR")
@NamedQueries(
        {
            @NamedQuery(name = "authorByEmailAndPassword", query = "select a from AuthorEntity a where a.email=:email and a.password=:pass")
        }
)
public class AuthorEntity extends BaseEntity<Integer> {
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    
    private Set<PostEntity> posts;
    
    @Column(name = "firstname", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name = "lastname", nullable = false)
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    @OneToMany(mappedBy = "author", orphanRemoval = true, cascade = CascadeType.ALL)
    public Set<PostEntity> getPosts() {
        if (posts == null) {
            this.posts = new LinkedHashSet<>();
        }
        return posts;
    }
    
    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }
    
    public void addPost(PostEntity postEntity) {
        getPosts().add(postEntity);
        postEntity.setAuthor(this);
    }
    
    public void removePost(PostEntity postEntity) {
        getPosts().remove(postEntity);
        postEntity.setAuthor(null);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.email);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthorEntity other = (AuthorEntity) obj;
        return Objects.equals(this.email, other.email);
    }
    
}
