package Module;

import Config.JdbcConnection;
import Controller.ReservationController;
import Data.Airplane;
import Data.DataManager;
import Data.Seat;
import Data.Ticket;
import Enums.ModuleType;
import View.ReservationView;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ReservationModule extends ModuleBase {

    private ReservationView view = new ReservationView();

    public ReservationModule(Scanner sc) {
        super(sc);
        controller = new ReservationController(sc);
    }

    public List<Airplane> airplaneList = new ArrayList<>();
    public List<Ticket> ticketList = new ArrayList<>();
    public List<Seat> seatList = new ArrayList<>();

    @Override
    protected void init() {
        super.moduleType = ModuleType.RESERVATION;
    }

    @Override
    public void start() {
        view.printReservationMenu();
        switch (controller.SelectMenu()) {
            case 1:
                //todo 조회
                //select 로 비행기 테이블 전체 조회
                getAirPlaneList_by_Database();
                showAirplaneList();
                break;
            case 2:
                //todo 예약
                //티켓 만들어서 db에 insert
                reservationAirPlane_Step1();
                break;
            case 3:
                //todo 티켓 확인
//                getMyTickets_by_Database();
                break;
            case 0:
                ModuleManager.getInstance().changeModule(ModuleType.MAIN);
                break;
        }
    }

    public List<Airplane> getAirPlaneList_by_Database() {
        //비행기 목록 clear
        airplaneList.clear();

        //db에서 읽어온다
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from airplane";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                //컬럼 명으로 읽어와서 생성
                int id = rst.getInt("id");
                String name = rst.getString("airplane_name");
                Date departure_time = rst.getDate("departure_time");
                String start_destination = rst.getString("start_destination");
                String end_destination = rst.getString("end_destination");
                //Date date = new Date(2023, 6, 10);
                Date date = new Date(Calendar.getInstance().getTimeInMillis());
                //리스트 추가
                Airplane p = new Airplane(id, name, departure_time, start_destination, end_destination, 5, 4);
                airplaneList.add(p);
            }
        } catch (SQLException e) {
            System.out.println("show air plane error");
        }

        //db connection 닫기
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql close fail");
        }

        return airplaneList;
    }

    private void reservationAirPlane_Step1() {
        view.printReservationAirPlan_askShowList();
        switch (controller.SelectMenu()) {
            case 1:
                getAirPlaneList_by_Database();
                showAirplaneList();
//                reservationAirPlane_Step2();
                break;
            case 2:
//                reservationAirPlane_Step2();
                break;
            default:
                break;
        }
    }

    public void reservationAirPlane_Step2(String airplaneName, String seatName, String userID) {
        //비행기 이름 입력
//        view.printInputAirPlanName();
//        String airplaneName = controller.inputString();


        //티켓 생성 전에 비행기 리스트 다시 갱신 (db 로부터 불러오기)
        getAirPlaneList_by_Database();

        //입력 받은 비행기 이름 기준으로 찾는다
        Airplane air = findAirPlane(airplaneName);

        //air 가 있으면 티켓 생성 없으면 애러 메시지
//        if (air == null) {
//            view.printFindAirplaneError();
//        } else {
        // 전체 좌석 리스트 출력
//            view.printSeatList();
        assert air != null;

        System.out.println(air.getSeatList());

        // 예매 불가 좌석 리스트 출력
//            view.printReservationSeatList();
//            getSeats_by_Database(air.getAirplaneName());

        //좌석 이름 입력
//            view.printInputSeatName();
//            String seatName = controller.inputString();

        if (air.getSeatList().contains(seatName)) {
            Connection conn = new JdbcConnection().getJdbc();

            // 티켓 관련 sql
            String ticketSql = "insert into ticket (userid, airplane_name, departure_time, start_destination, end_destination, seat_name)\n"
                    + "values (?, ?, ?, ?, ?, ?)";

            // 좌석 관련 sql
            String seatSql = "insert into airplane_seat (userid, airplane_name, seat_name)\n"
                    + "values (?, ?, ?)";

            try {
                //db에 티켓 insert
                PreparedStatement ticketPst = conn.prepareStatement(ticketSql);
                ticketPst.setString(1, userID);
                ticketPst.setString(2, air.getAirplaneName());
                ticketPst.setDate(3, air.getDepartureTime());
                ticketPst.setString(4, air.getStartDestination());
                ticketPst.setString(5, air.getEndDestination());
                ticketPst.setString(6, seatName);

                //db에 좌석 insert
                PreparedStatement seatPst = conn.prepareStatement(seatSql);
                seatPst.setString(1, userID);
                seatPst.setString(2, air.getAirplaneName());
                seatPst.setString(3, seatName);

                if (ticketPst.executeUpdate() == 0 || seatPst.executeUpdate() == 0) {
                    System.out.println("insert error");
                } else {
                    view.printSuccessAirPlane();
                }

            } catch (SQLIntegrityConstraintViolationException e) {
                // 티켓 테이블, 좌석 테이블에서 비행기와 좌석 데이터가 동일한 데이터가 들어있으면 예매 불가
                System.out.println("해당 좌석을 예매할 수 없습니다.");
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("sql close fail");
            }
        } else {
            System.out.println("좌석이 존재하지 않습니다. 다시 입력해주세요");
        }

//        }
    }

    public List<Ticket> getMyTickets_by_Database(String userID) {
        //비행기 목록 clear
        ticketList.clear();

        //db에서 읽어온다
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from ticket where userid = ?";
        try {

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userID);

            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                //컬럼 명으로 읽어와서 생성
                int ticketNumber = rst.getInt("ticket_number");
                String userid = rst.getString("userid");
                String airplaneName = rst.getString("airplane_name");
                Date departure_time = rst.getDate("departure_time");
                String start_destination = rst.getString("start_destination");
                String end_destination = rst.getString("end_destination");
                String seat = rst.getString("seat_name");

                //리스트 추가
                Ticket t = new Ticket(ticketNumber, userid, airplaneName, departure_time,
                        start_destination, end_destination, seat);
                ticketList.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("select ticket error");
        }

        //db connection 닫기
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql close fail");
        }

        if (ticketList.size() == 0) {
            //티켓 없음
            view.printNoTicket();
        } else {
            showTicketList();
        }
        return ticketList;
    }

    public List<Seat> getSeats_by_Database(String airplaneName) {
        // 좌석 목록 clear
        seatList.clear();

        //입력 받은 비행기 이름 기준으로 찾는다
        Airplane air = findAirPlane(airplaneName);


        // db에서 읽어오기
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from airplane_seat where airplane_name = ?";
        try {

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, air.getAirplaneName());

            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                //컬럼 명으로 읽어와서 생성
                String seat = rst.getString("seat_name");

                //리스트 추가
                Seat s = new Seat(seat);
                seatList.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("select seat error");
        }

        //db connection 닫기
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql close fail");
        }

        if (seatList.size() == 0) {
            //좌석 없음
            view.printNoTicket();
        } else {
            System.out.println(seatList);
        }
        return seatList;
    }

    //비행기 리스트 출력
    private void showAirplaneList() {
        for (int i = 0; i < airplaneList.size(); i++) {
            System.out.println(airplaneList.get(i).toString());
        }
    }

    //비행기 리스트에서 비행기 이름으로 찾기
    public Airplane findAirPlane(String name) {
        for (int i = 0; i < airplaneList.size(); i++) {
            if (airplaneList.get(i).getAirplaneName().equals(name))
                return airplaneList.get(i);
        }

        return null;
    }

    //티켓 리스트 출력
    private void showTicketList() {
        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println(ticketList.get(i).toString());
        }
    }


    @Override
    public void outModule() {
        airplaneList.clear();
        ticketList.clear();
    }
}
