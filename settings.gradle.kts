rootProject.name = "spring-io-guides"

include("build-gradle-java")
include("multi-module-project:library")
include("multi-module-project:application")
include("reactive-rest-service")
include("spring-cloud-stream-rabbit:usage-details-sender")
include("spring-cloud-stream-rabbit:usage-cost-processor")
include("spring-cloud-stream-rabbit:usage-cost-logger")
