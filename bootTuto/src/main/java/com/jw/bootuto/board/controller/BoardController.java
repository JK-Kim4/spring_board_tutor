package com.jw.bootuto.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jw.bootuto.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

}
