package Module;

import Config.JdbcConnection;
import Controller.LoginController;
import Data.DataManager;
import Data.User;
import Enums.ModuleType;
import View.LoginView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginModule extends ModuleBase{
    private final Scanner sc;
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String newPassword;
    private Integer id0;
    private String name0;
    private String username0;
    private String password0;

    private String sql = "SELECT * FROM user WHERE username = ? AND password = ?"; //id와 password를 user로부터 받아와서 sql로 선언함
    private String selectSql = "SELECT username FROM user WHERE username = ?"; //username으로 부터 username을 selectsql로 선언함
    private String insertSql = "INSERT INTO user(username, password, name) VALUES (?, ?, ?)"; //user의 다음 항목들을 각각 값으로 insertsql로 선언함

    public LoginModule(Scanner sc) {  //입력받은 sc를 초기화 한후, LoginController의 새 인스턴스로 생성함
        super(sc);
        super.controller = new LoginController(sc);
        this.sc = sc;
    }


    public boolean login(String username, String password) { //login 메소드로 id pw를 입력받음
        LoginView loginView = new LoginView();
        LoginView.printenterView1();
        LoginView.printenterView2();
        this.username = username;
        LoginView.printenterView3();
        this.password = password;

        if (this.username.trim().isEmpty() ) { // 공백을 입력받을시 error를 출력함
            System.out.println("로그인 실패, 잘못된 입력입니다.");
            return false;
        }

        Connection conn = new JdbcConnection().getJdbc(); //sql의 데이터를 받아와서 conn 인스턴스를 생성

        try {   //RreparedStatement클래스인 psmt 인스턴스를 생성하고 천번째, 두번째매개변수에 username과 password를 셋해줌
            PreparedStatement psmt = conn.prepareStatement(sql);    //그리고, ResultSet 클래스인 resultSet변수에 psmt의 쿼리문을 실행하여 결과를 저장함
            psmt.setString(1, this.username);
            psmt.setString(2, this.password);
            ResultSet resultSet = psmt.executeQuery();

            if (resultSet.next()) { //resultSet의 다음으로 오는 값 즉, 초기값이 쿼리에 있는 username, password과 같은지를 확인해서 만약 다 일치한다면, 환영문구를 띄움.
                id0 = resultSet.getInt("id");   //같지 않다면, 로그인 실패
                username0 = resultSet.getString("username");
                password0 = resultSet.getString("password");
                name0 = resultSet.getString("name");

                User user = new User(id0, username0, password0, name0);

                DataManager.getInstance().setUser(user);
                System.out.println(this.username + " " + name0 + " 환영해요");

                System.out.println(this.username + " " + name0+"님 환영합니다.");

                return true;
            } else {
                System.out.println("로그인 실패, 유효하지 않은 ID 입니다.");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally { // finally 구문을 통해 conn 즉, database 연결을 닫음
            try {
                conn.close();
                //로그인 했으니 메인모듈로
                ModuleManager.getInstance().changeModule(ModuleType.MAIN);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public boolean signup(String username, String password, String name) { //signup 메소드로 id pw 이름를 입력받음
        Connection conn = new JdbcConnection().getJdbc();

        LoginView loginView = new LoginView();

        LoginView.printsignView1();
        LoginView.printsignView2();
        this.username = username;
        LoginView.printsignView3();
        this.password = password;
        LoginView.printsignView4();
        this.name = name;

        if (!isPasswordValid(password)) { // 패스워드가 유효를 메소드에서 검사함 (참인지것지인지)
            System.out.println("ERROR: 비밀번호가 적어도 한개 이상의 특수문자와 대문자를 포함해야 합니다.");
            return false;
        }else{System.out.println("사용가능한 비밀번호입니다!");
        }

        try {   //RreparedStatement클래스인 selectpsmt 인스턴스를 생성하고 천번째 매개변수에 username을 셋해줌
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);    //그리고, ResultSet 클래스인 resultSet변수에 psmt의 쿼리문을 실행하여 결과를 저장함
            selectStmt.setString(1, username);
            ResultSet resultSet = selectStmt.executeQuery();

            if (resultSet.next()) { ////resultSet의 다음으로 오는 값 즉, 초기값이 쿼리에 있는 username이 db에 있다면 if문 실행하고 if문 종료
                System.out.println("ERROR: 유저가 이미 존재합니다!");
                return false;
            }

            // 유저 생성: sql에서 받아온 (username, password, name)을 insertStmt로 선언
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, username); // 여기서 username == id라고 보면됨
            insertStmt.setString(2, password);
            insertStmt.setString(3, name);
            int rowsAffected = insertStmt.executeUpdate(); // 몇개의 행이 업데이트 되었는지 rowsAffected에 저장

            if (rowsAffected > 0) { // 0보다 크다면 기존 db에 없는것이므로 등록 성공, 0보다 작다면 변경사항이 없으므로 등록 실패
                System.out.println("유저 등록 성공!");

            } else {
                System.out.println("ERROR: 유저 등록 실패!");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {   //DB 내용이 null이 아니라면 DB연결을 종료하겠음
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    private boolean isPasswordValid(String password) { //비밀번호가 반드시 한개의 특수문자나 대문자를 포함하는지 불린 메소드로 생성
        // Password must contain at least one special character and one uppercase letter
        String specialCharacters = "!@#$%^&*()-_=+[{]};:'\",<.>/?"; // 각각 String 변수로 지정
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        boolean hasSpecialCharacter = false; // 각각 초기값은 false
        boolean hasUppercaseLetter = false;

        for (char c : password.toCharArray()) { //password에 요소를 전부 탐색해서, 특수문자를 포함하고있다면 true로 똑같이 대문자를 포함하고있다면 true로 지정.

            if (specialCharacters.contains(String.valueOf(c))) {
                hasSpecialCharacter = true;
            } else if (uppercaseLetters.contains(String.valueOf(c))) {
                hasUppercaseLetter = true;
            }

            // 둘다 포함하고 있다면 반복문 탈출
            if (hasSpecialCharacter && hasUppercaseLetter) {
                break;
            }
        }

        return hasSpecialCharacter && hasUppercaseLetter; // 최종적으로  hasSpecialCharacter && hasUppercaseLetter가 참인지 거짓인지를 반환함
    }

    public void findPW(String username, String name) { //signup 메소드로 id와 이름을 입력받음
        Connection conn = new JdbcConnection().getJdbc();

        LoginView loginView = new LoginView();

        LoginView.printpwView1();
        this.username = username;
        LoginView.printpwView2();
        this.name = name;


        try {
            // Sqlx에 db에서 입력 받아온 password를 저장
            String Sqlx = "SELECT password FROM user WHERE username = ? and name=?";
            PreparedStatement beforePsmt = conn.prepareStatement(Sqlx);
            beforePsmt.setString(1, username);
            beforePsmt.setString(2, name);
            ResultSet resultSet = beforePsmt.executeQuery();
            String password; // password 초기값 지정

            if (!resultSet.next()) { ////resultSet의 다음으로 오는 값 즉, 초기값이 쿼리에 있는 username이 db에 있다면 if문 실행하고 if문 종료
                System.out.println("ERROR: 회원 정보가 없습니다.");
                return;
            } else {
                password = resultSet.getString("password");
            }

            // 새로운 비밀번호를 입력함
            System.out.print("새로운 비밀번호를 입력해주세요: ");
            newPassword = sc.nextLine();


            if (newPassword.equals(password)){ // 비밀번호가 중복될시 메소드 종료
                System.out.println("비밀번호가 중복됩니다.");
                return;
            }

            if (!isPasswordValid(newPassword)) { // 패스워드가 유효를 메소드에서 검사함 (참인지것지인지)
                System.out.println("ERROR: 비밀번호가 적어도 한개 이상의 특수문자와 대문자를 포함해야 합니다.");
                return;
            } else {
                System.out.println("변경가능한 비밀번호입니다!");
            }



            String updateSql = "UPDATE user SET password = ? WHERE username = ?"; //user의 다음 항목들을 각각 값으로 insertsql로 선언함
            PreparedStatement updatePsmt = conn.prepareStatement(updateSql);
            updatePsmt.setString(1, newPassword);
            updatePsmt.setString(2, username);
            Integer resultUpdate = updatePsmt.executeUpdate();


            if (resultUpdate == 1) {
                System.out.println("비밀번호가 변경되었습니다");
                return;
            } else {
                System.out.println("회원정보 변경 실패, 사용자 이름이나 비밀번호가 잘못되었습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                // If the DB connection is not null, close it
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void init(){
        super.moduleType = ModuleType.LOGIN;
    }

    @Override
    public void start(){
        LoginView loginView = new LoginView();
        LoginView.printloginView();
        switch (controller.SelectMenu()){
            case 1:
                //todo login
                login(username, password);
                break;
            case 2:
                //todo signup
                signup(username, password, name);
                break;
            case 3:
                //todo find pw
                findPW(username,name);
                break;

            case 0:
                ModuleManager.getInstance().changeModule(ModuleType.MAIN);
                break;
        }
    }

    @Override
    public void outModule(){

    }
}
