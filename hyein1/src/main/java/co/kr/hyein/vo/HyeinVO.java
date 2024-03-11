package co.kr.hyein.vo;

public class HyeinVO {
	private int boardNo;
	private int hitnum;
	private String regdate;
	private String content;
	private String writer;
	private String title;
	private int limit;
	private int offset;
	private int cnt;
	private int page;
	private int startBlock;
	private int nowPage;
	private int total;
	private String searchOption;
	private String keyword;
	private String fileName;
	private String fileRegdate;
	private String filePath;
	private String fileId;
	

	@Override
	public String toString() {
		return "HyeinVO [boardNo=" + boardNo + ", hitnum=" + hitnum + ", regdate=" + regdate + ", content=" + content
				+ ", writer=" + writer + ", title=" + title + ", limit=" + limit + ", offset=" + offset + ", cnt=" + cnt
				+ ", page=" + page + ", startBlock=" + startBlock + ", nowPage=" + nowPage + ", total=" + total
				+ ", searchOption=" + searchOption + ", keyword=" + keyword + ", fileName=" + fileName
				+ ", fileRegdate=" + fileRegdate + ", filePath=" + filePath + ", fileId=" + fileId + "]";
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRegdate() {
		return fileRegdate;
	}

	public void setFileRegdate(String fileRegdate) {
		this.fileRegdate = fileRegdate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getBoard_no() {
		return boardNo;
	}

	public void setBoard_no(int board_no) {
		this.boardNo = board_no;
	}

	public int getHitnum() {
		return hitnum;
	}

	public void setHitnum(int hitnum) {
		this.hitnum = hitnum;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public void Calculation(HyeinVO vo){
		this.cnt = (int)Math.ceil((double)this.total / (double)this.limit); //Math.ceil : 소수점 이하 반올림
		this.nowPage = (int)Math.ceil(this.page / 5.0);
		this.startBlock = (nowPage * 5) -4;
		this.offset = ( this.page -1 ) * this.limit;
	}
	
}
