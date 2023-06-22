package Module;

import Config.JdbcConnection;
import Controller.EditController;
import Data.Airplane;
import Data.User;
import Enums.ModuleType;
import View.EditView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class EditModule extends ModuleBase {

    private EditView view = new EditView();

    public EditModule(Scanner sc) {
        super(sc);
        controller = new EditController(sc);
    }

    private List<Airplane> airplaneList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);


    @Override
    protected void init() {
        super.moduleType = ModuleType.EDIT;
    }

    @Override
    public void start() {
        view.printDevelopMenu();
        switch (controller.SelectMenu()) {
            case 1:
//                TODO: 비행기 데이터 조회
                getAirPlaneList_by_Database();
                showAirplaneList();

                break;
            case 2:
//                TODO: 비행기 데이터 추가
                //insertAirplane();
                break;
            case 3:
//                TODO: 비행기 데이터 삭제
                showAirplaneList();
                deleteAirplane();
                break;
            case 4:
//                TODO: 유저 정보 조회
                getUserList_by_Database();
                showUserList();

                break;
            case 5:
//                TODO: 유저 정보 추가
                insertUser();
                break;
            case 6:
//                TODO: 유저 정보 삭제
                showUserList();
                deleteUser();
                break;
            case 0:
                ModuleManager.getInstance().changeModule(ModuleType.MAIN);
                break;
        }
    }

    public List<Airplane> getAirPlaneList_by_Database() {
        // 비행기 리스트 요소 clear
        List<Airplane> result = new ArrayList<>();

        //db에서 airplane 테이블 정보 가져오기
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from airplane";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery(sql);
            while (rst.next()) {
                // 컬럼명으로 읽어와서 생성
                int id = rst.getInt("id");
                String airplane_name = rst.getString("airplane_name");
                Date departure_time = rst.getDate("departure_time");
                String start_destination = rst.getString("start_destination");
                String end_destination = rst.getString("end_destination");

                // 리스트에 비행기 정보 추가
                Airplane p = new Airplane(id, airplane_name, departure_time, start_destination, end_destination, 5, 4);
                result.add(p);
            }
        } catch (SQLException e) {
            System.out.println("show air plane error");
        }

        // db connection 닫기
        try {
            conn.close();
            ;
        } catch (SQLException e) {
            System.out.println("sql close fail");
        }

        return result;
    }

    public boolean insertAirplane(String airplaneName, String date, String startDestination, String endDestination) {
        // 비행기 테이블에 비행기 정보 임의로 insert
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "insert into airplane(airplane_name, departure_time, start_destination, end_destination)\n"
                + "values (?, ?, ?, ?)";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, airplaneName);
            pst.setDate(2, Date.valueOf(date));
            pst.setString(3, startDestination);
            pst.setString(4, endDestination);

            if (pst.executeUpdate() == 0) {
                System.out.println("insert airplane error");
            } else {
                System.out.println("insert airplane 성공");
            }
        } catch (SQLException e) {
            System.out.println("쿼리 확인");
            return false;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql close fail");
            return false;
        }

        return true;
    }

    private void deleteAirplane() {
        // 비행기 이름으로 delete
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "delete from airplane where airplane_name = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            view.printAirplaneInfo(1);
            String airplaneName = sc.nextLine();
            pst.setString(1, airplaneName);

            if (pst.executeUpdate() == 0) {
                System.out.println("delete airplane error");
            } else {
                view.printSuccess();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql close fail");
        }
    }

    private void showAirplaneList() {
        // 비행기 리스트 요소 출력
        for (int i = 0; i < airplaneList.size(); i++) {
            System.out.println(airplaneList.get(i).toString());
        }
    }

    private void getUserList_by_Database() {
        // 유저 리스트 요소 clear
        userList.clear();

        // db에서 user 테이블 가져오기
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from user";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery(sql);

            while (rst.next()) {
                int id = rst.getInt("id");
                String username = rst.getString("username");
                String password = rst.getString("password");
                String name = rst.getString("name");

                // 리스트에 유저 정보 추가
                User u = new User(id, username, password, name);
                userList.add(u);
            }
        } catch (SQLException e) {
            System.out.println("show user error");
        }

        // db connection 닫기
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql close fail");
        }

    }

    private void insertUser() {
        // db에 유저 정보 임의로 insert
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "insert into user(username, password, name)\n"
                + "values(?, ?, ?)";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            view.printUserInfo(1);
            String username = sc.nextLine();
            view.printUserInfo(2);
            String password = sc.nextLine();
            view.printUserInfo(3);
            String name = sc.nextLine();

            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, name);

            if (pst.executeUpdate() == 0) {
                System.out.println("insert user error");
            } else {
                view.printSuccess();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql close fail");
        }
    }

    private void deleteUser() {
        // 유저 아이디로 delete
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "delete from user where username = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            view.printUserInfo(1);
            String username = sc.nextLine();
            pst.setString(1, username);

            if (pst.executeUpdate() == 0) {
                System.out.println("delete user error");
            } else {
                view.printSuccess();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql close fail");
        }
    }

    private void showUserList() {
        // 유저 리스트 요소 출력
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).toString());
        }
    }

    @Override
    public void outModule() {

    }
}
