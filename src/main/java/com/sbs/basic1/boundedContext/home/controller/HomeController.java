package com.sbs.basic1.boundedContext.home.controller;

import com.sbs.basic1.boundedContext.member.entity.Member;
import com.sbs.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
public class HomeController {

    private int count;

    private List<Person> people = new ArrayList<>();
    //필드주입
    //@Autowired
    private final MemberService memberService;

    public HomeController(MemberService memberService){
        count = -1;
        people = new ArrayList<>();
        this.memberService = memberService;
    }

    @GetMapping("/home/main")
    @ResponseBody
    public String showHome(){
        return "안녕하세요.";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showHome2(){
        return "환영합니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showHome3(){
        return "스프링부트는 획기적이다.";
    }

    @GetMapping("/home/increase")
    @ResponseBody
    public int showIncrease(){
        count++;
        return count;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b){
        return a + b;
    }

    @GetMapping("/home/returnBoolean")
    @ResponseBody
    public boolean showBoolean(){
        return true;
    }

    @GetMapping("/home/returnDouble")
    @ResponseBody
    public double showDouble(){
        return Math.PI;
    }

    @GetMapping("/home/returnIntList")
    @ResponseBody
    public List<Integer> showIntList(){
        List<Integer> list = new ArrayList<>(){{
            add(10);
            add(20);
            add(30);
        }};

        return list;
    }

    @GetMapping("/home/returnMap")
    @ResponseBody
    public Map<String, Object> showReturnMap(){
        Map<String, Object> map = new LinkedHashMap<>(){{
            put("id", 1);
            put("speed", 100);
            put("name", "아반떼");
            put("relatedIds", new ArrayList<>(){{
                add(1);
                add(2);
                add(3);
            }});
        }};

        return map;
    }

    @GetMapping("/home/returnCar")
    @ResponseBody
    public Car showReturnCar(){
        Car car = new Car(1,100,"아반떼",new ArrayList<>(){{
            add(1);
            add(2);
            add(3);
        }});

        return car;
    }

    @GetMapping("/home/returnCar2")
    @ResponseBody
    public CarV2 showReturnCar2(){
        CarV2 car = new CarV2(1,100,"아반떼",new ArrayList<>(){{
            add(1);
            add(2);
            add(3);
        }});

        car.setName(car.getName() + "V2");

        return car;
    }

    @GetMapping("/home/returnCarMapList")
    @ResponseBody
    public List<Map<String, Object>> showReturnCarMapList(){

        Map<String, Object> carMap1 = new LinkedHashMap<>(){{
                put("id", 1);
                put("speed", 100);
                put("name", "아반떼");
                put("relatedIds", new ArrayList<>() {{
                    add(2);
                    add(3);
                    add(4);
                }});
            }};

        Map<String, Object> carMap2 = new LinkedHashMap<>(){{
            put("id", 2);
            put("speed", 200);
            put("name", "산타페");
            put("relatedIds", new ArrayList<>() {{
                add(5);
                add(6);
                add(7);
            }});
        }};

        List<Map<String, Object>> list = new ArrayList<>();

        list.add(carMap1);
        list.add(carMap2);

        return list;
    }

    @GetMapping("/home/returnCarList")
    @ResponseBody
    public List<CarV2> showReturnCarList(){


            CarV2 car1 = new CarV2(1, 100, "아반떼", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);
            }});


            CarV2 car2 = new CarV2(1,100,"아반떼",new ArrayList<>(){{
                add(5);
                add(6);
                add(7);
            }});

        List<CarV2> list = new ArrayList<>();

        list.add(car1);
        list.add(car2);

        return list;
    }



    class Car {
        private final int id;

        private final int speed;

        private final String name;

        private final List<Integer> relatedIds;

        public Car(int id, int speed, String name, List<Integer> relatedIds) {
            this.id = id;
            this.speed = speed;
            this.name = name;
            this.relatedIds = relatedIds;
        }

        public int getId() {
            return id;
        }

        public int getSpeed() {
            return speed;
        }

        public String getName() {
            return name;
        }

        public List<Integer> getRelatedIds() {
            return relatedIds;
        }
    }

    @AllArgsConstructor
    @Getter
    class CarV2 {
        private final int id;
        private final int speed;
        @Setter
        private String name;
        private final List<Integer> relatedIds;
    }

    @ToString
    @Getter
    class Person{
        private static int lastId;
        private final int id;
        @Setter
        private String name;
        @Setter
        private int age;

        static {
            lastId = 0;
        }

        public Person(String name, int age) {
            this(++lastId, name, age);
        }

        public Person(int id, String name, int age){
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(String name, int age){
        Person p = new Person(name, age);
        System.out.println(p);
        people.add(p);
        return "%d번 사람이 추가되었습니다.".formatted(p.getId());
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople(){
        return people;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(int id){
        boolean removed = people.removeIf(person -> person.getId() == id);

        if(removed == false){
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }

        return "%d번 사람이 삭제되었습니다.".formatted(id);
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(int id, String name, int age){
       Person found = people.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

       if(found == null){
           return "%d번 사람이 존재하지 않습니다.".formatted(id);
       }

       found.setName(name);
       found.setAge(age);

        return "%d번 사람이 수정되었습니다.".formatted(id);
    }

    @GetMapping("/home/reqAndResp")
    @ResponseBody
    public void showReqAndResp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int age = Integer.parseInt(req.getParameter("age"));
        resp.getWriter().append("Hello");
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public long showIncrease(HttpServletRequest req, HttpServletResponse resp){
        long countInCookie = 0;

        if(req.getCookies() != null) {
                countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(cookie -> cookie.getValue())
                    .mapToLong(Long::parseLong)
                    .findFirst()
                    .orElse(0);
        }

        long newCountInCookie = countInCookie + 1;

        resp.addCookie(new Cookie("count", newCountInCookie + ""));

        return newCountInCookie;
    }

    @GetMapping("/home/user1")
    @ResponseBody
    public Member showUser1(){
        return memberService.findByUserName("user1");
    }

}
