package makonahi.ToDoList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ToDoListApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
	}


	//Tests initial database connection. If successful, lists first 3 entries from "tasks" table from DB.
	@Test
	void DatabaseConnectionTest(){
		String sqlTestQuery = "SELECT * FROM tasks LIMIT 3";
		List<Map<String, Object>> testRows = jdbcTemplate.queryForList(sqlTestQuery);

		for (Map<String, Object> row : testRows){
			System.out.println(row);
		}

	}
}
