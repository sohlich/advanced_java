package cz.edhouse.jpa.dto;

import cz.edhouse.jpa.domain.PostEntity;

/**
 *
 * @author Frantisek Spacek
 */
public class PostDto extends BaseDto<Integer> {

    private String title;
    private String content;
    private boolean published;

    public PostDto(Integer id, String title, String content, boolean published) {
        super(id);
        this.title = title;
        this.content = content;
        this.published = published;
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

    public static PostEntity to(PostDto dto) {
        final PostEntity entity = new PostEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return entity;
    }

    public static PostDto from(PostEntity postEntity) {
        return new PostDto(postEntity.getId(), postEntity.getTitle(), postEntity.getContent(), postEntity.isPublished());
    }
}
