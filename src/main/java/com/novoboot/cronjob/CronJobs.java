package com.novoboot.cronjob;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.novoboot.service.BookingService;
import com.novoboot.service.DateTimeSlotService;
import com.novoboot.utils.ApplicationProperties;

@Component
public class CronJobs {

	@Autowired
	DateTimeSlotService dateTimeSlotService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	private static final Logger logger = Logger.getLogger(CronJobs.class);
	
//	@RequestMapping(method = RequestMethod.POST, value = "/insertDateSlot")
//	@Scheduled(cron = "0 0 23 * * *")
//	@Scheduled(fixedDelay =5000)
	public void insertDateSlot() {
	logger.info("--------------------------call task scheduler=======");
		try {
			
			getConnection();
			readCsvUsingLoad();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  Connection getConnection() throws SQLException, ClassNotFoundException {
		// Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/novo_wash", "root", "root");

		return connection;
	}
	
	private  void readCsvUsingLoad() {
		try (Connection connection = getConnection()) {
			
			String truncateTable = "truncate table service_date_slot;";
			Statement stmt = connection.createStatement();
			stmt.execute(truncateTable);
			
			logger.info("csv file location ==="+this.applicationProperties.getProperty("csv_file_location") );
			Statement stmt1 = connection.createStatement();
			String loadQuery = "LOAD DATA LOCAL INFILE '" + this.applicationProperties.getProperty("csv_file_location")
					+ "' INTO TABLE service_date_slot FIELDS TERMINATED BY ','"
					+ " LINES TERMINATED BY '\n' (id, service_master_id, next_no_of_day_available, time_slot_duration , next_disabled_dates) ";
			System.out.println(loadQuery);
			stmt1.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
