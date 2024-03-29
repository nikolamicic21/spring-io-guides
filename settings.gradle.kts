rootProject.name = "spring-io-guides"

include("build-gradle-java")
include("multi-module-project:library")
include("multi-module-project:application")
include("reactive-rest-service")
include("accessing-data-with-r2dbc")
include("spring-cloud-circuit-breaker:bookstore-server")
include("spring-cloud-circuit-breaker:reading-client")
include("spring-cloud-stream-rabbit:usage-details-sender")
include("spring-cloud-stream-rabbit:usage-cost-processor")
include("spring-cloud-stream-rabbit:usage-cost-logger")
include("spring-cloud-task:bill-setup")
include("spring-cloud-task:bill-run")
include("integrating-data")
include("batch-service")
include("managing-transactions")
include("caching-data")
include("async-methods")
include("scheduling-tasks")
include("messaging-stomp-websocket")
include("testing-web-layer")
include("spring-boot-with-docker")
include("spring-boot-kubernetes")
include("spring-boot-on-kubernetes")
