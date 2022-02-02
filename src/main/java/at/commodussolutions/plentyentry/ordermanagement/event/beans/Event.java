package at.commodussolutions.plentyentry.ordermanagement.event.beans;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "EVENT")
@Data
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

    @Column(name = "TICKET_COUNTER")
    private Integer ticketCounter;

    @Column(name = "TICKET_ID")
    private Long ticketId;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

//TODO: Saving images on s3 bucket and checking the url if it exists

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "EVENT_IMAGE_URLS", joinColumns = @JoinColumn(name = "EVENT_ID"))
    @Column(name = "IMAGE_URLS")
    private List<String> eventImageUrls = new ArrayList<>();

    @ManyToMany(mappedBy = "entertainedEvents")
    private Set<User> entertainers;

}