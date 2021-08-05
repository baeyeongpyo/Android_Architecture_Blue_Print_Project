import com.yeong.reuslt.Result
import org.junit.Assert
import org.junit.Test

class ResultExtTest {

    @Test
    fun successTest() {
        val successMessage = "test"
        val result = Result.Success(successMessage)

        result.onSuccess<String> {
            assert(true)
            Assert.assertSame(successMessage, it)
        }.onDone {
            assert(true)
        }.onFail {
            assert(false)
        }.onLoading {
            assert(false)
        }
    }

    @Test
    fun failTest() {
        val errorMessage = "testError"
        val result = Result.Fail(Exception(errorMessage))

        result.onSuccess<String> {
            assert(false)
        }.onDone {
            assert(true)
        }.onFail {
            assert(true)
            Assert.assertSame(errorMessage, it.message)
        }.onLoading {
            assert(false)
        }
    }


    @Test
    fun loadingTest() {
        val result = Result.Loading

        result.onSuccess<String> {
            assert(false)
        }.onDone {
            assert(false)
        }.onFail {
            assert(false)
        }.onLoading {
            assert(true)
        }
    }

}