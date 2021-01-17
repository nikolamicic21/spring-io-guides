package io.mickeckemi21.springioguides.springcloudtask.billsetup

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@SpringBootTest
internal class BillSetupTaskApplicationTest {

    @Autowired
    private lateinit var dataSource: DataSource

    @Test
    internal fun `test repository`() {
        val result: Int? = JdbcTemplate(dataSource).let {
            it.queryForObject("SELECT COUNT(*) FROM BILL_STATEMENTS", Int::class.java)
        }

        assertEquals(0, result!!)
    }

}
