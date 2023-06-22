package Module;

import Config.JdbcConnection;
import Enums.ModuleType;

import java.util.Scanner;

public class ModuleManager {
    private static Scanner sc = null;

    private static class LazyHolder {
        public static final ModuleManager INSTANCE = new ModuleManager();
    }

    public static ModuleManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private ModuleBase nowModule = null;

    public void initModuleManager() {
        //모듈 초기화
        //scanner 생성하고 첫 모듈 mainmodule 로 설정
        sc = new Scanner(System.in);
        nowModule = new MainModule(sc);
    }

    public void start() {
//        while (true){
//
//            //현제 모듈 start 실행
//            nowModule.start();
//        }
    }

    public void changeModule(ModuleType type) {
        //모듈 교체를 위해 outmodule 실행 후 모듈을 null로 초기화
        nowModule.outModule();
        nowModule = null;

        //입력받은 type에 따라 모듈 세팅
        switch (type) {
            case MAIN:
                nowModule = new MainModule(sc);
                break;
            case LOGIN:
                nowModule = new LoginModule(sc);
                break;
            case RESERVATION:
                nowModule = new ReservationModule(sc);
                break;
            case EDIT:
                nowModule = new EditModule(sc);
                break;
            default:
                break;
        }


        if (nowModule != null)
            nowModule.init();
    }

    public ModuleType getNowModuleType() {
        return nowModule.getModuleType();
    }

    public MainModule getMainModuleByNowMobule() {
        if (getNowModuleType() == ModuleType.MAIN)
            return (MainModule) nowModule;
        else
            return null;
    }

    public LoginModule getLoginModuleByNowMobule() {
        if (getNowModuleType() == ModuleType.LOGIN)
            return (LoginModule) nowModule;
        else
            return null;
    }

    public ReservationModule getReservationModuleByNowMobule() {
        if (getNowModuleType() == ModuleType.RESERVATION)
            return (ReservationModule) nowModule;
        else
            return null;
    }

    public EditModule getEditModuleByNowMobule() {
        if (getNowModuleType() == ModuleType.EDIT)
            return (EditModule) nowModule;
        else
            return null;
    }
}
