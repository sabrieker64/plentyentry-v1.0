package at.commodussolutions.plentyentry.user.coronastate;


import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CORONA_STATUS")
@Data
public class CoronaStatus {
    @Id
    @Column(name = "ID")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_CORONA_STATUS_USER_ID"), referencedColumnName = "ID")
    private User user;


}
