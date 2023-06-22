package Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Airplane {
    //primary key
    private int id;
    //비행기 이름
    private String airplaneName;
    //출발시간
    private Date departureTime;
    //출발지
    private String startDestination;
    //도착지
    private String endDestination;
    //    좌석 리스트 생성
    public List<String> seatList;

    public Airplane(int id, String airplaneName, Date departureTime, String startDestination, String endDestination, int totalRows, int seatsPerRow) {
        this.id = id;
        this.airplaneName = airplaneName;
        this.departureTime = departureTime;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.seatList = generateSeatList(totalRows, seatsPerRow);
    }

    public List<String> generateSeatList(int totalRows, int seatsPerRow) {
        List<String> seatList = new ArrayList<>();

        // 좌석 리스트 생성
        char rowLabel = 'A';
        for (int row = 1; row <= totalRows; row++) {
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                String seatName = String.format("%c%02d", rowLabel, seat);
                seatList.add(seatName);
            }
            rowLabel++;
        }
        return seatList;
    }

    public int getId() {
        return id;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public List<String> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<String> seatList) {
        this.seatList = seatList;
    }


    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", airplaneName='" + airplaneName + '\'' +
                ", departureTime=" + departureTime +
                ", startDestination='" + startDestination + '\'' +
                ", endDestination='" + endDestination + '\'' +
                '}';
    }
}
