package com.jw.bootuto.board.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	
	private int boardNo;
	private String boardWriter;
	private String boardTitle;
	private String boardContent;
	private Date regDate;

}
