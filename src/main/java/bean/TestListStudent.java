package bean;

public class TestListStudent implements java.io.Serializable {
	private String subjectName;
	private String subjectCd;
	private int num;
	private int point;
	
	public String getSubjctName() {
		return subjectName;
	}
	
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getSubjectCd() {
		return subjectCd;
	}
	
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int Num) {
		this.num=Num;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int Point) {
		this.point=Point;
	}
}
	
	
	