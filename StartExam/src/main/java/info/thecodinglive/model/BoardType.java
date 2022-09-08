package info.thecodinglive.model;

public enum BoardType {
	notice("공지사항"),
	free("자유게시판");
	
	private String value;
	private BoardType(String value) {
		this.value=value;
	}
	private String getValue() {
		return value;
	}
}
