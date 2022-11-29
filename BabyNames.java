package babynames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BabyNames {
	public static void main(String[] args) {

		// jbdc url for the database
		String jdbcUrl = "jdbc:derby:C:/Users/yash santhalia/Desktop/Avi/babynames-derby/babynames";
		try {
			// establishing connection to the database
			Connection connection = DriverManager.getConnection(jdbcUrl, "user", "password");

			// creating statement
			Statement statement = connection.createStatement();

			ResultSet resultSet;

			// Executing various queries
			String sql_1 = "select name from babynames where num_babies = (select MAX(num_babies) from babynames where us_state='MD' and date_year=1991 and gender='M') and us_state='MD' and date_year=1991 and gender='M'";

			resultSet = statement.executeQuery(sql_1);
			System.out.print("Most Common boys name in Maryland in 1991: ");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}

			String sql_2 = "select date_year from babynames where num_babies = (select MAX(num_babies) from babynames where name='Christopher' and gender='M') and name='Christopher' and gender='M'";
			resultSet = statement.executeQuery(sql_2);
			System.out.print("Most baby boys named Cristopher born in any state in the year: ");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}

			String sql_3 = "select date_year from babynames where num_babies = (select MAX(num_babies) from babynames where name='Rosemary' and gender='F') and name='Rosemary' and gender='F'";
			resultSet = statement.executeQuery(sql_3);
			System.out.print("Most baby girls named Rosemary born in any state in the year: ");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}

			String sql_4 = "select name from babynames where date_year=2000 and us_state='MD' and num_babies > 500";
			resultSet = statement.executeQuery(sql_4);
			System.out.print("Baby names used more than 500 times in Maryland in 2000: ");
			while (resultSet.next()) {
				System.out.print(resultSet.getString(1) + ", ");
			}
			System.out.println();

			String sql_5 = "select us_state from babynames where num_babies = (select MIN(num_babies) from babynames where gender='M' and name='Xavier' and date_year=2014) and gender='M' and name = 'Xavier' and date_year = 2014";
			resultSet = statement.executeQuery(sql_5);
			System.out.print("State having fewest boys named Xavier in 2014: ");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}

			String sql_6 = "select us_state from babynames where num_babies = (select MAX(num_babies) from babynames where gender='F' and name='Hannah' and date_year=1997) and gender='F' and name = 'Hannah' and date_year = 1997";
			resultSet = statement.executeQuery(sql_6);
			System.out.print("State having most girls named Hannah in 1997: ");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}

			String sql_7 = "insert into babynames values (10000000, 'Joseph', 2016, 'M', 'PA', 476)";
			int count = statement.executeUpdate(sql_7);
			System.out.println(count + "rows inserted");

			String sql_8 = "delete from babynames where id = 10000000";
			count = statement.executeUpdate(sql_8);
			System.out.println(count + "rows deleted");

			String sql_9 = "select name from babynames where gender='M' and date_year=1991 group by name having SUM(num_babies) = (select MAX(x.y) from (select SUM(num_babies) as y from babynames where gender='M' and date_year=1991 group by name)x)";
			resultSet = statement.executeQuery(sql_9);
			System.out.print("Most common boys name across US in 1991: ");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}