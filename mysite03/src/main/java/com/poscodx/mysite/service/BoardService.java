package com.poscodx.mysite.service;

import com.poscodx.mysite.repository.BoardRepository;
import com.poscodx.mysite.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> getContentsList() {
		return boardRepository.findAll();
	}
	
	public Boolean boarddeletes(Long no, String password) {
		return boardRepository.deleteByNoAndPassword(no, password);
	}
	
	public Boolean boardWrite(BoardVo vo) {
		return boardRepository.insert(vo);
	}
}
