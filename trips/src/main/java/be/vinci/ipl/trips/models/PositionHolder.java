package be.vinci.ipl.trips.models;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PositionHolder {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "origin_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "origin_logitude"))
    })
    private Position origin;
    @Embedded
    @AttributeOverrides ({
            @AttributeOverride(name = "latitude", column = @Column(name = "destination_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "destination_logitude"))
    })
    private Position destination;
}
