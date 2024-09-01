package com.kng.myboard.service;

import com.kng.myboard.dao.BoardDAO;
import com.kng.myboard.dto.BoardDTO;
import com.kng.myboard.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardDAO boardDAO;

    // 게시글 목록 조회
    public List<BoardDTO> getBoardList() {
        List<Board> boards = boardDAO.findAll();
        return boards.stream()
                .map(board -> new BoardDTO(board.getId(), board.getTitle(), board.getContent(), board.getAuthor()))
                .collect(Collectors.toList());
    }

    // 게시글 작성
    public void saveBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setAuthor(boardDTO.getAuthor());
        board.setCreatedDate(LocalDateTime.now());
        boardDAO.save(board);
    }

    // 게시글 상세 조회
    public BoardDTO getBoardById(Long id) {
        Board board = boardDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
        return new BoardDTO(board.getId(), board.getTitle(), board.getContent(), board.getAuthor());
    }

    // 게시글 수정
    public void updateBoard(Long id, BoardDTO boardDTO) {
        Board board = boardDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setUpdatedDate(LocalDateTime.now());
        boardDAO.save(board);
    }

    // 게시글 삭제
    public void deleteBoard(Long id) {
        boardDAO.deleteById(id);
    }
}
