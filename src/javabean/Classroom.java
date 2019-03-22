package javabean;

/**
 * Created by ray on 2017/5/19.
 */
public class Classroom {
    public static String[] buildings = {"第1教学大楼", "第2教学大楼", "第3教学大楼"};

    private long id;
    private String building;
    private String room_number;

    public Classroom(String building, String room_number) {
        this.building = building;
        this.room_number = room_number;
    }

    public Classroom(long id, String building, String room_number) {
        this.id = id;
        this.building = building;
        this.room_number = room_number;
    }

    public Classroom() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

}