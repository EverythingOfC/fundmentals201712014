package iducs.javaweb.blog201712014.model;

public class MemberDTO { // 데이터 전송 객체 ( VO(특정한 값을 담는 객체)와 DTO(값을 전송하기 위한 getter,setter객체) )
    private String fullname;
    private String email;
    private String tel;
    private String message;
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
