package com.isoo.VO;

import java.util.Date;

public class MvcboardVO {
	

	
	// 1_1. 필드 만들기
	private int idx;			// 글번호
	private String name;		// 작성자
	private String title;		// 제목
	private String content;		// 내용
	private Date writeDate;		// 작성일
	private int readCount;		// 조회수
	private int ref;			// 글 그룹
	private int step;			// 글 그룹 내 글 표시하는 순서
	private int indent;			// 답글의 레벨(들여쓰기 뎁스)
	

	public MvcboardVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MvcboardVO(int idx, String name, String title, String content, Date writeDate, int readCount, int ref,
			int step, int indent) {
		super();
		this.idx = idx;
		this.name = name;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.readCount = readCount;
		this.ref = ref;
		this.step = step;
		this.indent = indent;
	}
	
	
	//1_2. 게터세터
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}


	@Override
	public String toString() {
		return "MvcboardVO [idx=" + idx + ", name=" + name + ", title=" + title + ", content=" + content
				+ ", writeDate=" + writeDate + ", readCount=" + readCount + ", ref=" + ref + ", step=" + step
				+ ", indent=" + indent + "]";
	}
	


}
