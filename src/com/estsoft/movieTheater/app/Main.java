package com.estsoft.movieTheater.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.estsoft.movieTheater.dao.MovieDao;
import com.estsoft.movieTheater.dao.PersonDao;
import com.estsoft.movieTheater.dao.ReservationDao;
import com.estsoft.movieTheater.vo.MovieVO;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print(
                        "\n== Ticketing System ==\n" +
                                "1. 영화목록 확인\n" +
                                "2. 예매 \n" +
                                "3. 예매 확인\n" +
                                "4. 예매 취소\n" +
                                "5. 종료\n" +
                                "> ");
                String kind = br.readLine();
                switch (Integer.parseInt(kind)) {
                    case 1:
                        doCount();
                        break;
                    case 2:
                        doReserve();
                        break;
                    case 3:
                        doLogin();
                        break;
                    case 4:
                        //doCancel();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("\n Wrong.\n\n");
                }
            } catch (IOException ie) {
                System.out.println("Incorrect input.");
            }
        }
    }


    static void printMovieListMenu(String title) {
        System.out.println("========" + title + "=========");
        MovieDao movieDao = new MovieDao();
        List<MovieVO> list = movieDao.getList();

        for (MovieVO vo : list) {
            System.out.println(vo);
        }
    }

    static void doReserve() throws IOException {
        printMovieListMenu("RESERVATION");
        System.out.print("몇번 영화 볼래?");
        String no = br.readLine();
        List<MovieVO> movieDaoList = new MovieDao().getList();
        int num_movies = movieDaoList.size();
        if (Integer.parseInt(no) <= num_movies) {
            System.out.print("Phone: ");
            String phone_num = br.readLine();
            System.out.print("몇장 예약할래?");
            String seats = br.readLine();
            PersonDao personDao = new PersonDao();
            personDao.insert(phone_num);
            int id_person = personDao.getIdByPhone_num(phone_num);
            new ReservationDao().insert(id_person, Integer.parseInt(no), Integer.parseInt(seats));
            System.out.print("예약 완료. 계속 진행하려면 엔터를 누르세요");
            br.readLine();
        }
    }

    // TODO
    static void doLogin() throws IOException {
        System.out.print("Your name: ");
        PersonDao personDao = new PersonDao();
        ReservationDao reservationDao = new ReservationDao();
        String name = br.readLine();
        int person_id = personDao.getIdByPhone_num(name);
        if ( person_id == 0 ){
            return;
        }
        System.out.println("======" + name + " 님의 예약 목록========");
        reservationDao.printReservationListByPerson_id(person_id);
        System.out.println("계속 진행하려면 엔터를 누르세요");
        br.readLine();
    }

    static void doCount() throws IOException {
        printMovieListMenu("Count");
        System.out.println("계속 진행하려면 엔터를 누르세요");
        String input = br.readLine();
        if (input != null) {
            return;
        }
    }
}
