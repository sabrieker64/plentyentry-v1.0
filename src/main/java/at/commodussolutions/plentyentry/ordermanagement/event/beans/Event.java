package at.commodussolutions.plentyentry.ordermanagement.event.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "EVENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @Column(name = "ID")
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
}