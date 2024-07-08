package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;  // 생성자를 통해 의존성 주입
    }


    public PostResponseDto createPost(PostRequestDto requestDto) {

        //RequestDto -> Entity
        Post post = new Post(requestDto);

        //DB 저장
        Post savePost = postRepository.save(post);

        //Entity -> ResponseDto
        return new PostResponseDto(savePost);


    }

    public List<PostResponseDto> getPost() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않습니다."));

        post.setUsername(requestDto.getUsername());
        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());
        post.setPrice(requestDto.getPrice());

        Post updatedPost = postRepository.save(post);
        return new PostResponseDto(updatedPost);
    }


    public long deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않습니다."));

        postRepository.delete(post);
        return id;


    }
}
