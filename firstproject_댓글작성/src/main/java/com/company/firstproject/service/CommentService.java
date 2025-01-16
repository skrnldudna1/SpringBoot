package com.company.firstproject.service;

import com.company.firstproject.dto.CommentDto;
import com.company.firstproject.entity.Article;
import com.company.firstproject.entity.Comment;
import com.company.firstproject.repository.ArticleRepository;
import com.company.firstproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
       /* //1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        //2. 엔티티 -> DTO 변환
        List<CommentDto>dtos = new ArrayList<CommentDto>();
        for (int i= 0; i< comments.size(); i++) {   // 조회한 댓글 엔티티 수만큼 반복하기
            Comment c = comments.get(i);            //
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }*/

        //3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream() //댓글 엔티티 목록을 스트림으로 변환
                .map(comment -> CommentDto.createCommentDto(comment)) //엔티티를 DTO로 매핑
                .collect(Collectors.toList());  //스트림을 리스트로 변환

    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) throws IllegalAccessException {

        //1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)    //부모 게시글 가져오기
                .orElseThrow(()-> new IllegalAccessException("댓글 생성 실패! "+ "대상 게시글이 없습니다."));

        //2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        //3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);

        //4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }



@Transactional
    public CommentDto update(Long id, CommentDto dto) throws IllegalAccessException {

        //1. 댓글 조회 및 예외 발생
    Comment target = commentRepository.findById(id)    //부모 게시글 가져오기
            .orElseThrow(()-> new IllegalAccessException("댓글 수정 실패! "+ "대상 댓글이 없습니다."));

    //2. 댓글 수정
    target.patch(dto);

    //3. DB로 갱신
    Comment updated = commentRepository.save(target);

    //4. 댓글 엔티티를 DTO로 변환 및 반환
    return CommentDto.createCommentDto(updated);
    }


    public CommentDto delete(Long id) throws IllegalAccessException {

        //1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)    //부모 게시글 가져오기
                .orElseThrow(()-> new IllegalAccessException("댓글 삭제 실패! "+ "대상 댓글이 없습니다."));

        //2. 댓글 삭제
        commentRepository.delete(target);

        //3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
