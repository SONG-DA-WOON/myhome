package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardApiController {
    @Autowired
    private BoardRepository repository;


    // 검색할때 사용 하면 좋을거같다.
    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
                    @RequestParam(required = false, defaultValue = "") String content) {

        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) { // title, content 값이 없을때는 다 나올수있게 만들기
            return repository.findAll();
        } else {
            return repository.findByTitleOrContent(title, content); // title, content 값이 있으면 title, content 값만 나오게
        }

    }

    @PostMapping("/boards")
    Board newEmployee(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {

        return repository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/boards/${id}")
    void deleteBoard(@PathVariable Long id) {

        repository.deleteById(id);
    }
}


