package iducs.javaweb.blog201712014.model;

import lombok .*;   // 의존성 문제 해결

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Blog { // Blog DTO

    private long id;    // id
    private String title;   // 제목
    private String content; // 내용
    private String author;  // 작성자
    private String email;   // 이메일

    private String filepath;    // 파일 경로

    private Timestamp regdatetime;  // 등록일

}
