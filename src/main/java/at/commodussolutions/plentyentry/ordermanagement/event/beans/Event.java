package at.commodussolutions.plentyentry.ordermanagement.event.beans;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "EVENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Double price;

    //todo die anzahl von gewünscten freistellung von tickets wird hier eingetragen dann nach jeder kaufbestäting
    // wird heruntergezählt somit können wir die verfügbaren tickets aktuell halten
    @Column(name = "TICKET_COUNTER")
    private Long ticketCounter;

    @Column(name = "TICKET_ID")
    private Long ticketId;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "EVENT_IMAGE_URLS", joinColumns = @JoinColumn(name = "EVENT_ID"))
    @Column(name = "IMAGE_URLS")
    private List<String> eventImageUrls;

    @ManyToMany(mappedBy = "entertainedEvents")
    private Set<User> entertainers;

}