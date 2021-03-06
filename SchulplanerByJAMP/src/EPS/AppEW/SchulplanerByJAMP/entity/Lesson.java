package EPS.AppEW.SchulplanerByJAMP.entity;


public class Lesson {

	private long id;

	private String name;
	
	private String teacher;
	
	private String room;
	
	private String note;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", name=" + name + ", teacher=" + teacher
				+ ", room=" + room + ", note=" + note + "]";
	}
}
