package EPS.AppEW.SchulplanerByJAMP.dao;

import java.util.ArrayList;
import java.util.List;

import EPS.AppEW.SchulplanerByJAMP.entity.DatabaseHelper;
import EPS.AppEW.SchulplanerByJAMP.entity.Lesson;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class privides services to handle database transacgtions.
 * @author pascal.perau
 */
public class LessonDao {

	private SQLiteDatabase db;

	private DatabaseHelper dbHelper;

	private String[] columns = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_TEACHER,
			DatabaseHelper.COLUMN_ROOM, DatabaseHelper.COLUMN_NOTE };

	public LessonDao(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
	    dbHelper.close();
	}
	
	public Lesson createLesson(Lesson persistableLesson){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_NAME, persistableLesson.getName());
		values.put(DatabaseHelper.COLUMN_TEACHER, persistableLesson.getTeacher());
		values.put(DatabaseHelper.COLUMN_ROOM, persistableLesson.getRoom());
		values.put(DatabaseHelper.COLUMN_NOTE, persistableLesson.getNote());
		long lessonId = db.insert(DatabaseHelper.TABLE_LESSON, null, values);
		Cursor cursor = db.query(DatabaseHelper.TABLE_LESSON, columns, DatabaseHelper.COLUMN_ID + " = " + lessonId, null, null, null, null);
		cursor.moveToFirst();
		Lesson newLesson = cursorToLesson(cursor);
		cursor.close();
		return newLesson;
	}
	
	public void deleteLesson(Lesson lesson) {
	    long id = lesson.getId();
	    System.out.println("Lesson deleted with id: " + id);
	    db.delete(DatabaseHelper.TABLE_LESSON, DatabaseHelper.COLUMN_ID + " = " + id, null);
	  }

	  public List<Lesson> getAllLessons() {
	    List<Lesson> lessons = new ArrayList<Lesson>();

	    Cursor cursor = db.query(DatabaseHelper.TABLE_LESSON, columns, null, null, null, null, null);
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Lesson l = cursorToLesson(cursor);
	      lessons.add(l);
	      cursor.moveToNext();
	    }
	    cursor.close();
	    return lessons;
	  }
	
	private Lesson cursorToLesson(Cursor cursor) {
	    Lesson lesson = new Lesson();
	    lesson.setId(cursor.getLong(0));
	    lesson.setName(cursor.getString(1));
	    lesson.setTeacher(cursor.getString(2));
	    lesson.setRoom(cursor.getString(3));
	    lesson.setNote(cursor.getString(4));
	    return lesson;
	  }
	
}
