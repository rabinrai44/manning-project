package slot;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Slot {
	@Id
	private Long id;
	private LocalDateTime confirmedAt;
	private LocalDateTime lockedAt;
	private LocalDateTime slotFor;
	private Integer status;
	private String stylistName;
	private Long selectedServiceId;
}
