package salonservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "salon_service_detail")
public class SalonServiceDetail {

	@Id
	private Long id;
	private String description;
	private Long price;
	private Integer timeInMinutes;
	
}
