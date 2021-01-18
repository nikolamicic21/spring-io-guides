package io.mickeckemi21.springioguides.managingtransactions.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookingService(private val jdbcTemplate: JdbcTemplate) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(BookingService::class.java)
    }

    @Transactional
    fun book(vararg persons: String?) = persons
        .forEach {
            log.info("Booking $it in a seat...")
            jdbcTemplate.update(
                "INSERT INTO BOOKINGS(FIRST_NAME) VALUES (?)",
                it
            )
        }

    fun findAllBookings(): List<String> =
        jdbcTemplate.query(
            "SELECT FIRST_NAME FROM BOOKINGS"
        ) { rs, _ -> rs.getString("FIRST_NAME") }

}