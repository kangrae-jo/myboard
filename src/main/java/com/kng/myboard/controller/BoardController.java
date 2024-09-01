package com.kng.myboard.controller;

import com.kng.myboard.dto.BoardDTO;
import com.kng.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 목록 조회
    @GetMapping
    public List<BoardDTO> getBoards() {
        return boardService.getBoardList();
    }

    // 게시글 작성
    @PostMapping
    public ResponseEntity<String> createBoard(@RequestBody BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO);
        return ResponseEntity.ok("Board created successfully");
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        boardService.updateBoard(id, boardDTO);
        return ResponseEntity.ok("Board updated successfully");
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok("Board deleted successfully");
    }
}
