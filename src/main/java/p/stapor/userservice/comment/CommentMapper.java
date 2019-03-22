package p.stapor.userservice.comment;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentMapper {

public Comment rewriteCommentDtoToEntity(CommentDto commentDto){
    Comment comment=new Comment();
    comment.setDate(new Date());
    comment.setComment(commentDto.getComment());
    return comment;
}
}
