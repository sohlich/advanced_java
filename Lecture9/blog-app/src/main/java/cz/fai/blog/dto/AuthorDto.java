package cz.fai.blog.dto;

import cz.fai.blog.domain.AuthorEntity;

/**
 *
 * @author Frantisek Spacek
 */
public class AuthorDto extends BaseDto<Integer> {

    private  String firstName;
    private  String lastName;
    private  String email;

    public AuthorDto(Integer id, String firstName, String lastName, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public static AuthorDto from(AuthorEntity entity) {
        return new AuthorDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail());
    }
}
