package com.addi.question.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
public enum Questions {
    Question1("평소에 연세에 비해 건강한 편이라고 생각하세요?"),

    Question2("취업 등의 새로운 경제적 기회가 생긴다면 참여의사가 있으세요?"),

    Question3("평소에 건강검진을 주변사람보다 많이 받는 편이신가요?"),

    Question4("평소에 사소한 증상이라도 보이면 병원에 가세요?"),

    Question5("평소에 감기 몸살에 자주 걸리시는 편이세요?"),

    Question6("계절병에 자주 걸리시는 편인가요?"),

    Question7("원래 가지고 있는 질병 때문에 병원에 정기적으로 치료받으러 가시나요?"),

    Question8("목욕이나 등산, 운동을 할 때에 불편감을 느낄 때가 있으세요?"),

    Question9("최근들어 몸이 무겁고 기운이 없을 때가 있으세요?"),

    Question10("건강 때문에 사회생활을 제한 받으신 적이 있으신가요?"),

    Question11("현재 금전적 수입이 있으신가요?"),

    Question12("자녀나 손녀분께 금전적 도움을 받고 계신가요?"),

    Question13("평소에 재정적인 문제가 있으시진 않으신가요?"),

    Question14("현재 금전적인 상황은 괜찮으신가요?"),

    Question15("자녀분들과 관계는 화목하신가요?"),

    Question16("며느리, 사위분들과의 관계는 화목하신가요?"),

    Question17("손자, 손녀분들과의 관계는 화목하신가요?"),

    Question18("가족분들과 사소하게라도 다투신 적이 있으세요?"),

    Question19("하루 일과는 어떻게 되세요?"),

    Question20("매일 자주 하는 활동은 무엇인가요?"),

    Question21("가장 좋아하는 취미는 무엇인가요?"),

    Question22("가장 좋아하는 음식은 무엇인가요?"),

    Question23("평소에 집에서 주로 계시는 공간이 어디세요?"),

    Question24("병원 방문이 필요할 때 어떻게 이동하세요?"),

    Question25("현재 건강상태는 어떠세요?"),

    Question26("정기적으로 복용하고 있는 약이 있으세요?"),

    Question27("평소에 운동을 자주 하세요?"),

    Question28("요새 가장 필요하다고 생각하는 물건 있으세요?"),

    Question29("평소에 만나시는 지인분 있으세요?"),

    Question30("가장 기억에 남는 추억을 말씀해주세요"),

    Question31("가장 자랑스럽게 생각하는 것을 말씀해주세요"),

    Question32("가장 좋아하는 동물 있으세요?"),

    Question33("오늘 기분은 어떠세요? 괜찮으신가요?"),

    Question34("자주 보시는 TV 프로그램 있으세요?"),

    Question35("오늘 아침에 뭐 드셨어요?"),

    Question36("혼자 계실 때 안 심심하세요?"),

    Question37("손주 오시면 어떤 음식해드리고 싶어요?"),

    Question38("가장 좋아하는 시간대는 언제에요?"),

    Question39("가장 좋아하는 계절 있으세요?"),

    Question40("가장 좋아하는 동물은 무엇인가요?"),

    Question41("평소에 어떤 옷을 즐겨 입으세요?"),

    Question42("가장 좋아하는 가수는 누구에요?"),

    Question43("가장 즐거우셨을 때 기억나시는거 있으세요?"),

    Question44("가장 좋아하는 꽃 있으세요?"),

    Question45("가장 기억에 남는 책이 있으세요? 책을 추천해주세요!"),

    Question46("평소에 하시는 운동이 있으세요?"),

    Question47("평소에 자주 바깥으로 놀러 가세요?"),

    Question48("요새 즐거우신 일이 많으신가요?"),

    Question49("요새 받고계시는 복지 서비스가 있으신가요?"),

    Question50("요새 소외감을 느끼실 때가 있으신가요?"),

    Question51("요새 걱정하시는게 있으신가요?"),

    Question52("친구분들과 싸우는 일이 종종 있으신가요?"),

    Question53("평소에 화를 잘 내시는 편인가요?"),

    Question54("가장 좋아하는 꽃 있으세요?"),

    Question55("평소에 전자기기 자주 사용하세요?"),

    Question56("어떤 뉴스를 가장 관심있게 보시나요?"),

    Question57("주변에서 도움을 받고 싶은 것이 있으세요?"),

    Question58("건강상의 문제로 힘들께 느끼시는 부분이 있으세요?"),

    Question59("삶에 대해 가장 중요하게 생각하는 가치가 있으세요?"),

    Question60("어떤 상황이 가장 슬프셨나요?"),

    Question61("평소에 자주가는 곳이 있으세요?");


    private final String question;
    Questions(String question) {
        this.question = question;
    }

    private static final Random random = new Random();

    public static List<String> getRandomQuestions(int count) {
        if (count <= 0 || count > values().length) {
            throw new IllegalArgumentException("Invalid count for random questions");
        }

        List<Questions> allQuestions = Arrays.asList(values());
        Collections.shuffle(allQuestions, random);

        return allQuestions.subList(0, count).stream()
                .map(Questions::getQuestion)
                .collect(Collectors.toList());
    }

    public String getQuestion() {
        return this.question;
    }

}