package cz.fai.utb.spring.blog.dto;

import cz.fai.utb.spring.blog.domain.PostEntity;
import java.util.Date;

/**
 * @author Frantisek Spacek
 */
public class PostDto extends BaseDto<Integer> {

    private String title;
    private String content;
    private Date createDate;
    private boolean published;

    public PostDto() {
        super(null);
    }

    public PostDto(Integer id, String title, String content, boolean
            published, Date createDate) {
        super(id);
        this.title = title;
        this.content = content;
        this.published = published;
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public static PostEntity to(PostDto dto) {
        final PostEntity entity = new PostEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setCreateDate(dto.getCreateDate());
        entity.setPublished(dto.isPublished());
        return entity;
    }

    public static PostDto from(PostEntity postEntity) {
        return new PostDto(postEntity.getId(), postEntity.getTitle(),
                postEntity.getContent(), postEntity.isPublished(), postEntity.getCreateDate());
    }
}
