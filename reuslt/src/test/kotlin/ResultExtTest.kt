import com.yeong.reuslt.Result
import org.junit.Assert
import org.junit.Test
import java.lang.NullPointerException

class ResultExtTest {

    @Test
    fun successTest() {
        val successMessage = "test"
        val result = Result.Success(successMessage)

        var successPass = false
        var failPass = false
        var loadingPass = false
        var donePass = false

        result.onSuccess<String> {
            successPass = true
            Assert.assertSame(successMessage, it)
        }.onDone {
            donePass = true
        }.onFail {
            failPass = true
        }.onLoading {
            loadingPass = true
        }

        Assert.assertTrue(successPass)
        Assert.assertFalse(failPass)
        Assert.assertFalse(loadingPass)
        Assert.assertFalse(donePass)

    }

    @Test
    fun successWithNullDataExceptionTest() {
        val successMessage = null
        val result = Result.Success(successMessage)

        var successPass = false
        var failPass = false
        var loadingPass = false
        var donePass = false

        Assert.assertThrows(NullPointerException::class.java) {
            result.onSuccess<String> {
                successPass = true
                Assert.assertSame(successMessage, it)
            }.onDone {
                donePass = true
            }.onFail {
                failPass = true
            }.onLoading {
                loadingPass = true
            }
        }

        Assert.assertFalse(successPass)
        Assert.assertFalse(failPass)
        Assert.assertFalse(loadingPass)
        Assert.assertFalse(donePass)

    }

    @Test
    fun successWithNullDataTest() {
        val successMessage = null
        val result = Result.Success(successMessage)

        var successPass = false
        var failPass = false
        var loadingPass = false
        var donePass = false

        result.onSuccess<String?> {
            successPass = true
            Assert.assertSame(successMessage, it)
        }.onDone {
            donePass = true
        }.onFail {
            failPass = true
        }.onLoading {
            loadingPass = true
        }

        Assert.assertTrue(successPass)
        Assert.assertFalse(failPass)
        Assert.assertFalse(loadingPass)
        Assert.assertFalse(donePass)

    }

    @Test
    fun failTest() {
        val errorMessage = "testError"
        val result = Result.Fail(Exception(errorMessage))

        var successPass = false
        var failPass = false
        var loadingPass = false
        var donePass = false

        result.onSuccess<String> {
            successPass = true
        }.onDone {
            donePass = true
        }.onFail {
            failPass = true
            Assert.assertSame(errorMessage, it.message)
        }.onLoading {
            loadingPass = true
        }

        Assert.assertFalse(successPass)
        Assert.assertTrue(failPass)
        Assert.assertFalse(loadingPass)
        Assert.assertFalse(donePass)

    }

    @Test
    fun loadingTest() {
        val result = Result.Loading

        var successPass = false
        var failPass = false
        var loadingPass = false
        var donePass = false

        result.onSuccess<String> {
            successPass = true
        }.onDone {
            donePass = true
        }.onFail {
            failPass = true
        }.onLoading {
            loadingPass = true
        }

        Assert.assertFalse(successPass)
        Assert.assertFalse(failPass)
        Assert.assertTrue(loadingPass)
        Assert.assertFalse(donePass)

    }

    @Test
    fun doneTest() {
        val result = Result.Done

        var successPass = false
        var failPass = false
        var loadingPass = false
        var donePass = false

        result.onSuccess<String> {
            successPass = true
        }.onDone {
            donePass = true
        }.onFail {
            failPass = true
        }.onLoading {
            loadingPass = true
        }

        Assert.assertFalse(successPass)
        Assert.assertFalse(failPass)
        Assert.assertFalse(loadingPass)
        Assert.assertTrue(donePass)

    }

}