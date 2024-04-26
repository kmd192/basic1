package com.sbs.basic1.boundedContext.member.repository;

import com.sbs.basic1.boundedContext.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private List<Member> members;

    public MemberRepository(){
        members = new ArrayList<>();

        members.add(new Member("user2" , "1234"));
        members.add(new Member("user1" , "1234"));
        members.add(new Member("user3" , "1234"));
        members.add(new Member("user4" , "1234"));
        members.add(new Member("user5" , "1234"));
        members.add(new Member("user6" , "1234"));
        members.add(new Member("user7" , "1234"));
        members.add(new Member("user8" , "1234"));
        members.add(new Member("user9" , "1234"));
        members.add(new Member("user10" , "1234"));
    }

    public Member findByUserName(String username) {
        return members.stream()
                .filter(member -> member.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
