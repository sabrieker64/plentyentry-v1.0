package at.commodussolutions.plentyentry.user.coronastate.beans;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CORONA_STATUS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoronaStatus {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PCR_TEST")
    private Boolean pcrTest;

    @Column(name = "ANTIGEN_TEST")
    private Boolean antigenTest;

    @Column(name = "FIRST_VACCINE")
    private Boolean firstVaccine;

    @Column(name = "SECOND_VACCINE")
    private Boolean secondVaccine;

    @Column(name = "THIRD_VACCINE")
    private Boolean thirdVaccine;

    @Column(name = "QUARANTINE")
    private Boolean quarantine;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

}
