import javax.persistence.*;


@Entity
@Table(name = "CITIZEN")
public class Citizen {
    @Column(name = "CNAME")
    private String cname;

    @Id
    @Column(name = "PID")
    private int pid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "PID")
    private Passport passport;

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
