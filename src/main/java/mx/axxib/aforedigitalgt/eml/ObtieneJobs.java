package mx.axxib.aforedigitalgt.eml;

import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "ObtieneJobs", 
				classes = { @ConstructorResult(targetClass = ObtieneJobs.class, 
					columns = {
						@ColumnResult(name = "JOB", type = Integer.class),
						@ColumnResult(name = "LOG_USER", type = String.class),
						@ColumnResult(name = "NEXT_DATE", type = Date.class),
						@ColumnResult(name = "WHAT", type = String.class),
						@ColumnResult(name = "TOTAL_TIME", type = Integer.class)
						
					})
				})
})

public class ObtieneJobs {
	private Integer job;
	private String logUser;
	private Date nextDate;
	private String what;
	private Integer totalTime;
}

//JOB		NUMBER	  
//LOG_USER  VARCHAR
//NEXT_DATE	DATE	
//WHAT    	VARCHAR2 (4000 Byte)         
//TOTAL_TIME	NUMBER