package baekjoon.Quiz1379;

import java.util.*;
import java.io.*;

/*
1. 강의번호, 시간, 인아웃, 강의실번호를 가지는 클래스 생성
2. 시작시간 오름차순, 타입은 아웃이 우선으로 정렬
3. 순차적으로 처리하며 인일경우 다음 작업들을 수행
3.1. 현재 강의실 순서 1증가
3.2. 최소 강의실 개수와 현재 강의실 개수를 비교해 갱신
3.3. 빈 강의실이 있다면 해당 강의실을 사용하고, 없다면 현재 강의실 개수가 강의실 번호가 된다.
4. 아웃일 경우 현재 강의실 번호 1감소, 현재 강의실 반납
5. 모든 원소 처리했다면 인인 객체들만 추출해서 강의번호 순으로 정렬
6. 정렬한 리스트의 강의실 번호를 출력
*/

public class Quiz1379 {

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[N * 2];
        for(int i=0; i<N*2; i+=2) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int lectureNo = Integer.parseInt(st.nextToken());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            LectureRoom lectureRoom = LectureRoom.create();
            lectures[i] = Lecture.create(lectureNo, startTime, TimeType.START, lectureRoom);
            lectures[i+1] = Lecture.create(lectureNo, endTime, TimeType.END, lectureRoom);
        }

        sortTimeSequenceOf(lectures);

        int minimumCount = calculateMinimumLectureRoomCount(lectures);
        printAnswer(minimumCount, lectures);
    }

    private static void sortTimeSequenceOf(Lecture[] lectures) {
        Arrays.sort(lectures, (o1, o2) -> {
            if(o1.getTime() == o2.getTime()) {
                if(o1.getTimeType() != o2.getTimeType()) {
                    return o1.getTimeType().compareTo(o2.getTimeType());
                }
            }

            return o1.getTime() - o2.getTime();
        });
    }

    private static int calculateMinimumLectureRoomCount(Lecture[] lectures) {
        int minimumCount = 0;
        int currentCount = 0;
        ArrayDeque<Integer> availableRooms = new ArrayDeque<>();

        for (Lecture lecture : lectures) {
            if (lecture.isStartType()) {
                currentCount++;
                minimumCount = Math.max(minimumCount, currentCount);
                acquireLectureRoom(lecture, availableRooms, currentCount);
                continue;
            }

            if(lecture.isEndType()) {
                releaseLectureRoom(lecture, availableRooms);
                currentCount--;
                continue;
            }

            throw new IllegalStateException("Unsupported lecture type: " + lecture.getTimeType());
        }

        return minimumCount;
    }

    private static void acquireLectureRoom(Lecture lecture, ArrayDeque<Integer> availableRooms, int currentCount) {
        if (availableRooms.isEmpty()) {
            lecture.updateRoomNo(currentCount);
            return;
        }

        lecture.updateRoomNo(availableRooms.poll());
    }

    private static void releaseLectureRoom(Lecture lecture, ArrayDeque<Integer> availableRooms) {
        availableRooms.offer(lecture.getRoomNo());
    }

    private static void printAnswer(int minimumCount, Lecture[] lectures) {
        StringBuilder sb = new StringBuilder();
        sb.append(minimumCount).append("\n");

        Arrays.sort(lectures, Comparator.comparingInt(Lecture::getNo));
        for (Lecture lecture : lectures) {
            if (lecture.isStartType()) {
                sb.append(lecture.getRoomNo()).append("\n");
            }
        }

        System.out.print(sb);
    }

    static class Lecture {

        private int no;
        private int time;
        private TimeType type;
        private LectureRoom lectureRoom;

        private Lecture(int no, int time, TimeType type, LectureRoom lectureRoom) {
            this.no = no;
            this.time = time;
            this.type = type;
            this.lectureRoom = lectureRoom;
        }

        public static Lecture create(int no, int time, TimeType type, LectureRoom lectureRoom) {
            return new Lecture(no, time, type, lectureRoom);
        }

        public void updateRoomNo(int roomNo) {
            this.lectureRoom.updateNo(roomNo);
        }

        public boolean isStartType() {
            return TimeType.START == this.type;
        }

        public boolean isEndType() {
            return TimeType.END == this.type;
        }

        public int getNo() {
            return this.no;
        }

        public int getTime() {
            return this.time;
        }

        public TimeType getTimeType() {
            return this.type;
        }

        public int getRoomNo() {
            return this.lectureRoom.getNo();
        }
    }

    static class LectureRoom {

        private static final int NOT_INITIALIZED = -1;

        private int no;

        private LectureRoom(int no) {
            this.no = no;
        }

        public static LectureRoom create() {
            return new LectureRoom(NOT_INITIALIZED);
        }

        public void updateNo(int roomNo) {
            this.no = roomNo;
        }

        public int getNo() {
            return this.no;
        }
    }

    enum TimeType {

        END, START
    }
}
