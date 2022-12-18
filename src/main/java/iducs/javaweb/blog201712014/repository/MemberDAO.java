package iducs.javaweb.blog201712014.repository;

import iducs.javaweb.blog201712014.model.Member;

import java.util.List; // 컬렉션 자료구조 List를 사용하기 위함

public interface MemberDAO { // 멤버 데이터에 접근하기 위한 인터페이스

    int create(Member m);
    Member read(Member m);
    List<Member> readList();
    int update(Member m);
    int delete(Member m);
    Member login(Member m);

    List<Member> sortList(String sort);
}
