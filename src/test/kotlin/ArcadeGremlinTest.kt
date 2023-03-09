import org.apache.tinkerpop.gremlin.driver.Cluster
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import kotlin.test.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.output.OutputFrame
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.DockerImageName

class ArcadeGremlinTest {
    var logger: Logger = LoggerFactory.getLogger(this::class.java)
    val container by lazy {
        GenericContainer(DockerImageName.parse("arcadedata/arcadedb:latest")).apply {
            withExposedPorts(2480, 2424, 8182)
            withEnv("JAVA_OPTS","""
       -Darcadedb.server.rootPassword=root_root
       -Darcadedb.server.plugins.GremlinServer=com.arcadedb.server.gremlin.GremlinServerPlugin""")
            waitingFor(Wait.forLogMessage(".*ArcadeDB Server started.*\\n", 1))
            start()
            println("ARCADE: http://${host}:${getMappedPort(2480)}")
            println("ARCADE: http://${host}:${getMappedPort(2424)}")
            println(this.logs)
            println("RUNNING?: ${this.isRunning}")
        }
    }
    init {
        val logConsumer = Slf4jLogConsumer(logger)
        container.followOutput(logConsumer, OutputFrame.OutputType.STDOUT)
    }
    private val cluster by lazy {
        Cluster.build().apply {
            addContactPoints(*container.host.split(Regex("\\s*,\\s*")).toTypedArray())
            port(container.getMappedPort(8182))
            enableSsl(false)
        }.create()
    }

    @Test
    fun testing(){

    }
    fun createGremlin(): GraphTraversalSource {
        return  traversal().withRemote(DriverRemoteConnection.using(cluster))
    }
}