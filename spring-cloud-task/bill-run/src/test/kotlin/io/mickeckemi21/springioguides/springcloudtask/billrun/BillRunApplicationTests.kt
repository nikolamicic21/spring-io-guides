package io.mickeckemi21.springioguides.springcloudtask.billrun

import io.mickeckemi21.springioguides.springcloudtask.billrun.model.Bill
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@SpringBootTest
internal class BillRunApplicationTests {

    @Autowired
    private lateinit var dataSource: DataSource

    private lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    internal fun setUp() {
        jdbcTemplate = JdbcTemplate(dataSource)
    }

    @Test
    internal fun `test job results`() {
        val billStatements = jdbcTemplate.query(
            "SELECT id, first_name, last_name, minutes, data_usage, bill_amount " +
                    "FROM bill_statements ORDER BY id"
        ) { resultSet, _ ->
            Bill().apply {
                id = resultSet.getLong("ID")
                firstName = resultSet.getString("FIRST_NAME")
                lastName = resultSet.getString("LAST_NAME")
                dataUsage = resultSet.getLong("DATA_USAGE")
                minutes = resultSet.getLong("MINUTES")
                billAmount = resultSet.getDouble("BILL_AMOUNT")
            }
        }

        assertEquals(5, billStatements.size)

        val firstBillStatement = billStatements[0]
        assertEquals(1, firstBillStatement.id)
        assertEquals("jane", firstBillStatement.firstName)
        assertEquals("doe", firstBillStatement.lastName)
        assertEquals(500, firstBillStatement.minutes)
        assertEquals(1000, firstBillStatement.dataUsage)
        assertEquals(6.0, firstBillStatement.billAmount)
    }

}
