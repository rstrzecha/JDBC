package jdbc.entity;

public class Run {
    private Integer id;
    private String name;
    private Integer memberslimit;

    public Run() {
    }

    public Run(Integer id, String name, Integer memberslimit) {
        this.id = id;
        this.name = name;
        this.memberslimit = memberslimit;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMemberslimit() {
        return memberslimit;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemberslimit(Integer memberslimit) {
        this.memberslimit = memberslimit;
    }
}
