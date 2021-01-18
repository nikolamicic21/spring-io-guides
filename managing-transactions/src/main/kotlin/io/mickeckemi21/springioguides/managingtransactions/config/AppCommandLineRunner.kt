package io.mickeckemi21.springioguides.managingtransactions.config

import io.mickeckemi21.springioguides.managingtransactions.service.BookingService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.util.Assert
import java.lang.RuntimeException

@Component
class AppCommandLineRunner(private val bookingService: BookingService)
    : CommandLineRunner {

    companion object {
        private val log = LoggerFactory.getLogger(AppCommandLineRunner::class.java)
    }

    override fun run(vararg args: String) {
        bookingService.book("Alice", "Bob", "Carol")
        Assert.isTrue(
            bookingService.findAllBookings().size == 3,
            "First booking should work with no problem"
        )
        log.info("Alice, Bob and Carol have been booked")

        try {
            bookingService.book("Chris", "Samuel")
        } catch (e: RuntimeException) {
            log.info("v--- The following exception is expect because 'Samuel' is too " +
                "big for the DB ---v")
            log.error(e.message)
        }
        bookingService.findAllBookings().forEach {
            log.info("So far, $it is booked")
        }
        log.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, " +
                "and Chris was rolled back in the same TX")
        Assert.isTrue(bookingService.findAllBookings().size == 3,
            "'Samuel' should have triggered a rollback")

        try {
            bookingService.book("Buddy", null)
        } catch (e: RuntimeException) {
            log.info("v--- The following exception is expect because null is not " +
                    "valid for the DB ---v")
            log.error(e.message)
        }
        bookingService.findAllBookings().forEach {
            log.info("So far, $it is booked")
        }
        log.info("You shouldn't see Buddy or null. null violated DB constraints, and " +
                "Buddy was rolled back in the same TX")
        Assert.isTrue(bookingService.findAllBookings().size == 3,
            "'null' should have triggered a rollback")
    }
}