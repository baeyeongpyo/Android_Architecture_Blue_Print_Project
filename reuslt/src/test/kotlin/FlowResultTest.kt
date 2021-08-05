import com.yeong.reuslt.FlowResult
import com.yeong.reuslt.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.junit.Test

class FlowResultTest {

    @Test
    fun flowResultTest() = runBlocking {

        val job = CoroutineScope(Dispatchers.Default).async {
            delay(1000)
            Result.Success("test")
        }

//        runBlocking {
        FlowResult.collect({ job.await() }, {
            onSuccess<String> {
                println("success")
                println(it)
            }.onFail {
                println("fail")
                println(it.message)
            }.onDone {
                println("done")
            }.onLoading {
                println("loading")
            }
        })

        println("end")

    }
}